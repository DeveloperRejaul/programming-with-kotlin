# Kotlin Project

This repository contains a simple Kotlin project. Follow the instructions below to set up the environment, compile the code, and run the project.

### Create Kotlin Project Using Gradle (Command Line)

1. **Install prerequisites:**

   * Java (JDK 17+)
   * [Gradle](https://gradle.org/install/)
   * Kotlin compiler (optional, Gradle will handle it)

2. **Generate Gradle project:**

   ```bash
   gradle init
   ```

3. **Select options during prompts:**

   * Type of project: `2` - Application
   * Language: `3` - Kotlin
   * Build tool: `1` - Groovy (or Kotlin DSL)
   * Test framework: your choice (e.g., JUnit)
   * Project name: e.g., `MyKotlinApp`

4. **Project structure created:**

   ```
   MyKotlinApp/
   ├── build.gradle
   ├── settings.gradle
   └── src/
       └── main/
           └── kotlin/
               └── App.kt
   ```

5. **Run the app:**

   ```bash
   ./gradlew run
   ```
