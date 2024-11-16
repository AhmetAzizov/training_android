package com.example.training_android

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite (
    val favoriteNumber: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)