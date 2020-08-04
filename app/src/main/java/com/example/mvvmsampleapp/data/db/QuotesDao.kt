package com.example.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmsampleapp.data.db.entity.Quotes

@Dao
interface QuotesDao{
    @Insert
     fun saveAllQuotes(quotes:List<Quotes>)

    @Query("Select * from Quotes")
     fun getAllQuotes() : LiveData<List<Quotes>>
}