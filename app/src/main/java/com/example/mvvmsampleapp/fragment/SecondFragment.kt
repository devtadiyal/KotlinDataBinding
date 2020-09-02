package com.example.mvvmsampleapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvvmsampleapp.R

/**
 * A simple [Fragment] subclass.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        System.out.println("Fragment B onCreateView");

        return inflater.inflate(R.layout.fragment_second, container, false)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        System.out.println("Fragment B onAttach");
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        System.out.println("Fragment B onCreate");

    }

    override fun onStart() {
        super.onStart()
        System.out.println("Fragment B onStart");

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        System.out.println("Fragment B onActivityCreated");

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        System.out.println("Fragment B onViewCreated");

    }

    override fun onPause() {
        super.onPause()
        System.out.println("Fragment B onPause");

    }

    override fun onStop() {
        super.onStop()
        System.out.println("Fragment B onStop");

    }

    override fun onDestroy() {
        super.onDestroy()
        System.out.println("Fragment B onDestroy");

    }

    override fun onDestroyView() {
        super.onDestroyView()
        System.out.println("Fragment B onDestroyView");

    }

    override fun onDetach() {
        super.onDetach()
        System.out.println("Fragment B onDetach");

    }
}
