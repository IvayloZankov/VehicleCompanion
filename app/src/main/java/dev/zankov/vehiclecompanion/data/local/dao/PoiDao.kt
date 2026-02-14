package dev.zankov.vehiclecompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.zankov.vehiclecompanion.data.local.entity.PoiEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PoiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pois: List<PoiEntity>)

    @Query("SELECT * FROM poi_table WHERE id = :id")
    fun getPoiById(id: Int): Flow<PoiEntity?>

    @Query("SELECT * FROM poi_table")
    suspend fun getAllPois(): List<PoiEntity>

    @Query("DELETE FROM poi_table")
    suspend fun clearAll()
}