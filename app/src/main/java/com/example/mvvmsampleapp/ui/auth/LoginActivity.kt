package com.example.mvvmsampleapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.data.db.entity.User
import com.example.mvvmsampleapp.databinding.ActivityLoginBinding
import com.example.mvvmsampleapp.ui.home.HomeActivity
import com.example.mvvmsampleapp.utils.hide
import com.example.mvvmsampleapp.utils.show
import com.example.mvvmsampleapp.utils.snackbar
import com.example.mvvmsampleapp.utils.toast
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), AuthListener, KodeinAware {
    //using kodein di getting instance of factory and pass to viewmodel
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //creating instance of viewmodel and databinding
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        //set viewmodel to binding
        binding.viewModel = viewModel
        //set authListener to viewmodel
        viewModel.authListener = this
        //call reactive programmming//rxJava method
        reactiveProgramming()
       // getObservableFromList(listOf<String>("Apple", "Orange", "Fig"))
       // interval()
        mayBe()

        viewModel.getUserLoggedIn().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackbar("Login Successfully ${user.name}")
    }

    override fun onFailure(errorMsg: String) {
        progress_bar.hide()
        toast(errorMsg)
    }

    //demo Reactive Programming
     fun reactiveProgramming() {
         Observable.just(1,2,3,4,5,6)
             .map({ input -> input*2 } )
             .filter({value -> value>6})
             .subscribe(
                 { value -> println("Received: $value") }, // onNext
                 { error -> println("Error: $error") },    // onError
                 { println("Completed!") }                 // onComplete
             )
     }
    fun getObservableFromList(myList: List<String>) =
        Observable.create<String> { emitter ->
            myList.forEach { kind ->
                if (kind == "") {
                    emitter.onError(Exception("There's no value to show"))
                }
                emitter.onNext(kind)
            }
            emitter.onComplete()
        }
    fun interval(){
        Observable.intervalRange(
            20L,
            5L,
            0L,
            1L,
            TimeUnit.SECONDS).subscribe{println("Result is $it")}
    }
    fun mayBe(){
        Single.just("")
            .subscribe(
                { value -> println("Dev Received: $value") },
                { error -> println("Dev Error: $error") }
            )
    }


}
