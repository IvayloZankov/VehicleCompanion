package dev.zankov.vehiclecompanion.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.zankov.vehiclecompanion.domain.model.Vehicle
import kotlinx.serialization.Serializable

const val VEHICLES_DATABASE_TABLE = "vehicles_table"

@Serializable
@Entity(tableName = VEHICLES_DATABASE_TABLE)
data class VehicleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String = "",
    var make: String = "",
    var model: String = "",
    var vin: String = "",
    var fuelType: String = ""
)

fun Vehicle.toEntity(): VehicleEntity {
    return VehicleEntity(
        id = id,
        name = name,
        make = make,
        model = model,
        vin = vin,
        fuelType = fuelType
    )
}

fun VehicleEntity.toDomain(): Vehicle {
    return Vehicle(
        id = this.id,
        name = this.name,
        make = this.make,
        model = this.model,
        vin = this.vin,
        fuelType = this.fuelType
    )
}
