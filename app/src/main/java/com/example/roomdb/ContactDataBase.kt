package com.example.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Contact::class], version = 1)
@TypeConverters(Convertors::class)
abstract class ContactDataBase : RoomDatabase() {
    abstract fun contactDao(): ContactDAO

    companion object {

        /*private val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN address TEXT NOT NULL DEFAULT(0)")
            }
        }*/

        @Volatile
        private var INSTANCE: ContactDataBase? = null

        fun getDatabase(context: Context): ContactDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDataBase::class.java,
                        "contactDB"
                    )/*.addMigrations(
                        migration_1_2
                    )*/.build()
                }
            }
            return INSTANCE!!
        }
    }
}