package dev.zankov.vehiclecompanion.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.zankov.vehiclecompanion.data.local.dao.PoiDao
import dev.zankov.vehiclecompanion.data.local.dao.VehicleDao
import dev.zankov.vehiclecompanion.model.PoiEntity
import dev.zankov.vehiclecompanion.model.Vehicle

@Database(entities = [Vehicle::class, PoiEntity::class], version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class VehicleDatabase: RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun poiDao(): PoiDao
}
