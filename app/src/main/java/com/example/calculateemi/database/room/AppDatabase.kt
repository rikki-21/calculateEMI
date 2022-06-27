package com.example.calculateemi.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.calculateemi.database.entity.Loan
import com.example.calculateemi.LoanDao


@Database(entities = [Loan::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun loanDao():LoanDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context : Context): AppDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "jetpack"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
