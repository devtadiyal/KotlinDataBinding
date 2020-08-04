package com.example.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleapp.data.repository.UserRepository
//I created this ViewModelFactory because i want to send repository instance to ViewModel
//I can't send instance of viewmodel from loginActivity where we getting instance of viewmodel using ViewModelProvider
class ProfileViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}