import { defineConfig, devices } from "@playwright/test";

export default defineConfig({
  testDir: "specs",
  forbidOnly: !!process.env.CI,
  reporter: process.env.CI ? "github" : "list",
  use: {
    baseURL: "http://localhost:8080",
    ...devices["Desktop Chrome"],
  },
  webServer: {
    command:
      "./gradlew :composeApp:wasmJsBrowserDistribution && http-server composeApp/build/dist/wasmJs/productionExecutable -p 8080 -c-1 -s",
    url: "http://localhost:8080",
    reuseExistingServer: !process.env.CI,
    timeout: 600_000,
  },
});
