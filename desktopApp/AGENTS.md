# desktopApp CLI Instructions

## Audience

- This file is for non-Claude CLIs.
- Claude CLI should use `desktopApp/CLAUDE.md`.

## Scope & Priority

- Applies only to `desktopApp/**`.
- Priority: **User instruction > this file > repository root AGENTS.md/CLAUDE.md**.
- Same-level conflict rule: **narrower path scope wins; if still tied, stricter rule wins**.

## Must-Follow Rules

- MUST keep desktop entrypoint thin (`desktopApp/src/main/kotlin/com/scaffold/kmp/main.kt`): host/window bootstrap wiring only.
- MUST not re-implement page UI assembly in `desktopApp`; render `ScaffoldWrappedRootScreen(...)`.
- MUST use `Greeting().greet()` from `shared` for greeting input; avoid desktop-only business logic duplication.
- MUST preserve cross-platform logging conventions via `AppLogger` when adding startup/runtime logs.
- SHOULD isolate desktop-only APIs (window/menu/filesystem) in `desktopApp`.
- SHOULD avoid blocking Compose UI thread with long-running or blocking operations.

## Change Boundaries

- Prefer changes inside `desktopApp/**`; touch `shared` or `ui-components` only when required by the task.

## Validation Expectations

- Minimum: `./gradlew :desktopApp:compileKotlin`
- Additional checks are required when changing entrypoints/startup flow:
  - `./gradlew :desktopApp:run`
