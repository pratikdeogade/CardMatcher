package com.pi.cardmatcher.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pi.cardmatcher.data.model.CardMatcherModel

@Database(entities = [CardMatcherModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getShadiCardMatcherDao(): CardMatcherDao
}