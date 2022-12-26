# Android Argyle Link Sample
Sample Android app which displays and filters links from Argyle Link API. The app is built using Kotlin, Jetpack Compose, and MVVM architecture. The code is covered by unit and instrumented tests.

## Functionality
The results are shown using the following logic:
- If a query input is empty, all the results are shown.
- If a single character is entered in query input, the message `Enter at least two characters to filter` is shown.
- If two or more characters are entered in query input, the filtered results are shown.
- If there are no results, the message `No results` is shown.
- If there is no internet connection when performing a request, the snackbar `Check your internet connection.` is shown.  
  Query input is always trimmed, so spaces at the beginning and the end are ignored and results are always limited to 15 entries.

## Architecture
MVVM (Model-View-ViewModel) architecture is used throughout the app.

### Model
All model-related classes are placed inside a package `model`.
The package is further divided into the following packages:
- `local` - data classes used in all app layers.
- `remote` - API-related classes.
- `repository` - classes implementing repository design pattern.

## Libraries used
- Jetpack Compose - UI components
- Retrofit - API requests
- Gson - JSON object mapping
- Hilt - Dependency injection
- Coil - Image loading

## Tests
Both unit and instrumented tests are written.

### Unit tests
All the classes containing logic (ViewModels, Repositories) are covered by unit tests. MockK library is used for dependency mocking.

### Instrumented tests
The most important test cases are covered by instrumented tests run on Android devices.
API-related logic is mocked, so no real requests are made while running these tests and sample results are always returned.
This allows make the tests predictable and not dependent on the API.
