# Quran App

## Project Overview
This is an Android application developed in Kotlin using Jetpack Compose for the UI. The app aims to provide a comprehensive platform for reading and listening to the Quran. It integrates with various modern Android development libraries and follows clean architecture principles.

## Features
- **Browse Surahs**: View a list of all Surahs from the Quran.
- **Surah Details**: Access detailed information for each Surah.
- **Audio Playback**: Listen to the recitation of Surahs using ExoPlayer.
- **Offline Capabilities**: (To be confirmed based on Room database usage)
- **Search Functionality**: (To be confirmed based on implementation)

## Technologies Used
The app leverages a variety of modern Android development tools and libraries:

*   **Kotlin**: Primary programming language.
*   **Jetpack Compose**: Declarative UI toolkit for building native Android UIs.
*   **Hilt (Dagger Hilt)**: Dependency injection framework for Android.
*   **Ktor**: Networking client for making API requests.
*   **Kotlinx Serialization**: For efficient JSON serialization/deserialization.
*   **Room**: Persistence library for local data storage (SQLite database).
*   **Paging 3**: For efficient loading and displaying large datasets.
*   **ExoPlayer**: Media player library for audio playback.
*   **AndroidX Libraries**: A collection of libraries that help build robust, testable, and maintainable Android apps.
    *   `androidx.lifecycle.runtime.ktx`
    *   `androidx.activity.compose`
    *   `androidx.ui`
    *   `androidx.ui.graphics`
    *   `androidx.ui.tooling.preview`
    *   `androidx.material3`
    *   `androidx.graphics.shapes.android`
    *   `androidx.paging.common.android`
    *   `androidx.paging.compose.android`
    *   `androidx.hilt.navigation.compose`
    *   `androidx.navigation.compose`

## Setup and Installation

### Prerequisites
*   Android Studio IDE
*   Android SDK

### Steps
1.  **Clone the repository**:
    ```bash
    git clone https://github.com/aarundn/Quran.git
    ```
2.  **Open in Android Studio**:
    Open the cloned project in Android Studio.
3.  **Sync Gradle**:
    Allow Android Studio to sync the Gradle project. This will download all necessary dependencies.
4.  **Run on Device/Emulator**:
    Select a target device or emulator and run the application from Android Studio.

## Architecture
The app follows a Clean Architecture approach, separating concerns into distinct layers:

*   **Presentation Layer**: Built with Jetpack Compose, responsible for UI and user interaction. It uses ViewModels to manage UI state.
*   **Domain Layer**: Contains business logic and use cases. It is independent of Android-specific frameworks.
*   **Data Layer**: Responsible for data retrieval from various sources (network, local database). It includes repositories and data sources.

## Contributing

## License
