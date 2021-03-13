package com.example.fooseballtrackerdeluxe.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fooseballtrackerdeluxe.R
import com.example.fooseballtrackerdeluxe.databinding.AddGameDialogFragmentBinding
import com.example.fooseballtrackerdeluxe.databinding.LeaderboardFragmentBinding
import com.example.fooseballtrackerdeluxe.ui.adapter.PlayerRecyclerAdapter
import kotlinx.android.synthetic.main.leaderboard_fragment.*

class LeaderBoardFragment : Fragment() {

    companion object {
        fun newInstance() =
            LeaderBoardFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var playerAdapter: PlayerRecyclerAdapter
    lateinit var leaderBoardFragmentBinding: LeaderboardFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        leaderBoardFragmentBinding = LeaderboardFragmentBinding.inflate(layoutInflater)
        return leaderBoardFragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerAdapter = PlayerRecyclerAdapter()
        leaderBoardFragmentBinding.players.adapter = playerAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
      }

    override fun onResume() {
        super.onResume()
        viewModel.getLeaderboard().observe(this, Observer {
            playerAdapter.setData(it)
        })
    }
}
