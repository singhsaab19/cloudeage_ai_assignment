# Playwright-Based Automation Framework

## Project Description
This project is a Playwright-based automation framework written in Java. It is designed to test the Amazon printer purchase flow, including searching for a printer, adding it to the cart, and verifying the purchase process. The framework uses TestNG for test execution and includes features like video recording of test runs and CI/CD compatibility.

## Prerequisites
- Java 11
- Maven 3.6+
- A supported browser (e.g., Chromium)

## Setup Instructions
1. Extract the zip file to your desired location.
2. Navigate to the project directory:
   ```bash
   cd cloudeage_assignment
   ```
3. Install dependencies:
   ```bash
   mvn install
   ```
4. Configure the `config.properties` file located in `src/test/resources`:
   - Set the `baseUrl` to the target application URL.
   - Add other necessary configurations as required.

## Uploading to GitHub
1. Initialize a Git repository:
   ```bash
   git init
   ```
2. Add all files to the repository:
   ```bash
   git add .
   ```
3. Commit the changes:
   ```bash
   git commit -m "Initial commit"
   ```
4. Create a new repository on GitHub (e.g., `cloudeage_assignment`).
5. Add the remote origin:
   ```bash
   git remote add origin https://github.com/<your-username>/cloudeage_assignment.git
   ```
6. Push the code to GitHub:
   ```bash
   git push -u origin main
   ```

## Sharing the GitHub Link
Once the code is pushed, share the repository link (e.g., `https://github.com/<your-username>/cloudeage_assignment`) with others.

## Usage
1. Run the tests using Maven:
   ```bash
   mvn test
   ```
2. Test results will be available in the `target/surefire-reports` directory.
3. Video recordings of test runs are saved in the `videos` directory.

## Project Structure
- `src/main/java`
  - `base`: Contains the `BaseTest` class for Playwright setup and teardown.
  - `pages`: Page Object Model (POM) classes for different pages (e.g., `HomePage`, `CartPage`).
  - `utils`: Utility classes like `ConfigReader` and `WaitHelper`.
- `src/test/java`
  - `tests`: Test classes (e.g., `AmazonPrinterTest`).
- `src/test/resources`
  - `config.properties`: Configuration file for environment-specific settings.

## Features
- **Video Recording**: Test runs are recorded and saved in the `videos` directory.
- **Headless Mode**: Automatically toggles headless mode for CI environments.
- **TestNG Integration**: Supports parallel test execution and detailed reporting.

## CI/CD Compatibility
The framework is designed to run seamlessly in CI/CD pipelines. It detects the `GITHUB_ACTIONS` environment variable to adjust settings for headless mode and standard resolutions.

## Extending the Framework
To add new test cases:
1. Create a new test class in `src/test/java/tests`.
2. Use the existing Page Object Model classes or create new ones in `src/main/java/pages`.
3. Follow the TestNG conventions for annotations and assertions.
