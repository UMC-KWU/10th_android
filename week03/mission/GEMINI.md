# Gemini CLI Context: Android Project (Week 03 Mission)

This project is an Android application developed as part of the UMC 10th Android mission for week 03. It focuses on implementing a list of friends using `RecyclerView` and `Fragments`.

## Project Overview
- **Purpose:** Demonstrate `RecyclerView`, `Adapter`, `ViewHolder`, and `Fragment` implementation in Android.
- **Main Technologies:**
    - **Language:** Kotlin
    - **Build System:** Gradle (Kotlin DSL)
    - **UI Toolkit:** Android View (XML) with ViewBinding
    - **Architecture:** Layered approach (Domain, Presentation)
    - **Key Components:** `MainActivity`, `FriendListFragment`, `FriendAdapter`, `FriendViewHolder`, `FriendData`.

## Architecture & Structure
- **`domain/model`**: Contains data classes like `FriendData`.
- **`presentation/fragment/recycler`**: Contains `FriendListFragment`, which manages the UI logic for the friend list.
- **`presentation/screen/recycler`**: Contains `FriendAdapter` and `FriendViewHolder` for the `RecyclerView` implementation.
- **`res/layout`**:
    - `activity_main.xml`: Main container using `FragmentContainerView`.
    - `fragment_friend_list.xml`: Contains the `RecyclerView`.
    - `item_friend.xml`: Layout for individual list items.

## Building and Running
- **Build:** `./gradlew assembleDebug`
- **Install & Run:** `./gradlew installDebug`
- **Unit Tests:** `./gradlew :app:test`
- **Instrumented Tests:** `./gradlew :app:connectedAndroidTest`
- **Lint:** `./gradlew lint`

## Development Conventions
- **ViewBinding:** Always use ViewBinding instead of `findViewById`. Ensure to nullify `_binding` in `onDestroyView()` for Fragments.
- **Dependency Management:** All versions and library declarations are centralized in `gradle/libs.versions.toml`.
- **Target SDK:** 36 (Android 16 / Vanilla Ice Cream)
- **Min SDK:** 24 (Android 7.0 / Nougat)
- **Coding Style:** Follow standard Kotlin/Android coding conventions (PascalCase for classes, camelCase for variables/methods).
- **Edge-to-Edge:** Enabled in `MainActivity` using `enableEdgeToEdge()`.
