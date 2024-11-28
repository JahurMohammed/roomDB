package com.example.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class Users(@PrimaryKey val events: List<String>)

