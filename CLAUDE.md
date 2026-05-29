# KMPScaffold Agent Instructions

## Audience

- This file is for Claude CLI only.
- Non-Claude CLIs should use `AGENTS.md`.

## Scope and priority

- This file applies to the **root project only** (`KMPScaffold`).
- **Do not import or apply sub-module instruction files** from `compose-ui-components/**` in root-level work.
- If there is a direct user instruction, follow user instruction first.
- For `androidApp/**`, `desktopApp/**`, and `webApp/**`, module-level `AGENTS.md` / `CLAUDE.md` override this root file.
- Effective order for those modules: **User instruction > module-level file > root file**.
- Same-level conflict rule: **narrower path scope wins; if still tied, stricter rule wins**.

## Build, test, and lint commands

Run commands from repository root.

### Run / build

- Android debug package: `./gradlew :androidApp:assembleDebug`
- Android Kotlin compile: `./gradlew :androidApp:compileDebugKotlin`
- Desktop run: `./gradlew :desktopApp:run`
- Desktop Kotlin compile: `./gradlew :desktopApp:compileKotlin`
- Web Wasm dev server: `./gradlew :webApp:wasmJsBrowserDevelopmentRun`
- Web Wasm compile: `./gradlew :webApp:compileKotlinWasmJs`
- Shared metadata compile: `./gradlew :shared:compileKotlinMetadata`

### Tests

- Shared JVM tests: `./gradlew :shared:jvmTest`
- Shared Android host tests: `./gradlew :shared:testAndroidHostTest`
- Shared JS tests: `./gradlew :shared:jsTest`
- Shared Wasm tests: `./gradlew :shared:wasmJsTest`

Single test examples:

- `./gradlew :shared:jvmTest --tests com.scaffold.kmp.SharedLogicDesktopTest`
- `./gradlew :shared:jvmTest --tests com.scaffold.kmp.AppObservabilityTest`

### Lint

- Android lint: `./gradlew :androidApp:lint`
- Android debug lint: `./gradlew :androidApp:lintDebug`

## Architecture snapshot (root project)

1. `androidApp`, `desktopApp`, `webApp` are platform host entry modules.
2. `shared` holds cross-platform logic (`Greeting`, logging abstractions, platform `expect/actual`).
3. Host entrypoints render `ScaffoldWrappedRootScreen(...)` from `:ui-components`.
4. `settings.gradle.kts` maps `:ui-components` and `:component-registry` as top-level projects via `projectDir`.

## Project conventions

### Dependency wiring

- Host modules should depend on `project(":ui-components")` for UI rendering.
- Keep existing project mapping in `settings.gradle.kts` stable unless explicitly requested.

### Logging

- Use `AppLogger` from `shared` for cross-platform logging.
- Keep log messages in English with unified format:
  - `[KMP][LEVEL][Tag][Event] message`

### Repository mirrors

- Preserve current Maven repository mirror setup in `settings.gradle.kts` unless asked to change it.

## Change boundaries

- Make focused edits for requested scope only.
- Do not introduce unrelated refactors.
- Do not auto-commit unless explicitly requested.

## Validation expectations

Minimum validation for root-level or shared-impact changes:

1. `./gradlew :shared:compileKotlinMetadata`
2. `./gradlew :androidApp:compileDebugKotlin`
3. `./gradlew :desktopApp:compileKotlin`
4. `./gradlew :webApp:compileKotlinWasmJs`

Additional trigger-based validation:

- If changing entrypoints/startup flow/resource preload/platform API wiring, run module-specific additional checks defined in that module's `AGENTS.md` / `CLAUDE.md`.
