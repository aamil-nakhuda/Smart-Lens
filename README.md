# 📘 Smart Lens
![Built With Java](https://img.shields.io/badge/Built%20With-Java-blue)
![Firebase ML](https://img.shields.io/badge/Firebase-ML--Kit-orange)
![Platform](https://img.shields.io/badge/Platform-Android-green)
![Status](https://img.shields.io/badge/Project%20Status-Completed-brightgreen)
![UI/UX](https://img.shields.io/badge/Design-Responsive%20UI-blueviolet)
![Code Coverage](https://img.shields.io/badge/Tests-Passed%20100%25-success)
![Contributors](https://img.shields.io/badge/Maintained%20By-Solo%20Developer-informational)
![Mobile Support](https://img.shields.io/badge/Min%20Android%20Version-6.0%20Marshmallow-lightgrey)
![Repo Size](https://img.shields.io/github/repo-size/aamil-nakhuda/Smart-Lens)
![Last Commit](https://img.shields.io/github/last-commit/aamil-nakhuda/Smart-Lens)
![Stars](https://img.shields.io/github/stars/aamil-nakhuda/Smart-Lens?style=social)

&#x20;&#x20;

## ✨ Table of Contents
- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Architecture](#-project-architecture)
- [My Role and Contributions](#-my-role-and-contributions)
- [Getting Started / Installation](#-getting-started--installation)
- [Testing](#-testing)
- [Documentation](#-documentation)
- [Mockups](#-mockups)
- [License](#-license)
- [GitHub Repository](#-github-repository)

## 🚀 Overview

**Smart Lens** is an innovative Android application inspired by Google Lens, developed as a final-year B.Sc. IT project. It empowers users to quickly understand and search visual information using their smartphone camera. The app simplifies image-based searches for objects, text search, which the user can use for multi-language text translation, and even code recognition with execution.

This project was undertaken individually, simulating real-world product design, development, testing, and project management. It demonstrates not just technical fluency but a structured, adaptive development approach.


## ⚒️ Features

* **📷 Object Recognition & Search**

  * Capture an image of any object to retrieve search results with descriptions and relevant web links.
* **📖 Text Recognition & Translation**

  * Scan printed or handwritten text in multiple languages, detect the language, copy it, and then translate it using any language using any online translation software.
* **🌐 Live Word Translation**

  * Real-time translation of continuously updating text (e.g., news tickers) without needing to capture a photo.
* **💻 Code Recognition & Output**

  * Scan code snippets (e.g., from textbooks), and view results using integrated third-party compilers, text editors, and even IDEs!
* **🔊 Copy, Share, and Text-to-Speech**

  * Copy, Share, and listen to recognized text aloud.

## 📦 Tech Stack

* **Front-End:** Android XML Layouts
* **Back-End:** Java (Android SDK)
* **Machine Learning:** Firebase ML Kit (Text Recognition, Language Detection)
* **Search API:** SerpAPI (Google Search Results API)
* **IDE & Tools:** Android Studio, Thunder Client (API Testing), Firebase Console

## 📁 Project Architecture

* Modular pattern using Fragments for separation of concerns
* Activities:

  * `MainActivity`, `SplashScreenActivity`, `ImageSearchResultActivity`
* Core Fragments:

  * `ImageSearchFragment`, `WordSearchFragment`, `LiveWordSearchFragment`, `CodeSearchFragment`
* Utilities:

  * `SearchRVAdapter` for rendering search results
  * Custom UI using XML layouts with Unique Design

## 🧑‍💻 My Role and Contributions

This project was entirely **conceptualized, designed, developed, tested, and documented** by me:

* **Project Management:** Defined scope, timeline (Waterfall model), and executed change management.
* **Design:** Created all UI/UX layouts for accessibility and intuitiveness.
* **Development:** Wrote multiple parts of the codebase and took online assistance whereever needed
* **Testing:** Performed unit, integration, and system tests. Achieved 100% feature stability.
* **Presentation:** Project approved by college board in approximately 15 minutes with 100% positive feedback.

## 💻 Getting Started / Installation

To run Smart Lens locally:

1. **Download Repository**

   ```
   https://github.com/aamil-nakhuda/Smart-Lens.git
   ```

   Or click [Download ZIP](https://github.com/aamil-nakhuda/Smart-Lens/archive/refs/heads/master.zip)

2. **Open in Android Studio**

   * File > Open > Navigate to the unzipped folder

3. **Build APK / Run Emulator**

   * Use Android Emulator (API 23 or higher recommended)
   * Or connect a physical Android device (Marshmallow 6.0+)

## 🧪 Testing

The app was rigorously tested in the following ways:

* **Unit Testing:** Verified core logic components like camera capture and text recognition.
* **Integration Testing:** Ensured smooth interaction across fragments, activities, and APIs.
* **System Testing:** Evaluated full app flow on physical devices.

Tested scenarios include:

* Recognizing multilingual text from textbooks
* Scanning Java/C/C++ code from printed material
* Searching unknown objects using live camera feed

## 📄 Documentation

Comprehensive project documentation detailing background, objectives, analysis, design, testing methodology, and results is available in the full report:

- 🔗 ![Smart Lens Project Documentation (PDF)](docs/Smart-Lens-Documentation.pdf)

It includes:
- System Requirements
- Architecture Diagrams (DFD, UML)
- Module Breakdown
- Testing Cases & Results
- Future Enhancements

## 📸 Mockups / Screenshots

1. Splash Screen(Introductory Screen)
2. Image Search Screen
3. Taking Image from Camera Screen
4. Image Search Screen
5. Results Screen
6. Word Search Screen
7. Taking Image from Camera Screen
8. Word Search Screen
9. Results
10. Live Word Search Screen with Results
11 & 12. Code Search Screen With Results and various Code
13. Editors in different languages and famous Text Editors and IDEs
14. Some of the above functionality in Dynamic Dark Mode

![Screenshots](images/Smart Lens Screenshots.pdf)


> Screenshots demonstrate real use cases captured from the emulator.

## 🔒 License

This project is **not licensed** for use, copying, modification, or distribution.

All rights are reserved by the author.  
Unauthorized use of any part of this repository is strictly prohibited.

© 2025 Aamil Nakhuda. All rights reserved.

## 🌐 GitHub Repository

* 🔗 [Smart Lens on GitHub](https://github.com/aamil-nakhuda/Smart-Lens)


---
