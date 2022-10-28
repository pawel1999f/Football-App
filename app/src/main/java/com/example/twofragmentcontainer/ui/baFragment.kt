package com.example.twofragmentcontainer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.twofragmentcontainer.FootballViewModel
import com.example.twofragmentcontainer.FootballViewModelFactory
import com.example.twofragmentcontainer.databinding.FragmentAbBinding
import com.example.twofragmentcontainer.databinding.FragmentBaBinding

//import com.example.twofragmentcontainer.databinding.FragmentAaBinding
//import com.example.twofragmentcontainer.databinding.FragmentBaBinding

class BaFragment : Fragment() {

    lateinit var binding: FragmentBaBinding

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
        val bd = FragmentBaBinding.inflate(inflater, container, false)
        binding = bd
        return bd.root
//        return FragmentBaBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.baFragment = this
        val slidingPane = binding.slidingPane
        val callb = FragmentOnBackPressedCallback(slidingPane)
        slidingPane.addPanelSlideListener(callb)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            callb
        )

        viewModel.teamId.observe(viewLifecycleOwner) { teamId ->
            viewModel.players.removeObservers(viewLifecycleOwner)
            viewModel.setPlayersLiveData(teamId)
            observeNewPlayers()
//            viewModel.setPlayer(null)
            binding.slidingPane.closePane()

//            val players = viewModel.getPlayers(teamId)
//            println("players" +  players)
//            if(players == null)
//                return@observe
//
//            val adapter = PlayersAdapter(players) { playerId ->
//                binding.slidingPane.openPane()
//            }
//            binding.recyclerView.adapter = adapter
        }
    }

    fun observeNewPlayers() {
        viewModel.players.observe(viewLifecycleOwner) { players ->
            println("players" +  players)
            val adapter = PlayersAdapter(players) { player ->
                viewModel.setPlayer(player)
                println("picked" + viewModel.pickedPlayer.value)
                binding.slidingPane.openPane()
            }
            binding.recyclerView.adapter = adapter
        }
    }

    fun goToNextScreen() {
        print("aaaa")
//        findNavController().navigate(R.id.action_baFragment_to_bbFragment)
        binding.slidingPane.apply {
            if(!isOpen)
                openPane()
            else
                closePane()
        }

    }
}

class FragmentOnBackPressedCallback(private val slidingPaneLayout: SlidingPaneLayout) : OnBackPressedCallback(
    slidingPaneLayout.isSlideable &&  slidingPaneLayout.isOpen),
    SlidingPaneLayout.PanelSlideListener{
    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) {
    }

    override fun onPanelOpened(panel: View) {
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        isEnabled = false
    }

//    init {
//        slidingPaneLayout.addPanelSlideListener(this)
//    }

}