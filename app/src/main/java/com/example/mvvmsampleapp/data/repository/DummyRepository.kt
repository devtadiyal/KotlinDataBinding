package com.example.mvvmsampleapp.data.repository

import com.example.mvvmsampleapp.data.db.AppDatabase
import com.example.mvvmsampleapp.data.network.SafeApiRequest
import com.example.mvvmsampleapp.data.network.response.DummyResponse
import net.simplifiedcoding.mvvmsampleapp.data.network.MyApi

class DummyRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    //add suspend before funtion becoz we are calling suspend funtion userLogin,
// suspend funtion can call inside suspend or coroutines block
    suspend fun passDummyData(name: String): DummyResponse {
        //now we creating instance of MyApi class here which is wrong due to this depency is present in UserRepository of MyApi
        //We will change it after in DI
        //return apiRequest { MyApi().userLogin(email, password) }
        //now passing MyApi class object using constructor to remove dependency
        return apiRequest { api.getDummyData(name) }
    }
}