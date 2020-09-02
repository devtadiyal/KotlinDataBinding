package com.example.mvvmsampleapp.data.repository

import com.example.mvvmsampleapp.data.db.AppDatabase
import com.example.mvvmsampleapp.data.db.entity.User
import com.example.mvvmsampleapp.data.network.SafeApiRequest
import com.example.mvvmsampleapp.data.network.response.AuthResponse
import net.simplifiedcoding.mvvmsampleapp.data.network.MyApi

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    //add suspend before funtion becoz we are calling suspend funtion userLogin,
// suspend funtion can call inside suspend or coroutines block
    suspend fun userLogin(email: String, password: String): AuthResponse {
        //now we creating instance of MyApi class here which is wrong due to this depency is present in UserRepository of MyApi
        //We will change it after in DI
        //return apiRequest { MyApi().userLogin(email, password) }
        //now passing MyApi class object using constructor to remove dependency
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignup(name: String, email: String, password: String): AuthResponse {
        return apiRequest { api.userSignup(name, email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}
