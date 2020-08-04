package com.example.mvvmsampleapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_QUOTES_ID = 0

@Entity
data class Quotes(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
    var quote: String? = null,
    var author: String? = null,
    var thumbnail: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null
)
