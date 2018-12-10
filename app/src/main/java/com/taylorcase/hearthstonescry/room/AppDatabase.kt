package com.taylorcase.hearthstonescry.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.taylorcase.hearthstonescry.model.Card

@Database(entities = arrayOf(Card::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object {
        const val DATABASE = "card-database"
    }
}
