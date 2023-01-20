package com.kazim.timerapp

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user ORDER BY id DESC  ")
    suspend fun getAllUser():List<User>

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM user")
    suspend fun allDelete()
}