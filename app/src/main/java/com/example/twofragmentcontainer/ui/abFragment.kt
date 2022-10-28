package com.example.twofragmentcontainer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.twofragmentcontainer.FootballViewModel
import com.example.twofragmentcontainer.FootballViewModelFactory
import com.example.twofragmentcontainer.R
import com.example.twofragmentcontainer.databinding.FragmentAbBinding

class AbFragment : Fragment() {

    lateinit var binding: FragmentAbBinding

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
        // Inflate the layout for this fragment
        val bd = FragmentAbBinding.inflate(inflater, container, false)
        binding = bd
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.abFragment = this

        viewModel.teams.observe(viewLifecycleOwner) { teams ->
            println(teams)
            println(viewModel.leagueCode)
            val adapter = TeamsAdapter(teams) { id ->
                viewModel.refreshPlayers(viewModel.leagueCode!!, id)
                viewModel.changeTeam(id)
                viewModel.setPlayer(null)
            }
            binding.recyclerView.adapter = adapter
        }
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_abFragment_to_acFragment)
    }
}