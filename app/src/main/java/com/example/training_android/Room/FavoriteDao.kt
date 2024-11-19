package com.example.training_android.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    suspend fun insertFavorite(favorite: Favorite)


    @Query("DELETE FROM Favorite")
    suspend fun deleteAllFavorites()

    @Query("SELECT * FROM Favorite")
    suspend fun getFavorites(): List<Favorite>
}