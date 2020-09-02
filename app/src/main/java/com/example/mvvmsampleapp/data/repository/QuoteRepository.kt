package com.example.mvvmsampleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmsampleapp.data.db.AppDatabase
import com.example.mvvmsampleapp.data.db.entity.Quotes
import com.example.mvvmsampleapp.data.network.SafeApiRequest
import com.example.mvvmsampleapp.data.preferences.PreferenceProvider
import com.example.mvvmsampleapp.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.simplifiedcoding.mvvmsampleapp.data.network.MyApi
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_TIME_STAMP = 6
class QuoteRepository(
    private val api: MyApi,
    private val database: AppDatabase,
    private val preferenceProvider: PreferenceProvider):SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quotes>>()
    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }
    private fun saveQuotes(list:List<Quotes>){
        //saving quotes list in database using coroutines using background thread
        Coroutines.io {
            //save timestamp in sharedpreference
            preferenceProvider.saveTimeStamp(LocalDateTime.now().toString())
            //save response list into db
            database.getQuotesDao().saveAllQuotes(list)
        }
    }
//fetch quotes data from api
    private suspend fun fetchQuotes(){
    val getTime = preferenceProvider.getTimeStamp()
        if(getTime==null || isFetchNeeded(LocalDateTime.parse(getTime))){
            val response = apiRequest { api.getQuotes() }
            //pass this response into livedata
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(parse: LocalDateTime):Boolean{
        return ChronoUnit.HOURS.between(parse,LocalDateTime.now())> MINIMUM_TIME_STAMP
    }
    //to pass quotes response to viewmodel
    suspend fun getQuotes(): LiveData<List<Quotes>> {
        return withContext(Dispatchers.IO){
            fetchQuotes()
            database.getQuotesDao().getAllQuotes()
        }
    }
}