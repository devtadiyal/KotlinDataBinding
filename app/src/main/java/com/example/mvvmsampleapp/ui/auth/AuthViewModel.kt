package com.example.mvvmsampleapp.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repository.UserRepository
import com.example.mvvmsampleapp.utils.ApiException
import com.example.mvvmsampleapp.utils.Coroutines
import com.example.mvvmsampleapp.utils.NoInternetException

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    var email: String? = null
    var password: String? = null
    val mutableLiveData : MutableLiveData<String>? = null
    var authListener: AuthListener? = null
    var name: String? = null
    var passwordConfirm: String? = null

    fun getUserLoggedIn() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            //on failure
            authListener?.onFailure("email and password is invalid")
            return
        }
        //get login Response from repository and pass to UI with the help of authlistener
        //Again we creating UserRepository instance here bad practice , after we will change it using DI
        // (Tight Coupling AuthViewMOdel depends on UserRepository)
        Coroutines.main {
            try {
                // var loginResponse = UserRepository().userLogin(email!!, password!!)
                //constructor injection pass object through constructor
                var loginResponse = repository.userLogin(email!!, password!!)
                loginResponse.user?.let {
                    authListener?.onSuccess(it)
                    //saving user data into database
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(loginResponse.messgae!!)
            } catch (exception: ApiException) {
                authListener?.onFailure(exception.message!!)
            } catch (exception: NoInternetException) {
                authListener?.onFailure(exception.message!!)
            }
        }

    }

    fun onSignUpTextClicked(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLoginTextClick(view: View) {
        Intent(view.context, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view: View) {
        authListener?.onStarted()
        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Name is required")
        }
        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Email is require")
            return
        }
        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Password is require")
            return
        }
        if (password != passwordConfirm) {
            authListener?.onFailure("Password not same")
            return
        }
        Coroutines.main {
            try {
                // var loginResponse = UserRepository().userLogin(email!!, password!!)
                //constructor injection pass object through constructor
                var signupResponse = repository.userSignup(name!!, email!!, password!!)
                signupResponse.user?.let {
                    authListener?.onSuccess(it)
                    //saving user data into database
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(signupResponse.messgae!!)
            } catch (exception: ApiException) {
                authListener?.onFailure(exception.message!!)
            } catch (exception: NoInternetException) {
                authListener?.onFailure(exception.message!!)
            }
        }

    }
}