package dev.zankov.vehiclecompanion.data.repository

import dev.zankov.vehiclecompanion.domain.model.Vehicle

val fakeVehicles = listOf(
    Vehicle(
        id = 1,
        name = "My Car",
        make = "Toyota",
        model = "Camry",
        vin = "123456789",
        fuelType = "Gasoline"
    ),
    Vehicle(
        id = 2,
        name = "Work Van",
        make = "Ford",
        model = "Transit",
        vin = "987654321",
        fuelType = "Diesel"
    ),
    Vehicle(
        id = 3,
        name = "Motorcycle",
        make = "Honda",
        model = "CBR",
        vin = "ABC123DEF",
        fuelType = "Gasoline"
    )
)
