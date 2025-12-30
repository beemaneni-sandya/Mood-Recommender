# Mood Recommender Application

## Overview
Mood Recommender is a Spring Boot–based web application that recommends
quotes and music based on the user’s selected mood. The project demonstrates
backend API development combined with a lightweight frontend using static
web assets.

## Problem Statement
Users often look for quick, mood-based content such as motivational quotes
or music recommendations. This project provides a simple, interactive way
to deliver curated recommendations based on user mood selection.

## Solution
The application allows users to:
- Select a mood from the UI
- Receive mood-based quotes and music recommendations
- Interact with a backend service that processes requests and returns results

The system is designed to be simple, extensible, and easy to understand,
making it a good foundation for future enhancements.

## Architecture
- **Backend:** Spring Boot REST application
- **Controller Layer:** Handles mood-based requests
- **Service / Logic Layer:** Processes recommendation logic
- **Frontend:** Static HTML, CSS, and JavaScript served by Spring Boot
- **Data Storage:** In-memory / lightweight storage (H2 dependency included)

## Tech Stack
- **Language:** Java 17
- **Framework:** Spring Boot
- **Backend:** Spring Boot Web (REST APIs)
- **Frontend:** HTML, CSS, JavaScript (static resources)
- **Database:** H2 (runtime dependency, lightweight storage)
- **Build Tool:** Maven
- **Utilities:** Lombok
- **Version Control:** Git

## Key Features
- Mood-based recommendation logic
- REST API for handling user requests
- Simple web UI for interaction
- Clean separation between backend and frontend
- Easy to extend with additional moods or data sources

## How to Run
1. Clone the repository:
   ```bash
   git clone git@github.com:beemaneni-sandya/mood-recommender.git
2. Navigate to the project directory:
   ```bash
   cd mood-recommender
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
4. Open in browser:
   ```bash
   http://localhost:8080

**Future Enhancements**
- Database-backed recommendation storage
- Integration with external music or quote APIs
- User personalization
- Advanced recommendation logic
