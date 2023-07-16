type OSS = {
  name: string;
  website?: string;
  github?: string;
  licenseUrl: string;
};

export const openSources: OSS[] = [
  // dependencies
  {
    name: "Emotion",
    website: "https://emotion.sh",
    github: "https://github.com/emotion-js/emotion",
    licenseUrl:
      "https://raw.githubusercontent.com/emotion-js/emotion/main/LICENSE",
  },
  {
    name: "MUI Core",
    website: "https://mui.com/core/",
    github: "https://github.com/mui/material-ui",
    licenseUrl: "https://raw.githubusercontent.com/mui/material-ui/master/LICENSE",
  },
  {
    name: "Definitely Typed",
    github: "https://github.com/DefinitelyTyped/DefinitelyTyped",
    licenseUrl:
      "https://raw.githubusercontent.com/DefinitelyTyped/DefinitelyTyped/master/LICENSE",
  },
  {
    name: "Axios",
    website: "https://axios-http.com",
    github: "https://github.com/axios/axios",
    licenseUrl: "https://raw.githubusercontent.com/axios/axios/v1.x/LICENSE",
  },
  {
    name: "ESLint",
    website: "https://eslint.org",
    github: "https://github.com/eslint/eslint",
    licenseUrl: "https://raw.githubusercontent.com/eslint/eslint/main/LICENSE",
  },
  {
    name: "Next.js",
    website: "https://nextjs.org",
    github: "https://github.com/vercel/next.js",
    licenseUrl:
      "https://raw.githubusercontent.com/vercel/next.js/canary/license.md",
  },
  {
    name: "React",
    website: "https://react.dev",
    github: "https://github.com/facebook/react",
    licenseUrl: "https://raw.githubusercontent.com/facebook/react/main/LICENSE",
  },
  {
    name: "TypeScript",
    website: "https://www.typescriptlang.org",
    github: "https://github.com/microsoft/TypeScript",
    licenseUrl:
      "https://raw.githubusercontent.com/microsoft/TypeScript/main/LICENSE.txt",
  },
  // devDependencies
  {
    name: "Jest",
    website: "https://jestjs.io",
    github: "https://github.com/jestjs/jest",
    licenseUrl: "https://raw.githubusercontent.com/jestjs/jest/main/LICENSE",
  },
  {
    name: "ts-jest",
    website: "https://kulshekhar.github.io/ts-jest/",
    github: "https://github.com/kulshekhar/ts-jest",
    licenseUrl:
      "https://raw.githubusercontent.com/kulshekhar/ts-jest/main/LICENSE.md",
  },
];
