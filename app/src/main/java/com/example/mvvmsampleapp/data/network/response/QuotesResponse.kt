package com.example.mvvmsampleapp.data.network.response

import com.example.mvvmsampleapp.data.db.entity.Quotes

data class QuotesResponse( var isSuccessful:Boolean?,
                           var quotes: List<Quotes>?)