package com.example.mvvmsampleapp.data.network.response

import com.example.mvvmsampleapp.data.db.entity.User

data class AuthResponse (
    var isSuccessful:Boolean?,
    var messgae:String?,
    var user: User?
    )