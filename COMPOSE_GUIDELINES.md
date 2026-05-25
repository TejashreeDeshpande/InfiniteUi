# Jetpack Compose Coding Standards & Guidelines

This document outlines the coding standards and best practices for developing Compose-first applications in the **InfiniteUI** project.

## 1. Naming Conventions

*   **Composable Functions**: Must use **PascalCase**.
    *   *Correct*: `fun UserProfile() { ... }`
    *   *Incorrect*: `fun userProfile() { ... }`
*   **Unit-returning Composables**: Should be named as nouns.
    *   *Example*: `MessageList`, `SubmitButton`.
*   **Value-returning Functions**: Should follow standard Kotlin camelCase.
    *   *Example*: `rememberScaffoldState()`.

## 2. Composable Parameter Order

To maintain consistency, follow this order for parameters:
1.  **Required parameters**: Those without default values.
2.  **Modifier**: The `modifier: Modifier = Modifier` should be the first optional parameter.
3.  **Optional parameters**: Those with default values.
4.  **Trailing Lambdas**: The main content lambda (e.g., `content: @Composable () -> Unit`) should be the last parameter.

## 3. Modifier Usage

*   **Public Composables**: Should always accept a `modifier` parameter.
*   **Top-level element**: The passed-in `modifier` should be applied to the outermost layout element within the Composable.
*   **Don't hardcode**: Avoid hardcoding sizes (like `.width(200.dp)`) inside Composables that are intended to be reusable. Let the parent decide the size via the modifier.

## 4. State Management & Hoisting

*   **State Hoisting**: Prefer hoisting state to make Composables stateless and easier to test/reuse.
*   **Events**: Pass lambdas for events (e.g., `onClicked: () -> Unit`).
*   **Stability**: Use `Stable` and `Immutable` annotations where appropriate to help the Compose compiler optimize recompositions.
*   **ViewModel**: Use ViewModels to manage complex screen-level state. Composables should ideally interact with a "State" object or individual values exposed by the ViewModel.

## 5. Performance Best Practices

*   **Remembering**: Use `remember` for any calculation that shouldn't be re-run on every recomposition.
*   **Derived State**: Use `derivedStateOf` when a state is calculated based on other states that change frequently (e.g., scroll position).
*   **Lazy Layouts**: Use `LazyColumn`, `LazyRow`, or `LazyVerticalGrid` for lists to ensure only visible items are composed.
*   **Avoid Side Effects**: Don't perform side effects (like network calls or database writes) directly in the Composable body. Use `LaunchedEffect`, `SideEffect`, or `DisposableEffect`.

## 6. Previews

*   **Mandatory Previews**: Every UI component should have at least one `@Preview` function.
*   **Multiple States**: Provide previews for different states (e.g., Light/Dark mode, Loading, Error, Success).
*   **Preview Data**: Use `PreviewParameterProvider` to supply mock data for previews.

## 7. Theming

*   **Material 3**: Use `MaterialTheme.colorScheme`, `MaterialTheme.typography`, and `MaterialTheme.shapes`.
*   **No Hardcoded Colors**: Always reference the theme. If a specific color is needed, add it to the `Color.kt` and include it in the `ColorScheme`.

---
