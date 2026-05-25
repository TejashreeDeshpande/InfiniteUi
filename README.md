# InfiniteUI

InfiniteUI is a sample Android application demonstrating how to implement infinite scrolling using **Jetpack Paging 3** and **Jetpack Compose**. It showcases three different UI patterns for infinite loading: an Article Feed, an Image Gallery, and a Grid View.

## Features
- **Infinite Scrolling**: Smoothly load data as the user scrolls using the Paging 3 library.
- **Multiple UI Patterns**:
    - **Article Feed**: A modern list of articles with details, categories, and read times.
    - **Image Gallery**: A standard list of high-quality images loaded asynchronously.
    - **Grid View**: A flexible grid layout showcasing how Paging 3 works with `LazyVerticalGrid`.
- **Modern UI**: Built entirely with **Jetpack Compose** and **Material 3**, featuring a custom color palette and responsive layouts.
- **Type-Safe Navigation**: Uses the latest **Compose Navigation** with type-safety for seamless transitions between screens.
- **Image Loading**: Efficiently loads and caches images using **Coil 3**.
- **Dependency Injection**: Uses **Koin** for clean and lightweight DI.
- **Mock Data**: Demonstrates paging logic using a simulated repository with realistic data and simulated network delays.

## Tech Stack
- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Design System**: Material 3
- **Pagination**: [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data)
- **Navigation**: [Compose Navigation (Type-Safe)](https://developer.android.com/guide/navigation/design/type-safety)
- **Dependency Injection**: [Koin](https://insert-koin.io/)
- **Image Loading**: [Coil 3](https://coil-kt.github.io/coil/)
- **Asynchronous Programming**: Coroutines & Flow

## How it Works
- **Paging Sources**: 
    - `ArticlePagingSource`: Handles fetching article data from the mock repository.
    - `ImagePagingSource`: Manages image data loading for the gallery and grid views.
- **ViewModels**:
    - `ArticleViewModel`: Manages the `Pager` for articles and exposes a `Flow` of `PagingData`.
    - `ImageViewModel`: Manages the `Pager` for images, shared across gallery and grid views.
- **Composables**: Renders the lists using `LazyColumn` and `LazyVerticalGrid`, handling different `LoadState` (Loading, Error, etc.) with custom UI components.

## Getting Started
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/InfiniteUI.git
   ```
2. Open the project in **Android Studio (Ladybug or newer)**.
3. Sync Gradle and run the app on an emulator or physical device.

## Coding Standards
For guidelines on how to write Compose code in this project, please refer to [COMPOSE_GUIDELINES.md](./COMPOSE_GUIDELINES.md).
