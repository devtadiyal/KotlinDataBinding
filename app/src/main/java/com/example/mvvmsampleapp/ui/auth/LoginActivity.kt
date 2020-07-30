package com.example.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.databinding.ActivityLoginBinding
import com.example.mvvmsampleapp.utils.toast

class MainActivity : AppCompatActivity() , AuthListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //creating instance of viewmodel and databinding
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        //set viewmodel to binding
        binding.viewModel = viewModel
        //set authListener to viewmodel
        viewModel.authListener = this
    }

    override fun onStarted() {
        toast("Login Started")
    }

    override fun onSuccess() {
        toast("Login Success")
    }

    override fun onFailure(errorMsg: String) {
        toast(errorMsg)
    }
}
