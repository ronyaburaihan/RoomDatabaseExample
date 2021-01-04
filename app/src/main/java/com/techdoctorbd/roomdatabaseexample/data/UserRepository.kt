package com.techdoctorbd.roomdatabaseexample.data

import androidx.lifecycle.LiveData
import com.techdoctorbd.roomdatabaseexample.dao.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllUser: LiveData<List<User>> = userDao.gelAllUser()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}