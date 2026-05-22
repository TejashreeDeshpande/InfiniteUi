# InfiniteUI

InfiniteUI is a sample Android application demonstrating how to implement infinite scrolling using **Jetpack Paging 3** and **Jetpack Compose**.

## Features
- **Infinite Scrolling**: Smoothly load data as the user scrolls using the Paging 3 library.
- **Modern UI**: Built entirely with **Jetpack Compose** and **Material 3**.
- **Dependency Injection**: Uses **Koin** for clean and lightweight DI.
- **Mock Data**: Demonstrates paging logic using a simulated repository.

## Tech Stack
- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Pagination**: [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data)
- **Dependency Injection**: [Koin](https://insert-koin.io/)
- **Asynchronous Programming**: Coroutines & Flow

## How it Works
- `ArticlePagingSource`: Handles the data fetching logic, simulating network/database delay.
- `ArticleViewModel`: Manages the `Pager` and exposes a `Flow` of `PagingData`.
- `ArticleListScreen`: Renders the list using `LazyColumn` and handles different `LoadState` (Loading, Error, etc.).

## Screenshots
*(Add screenshots here)*

## Getting Started
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/InfiniteUI.git
   ```
2. Open the project in **Android Studio (Ladybug or newer)**.
3. Sync Gradle and run the app on an emulator or physical device.
