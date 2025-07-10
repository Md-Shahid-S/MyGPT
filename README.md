# MyGPT - Android Chat App (Vibe Coding + Cursor AI)

Welcome to **MyGPT**, a beautiful and modern Android chat app built with:
- **Vibe Coding** (for a creative, expressive UI)
- **Cursor AI** (for smart code generation and productivity)
- **Android Studio** (for robust Android development)

---

## 🚀 **Project Highlights**
- **Built with Vibe Coding principles** for a lively, dynamic, and user-friendly experience.
- **Developed using Cursor AI** for rapid, intelligent code assistance.
- **Modern Jetpack Compose UI** with custom chat bubbles, animated backgrounds, and dynamic effects.
- **No external Markdown/RichText libraries**—all formatting is done with pure Compose.
- **Google Gemini API integration** for real-time AI chat.

---

## 📥 **How to Clone This Repository**

Open your terminal or command prompt and run:

```bash
# Clone the repository
git clone https://github.com/Md-Shahid-S/MyGPT.git
cd MyGPT

# Open the project in Android Studio, set your API key, then:
# To build and run the app on an emulator or device:
./gradlew assembleDebug
# or simply use the Run button in Android Studio
```

---

## 🗂️ **Repository Structure**

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/shahid/mygpt/
│   │   │   ├── MainActivity.kt                # App entry point
│   │   │   ├── data/Models.kt                 # Data models for chat and API
│   │   │   ├── network/GeminiApiService.kt    # Retrofit Gemini API service
│   │   │   ├── repository/ChatRepository.kt   # Handles API calls and chat logic
│   │   │   ├── viewmodel/ChatViewModel.kt     # State management (MVVM)
│   │   │   └── ui/components/                 # All UI composables
│   │   │       ├── ChatScreen.kt              # Main chat screen (animated background, welcome banner)
│   │   │       ├── MessageComponents.kt       # Message bubbles, avatars, custom bot formatting
│   │   │       ├── InputComponents.kt         # Input bar and send button
│   │   │       └── SetupScreen.kt             # API key setup screen (if used)
│   │   └── res/                               # Resources (icons, colors, themes)
│   └── AndroidManifest.xml                    # Permissions and app config
├── build.gradle.kts                           # App-level Gradle config
├── settings.gradle.kts                        # Project-level Gradle config (repositories)
└── README.md                                  # This file
```

---

## 🔑 **How to Set or Change Your API Key**

**File to edit:**
```
app/src/main/java/com/shahid/mygpt/viewmodel/ChatViewModel.kt
```

**Find this line (near the top of the class):**
```kotlin
private val _apiKey = MutableStateFlow("YOUR_API_KEY_HERE")
```

**Replace `YOUR_API_KEY_HERE` with your actual Gemini API key:**
```kotlin
private val _apiKey = MutableStateFlow("AIzaSy...your_actual_key...")
```
- Make sure there are no extra spaces or line breaks.
- Do **not** commit your real API key to public repositories!

---

## 🧑‍💻 **How to Run the Project**
1. **Clone the repo** and open in Android Studio.
2. **Set your API key** as described above.
3. **Sync Gradle** and run the app on an emulator or device.
4. **Enjoy a beautiful, dynamic chat experience!**

---

## 💡 **Features**
- Animated gradient background and welcome banner
- Custom chat bubbles with avatars
- Bullet points and keyword highlighting in bot replies
- Floating, modern input bar
- Error banners with icons and animation
- All UI built with Jetpack Compose (no external Markdown libraries)

---

## 🤖 **Built With**
- **Vibe Coding**: For creative, expressive, and modern UI/UX
- **Cursor AI**: For smart, rapid code generation
- **Android Studio**: For robust Android development
- **Jetpack Compose**: For declarative, beautiful UI

---

## 📢 **Notes**
- If you want to use a different API key, just update the line in `ChatViewModel.kt` as shown above.
- For production, consider using Android's DataStore or EncryptedSharedPreferences to store the API key securely.
- Never share your API key in public repositories.

---

**Happy coding with Vibe, Cursor AI, and Android Studio!** 