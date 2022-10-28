package com.example.twofragmentcontainer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.twofragmentcontainer.FootballViewModel
import com.example.twofragmentcontainer.FootballViewModelFactory
import com.example.twofragmentcontainer.databinding.FragmentAaBinding
import com.example.twofragmentcontainer.databinding.FragmentBbBinding

class bbFragment : Fragment() {

    lateinit var binding: FragmentBbBinding

    val viewModel: FootballViewModel by activityViewModels() {
        FootballViewModelFactory(activity?.application!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBbBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}