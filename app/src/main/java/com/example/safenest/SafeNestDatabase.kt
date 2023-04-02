package com.example.safenest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactModel::class], version = 1, exportSchema = false)
abstract class SafeNestDatabase : RoomDatabase() {

    abstract fun contactDao () : ContactDao

    companion object{

        @Volatile
        private var INSTANCE : SafeNestDatabase? = null

        fun getDatabase(context: Context) : SafeNestDatabase{

            if(INSTANCE == null){

                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,SafeNestDatabase::class.java,"SafeNestDB")
                        .build()
                }
            }

            return INSTANCE!!
        }
    }

}