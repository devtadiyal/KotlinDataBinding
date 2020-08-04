package com.example.mvvmsampleapp.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.data.db.entity.User
import com.example.mvvmsampleapp.databinding.ActivitySignupBinding
import com.example.mvvmsampleapp.utils.hide
import com.example.mvvmsampleapp.utils.show
import com.example.mvvmsampleapp.utils.snackbar
import com.example.mvvmsampleapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(),AuthListener,KodeinAware {
    //using kodein di getting instance of factory and pass to viewmodel
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //creating instance of viewmodel and databinding
        val binding: ActivitySignupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        //set viewmodel to binding
        binding.viewModel = viewModel
        //set authListener to viewmodel
        viewModel.authListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackbar("Signup Successfully ${user.name}")
    }

    override fun onFailure(errorMsg: String) {
        progress_bar.hide()
        toast(errorMsg)
    }
}
