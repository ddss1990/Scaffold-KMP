# webApp CLI Instructions

## Audience

- This file is for non-Claude CLIs.
- Claude CLI should use `webApp/CLAUDE.md`.

## Scope & Priority

- Applies only to `webApp/**`.
- Priority: **User instruction > this file > repository root AGENTS.md/CLAUDE.md**.
- Same-level conflict rule: **narrower path scope wins; if still tied, stricter rule wins**.

## Must-Follow Rules

- MUST keep web entrypoint thin (`webApp/src/webMain/kotlin/com/scaffold/kmp/main.kt`): viewport/bootstrap only.
- MUST not move page assembly back into `webApp`; render `ScaffoldWrappedRootScreen(...)` from `:ui-components`.
- MUST not block full-page rendering forever on async font/resource preload.
- MUST keep a fallback render path with minimum visible content.
- SHOULD keep web-only behavior in `webApp` unless a clean `expect/actual` abstraction is required.
- SHOULD prefer ASCII UI text in web-only edits unless font coverage is explicitly guaranteed.

## Change Boundaries

- Prefer changes inside `webApp/**`; touch `shared` or `ui-components` only when needed to fix root cause.

## Validation Expectations

- Minimum: `./gradlew :webApp:compileKotlinWasmJs`
- Additional checks are required when changing entrypoints/resource loading:
  - `./gradlew :webApp:wasmJsBrowserDevelopmentRun`
