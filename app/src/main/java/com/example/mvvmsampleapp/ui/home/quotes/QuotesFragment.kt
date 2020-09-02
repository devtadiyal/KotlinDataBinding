package com.example.mvvmsampleapp.ui.home.quotes

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmsampleapp.BaseFragment
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.data.db.entity.Quotes
import com.example.mvvmsampleapp.databinding.QuoteFragmentBinding
import com.example.mvvmsampleapp.utils.Coroutines
import com.example.mvvmsampleapp.utils.hide
import com.example.mvvmsampleapp.utils.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.ViewHolder
import kotlinx.android.synthetic.main.quote_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : BaseFragment<QuoteFragmentBinding,QuotesViewModel>() , KodeinAware {


    override fun getViewModel() = QuotesViewModel::class.java
    override fun getFragmentView()= R.layout.quote_fragment
    private lateinit var viewModel: QuotesViewModel
    //using kodein di getting instance of factory and pass to viewmodel
    override val kodein by kodein()
    private val factory: QuoteViewModelFactory by instance()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       viewModel = ViewModelProvider(this,factory).get(QuotesViewModel::class.java)
        // TODO: Use the ViewModel
        bindUI()

    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        //getting the response from the viewmodel using coroutines
        val response = viewModel.quotes.await()
        progress_bar.hide()
        response.observe(viewLifecycleOwner, Observer {
            //context?.toast(it.size.toString())
            initRecyclerView(it.toQuoteItem())
        })

    }

    private fun initRecyclerView(toQuoteItem: List<QuoteItem>) {
        val quoteAdapter = GroupAdapter<ViewHolder<*>>().apply {
            addAll(toQuoteItem)
        }
        quotesList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = quoteAdapter
        }
    }

    private fun List<Quotes>.toQuoteItem():List<QuoteItem>{
        return this.map {
            QuoteItem(it)
        }
    }


}
