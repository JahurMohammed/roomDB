package com.example.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(users: Users)

    @Query("Select * from user")
    fun gelAllUsers(): String

    @Update
    fun updateUser(users: Users)

    @Delete
    fun deleteUser(users: Users)

    @Query("DELETE FROM User")
    fun deleteAll()

}