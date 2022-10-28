package com.example.twofragmentcontainer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.twofragmentcontainer.FootballApplication
import com.example.twofragmentcontainer.FootballViewModel
import com.example.twofragmentcontainer.FootballViewModelFactory
import com.example.twofragmentcontainer.R
import com.example.twofragmentcontainer.databinding.FragmentAaBinding


class AaFragment : Fragment() {

    var binding: FragmentAaBinding? = null

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
        val bd = FragmentAaBinding.inflate(inflater, container, false)
        binding = bd
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.aaFragment = this

        viewModel.insertLeagues()

        viewModel.leagues.observe(viewLifecycleOwner) { leagues ->
            println(leagues)
            val adapter = LeagueAdapter(leagues) { code ->
                viewModel.changeLeague(code)
                viewModel.refreshTeams(code)
                findNavController().navigate(R.id.action_aaFragment_to_abFragment)
            }
            binding?.recyclerView?.adapter = adapter
        }
/*
        viewModel.changeLeague("PL")
        viewModel.refreshTeams("PL")

        viewModel.teams.observe(viewLifecycleOwner) { teams ->
            println(teams)
        }

        viewModel.changeTeam(64)
        viewModel.refreshPlayers("PL", 64)

        viewModel.players.observe(viewLifecycleOwner) { players ->
            println(players)
        }
        */

    }

    fun goToNextScreen() {
        print("aaaa1")
        findNavController().navigate(R.id.action_aaFragment_to_abFragment)
    }
}