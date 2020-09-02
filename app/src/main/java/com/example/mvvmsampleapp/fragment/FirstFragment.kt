package com.example.mvvmsampleapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mvvmsampleapp.R


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_first, container, false)
        val click = v.findViewById<TextView>(R.id.textone)
        System.out.println("Fragment A onCreateView");

        click.setOnClickListener{
            val fm = fragmentManager

            fm!!.beginTransaction()
                .add(R.id.fragment,SecondFragment(),"FragmentTwo")
                .addToBackStack(null).commit()

        }
        return v
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        System.out.println("Fragment A onAttach");
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        System.out.println("Fragment A onCreate");

    }

    override fun onStart() {
        super.onStart()
        System.out.println("Fragment A onStart");

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        System.out.println("Fragment A onActivityCreated");

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        System.out.println("Fragment A onViewCreated");

    }

    override fun onPause() {
        super.onPause()
        System.out.println("Fragment A onPause");

    }

    override fun onStop() {
        super.onStop()
        System.out.println("Fragment A onStop");

    }

    override fun onDestroy() {
        super.onDestroy()
        System.out.println("Fragment A onDestroy");

    }

    override fun onDestroyView() {
        super.onDestroyView()
        System.out.println("Fragment A onDestroyView");

    }

    override fun onDetach() {
        super.onDetach()
        System.out.println("Fragment A onDetach");

    }
}
