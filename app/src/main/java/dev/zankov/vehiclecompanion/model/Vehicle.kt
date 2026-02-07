package dev.zankov.vehiclecompanion.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.zankov.vehiclecompanion.data.local.VEHICLES_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = VEHICLES_DATABASE_TABLE)
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String = "",
    var make: String = "",
    var model: String = "",
    var vin: String = "",
    var fuelType: String = ""
)
