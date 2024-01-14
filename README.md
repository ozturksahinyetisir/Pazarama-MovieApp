
## Description
This application is the group project of the Pazarama Android Bootcamp, developed as a team effort, and it involves listing and detailing movies using data obtained from [OMDb Api](https://www.omdbapi.com/).  

## Technologies and Libraries
- [Android Architecture Components](https://developer.android.com/topic/architecture) - Collection
  of libraries that help you design robust, testable, and maintainable apps.
    - A single-activity architecture, using
      the [Navigation](https://developer.android.com/guide/navigation) to manage composable
      transactions.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an
      action when lifecycle state change
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
      UI-related data that isn’t destroyed on UI changes.
    - [UseCases](https://developer.android.com/topic/architecture/domain-layer) - Located domain
      layer
      that sits between the UI layer and the data layer.
    - [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in data
      layer that contains application data and business logic
- [Glide](https://github.com/bumptech/glide)
- [Kotlin](https://kotlinlang.org/)
  based [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) [Flow](https://developer.android.com/kotlin/flow)
  for asynchronous.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging
  network data.
- [Navigation](https://developer.android.com/guide/navigation) - Manage transaction among the
  fragments
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency
  Injection Library
- Testing
    - [ Flow Turbine](https://github.com/cashapp/turbine) - Turbine is a small testing library for
      kotlinx.coroutines Flow.
    - [Truth](https://truth.dev/) - A library for performing assertions in tests





## Contributors
- [Muhammed Mercan](https://github.com/muhammedmercan)
- [Tolga Pirim](https://github.com/tolgaprm)
- [Öztürk Şahin Yetişir](https://github.com/ozturksahinyetisir)
- [Kaan Binen](https://github.com/KaanBN)


## Screenshots
| Main Screen | Details Screen | Connection Error | Not Found Error |
| ----------- | -------------- | ---------------- | --------------- |
| ![Main Screen](https://github.com/muhammedmercan/PazaramaMovieApp/blob/master/assets/ss_movie_1.png) | ![Details Screen](https://github.com/muhammedmercan/PazaramaMovieApp/blob/master/assets/ss_movie_2.png) | ![Connection Error](https://github.com/muhammedmercan/PazaramaMovieApp/blob/master/assets/ss_movie_3.png) | ![Not Found Error](https://github.com/muhammedmercan/PazaramaMovieApp/blob/master/assets/ss_movie_4.png) 
