package com.example.fooseballtrackerdeluxe.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fooseballtrackerdeluxe.R
import com.example.fooseballtrackerdeluxe.ui.adapter.GameAdapter
import com.example.fooseballtrackerdeluxe.ui.adapter.PlayerAdapter
import kotlinx.android.synthetic.main.games_fragment.*
import kotlinx.android.synthetic.main.leaderboard_fragment.*

class LeaderBoardFragment : Fragment() {

    companion object {
        fun newInstance() =
            LeaderBoardFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.leaderboard_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            playerAdapter = PlayerAdapter(it)
        }
        val playersHeaderView = layoutInflater.inflate(R.layout.players_header_view, games,false)
        players.addHeaderView(playersHeaderView)
        players.adapter = playerAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
      }

    override fun onResume() {
        super.onResume()
        viewModel.getLeaderboard().observe(this, Observer {
            playerAdapter.setData(it)
        })
    }
}
