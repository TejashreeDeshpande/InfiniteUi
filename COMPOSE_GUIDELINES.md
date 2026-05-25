# Jetpack Compose Coding Standards & Guidelines

> Best practices for developing Compose-first applications in the **InfiniteUI** project.

---

## 1. Naming Conventions

### Composable functions
Must use **PascalCase**.

```kotlin
// ✓ Correct
fun UserProfile() { ... }

// ✗ Incorrect
fun userProfile() { ... }
```

### Unit-returning composables
Named as nouns.

- `MessageList`
- `SubmitButton`

### Value-returning functions
Follow standard Kotlin **camelCase**.

- `rememberScaffoldState()`

### CompositionLocal keys
Prefix with `Local` to distinguish them from regular values at the call site.

- `LocalContentColor`
- `LocalTypography`

### Preview functions
Suffix with `Preview` and keep visibility `private` — they are not consumed by real UI.

```kotlin
// ✓ Correct
@Preview
private fun UserCardPreview() { ... }

// ✗ Incorrect
@Preview
fun UserCard_Preview() { ... }
```

---

## 2. Composable Parameter Order

To maintain consistency, follow this order for parameters:

1. **Required parameters** — those without default values.
2. **Modifier** — `modifier: Modifier = Modifier` should be the first optional parameter.
3. **Optional parameters** — those with default values.
4. **Trailing lambdas** — the main content lambda (e.g. `content: @Composable () -> Unit`) should be the last parameter.

---

## 3. Modifier Usage

### Public composables
Always accept a `modifier` parameter.

### Top-level element
Apply the passed-in modifier to the **outermost** layout element only — never to inner children.

### No hardcoded sizes
Avoid hardcoding sizes like `.width(200.dp)` in reusable composables. Let the parent decide via the modifier.

### Modifier chain order
Order matters — place size before `.clip()` before `.background()`. Incorrect ordering causes visual and layout bugs.

```kotlin
// ✓ Correct
Modifier
    .size(72.dp)
    .clip(RoundedCornerShape(12.dp))
    .background(color)

// ✗ Incorrect
Modifier
    .background(color)
    .clip(RoundedCornerShape(12.dp))
    .size(72.dp)
```

---

## 4. State Management & Hoisting

### State hoisting
Prefer hoisting state to make composables stateless, easier to test, and more reusable.

### Events
Pass lambdas for events — e.g. `onClicked: () -> Unit`.

### rememberSaveable
Use `rememberSaveable` (not `remember`) for UI state that must survive configuration changes and process death — e.g. text field input, scroll position, selected tab. Avoid saving bulky objects; store IDs or primitive snapshots.

### Stability
Use `@Stable` and `@Immutable` annotations where appropriate to help the Compose compiler optimize recompositions.

> **Note:** Only apply `@Immutable` to data classes whose fields genuinely never change post-construction. Misuse causes incorrect skipping.

### ViewModel
Use ViewModels for complex screen-level state. Composables should interact with a State object or individual values exposed by the ViewModel — not the ViewModel directly where possible.

### Unidirectional data flow (UDF)
State flows **down** from ViewModel to composables; events flow **up** via lambdas. Never pass a ViewModel reference deep into child composables.

### CompositionLocal
Use `CompositionLocal` sparingly — only for truly cross-cutting concerns (e.g. theming, locale). It creates implicit dependencies that are hard to trace. Prefer explicit parameters and lambdas.

> **Tip:** Use `staticCompositionLocalOf` when the value rarely or never changes — reads are not tracked, which avoids unnecessary recomposition.

---

## 5. Performance Best Practices

### remember
Use `remember` for any calculation that shouldn't re-run on every recomposition. Always `remember` a `mutableStateOf` instance — forgetting it creates a new state object every recomposition.

### derivedStateOf
Use `derivedStateOf` when a value is calculated from states that change frequently (e.g. scroll position, list size). This avoids propagating every state change as a recomposition.

```kotlin
// ✓ Correct
val showFab by remember {
    derivedStateOf { listState.firstVisibleItemIndex > 0 }
}
```

### Lazy layout keys
Always provide stable `key` parameters in `LazyColumn` / `LazyRow` items. Without keys, Compose cannot reuse item compositions efficiently during list updates.

```kotlin
// ✓ Correct
items(list, key = { it.id }) { item ->
    ItemRow(item)
}
```

### Lazy layouts
Use `LazyColumn`, `LazyRow`, or `LazyVerticalGrid` for lists — only visible items are composed.

### Lambda stability
Avoid creating new lambdas inline inside composables — they are unstable and trigger recomposition in child composables. Pass method references or wrap lambdas in `remember { ... }` at the call site.

```kotlin
// ✓ Correct
Button(onClick = viewModel::onSubmit)

// ✗ Avoid
Button(onClick = { viewModel.onSubmit() })
```

### No side effects in body
Never perform side effects (network calls, DB writes) directly in the composable body. Use `LaunchedEffect`, `SideEffect`, or `DisposableEffect`.

### Defer reads
Read state as late as possible — inside lambdas passed to layout/draw phases rather than during composition — to narrow the scope of recomposition.

---

## 6. Previews

### Mandatory
Every UI component must have at least one `@Preview` function.

### Multiple states
Provide previews for distinct states — Light/Dark mode, Loading, Error, Success.

### Preview data
Use `PreviewParameterProvider` to supply mock data for previews.

### Private visibility
Preview functions should always be `private` — they are not part of the public API and should never be used in production code.

---

## 7. Theming

### Material 3
Always use `MaterialTheme.colorScheme`, `MaterialTheme.typography`, and `MaterialTheme.shapes`.

### No hardcoded colors
Always reference the theme. If a specific color is needed, add it to `Color.kt` and include it in the `ColorScheme`.

> **Warning:** Hardcoding colors breaks dark mode support and makes global theming changes impossible.

### No hardcoded text styles
Never pass raw `fontSize`, `fontWeight`, or `letterSpacing` to `Text()`. Always use a `MaterialTheme.typography` style or a custom token defined in your type system.

### No hardcoded strings
All user-visible strings must come from `stringResource()` — never hardcoded literals. This is required for localization.

---

## 8. Accessibility

### Content descriptions
Always provide `contentDescription` for `Image` and icon-only `IconButton` composables. Decorative images should explicitly pass `contentDescription = null`.

### Semantic roles
Use `Modifier.semantics { role = Role.Button }` (or the appropriate role) on custom interactive elements so that accessibility services correctly identify them.

### Minimum touch targets
Interactive elements must meet the 48×48dp minimum touch target size. Use `Modifier.minimumInteractiveComponentSize()` or ensure adequate padding.

### Merge semantics
Use `Modifier.semantics(mergeDescendants = true)` to group related elements (e.g. an icon + label) into a single accessibility node.

---

## 9. Testing

### Use ComposeTestRule
Write UI tests using `createComposeRule()` to set content and assert on semantic nodes. Prefer semantic finders (`onNodeWithText`, `onNodeWithContentDescription`) over positional finders.

### Test stateless composables
Because state is hoisted, stateless composables can be tested in isolation by passing in different combinations of parameters and lambdas — no ViewModel needed.

### Use test tags sparingly
Add `Modifier.testTag("...")` only when a node cannot be reliably identified by semantic properties. Overuse makes tests brittle to UI changes.

---

## 10. Architecture & Navigation

### Screen vs component split
Distinguish between **screen composables** (connected to a ViewModel, own their state) and **component composables** (stateless, driven entirely by parameters). Only screen composables should call `viewModel()`.

### Navigation
Do not pass `NavController` into child composables. Instead pass typed route callbacks — e.g. `onNavigateToDetail: (id: String) -> Unit`. This keeps composables decoupled from the nav graph.

### Single responsibility
Each composable should do one thing. If a composable is handling layout, business logic, and state simultaneously, break it down into smaller, focused units.

### Pass IDs, not models
When navigating between screens, pass primitive IDs in the nav arguments — not full model objects. The destination screen loads its own data from the ViewModel.

---

## 11. Tooling & Lint

### Compose lint rules
Enable the official Compose lint checks in Android Studio. They catch common errors: missing `remember`, unstable types, incorrect modifier usage, and more.

### Layout inspector
Use the Android Studio Layout Inspector to inspect the composition tree and identify unnecessary recompositions during development.

### Baseline profiles
Generate Baseline Profiles for release builds to pre-compile Compose code paths and reduce cold start time and initial frame drops.
