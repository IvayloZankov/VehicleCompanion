package dev.zankov.vehiclecompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import dev.zankov.vehiclecompanion.model.Vehicle
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Query("SELECT * FROM vehicles_table ORDER BY id ASC")
    fun selectAllVehicles(): Flow<List<Vehicle>>

    @Query("SELECT * FROM vehicles_table WHERE id = :id")
    fun getVehicleById(id: Int): Flow<Vehicle?>

    @Insert(onConflict = REPLACE)
    fun addVehicles(vehicle: List<Vehicle>)

    @Insert(onConflict = REPLACE)
    suspend fun insertVehicle(vehicle: Vehicle)

    @Delete
    suspend fun deleteVehicle(vehicle: Vehicle)

    @Query("DELETE FROM vehicles_table")
    fun deleteAllVehicles()
}