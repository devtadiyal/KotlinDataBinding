package com.example.mvvmsampleapp.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentVieewModel : ViewModel(){
    private var randomNumber = MutableLiveData<String>()

    fun createRandomNumber() {
        randomNumber?.value = " Number is : ${(0..10).random()}"
        println("Number is : ${(0..10).random()}")
    }
    fun getRandomData(): MutableLiveData<String> {
        if (randomNumber == null) {
            randomNumber = MutableLiveData()
            createRandomNumber()
        }
        return randomNumber
    }
}