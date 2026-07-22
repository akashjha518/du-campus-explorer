# SouthCampus

SouthCampus is an Android app for helping students and visitors explore a campus experience in one place. It includes screens for campus navigation, transport information, syllabus resources, hostel details, cafeteria info, event updates, facility complaints, user profile, and settings.

## Features

- Campus navigation and search
- Transport information and route details
- Academic resources and syllabus access
- Hostel information
- Cafeteria menu and info
- Event updates
- Facility complaint screen
- User profile and settings pages
- Login and onboarding flow

## Tech Stack

- Java
- Android SDK
- AndroidX
- Material Components
- ConstraintLayout
- Google Maps services

## Project Structure

- `app/src/main/java/com/codewithme/southcampus/` - app activities and adapters
- `app/src/main/res/layout/` - XML layouts for each screen
- `app/src/main/res/values/` - strings, colors, dimens, and themes
- `app/src/main/res/drawable/` - icons and images used in the UI

## Requirements

- Android Studio
- JDK 8 or later
- Android SDK 34
- Minimum Android version: API 24

## Build And Run

1. Open the project in Android Studio.
2. Let Gradle sync finish.
3. Run the `app` module on an emulator or physical device.

If you prefer the command line:

```bash
./gradlew assembleDebug
```

On Windows:

```powershell
gradlew.bat assembleDebug
```

## Permissions

The app declares the following permissions in the manifest:

- `INTERNET`
- `ACCESS_NETWORK_STATE`
- `ACCESS_FINE_LOCATION`
- `ACCESS_COARSE_LOCATION`

It also declares optional camera support.

## Notes

- The app currently uses a Google Maps API key in the manifest.
- Some screens and content appear to be campus-specific and may need updating with live or official data before release.

## Testing

Basic unit and instrumentation test placeholders are included under:

- `app/src/test/java/`
- `app/src/androidTest/java/`

## License

See [`LICENSE.txt`](LICENSE.txt) for licensing details.
