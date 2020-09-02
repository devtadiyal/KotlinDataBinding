package com.example.mvvmsampleapp.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.utils.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SPEECH_REQUEST_CODE = 0

public class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val input = findViewById<EditText>(R.id.input)
        val viewModel = ViewModelProvider(this).get(FragmentVieewModel::class.java)
        displaySpeechRecognizer()
        viewModel.createRandomNumber()
        val randomdata = viewModel.getRandomData()
        randomdata.observe(this, Observer {
            input.setText(randomdata.value)

        })

        loadFragment(FirstFragment())

        println("CoRoutines Start")
        GlobalScope.launch(Dispatchers.IO) {
            delay(1000)
            Launch()
            println("CoRoutines Hi Dev")
        }

    }

    // Create an intent that can start the Speech Recognizer activity
    private fun displaySpeechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
        }
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE)
    }

    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val spokenText: String? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->

                }.toString()
            toast(spokenText ?: "")            // Do something with spokenText
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    suspend fun Launch() {
        println("CoRoutines My Name is Dev Tadiyal")
    }

    fun loadFragment(frag: Fragment) {
        supportFragmentManager!!.beginTransaction().
            /*addToBackStack(null).*/
        add(R.id.fragment, frag, "").commit()
    }

}
