package com.example.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsampleapp.data.db.entity.Quotes

@Dao
interface QuotesDao{
    //it will replace same id
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun saveAllQuotes(quotes:List<Quotes>)

    @Query("Select * from Quotes")
     fun getAllQuotes() : LiveData<List<Quotes>>
}