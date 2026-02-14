package dev.zankov.vehiclecompanion.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.zankov.vehiclecompanion.data.local.dao.PoiDao
import dev.zankov.vehiclecompanion.data.local.dao.VehicleDao
import dev.zankov.vehiclecompanion.data.local.entity.PoiEntity
import dev.zankov.vehiclecompanion.data.local.entity.VehicleEntity

const val DATABASE_VERSION = 4

@Database(entities = [VehicleEntity::class, PoiEntity::class], version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class VehicleDatabase: RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun poiDao(): PoiDao

    companion object {
        const val DATABASE_NAME = "vehicle_companion_database"
    }
}
