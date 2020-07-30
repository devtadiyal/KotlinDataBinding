package com.example.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var email: String? = null

    //lateinit var email : String
    var password: String? = null
    //lateinit var password : String

    var authListener: AuthListener? = null
    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            //on failure
            authListener?.onFailure("email and password is invalid")
            return
        }
        //on success
        authListener?.onSuccess()
    }
}