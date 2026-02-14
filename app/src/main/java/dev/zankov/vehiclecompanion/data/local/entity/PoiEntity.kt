package dev.zankov.vehiclecompanion.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.zankov.vehiclecompanion.domain.model.Poi
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "poi_table")
data class PoiEntity(
    @PrimaryKey val id: Int,
    val loc: List<Double>,
    val name: String,
    val categoryName: String,
    val rating: Double,
    val url: String,
    val v320x320Url: String,
    val largeImageUrl: String
)

fun PoiEntity.toDomain(): Poi {
    return Poi(
        id = this.id,
        name = this.name,
        category = this.categoryName,
        location = this.loc,
        rating = this.rating,
        url = this.url,
        image = this.v320x320Url
    )
}
