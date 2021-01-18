package com.example.fooseballtrackerdeluxe.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.fooseballtrackerdeluxe.R
import com.example.fooseballtrackerdeluxe.model.Player
import com.example.fooseballtrackerdeluxe.ui.view.PlayerItem
import com.example.fooseballtrackerdeluxe.ui.view.percent
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PlayerAdapter(val context: Context): BaseAdapter() {

    var players: List<Player> = ArrayList()

    fun setData(data: List<Player>) {
        players = data
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val player = getItem(position) as Player
        if( view ==  null){
            view = PlayerItem(context)
        }

        view.apply {
            findViewById<TextView>(R.id.player_name).text = player.name
            findViewById<TextView>(R.id.games_played).text = player.gamesPlayed.toString()
            findViewById<TextView>(R.id.num_games_won).text = player.numGamesWon.toString()
            findViewById<TextView>(R.id.win_loss_ratio).text = player.winLossRatio.percent
            findViewById<TextView>(R.id.total_goals_scored).text = player.totalGoalsScored.toString()
            findViewById<TextView>(R.id.total_goals_against).text = player.totalGoalsAgainst.toString()
            findViewById<TextView>(R.id.plus_minus_goals).text = player.plusMinusGoals.toString()
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return players[position]
    }

    override fun getItemId(position: Int): Long {
        return players[position].hashCode().toLong()
    }

    override fun getCount(): Int {
        return players.size
    }
}
