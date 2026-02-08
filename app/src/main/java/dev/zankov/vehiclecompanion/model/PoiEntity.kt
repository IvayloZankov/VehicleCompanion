package dev.zankov.vehiclecompanion.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "poi_table")
data class PoiEntity(
    @PrimaryKey
    val id: Int,
    val loc: List<Double>,
    val name: String,
    val categoryName: String,
    val rating: Double,
    val url: String,
    val v320x320Url: String,
    val largeImageUrl: String
)

fun PoiEntity.toPoi(): Poi {
    return Poi(
        id = this.id,
        loc = this.loc,
        name = this.name,
        categoryName = this.categoryName,
        rating = this.rating,
        url = this.url,
        v320x320Url = this.v320x320Url,
        largeImageUrl = this.largeImageUrl
    )
}

fun Poi.toPoiEntity(): PoiEntity {
    return PoiEntity(
        id = this.id,
        loc = this.loc,
        name = this.name,
        categoryName = this.categoryName,
        rating = this.rating,
        url = this.url,
        v320x320Url = this.v320x320Url,
        largeImageUrl = this.largeImageUrl
    )
}