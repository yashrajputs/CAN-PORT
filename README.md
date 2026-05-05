# CAN Analyser (Android OBD App)

Android application for reading vehicle diagnostics over Bluetooth using an OBD-II adapter.

## Features

- Bluetooth pairing and connection flow for OBD devices
- Runtime Bluetooth permission handling (Android 12+)
- Live OBD response reading and status updates
- Splash and dashboard based app navigation
- Kotlin + Jetpack Compose UI architecture

## Tech Stack

- Kotlin
- Android SDK
- Jetpack Compose
- Coroutines + `StateFlow`

## Project Structure

- `app/src/main/java/com/odb/myapplication/data` - Bluetooth and OBD data layer
- `app/src/main/java/com/odb/myapplication/ui` - ViewModel, navigation, and screens
- `app/src/main/res` - Drawables and Android resources

## Requirements

- Android Studio (latest stable recommended)
- Android SDK and build tools installed
- A paired Bluetooth OBD-II adapter
- Android device with Bluetooth enabled

## Getting Started

1. Clone the repository:
   - `git clone https://github.com/yashrajputs/CAN-PORT.git`
2. Open the project in Android Studio.
3. Let Gradle sync complete.
4. Connect an Android device (or use an emulator with required capabilities).
5. Build and run the app.

## Screenshots

Add your app screenshots under `docs/screenshots/` and update these links if needed:

- Splash Screen: `docs/screenshots/splash.png`
- Dashboard: `docs/screenshots/dashboard.png`
- Connection Flow: `docs/screenshots/connection.png`

## Bluetooth Permissions

For Android 12 and above, the app requests:

- `BLUETOOTH_CONNECT`
- `BLUETOOTH_SCAN`

Make sure these permissions are granted at runtime.

## Notes

- The app expects an OBD Bluetooth device that supports serial communication (SPP profile behavior).
- If your adapter/device name differs, update the connection target in the relevant UI/workflow.

## Troubleshooting

- **Bluetooth permissions denied**: Go to App Settings and allow Nearby devices/Bluetooth permissions, then restart the app.
- **Device not found**: Ensure the OBD adapter is paired in Android Bluetooth settings before connecting from the app.
- **Connect fails immediately**: Toggle Bluetooth off/on, keep ignition ON, and verify the adapter is powered.
- **No live data received**: Confirm adapter compatibility and serial output format; try reconnecting and monitoring status logs.
- **Build or sync issues**: In Android Studio, run Gradle sync again and verify Android SDK/build-tools are installed.

## License

This project is licensed under the terms in `LICENSE`.
