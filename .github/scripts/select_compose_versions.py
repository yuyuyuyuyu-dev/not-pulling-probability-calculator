import json
import os
import re
import sys
import urllib.request

MAVEN = "https://repo1.maven.org/maven2"
CATALOG = "gradle/libs.versions.toml"
COMPOSE_GROUP = "org.jetbrains.compose"
MATERIAL3_GROUP = "org.jetbrains.compose.material3"
COMPOSE_KEY = "composeMultiplatform"
MATERIAL3_KEY = "material3"
QUALIFIER_RANKS = {"alpha": 0, "beta": 1, "rc": 2}


def parse(version):
    match = re.fullmatch(r"(\d+)\.(\d+)\.(\d+)(?:-(alpha|beta|rc)(\d+))?", version)
    if match is None:
        return None
    major, minor, patch, qualifier, number = match.groups()
    rank = (QUALIFIER_RANKS[qualifier], int(number)) if qualifier else (len(QUALIFIER_RANKS), 0)
    return (int(major), int(minor), int(patch), *rank)


def line_of(version):
    return parse(version)[:2]


def fetch(url):
    with urllib.request.urlopen(url, timeout=60) as response:
        return response.read().decode()


def published_versions(group, artifact):
    metadata = fetch(f"{MAVEN}/{group.replace('.', '/')}/{artifact}/maven-metadata.xml")
    return [version for version in re.findall(r"<version>([^<]+)</version>", metadata) if parse(version)]


def latest_stable_compose():
    stable = [
        version
        for version in published_versions("org.jetbrains.compose", "compose-gradle-plugin")
        if re.fullmatch(r"\d+\.\d+\.\d+", version)
    ]
    return max(stable, key=parse)


def is_compose_group(group):
    return group == COMPOSE_GROUP or group.startswith(f"{COMPOSE_GROUP}.")


def required_compose(material3):
    module = json.loads(
        fetch(f"{MAVEN}/org/jetbrains/compose/material3/material3/{material3}/material3-{material3}.module")
    )
    required = []
    for variant in module["variants"]:
        for dependency in variant.get("dependencies", []):
            group = dependency["group"]
            if not is_compose_group(group) or group == MATERIAL3_GROUP or group.startswith(f"{MATERIAL3_GROUP}."):
                continue
            constraint = dependency.get("version", {})
            version = constraint.get("strictly") or constraint.get("requires") or constraint.get("prefers")
            if version is None or parse(version) is None:
                sys.exit(f"material3 {material3} declares {dependency['group']}:{dependency['module']} as {constraint}")
            required.append(version)
    if not required:
        sys.exit(f"material3 {material3} declares no Compose Multiplatform dependency")
    return max(required, key=parse)


def paired_material3(compose):
    candidates = sorted(
        (
            version
            for version in published_versions("org.jetbrains.compose.material3", "material3")
            if line_of(version) == line_of(compose)
        ),
        key=parse,
        reverse=True,
    )
    for candidate in candidates:
        if parse(required_compose(candidate)) <= parse(compose):
            return candidate
    sys.exit(f"No material3 in the {'.'.join(map(str, line_of(compose)))} line works with Compose Multiplatform {compose}")


def catalog_problems(catalog):
    problems = []
    for line in catalog.splitlines():
        entry = re.fullmatch(r"\s*[\w.-]+\s*=\s*\{(.*)\}\s*", line)
        if entry is None:
            continue
        fields = dict(re.findall(r'([\w.]+)\s*=\s*"([^"]*)"', entry.group(1)))
        coordinate = fields.get("module") or fields.get("id")
        if coordinate is None:
            continue
        group = coordinate.split(":")[0]
        ref = fields.get("version.ref")
        if is_compose_group(group):
            expected = MATERIAL3_KEY if group == MATERIAL3_GROUP else COMPOSE_KEY
            if ref != expected:
                problems.append(f"{coordinate} must use version.ref {expected} so that this workflow owns it")
        elif ref in (COMPOSE_KEY, MATERIAL3_KEY):
            problems.append(f"{coordinate} must not use version.ref {ref}, which this workflow owns")
    return problems


def catalog_version(catalog, key):
    match = re.search(rf'^{re.escape(key)} = "([^"]+)"$', catalog, re.MULTILINE)
    if match is None or parse(match.group(1)) is None:
        sys.exit(f"{CATALOG} has no usable {key} version")
    return match.group(1)


def with_version(catalog, key, version):
    return re.sub(rf'^{re.escape(key)} = "[^"]+"$', f'{key} = "{version}"', catalog, flags=re.MULTILINE)


def write_outputs(outputs):
    lines = [f"{name}={value}" for name, value in outputs.items()]
    path = os.environ.get("GITHUB_OUTPUT")
    if path:
        with open(path, "a") as file:
            file.write("\n".join(lines) + "\n")
    print("\n".join(lines))


def main():
    with open(CATALOG) as file:
        catalog = file.read()
    problems = catalog_problems(catalog)
    if problems:
        sys.exit("\n".join(problems))
    current_compose = catalog_version(catalog, COMPOSE_KEY)
    current_material3 = catalog_version(catalog, MATERIAL3_KEY)
    compose = max(current_compose, latest_stable_compose(), key=parse)
    material3 = paired_material3(compose)
    changed = (compose, material3) != (current_compose, current_material3)
    if changed:
        catalog = with_version(catalog, COMPOSE_KEY, compose)
        catalog = with_version(catalog, MATERIAL3_KEY, material3)
        with open(CATALOG, "w") as file:
            file.write(catalog)
    write_outputs({"changed": str(changed).lower(), "compose": compose, "material3": material3})


if __name__ == "__main__":
    main()
