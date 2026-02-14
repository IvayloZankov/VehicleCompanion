package dev.zankov.vehiclecompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import dev.zankov.vehiclecompanion.data.local.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Query("SELECT * FROM vehicles_table ORDER BY id ASC")
    fun selectAllVehicles(): Flow<List<VehicleEntity>>

    @Query("SELECT * FROM vehicles_table WHERE id = :id")
    fun getVehicleById(id: Int): Flow<VehicleEntity?>

    @Insert(onConflict = REPLACE)
    fun addVehicles(vehicle: List<VehicleEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun insertVehicle(vehicle: VehicleEntity)

    @Delete
    suspend fun deleteVehicle(vehicle: VehicleEntity)

    @Query("DELETE FROM vehicles_table")
    fun deleteAllVehicles()
}