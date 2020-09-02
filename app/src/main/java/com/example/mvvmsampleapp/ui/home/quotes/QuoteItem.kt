package com.example.mvvmsampleapp.ui.home.quotes

import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.data.db.entity.Quotes
import com.example.mvvmsampleapp.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(private val quote:Quotes):BindableItem<ItemQuoteBinding>() {
    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
            viewBinding.setQuote(quote)
    }

    override fun getLayout() = R.layout.item_quote

}