# Vehicle Companion

Modern Android application designed to demonstrate **Clean Architecture** principles and current Android development best practices.

Beyond its functionality as a vehicle management and points-of-interest discovery tool, this project serves as a reference implementation for building scalable, testable, and maintainable Android apps using the latest Jetpack libraries.

## Architecture

This project follows **Clean Architecture** combined with the **MVVM** (Model-View-ViewModel) pattern. The codebase is divided into three distinct layers to ensure separation of concerns:

**Domain Layer**
*   Contains the **Business Logic** and **Use Cases**.
*   Defines **Repository Interfaces** and **Domain Models**.
*   Completely independent of the Android Framework and Data layers.

**Data Layer**
*   Implements the Repository interfaces defined in the Domain layer.
*   Manages data sources: **Room** (Local Persistence) and **Retrofit** (Remote API).
*   Handles data mapping: Converts **DTOs** (Network) and **Entities** (Database) into pure **Domain Models**.

**UI Layer**
*   Built entirely with **Jetpack Compose**.
*   **ViewModels** interact with Use Cases to expose UI State.
*   Follows **Unidirectional Data Flow (UDF)**.
## Features

### Garage (Local Data)
Manage your personal fleet of vehicles.
*   **CRUD Operations:** Add, Edit, and Delete vehicles.
*   **Offline First:** All data is persisted locally using Room.
*   **Data Mapping:** Demonstrates mapping between Domain models and Database Entities.

### Places (Remote Data)
Discover points of interest around you.
*   **Network Requests:** Fetches data from a REST API using Retrofit.
*   **Caching Strategy:** Fetches remote data and caches it locally in the database for offline access.
*   **Map Integration:** Visualizes locations using Google Maps SDK.

## Built With

- **UI:** Jetpack Compose
- **Dependency Injection:** Hilt
- **Database:** Room
- **Networking:** Retrofit
- **Image Loading:** Coil
- **Language:** Kotlin
- **Navigation:** Compose Navigation
