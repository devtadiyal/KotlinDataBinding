package com.example.mvvmsampleapp.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val KEY_SAVED_AT = "defaultKey"
class PreferenceProvider(context: Context) {
   private val appContext = context.applicationContext
    private val preference : SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveTimeStamp(timestamp: String){
        preference.edit().putString(KEY_SAVED_AT,
        timestamp).apply()
    }

    fun getTimeStamp():String?{
        return preference.getString(KEY_SAVED_AT,null)
    }
}