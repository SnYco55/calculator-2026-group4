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

## Getting Started

### Build Instructions

To build the entire project:

```bash
mvn clean install
```

This command will:
* Download all dependencies
* Compile the Java source code
* Package the backend API
* Generate test coverage reports

### Testing Instructions

To run all tests (unit tests & BDD scenarios) and package the backend:

```bash
mvn package
```

This command will:
* Compile the Java source code
* Execute JUnit 5 unit tests
* Execute Cucumber BDD scenarios
* Run JaCoCo coverage analysis
* Package the backend API into a JAR file

Test coverage reports will be generated in `target/site/jacoco`.

---

## Usage Guide

### Option 1: Interactive CLI Calculator

To run the command-line interface with REPL (Read-Eval-Print Loop):

```bash
mvn compile exec:java -Dexec.mainClass="calculator.Main"
```

This starts an interactive calculator where you can:
* Enter arithmetic expressions
* Use the `help` command for detailed instructions

### Option 2: Java API Server

To start the REST API server (runs on port 8080):

```bash
mvn spring-boot:run
```

Or package and run the JAR:

```bash
mvn package
java -jar target/*.jar
```

The API will be available at `http://localhost:8080`.

### Option 3: Web Interface (React Frontend)

#### 3.1 Local Development Setup
* Navigate to the frontend folder: `cd frontend`
* Install dependencies: `npm install`
* Start the development server: `npm start`
* The frontend will run on `http://localhost:3000` (requires Java API running on port 8080)

#### 3.2 Online Deployed Version
* **Important:** Before accessing the web interface, wake up the API backend first:
  * Visit: `https://calculator-2026-group4.onrender.com/calculator/health` (takes 1-2 minutes)
* Once the API has started, access the web interface at: `https://calculator-2026-group4.vercel.app`

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