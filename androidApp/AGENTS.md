# androidApp CLI Instructions

## Audience

- This file is for non-Claude CLIs.
- Claude CLI should use `androidApp/CLAUDE.md`.

## Scope & Priority

- Applies only to `androidApp/**`.
- Priority: **User instruction > this file > repository root AGENTS.md/CLAUDE.md**.
- Same-level conflict rule: **narrower path scope wins; if still tied, stricter rule wins**.

## Must-Follow Rules

- MUST keep Android entrypoint thin (`androidApp/src/main/kotlin/com/scaffold/kmp/MainActivity.kt`): host/lifecycle wiring only.
- MUST not re-implement page UI assembly in `androidApp`; render `ScaffoldWrappedRootScreen(...)`.
- MUST use `Greeting().greet()` from `shared` for displayed greeting; avoid Android-only business logic duplication.
- MUST use `AppLogger` for runtime logs; keep messages in English with unified format.
- SHOULD isolate Android-only APIs (permissions/intents/lifecycle) in `androidApp`, exposing shared abstractions when cross-platform behavior is required.

## Change Boundaries

- Prefer changes inside `androidApp/**`; touch `shared` or `ui-components` only when task requires it.

## Validation Expectations

- Minimum: `./gradlew :androidApp:compileDebugKotlin`
- Additional checks are required when changing entrypoints/startup flow/UI resources/platform API wiring:
  - `./gradlew :androidApp:assembleDebug`
  - `./gradlew :androidApp:lintDebug`
