**Releases:** [![Latest Release](https://img.shields.io/github/v/release/SnYco55/calculator-2026-group4?label=Latest%20Release)](https://github.com/SnYco55/calculator-2026-group4/releases/latest)

**CI / Build:** [![Build](https://github.com/SnYco55/calculator-2026-group4/actions/workflows/maven.yml/badge.svg)](https://github.com/SnYco55/calculator-2026-group4/actions/workflows/maven.yml)

**Code Quality:** [![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=SnYco55_calculator-2026-group4&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=SnYco55_calculator-2026-group4) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=SnYco55_calculator-2026-group4&metric=coverage)](https://sonarcloud.io/summary/new_code?id=SnYco55_calculator-2026-group4)

**Test Coverage:** [![Coverage](.github/badges/jacoco.svg)](.github/badges/coverage-summary.json) [![Branches](.github/badges/branches.svg)](.github/badges/coverage-summary.json)

# Calculator 2026 - Group 4

## About

This repository contains an advanced, multi-platform calculator developed as a collaborative group project for the software evolution course at the University of Mons, Belgium. 

Starting from a basic educational arithmetic expression evaluator, our team (Group 4) has significantly evolved the software by introducing robust architectural patterns, new mathematical capabilities, and modern user interfaces based on a client-server architecture.

## Features Implemented by Group 4

* **Web-Based Interface (React):** A modern, responsive graphical interface connected to the Java API backend.
* **Interactive CLI (REPL):** A built-in command-line loop that processes arithmetic expressions dynamically, complete with a `help` manual.
* **Extended Number Types:** Full support for evaluating expressions with Integers, Reals, Rationals, and Complex numbers.
* **Architectural Refactoring:** Implementation of the **Visitor Design Pattern** separating the logic of evaluation and formatting/printing from the structural expression definitions.

## Technologies & Tools Used

To comply with modern software evolution and CI/CD standards, we integrated the following stack:
* **Backend & Core Logic:** Java 25
* **Frontend:** React, Node.js, npm
* **Build & Dependencies:** Maven (Backend), npm (Frontend)
* **Testing:** JUnit 5 (Unit testing), Cucumber (BDD scenarios), JaCoCo (Coverage analysis)
* **CI/CD & Automation:** GitHub Actions
* **Quality & Security:** SonarCloud (Static analysis), GitHub Dependabot (Dependency updates), CodeQL (Security analysis)

---

## Installation and Testing Instructions

### 1. Backend (Java API & CLI)
* Ensure all dependencies are downloaded: `mvn clean install`
* Compile the source code: `mvn compile`
* Run the interactive CLI calculator: `mvn exec:java`
* Execute automated tests & BDD scenarios: `mvn test`

### 2. Frontend (React Web GUI)
* Navigate to the frontend folder: `cd frontend`
* Install dependencies: `npm install`
* Start the web interface:  (Runs on `https://calculator-2026-group4.vercel.app`)

## Test Coverage and JavaDoc Reporting

* Test coverage reports (JaCoCo) are generated in `target/site/jacoco` upon running `mvn test`.
* JavaDoc documentation is generated in `target/site/apidocs` when running `mvn package`.

## Developers & Contributors
* **Abdelouahad Alla**
* **Moulin Léo**
* **Sauvenière Nicolas**

*Original Base Code provided by Tom Mens & Gauvain Devillez for educational purposes.*

## Acknowledgements

* Software Engineering Lab, Faculty of Sciences, University of Mons, Belgium.
