package com.taylorcase.hearthstonescry.dagger.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.taylorcase.hearthstonescry.room.AppDatabase
import com.taylorcase.hearthstonescry.room.CardDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {

    private val database: AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE)
            // TODO: Get rid of this 'allowMainThreadQueries' --> Sloppy
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    internal fun provideAppDatabase(): AppDatabase = database

    @Singleton
    @Provides
    internal fun provideCardDao(): CardDao = database.cardDao()
}
