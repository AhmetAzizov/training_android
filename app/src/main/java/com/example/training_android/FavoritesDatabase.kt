package com.example.training_android

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Favorite::class],
    version = 1
)
abstract class FavoritesDatabase: RoomDatabase() {

    abstract val dao: FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoritesDatabase? = null

        fun getDatabase(context: Context): FavoritesDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoritesDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}