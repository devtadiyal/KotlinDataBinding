package com.example.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsampleapp.data.db.entity.CURRENT_USER_ID
import com.example.mvvmsampleapp.data.db.entity.User

//Data Access Object
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    @Query("Select * from user Where uid = $CURRENT_USER_ID")
    fun getUser() : LiveData<User>


}