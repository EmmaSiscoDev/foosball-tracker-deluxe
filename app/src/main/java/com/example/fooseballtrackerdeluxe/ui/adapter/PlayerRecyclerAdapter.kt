package com.example.fooseballtrackerdeluxe.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooseballtrackerdeluxe.R
import com.example.fooseballtrackerdeluxe.databinding.PlayerItemBinding
import com.example.fooseballtrackerdeluxe.model.Player
import com.example.fooseballtrackerdeluxe.ui.view.percent

class PlayerRecyclerAdapter: RecyclerView.Adapter<PlayerRecyclerAdapter.PlayerViewHolder>() {

    var playerList: List<Player>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val playerBinding = PlayerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PlayerViewHolder(playerBinding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        playerList?.get(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
       return playerList?.size ?: 0
    }
    fun setData(players: List<Player>){
        playerList = players
        notifyDataSetChanged()
    }

    class PlayerViewHolder(private val playerItemBinding: PlayerItemBinding): RecyclerView.ViewHolder(playerItemBinding.root) {

        fun bind(player: Player){
            playerItemBinding.playerName.text = player.name
            playerItemBinding.gamesPlayed.text = player.gamesPlayed.toString()
            playerItemBinding.numGamesWon.text = player.numGamesWon.toString()
            playerItemBinding.winLossRatio.text = player.winLossRatio.percent
            playerItemBinding.totalGoalsScored.text = player.totalGoalsScored.toString()
            playerItemBinding.totalGoalsAgainst.text = player.totalGoalsAgainst.toString()
            playerItemBinding.plusMinusGoals.text = player.plusMinusGoals.toString()
        }
    }
}