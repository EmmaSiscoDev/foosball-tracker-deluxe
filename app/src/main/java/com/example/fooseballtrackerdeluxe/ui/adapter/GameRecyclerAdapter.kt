package com.example.fooseballtrackerdeluxe.ui.adapter

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooseballtrackerdeluxe.R
import com.example.fooseballtrackerdeluxe.databinding.PlayerItemBinding
import com.example.fooseballtrackerdeluxe.model.Game
import com.example.fooseballtrackerdeluxe.model.Player
import com.example.fooseballtrackerdeluxe.ui.view.GameItem
import com.example.fooseballtrackerdeluxe.ui.view.percent
import java.text.SimpleDateFormat
import java.util.*

class GameRecyclerAdapter: RecyclerView.Adapter<GameRecyclerAdapter.GameViewHolder>() {

    var gameList: List<Game>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val gameBinding = GameItem(parent.context)
        return GameViewHolder(gameBinding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        gameList?.get(position)?.let {
            holder.bind(it, position)
        }
    }

    override fun getItemCount(): Int {
       return gameList?.size ?: 0
    }
    fun setData(games: List<Game>){
        gameList = games
        notifyDataSetChanged()
    }
    fun getItem(position: Int): Game? {
        gameList?.let {
            return it[position]
        }
        return null
    }

    class GameViewHolder(private val gameBinding: GameItem): RecyclerView.ViewHolder(gameBinding.rootView),
        View.OnCreateContextMenuListener {
        private var index: Int = 0
        fun bind(game: Game, index: Int){
            this.index = index
            gameBinding.rootView.setOnCreateContextMenuListener(this)
            gameBinding.playerOneName.text = game.playerOneName
            gameBinding.playerOneScore.text = game.playerOneScore.toString()
            gameBinding.playerTwoName.text = game.playerTwoName
            gameBinding.playerTwoScore.text = game.playerTwoScore.toString()
            gameBinding.gameDate.text = with(SimpleDateFormat("mm/dd/yy", Locale.getDefault())){
                format(game.date.toDate())
            }
        }

        override fun onCreateContextMenu(
            menu: ContextMenu,
            v: View,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu.add(0,v.id,index, R.string.delete)
            menu.add(0,v.id,index, R.string.edit)
        }
    }
}