package com.techdoctorbd.roomdatabaseexample.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.techdoctorbd.roomdatabaseexample.model.User

@Dao

interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM USER_TABLE ORDER BY ID ASC")
    fun gelAllUser(): LiveData<List<User>>
}