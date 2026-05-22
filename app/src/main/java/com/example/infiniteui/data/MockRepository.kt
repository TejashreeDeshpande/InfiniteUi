package com.example.infiniteui.data

object MockRepository {
    val mockArticles = (1..100).map { index ->

        val categories = listOf(
            "Architecture",
            "Compose",
            "Kotlin",
            "Performance",
            "Coroutines",
            "UI Engineering",
            "State Management",
            "Android"
        )

        val topics = listOf(
            "Scaling Jetpack Compose for Large Teams",
            "Advanced State Management in Compose",
            "Mastering Kotlin Coroutines",
            "Optimizing LazyColumn Performance",
            "Modern Android Architecture",
            "Offline First Android Apps",
            "Compose Animations That Feel Premium",
            "Building Reusable Design Systems",
            "Pagination 3 Deep Dive",
            "Production Ready Android UI"
        )

        val subtitles = listOf(
            "Building scalable and maintainable Android applications.",
            "Production-focused architecture and UI engineering concepts.",
            "Performance optimization techniques for modern Android apps.",
            "Exploring advanced Compose APIs and state management.",
            "Best practices for clean architecture and modularization.",
            "Creating smooth, responsive, and scalable mobile experiences.",
            "Modern Android development with Kotlin and Jetpack Compose.",
            "Efficient handling of large datasets and infinite scrolling."
        )

        Article(
            id = index,
            title = topics.random(),
            subtitle = subtitles.random(),
            category = categories.random(),
            readTime = "${(4..12).random()} min read"
        )
    }
}