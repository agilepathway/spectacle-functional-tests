# spectacle-functional-tests

[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-v1.4%20adopted-ff69b4.svg)](CODE_OF_CONDUCT.md)

## Functional tests for the [Spectacle Gauge plugin](https://github.com/getgauge/spectacle)

### Steps to get started
- [Install Gauge](https://docs.gauge.org/getting_started/installing-gauge.html)

- [Install language plugin](https://docs.gauge.org/plugin.html) by running<br>
  ```
  gauge install {language}
  ```

  ```
  gauge install java
  gauge install ruby
  gauge install dotnet
  gauge install python
  gauge install js
  ```

- Clone this repo.

- Executing specs

  ```
  ./gradlew clean spectacle{Language}FT # On Linux or Mac
  gradlew.bat clean spectacle{Language}FT # On Windows
  ```
  ```
  ./gradlew clean spectacleJavaFT      # For Windows - gradlew.bat clean spectacleJavaFT
  ./gradlew clean spectacleRubyFT      # For Windows - gradlew.bat clean spectacleRubyFT
  ./gradlew clean spectacleDotnetFT    # For Windows - gradlew.bat clean spectacleDotnetFT
  ./gradlew clean spectaclePythonFT    # For Windows - gradlew.bat clean spectaclePythonFT
  ./gradlew clean spectacleJsFT        # For Windows - gradlew.bat clean spectacleJsFT
  ```

This will also compile all the supporting code implementations and run your specs in parallel.

### Based on the [`getgauge/gauge-tests`](https://github.com/getgauge/gauge-tests) repository

This spectacle-functional-tests repository was created by using the 
[`getgauge/gauge-tests`](https://github.com/getgauge/gauge-tests) repository, which has the
functional tests for Gauge itself, as a 
[template](https://github.blog/2019-06-06-generate-new-repositories-with-repository-templates/).

The functional tests use Gauge itself to perform the tests, just as the `gauge-tests` repository
does.  This provides great end to end test coverage :-)
