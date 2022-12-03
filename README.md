# CodeChallenge-MovieApp

An Android movie app that demonstrates Clean Architecture and is written in Kotlin.


# Clean Architecture

The core principles of the clean approach can be summarized as followed:

#### 1. The application code is separated into layers.

These layers define the separation of concerns inside the code base.

#### 2. The layers follow a strict dependency rule.

Each layer can only interact with the layers below it.

#### 3. As we move toward the bottom layer — the code becomes generic.

The bottom layers dictate policies and rules, and the upper layers dictate implementation details such as the database, networking manager, and UI.


# Architecture Layers

The application consists of three layers:

The domain layer, the data layer, and the presentation layer.


# Used Libraries
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Retrofit2](https://github.com/square/retrofit)
* [View Binding](https://developer.android.com/topic/libraries/view-binding)
* [Mockito](https://github.com/mockito/mockito)
* [Android Architecture Components (ViewModels, LiveData, Room, etc.)](https://developer.android.com/topic/libraries/architecture/index.html)
