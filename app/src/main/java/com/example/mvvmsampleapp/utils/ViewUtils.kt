package com.example.mvvmsampleapp.utils

import android.content.Context
import android.widget.Toast

    //extension funtion to create toast
    fun Context.toast(str:String) {
        Toast.makeText(this,str,Toast.LENGTH_LONG).show()

}