package com.example.mvvmsampleapp.ui.auth

import com.example.mvvmsampleapp.data.db.entity.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(errorMsg:String)
}