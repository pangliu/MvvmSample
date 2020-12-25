package com.example.mvvm_test.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


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
//                        .addMigrations(MIGRATION_1_2)
                        .build()
                    INSTANCE = instance
                    instance
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS 'Record' ('id' INTEGER, PRIMARY KEY('id') )")
            }
        }
    }
}