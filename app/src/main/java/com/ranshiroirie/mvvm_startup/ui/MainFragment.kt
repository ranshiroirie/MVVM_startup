package com.ranshiroirie.mvvm_startup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ranshiroirie.mvvm_startup.MainViewModel
import com.ranshiroirie.mvvm_startup.R
import com.ranshiroirie.mvvm_startup.databinding.FragmentMainBinding

class MainFragment : Fragment(){
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels(
        ownerProducer = {requireActivity()},
        factoryProducer = {defaultViewModelProviderFactory}
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }
}