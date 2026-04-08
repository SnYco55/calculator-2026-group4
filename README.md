**Releases:** [![Latest Release](https://img.shields.io/github/v/release/SnYco55/calculator-2026-group4?label=Latest%20Release)](https://github.com/SnYco55/calculator-2026-group4/releases/latest)

**CI / Build:** [![Build](https://github.com/SnYco55/calculator-2026-group4/actions/workflows/maven.yml/badge.svg)](https://github.com/SnYco55/calculator-2026-group4/actions/workflows/maven.yml)

**Code Quality:** [![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=SnYco55_calculator-2026-group4&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=SnYco55_calculator-2026-group4) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=SnYco55_calculator-2026-group4&metric=coverage)](https://sonarcloud.io/summary/new_code?id=SnYco55_calculator-2026-group4)

**Test Coverage:** [![Coverage](.github/badges/jacoco.svg)](.github/badges/coverage-summary.json) [![Branches](.github/badges/branches.svg)](.github/badges/coverage-summary.json)

# Calculator 2026 - Group 4

## About

This repository contains an advanced, multi-platform calculator developed as a collaborative group project for the software evolution course at the University of Mons, Belgium. Starting from a basic arithmetic expression evaluator, our group has significantly extended the application by introducing robust architectural patterns, new mathematical capabilities, and modern user interfaces.

### Key Features Implemented

* **Extended Number Types:** Full support for interacting with Integers, Reals, Rationals, and Complex numbers.
* **Interactive CLI (REPL):** A built-in command-line loop that processes arithmetic expressions dynamically, complete with a `help` manual.
* **Web-Based Interface (React):** A modern, responsive graphical interface connected to an API backend to evaluate expressions securely.
* **Architectural Refactoring:** Implementation of the **Visitor Design Pattern** separating the logic of evaluation and printing from the expression structures.
* **Strict Quality Standards:** 100% CI/CD validation including JUnit/Cucumber tests, SonarQube analysis, and Sonar maintaining an 'A' maintainability rating.

---

### Unit testing and BDD

* All tests can be found in the `src/test` directory. They serve as executable documentation of the source code.
* The source code is accompanied by a set of **JUnit 5** unit tests and **Cucumber** BDD scenarios.
* The BDD scenarios are specified as `.feature` files in the `src/test/resources` directory.

### Prerequisites

* Java 25 (or backward compatible equivalent)
* Maven
* Node.js (for running the React frontend)

### Installation and Testing Instructions

#### 1. Backend (Java API & CLI)
* Ensure all dependencies are downloaded: `mvn clean install`
* Compile the source code: `mvn compile`
* Run the interactive CLI calculator: `mvn exec:java`
* Execute automated tests & BDD scenarios: `mvn test`

#### 2. Frontend (React Web GUI)
* Navigate to the frontend folder: `cd frontend`
* Install dependencies: `npm install`
* Start the web interface: `npm start` (Runs on `http://localhost:3000`)

### Test Coverage and JavaDoc Reporting

* Test coverage reports (JaCoCo) are generated in `target/site/jacoco` upon running `mvn test`.
* JavaDoc documentation is generated in `target/site/apidocs` when running `mvn package`.

## Developers & Contributors
* **Abdelouahad Alla**
* **Moulin Léo**
* **Sauvenière Nicolas**
* *Original Base Code by Tom Mens & Gauvain Devillez*

## Acknowledgements

* Software Engineering Lab, Faculty of Sciences, University of Mons, Belgium.
