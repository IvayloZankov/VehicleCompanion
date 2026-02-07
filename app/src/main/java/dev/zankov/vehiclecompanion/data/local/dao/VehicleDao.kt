package dev.zankov.vehiclecompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import dev.zankov.vehiclecompanion.model.Vehicle
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Query("SELECT * FROM vehicles_table ORDER BY id ASC")
    fun selectAllVehicles(): Flow<List<Vehicle>>

    @Insert(onConflict = REPLACE)
    suspend fun addVehicle(vehicle: List<Vehicle>)

    @Insert(onConflict = REPLACE)
    suspend fun updateVehicle(vehicle: Vehicle)

    @Query("DELETE FROM vehicles_table")
    suspend fun deleteAllVehicles()
}