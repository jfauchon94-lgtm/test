# Projet Compose Multiplatform : test

Ce projet a été généré avec **Compose Multiplatform 1.12.1**, **Kotlin 2.4.21** et **Android Gradle Plugin 9.3.1**, en utilisant la **toute dernière structure de dossiers officielle de JetBrains** (architecture découplée AGP 9+).

---

## 📁 Architecture du projet

Le projet sépare strictement le module de code partagé (`shared`) des modules de lancement propres à chaque plateforme :

```text
test/
├── gradle/
│   ├── libs.versions.toml                     # Version Catalog centralisé
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties          # Gradle 9.7.0
├── shared/                                    # Module Bibliothèque KMP & Compose UI
│   ├── build.gradle.kts                       # Utilise com.android.kotlin.multiplatform.library
│   └── src/
│       ├── commonMain/                        # Composables UI & Logique commune
│       │   ├── composeResources/              # Ressources vectorielles, images, chaînes
│       │   └── kotlin/com/droid/test/App.kt   # Composant UI racine
│       ├── commonTest/                        # Tests unitaires multiplateformes
│       ├── androidMain/                       # Implémentations spécifiques Android
│       ├── jvmMain/                           # Implémentations Desktop JVM
│       ├── iosMain/                           # Entry point iOS (MainViewController)
│       └── webMain/                           # Implémentations Web (Wasm & JS)
├── androidApp/                                # Application Android exécutable (AGP 9+)
│   ├── build.gradle.kts                       # com.android.application
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── kotlin/com/droid/test/MainActivity.kt
│       └── res/                               # Icônes adaptatives, styles, chaînes
├── desktopApp/                                # Application Desktop JVM exécutable
│   ├── build.gradle.kts
│   └── src/main/kotlin/com/droid/test/main.kt
├── webApp/                                    # Application Web (Kotlin/Wasm & JS)
│   ├── build.gradle.kts
│   └── src/webMain/
│       ├── kotlin/com/droid/test/main.kt
│       └── resources/index.html
├── iosApp/                                    # Projet Xcode SwiftUI consommant SharedUI
│   ├── Configuration/Config.xcconfig
│   └── iosApp/
│       ├── iOSApp.swift
│       └── ContentView.swift
├── build.gradle.kts                           # Configuration racine
├── settings.gradle.kts                        # Déclaration des modules
└── gradle.properties
```

---

## 📦 Versions des bibliothèques (`gradle/libs.versions.toml`)

- **Compose Multiplatform** : `1.12.1`
- **Compose Material 3** : `1.12.0-alpha03`
- **Material 3 Adaptive** : `1.3.0-rc01`
- **Kotlin** : `2.4.21` (K2 Compiler)
- **Android Gradle Plugin (AGP)** : `9.3.1`
- **Gradle** : `9.7.0`
- **Lifecycle Multiplatform** : `2.11.0`
- **Navigation Multiplatform** : `2.10.0-beta01`
- **Kotlinx Coroutines** : `1.11.0`
- **Kotlinx DateTime** : `0.8.0`
- **Kotlinx Serialization** : `1.11.0`
- **AndroidX Activity Compose** : `1.13.0`
- **AndroidX Core KTX** : `1.19.0`
- **Android SDK** : `compileSdk = 36`, `targetSdk = 36`, `minSdk = 26`

---

## 🚀 Commandes d'exécution

### 📱 Android
```bash
./gradlew :androidApp:assembleDebug
```

### 💻 Desktop (JVM)
```bash
./gradlew :desktopApp:run
```

### 🌐 Web (Kotlin/Wasm & JS)
```bash
./gradlew :webApp:wasmJsBrowserDevelopmentRun
# ou version JavaScript :
./gradlew :webApp:jsBrowserDevelopmentRun
```

### 🧪 Tests communs
```bash
./gradlew :shared:allTests
```
