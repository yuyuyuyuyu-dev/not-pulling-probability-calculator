# NotPullingProbabilityCalculator

[![Deploy](https://github.com/yuyuyuyuyu-dev/not-pulling-probability-calculator/actions/workflows/deploy.yml/badge.svg)](https://github.com/yuyuyuyuyu-dev/not-pulling-probability-calculator/actions/workflows/deploy.yml)
[![CI](https://github.com/yuyuyuyuyu-dev/not-pulling-probability-calculator/actions/workflows/ci.yml/badge.svg)](https://github.com/yuyuyuyuyu-dev/not-pulling-probability-calculator/actions/workflows/ci.yml)

## Screenshot

![non-drawing-probability-calculator vercel app_](https://github.com/user-attachments/assets/5a7da451-c650-4e5e-bf18-78e1bfa1dbe4)

## Tests

Specification tests live in `composeApp/src/specTest`. They state what the app must do, and drive the whole app with the Compose UI test framework. All other tests live in `composeApp/src/commonTest`.

Both run in a headless browser with:

```sh
./gradlew wasmJsTest
```
