package com.techdoctorbd.roomdatabaseexample.repository

import androidx.lifecycle.LiveData
import com.techdoctorbd.roomdatabaseexample.dao.UserDao
import com.techdoctorbd.roomdatabaseexample.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllUser: LiveData<List<User>> = userDao.gelAllUser()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser() {
        userDao.deleteAllUser()
    }
}