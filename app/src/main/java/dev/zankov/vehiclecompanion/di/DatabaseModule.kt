package dev.zankov.vehiclecompanion.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.zankov.vehiclecompanion.data.local.VehicleDatabase
import dev.zankov.vehiclecompanion.data.local.VehicleDatabase.Companion.DATABASE_NAME
import dev.zankov.vehiclecompanion.data.local.dao.PoiDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        VehicleDatabase::class.java,
        name = DATABASE_NAME
    ).fallbackToDestructiveMigration(false).build()

    @Provides
    @Singleton
    fun providePoiDao(vehicleDatabase: VehicleDatabase): PoiDao {
        return vehicleDatabase.poiDao()
    }
}
