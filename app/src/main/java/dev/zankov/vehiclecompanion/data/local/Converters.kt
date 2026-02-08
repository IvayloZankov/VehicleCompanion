package dev.zankov.vehiclecompanion.data.local

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromDoubleList(list: List<Double>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun toDoubleList(value: String): List<Double> {
        return value.split(',').map { it.toDouble() }
    }
}
