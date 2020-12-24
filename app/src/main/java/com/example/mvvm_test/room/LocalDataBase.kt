package com.example.mvvm_test.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [RecordEntity::class, AccountEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class LocalDataBase: RoomDatabase() {
    abstract fun accountDao(): AccountDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDataBase? = null
        private const val DATABASE_NAME = "local_db"

        fun getInstance(context: Context): LocalDataBase {
            return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDataBase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
            }
        }
    }
}