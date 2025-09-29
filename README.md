# Android Skip Intro Overlay Companion App

This app overlays a "Skip Intro" button on top of Stremio for Android/Firestick.  
It fetches intro skip data from your backend and (with accessibility permissions) can seek past intros in Stremio.

## Features
- Floating overlay button appears when Stremio is running
- Fetches intro skip data from your Koyeb backend
- Accessibility service included for future simulated seeking

## How to Build & Use

1. Open this repo in Android Studio or use GitHub Actions (see Actions tab)
2. Build and install the APK on your device
3. Grant Overlay and Accessibility permissions
4. Start the overlay from the app
5. Button appears on top of Stremio when video is playing
6. Tap button to (eventually) skip intro

## TODO
- Implement Accessibility logic to detect Stremio video and simulate seek
- Refine detection for current episode/file

---

Source: [Stremio Skip Intro Companion App](https://github.com/iHagoss/skipintro-companion-app)