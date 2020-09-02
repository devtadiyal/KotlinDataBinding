package com.example.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repository.QuoteRepository
import com.example.mvvmsampleapp.utils.lazyDefered

class QuotesViewModel(repository: QuoteRepository) : ViewModel() {

    val quotes by lazyDefered {
        repository.getQuotes()
    }
}
