# Suitmedia Mobile Test App

A modern Android application built with Jetpack Compose and Kotlin that demonstrates various Android development concepts including palindrome checking, user selection, and API integration.

## 📋 Overview

This application consists of three main screens:

1. **Palindrome Screen** - Input validation and palindrome checking
2. **Welcome Screen** - User greeting and navigation
3. **User Select Screen** - User list with API integration and pagination

## 🚀 Features

- **Palindrome Checker**: Validates text input to determine if it's a palindrome
- **User Management**: Browse and select users from a remote API
- **Modern UI**: Built with Jetpack Compose and Material Design 3
- **Responsive Design**: Adaptive layouts for different screen sizes
- **Pull-to-Refresh**: Refresh user data with swipe gesture
- **Pagination**: Efficient loading of large user lists
- **Error Handling**: Comprehensive error states and user feedback
- **Navigation**: Smooth navigation between screens with Navigation 3

## 🛠️ Tech Stack

### Core Technologies

- **Kotlin** - Programming language
- **Jetpack Compose** - Modern UI toolkit
- **Material Design 3** - UI components and theming
- **Navigation 3** - Screen navigation and state management

### Architecture & Dependencies

- **MVVM Pattern** - Clean architecture implementation
- **Koin** - Dependency injection
- **Retrofit** - Network requests
- **Paging 3** - Efficient data loading
- **Coil** - Image loading and caching
- **Coroutines** - Asynchronous programming

### Key Libraries

```kotlin
// UI & Compose
implementation "androidx.compose.ui:ui"
implementation "androidx.compose.material3:material3"
implementation "androidx.activity:activity-compose"

// Navigation
implementation "androidx.navigation3:navigation3-ui"
implementation "androidx.navigation3:navigation3-runtime"

// Network
implementation "com.squareup.retrofit2:retrofit"
implementation "com.squareup.retrofit2:converter-gson"
implementation "com.squareup.okhttp3:logging-interceptor"

// Image Loading
implementation "io.coil-kt.coil3:coil-compose"
implementation "io.coil-kt.coil3:coil-network-okhttp"

// Dependency Injection
implementation "io.insert-koin:koin-androidx-compose"
implementation "io.insert-koin:koin-core"

// Pagination
implementation "androidx.paging:paging-runtime-ktx"
implementation "androidx.paging:paging-compose"
```

## 🏗️ Project Structure

```
app/
├── src/main/java/com/nakersolutionid/suitmedia/
│   ├── data/                   # Data layer
│   │   ├── remote/            # API services and responses
│   │   └── UserRepository.kt  # Data repository
│   ├── di/                    # Dependency injection modules
│   │   └── AppModules.kt     # Koin modules
│   ├── extensions/           # Extension functions
│   ├── ui/                   # UI layer
│   │   ├── component/        # Reusable UI components
│   │   │   ├── common/       # Common components
│   │   │   ├── palindrome/   # Palindrome-specific components
│   │   │   └── userselect/   # User selection components
│   │   ├── navigation/       # Navigation setup
│   │   ├── screen/           # Screen composables
│   │   │   ├── palindrome/   # Palindrome screen
│   │   │   ├── userselect/   # User selection screen
│   │   │   └── welcome/      # Welcome screen
│   │   └── theme/            # App theming
│   ├── MainActivity.kt       # Main activity
│   └── MainApp.kt           # Application class
└── src/main/res/            # Resources
    ├── values/              # String resources, themes
    └── drawable/            # Images and icons
```

## 🎨 UI Components

### Custom Components

- **MyButton** - Styled button with consistent theming
- **MyTopAppBar** - Custom top app bar with navigation
- **ProfilePicture** - Circular profile image component
- **PalindromeInput** - Text input with validation
- **UserItem** - User list item with profile image and details

### Screen States

- **LoadingStateScreen** - Loading indicator
- **ErrorStateScreen** - Error message display
- **EmptyStateScreen** - Empty state with illustration

## 🔧 Setup Instructions

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 21 or higher
- Kotlin 1.8.0 or later

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/mhmdazkanfl/suitmedia-test.git
   cd suitmedia-test
   ```

2. **Open in Android Studio**

   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory

3. **Configure API Settings**

   - Create a `local.properties` file in the root directory
   - Add your API configuration:

   ```properties
   API_KEY=your_api_key_here
   HOSTNAME=https://your-api-endpoint.com/
   ```

4. **Build and Run**
   - Sync project with Gradle
   - Run the app on an emulator or physical device

## 📱 Usage

### Palindrome Screen

1. Enter your name in the "Name" field
2. Enter text to check in the "Palindrome" field
3. Tap "CHECK" to validate if the text is a palindrome
4. Tap "NEXT" to proceed to the welcome screen

### Welcome Screen

1. View personalized greeting with your name
2. See selected user (if any)
3. Tap "Choose a User" to browse users

### User Select Screen

1. Browse through the user list
2. Pull down to refresh the list
3. Tap on any user to select them
4. Navigate back to see the selected user

## 🎯 Key Features Explained

### Palindrome Validation

- Case-insensitive checking
- Ignores spaces and special characters
- Real-time feedback with snackbar messages

### User Selection

- API integration with pagination
- Image loading with Coil
- Pull-to-refresh functionality
- Error handling and retry mechanisms

### Theme System

- Material Design 3 theming
- Light and dark mode support
- Custom color schemes
- Consistent typography

## 🔍 Testing

### Running Tests

```bash
./gradlew test           # Unit tests
./gradlew connectedTest  # Instrumentation tests
```

### Test Coverage

- Unit tests for ViewModels
- UI tests for screen interactions
- Integration tests for API calls

## 📝 Development Notes

### API Integration

The app integrates with a RESTful API for user data:

- Base URL configured in `local.properties`
- Retrofit for network requests
- Gson for JSON parsing
- OkHttp interceptor for logging

### State Management

- StateFlow for reactive state management
- Compose state for UI updates
- Paging 3 for efficient list loading

### Error Handling

- Network error handling
- UI error states
- User feedback with snackbars

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Muhammad Naufal Azka**

- GitHub: [@mhmdazkanfl](https://github.com/mhmdazkanfl)
- Email: mhmdazkanfl@gmail.com

## 🙏 Acknowledgments

- [Suitmedia](https://www.suitmedia.com/) for the technical test requirements
- Android development community for excellent documentation
- Jetpack Compose team for the modern UI toolkit

---

_This project was created as part of a technical assessment for Android development skills._
