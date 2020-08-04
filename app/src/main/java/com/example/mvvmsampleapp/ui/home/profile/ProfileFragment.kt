package com.example.mvvmsampleapp.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.databinding.ProfileFragmentBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware {
    //using kodein di getting instance of factory and pass to viewmodel
    override val kodein by kodein()
    private val factory: ProfileViewModelFactory by instance()
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val profileBinding: ProfileFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)

        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        profileBinding.viewmodel = viewModel
        profileBinding.lifecycleOwner = this
        return profileBinding.root
    }


}