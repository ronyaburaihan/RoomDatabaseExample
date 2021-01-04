package com.techdoctorbd.roomdatabaseexample.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techdoctorbd.roomdatabaseexample.data.User

@Dao

interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM USER_TABLE ORDER BY ID ASC")
    fun gelAllUser(): LiveData<List<User>>
}