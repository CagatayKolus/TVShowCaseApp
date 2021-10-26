# TVShowCaseApp
An android application which uses HBO's Silicon Valley data (Mockable.io) for listing TV show episodes.

## Prerequisites

#### 1. Check the API

If the app cannot list any episode, check the the endpoint on TVShowCaseApiService class.

	https://demo2132984.mockable.io/tvshowcase

#### 2. Ready to run.

## Features
- Listing Episodes
- Caching Results (Offline Capability)
- Pull to Refresh
- Unit Tests

## Tech Stack
- **Kotlin** - Officially supported programming language for Android development by Google
- **Kotlin DSL** - Alternative syntax to the Groovy DSL
- **Coroutines** - Perform asynchronous operations
- **Flow** - Handle the stream of data asynchronously
- **Android Architecture Components**
  - **LiveData** - Notify views about data changes
  - **Room** - Persistence library
  - **ViewModel** - UI related data holder
  - **ViewBinding** - Allows to more easily write code that interacts with views
- **Hilt** - Dependency Injection framework
- **Retrofit** - Networking library
- **Moshi** - A modern JSON library for Kotlin and Java
- **Coil** - Image loading library
 
 ## Local Unit Tests
- The project uses MockWebServer (scriptable web server) to test API interactions.

## Screenshots
![tvshowcase_app](https://user-images.githubusercontent.com/25778714/138602654-097598ae-a2e6-405d-8a1d-f2e0094d09d3.png)

## Architecture
![arch500](https://user-images.githubusercontent.com/25778714/113482640-3801f100-94a8-11eb-98d6-e15cb21a905b.png)
