# MyGPT - Android Chat App with Gemini AI

A beautiful, intelligent chat application for Android built with Jetpack Compose and powered by Google's Gemini 1.5 Pro API. MyGPT provides a warm, conversational experience with advanced AI capabilities.

## 🌟 Features

### **Core Features**
- 🤖 **Gemini 1.5 Pro Integration** - Powered by Google's latest AI model
- 💬 **Real-time Chat** - Smooth, responsive messaging experience
- 🎨 **Modern UI** - Beautiful Material Design 3 interface
- 🌙 **Dark Theme Support** - Comfortable viewing in any lighting
- 📱 **Responsive Design** - Optimized for all Android devices
- ⚡ **Fast & Efficient** - Built with Kotlin and Jetpack Compose

### **User Experience**
- 📝 **Smart Input** - Multi-line text input with send button
- ⏱️ **Typing Indicators** - Visual feedback during AI processing
- 🕐 **Timestamps** - Messages show when they were sent (dd-MM-yyyy format)
- 🔄 **Auto-scroll** - Automatically scrolls to latest messages
- ❌ **Error Handling** - Clear error messages with easy dismissal
- 🔒 **Secure API Key** - Local storage of your Gemini API key

### **Technical Features**
- 🏗️ **MVVM Architecture** - Clean, maintainable code structure
- 🌊 **State Management** - Reactive UI with StateFlow
- 🌐 **Network Layer** - Robust API communication with Retrofit
- 🧵 **Coroutines** - Asynchronous operations for smooth performance
- 📦 **Modular Design** - Well-organized, reusable components

## 🚀 Getting Started

### **Prerequisites**
- Android Studio Hedgehog (2023.1.1) or later
- Android SDK 24+ (API level 24)
- Kotlin 1.9.0+
- A Google Gemini API key

### **Installation**

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/MyGPT.git
   cd MyGPT
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Open the project folder
   - Wait for Gradle sync to complete

3. **Get Your Gemini API Key**
   - Visit [Google AI Studio](https://aistudio.google.com)
   - Sign in with your Google account
   - Create a new API key
   - Copy the key for use in the app

4. **Build and Run**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio
   - The app will install and launch

### **First Time Setup**

1. **Launch the App**
   - Open MyGPT on your device
   - You'll see the welcome screen

2. **Enter API Key**
   - Tap the API key input field
   - Paste your Gemini API key
   - Tap "Start Chatting"

3. **Start Chatting**
   - Type your first message
   - Tap the send button or press Enter
   - Enjoy your conversation with AI!

## 🛠️ Technical Architecture

### **Project Structure**
```
app/src/main/java/com/shahid/mygpt/
├── data/
│   └── Models.kt                 # Data classes for API requests/responses
├── network/
│   └── GeminiApiService.kt       # Retrofit API service
├── repository/
│   └── ChatRepository.kt         # Data layer and API calls
├── ui/
│   └── components/
│       ├── ChatScreen.kt         # Main chat interface
│       ├── MessageComponents.kt  # Message bubbles and typing indicators
│       ├── InputComponents.kt    # Input field and error handling
│       └── SetupScreen.kt        # API key setup screen
├── viewmodel/
│   └── ChatViewModel.kt          # Business logic and state management
└── MainActivity.kt               # App entry point
```

### **Key Technologies**
- **Jetpack Compose** - Modern Android UI toolkit
- **Material Design 3** - Latest design system
- **Retrofit** - HTTP client for API calls
- **Coroutines** - Asynchronous programming
- **StateFlow** - Reactive state management
- **ViewModel** - UI state and lifecycle management

## 📱 Usage Guide

### **Sending Messages**
- Type your message in the input field at the bottom
- Tap the send button (📤) or press Enter
- Your message will appear in a blue bubble
- The AI will respond with a gray bubble

### **Understanding Responses**
- AI responses are generated using Gemini 1.5 Pro
- The AI maintains conversation context
- Responses are warm, helpful, and conversational
- Long responses are automatically formatted

### **Error Handling**
- **Network Errors**: Check your internet connection
- **API Key Errors**: Verify your Gemini API key is correct
- **Rate Limits**: Wait a moment before sending another message

### **Best Practices**
- Be specific in your questions for better responses
- The AI remembers conversation context
- Use natural language - no special commands needed
- Feel free to ask follow-up questions

## 🔧 Customization

### **Theme Colors**
The app uses Material Design 3 theming. To customize colors, edit:
```
app/src/main/java/com/shahid/mygpt/ui/theme/Color.kt
```

### **API Configuration**
To modify API settings, update:
```
app/src/main/java/com/shahid/mygpt/repository/ChatRepository.kt
```

### **UI Components**
All UI components are in the `ui/components/` directory and can be easily customized.

## 🐛 Troubleshooting

### **Common Issues**

**App won't start**
- Check that you have Android SDK 24+ installed
- Ensure all dependencies are synced in Gradle

**API key not working**
- Verify your API key is correct
- Check that you have an active Gemini API quota
- Ensure you're using the correct API key format

**Messages not sending**
- Check your internet connection
- Verify the Gemini API service is available
- Try restarting the app

**Slow responses**
- This is normal for AI generation
- Responses typically take 2-5 seconds
- Check your internet speed

### **Getting Help**
- Check the error messages in the app
- Verify your API key at [Google AI Studio](https://aistudio.google.com)
- Ensure you have sufficient API quota

## 🔒 Privacy & Security

- **Local Storage**: Your API key is stored locally on your device
- **No Data Collection**: We don't collect or store your messages
- **Secure Communication**: All API calls use HTTPS
- **Your Data**: Messages are sent to Google's Gemini API for processing

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 🙏 Acknowledgments

- Google for providing the Gemini API
- The Android and Jetpack Compose teams
- The open-source community for amazing libraries

---

**Made with ❤️ for the Android community**

*Your intelligent companion, powered by Gemini AI* 