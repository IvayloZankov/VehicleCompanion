package dev.zankov.vehiclecompanion.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.zankov.vehiclecompanion.data.local.dao.VehicleDao
import dev.zankov.vehiclecompanion.model.Vehicle

@Database(entities = [Vehicle::class], version = DATABASE_VERSION, exportSchema = false)
abstract class VehicleDatabase: RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}