# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [v1.2.0] - 2026-04-09
### Added
- **feat(web-gui):** Implement a robust React-based Frontend Calculator web application.
- **feat(api):** Connect the frontend to the Java CLI Evaluator backend through a secure API.
- **feat(security):** Integrate CodeQL dependency review action to fail on critical severities automatically via GitHub Actions.

### Changed
- **build(deps):** Update `react-scripts` to version 5.0.1 for better stability and bug fixes.
- **build(deps):** Update `nth-check` dependency to version 3.0.1 to resolve security vulnerabilities.
- **docs(readme):** Completely revamp the `README.md` to reflect Group 4 specific architecture and technologies (React, SonarQube, REPL, Java 25).

### Removed
- **chore:** Delete unnecessary `.vscode` directory from version control.

## [v1.1.0] - 2026-04-01
### Added
- **feat(cli):** Implement a fully interactive Read-Eval-Print Loop (REPL) for executing continuous calculations natively in the command line (Issues #10, #30).
- **feat(cli):** Add a built-in interactive help function within the REPL.
- **feat(types):** Introduce comprehensive new number types mapping to our updated architecture: `MyInteger`, `MyReal`, `MyRational`, and `MyComplex` (Issue #17).
- **test(types):** Add exhaustive testing for special calculation outcomes within `MyReal`, identifying edge cases like `NaN`, `+Inf`, and `-Inf`.

### Changed
- **refactor(visitor):** Transform the expression evaluation and printing structure to fully adhere to the **Visitor Design Pattern** for optimal separation of concerns and maintainability (Issue #36).
- **test(sonar):** Increase global test coverage and optimize code to achieve and maintain an 'A' maintainability rating on SonarQube.

## [v1.0.0] - 2026-02-01
### Added
- **feat(core):** Initial base structure cloned from University of Mons Professor Tom Mens & Gauvain Devillez.
- **feat(core):** Base arithmetic logic evaluation.
- **test(core):** Base setup for JUnit 5 and Cucumber BDD.
