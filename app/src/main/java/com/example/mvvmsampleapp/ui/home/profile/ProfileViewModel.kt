package com.example.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repository.UserRepository

//so we are passing value in profileviewmodel so need factoryviewmodel to pass value
class ProfileViewModel(repository: UserRepository) : ViewModel() {
    val repository = repository.getUser()
}
