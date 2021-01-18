package com.example.fooseballtrackerdeluxe.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.fooseballtrackerdeluxe.R
import com.example.fooseballtrackerdeluxe.model.Game
import com.example.fooseballtrackerdeluxe.ui.view.GameItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GameAdapter(val context: Context): BaseAdapter() {

    var games: List<Game> = ArrayList()

    fun setData(data: List<Game>) {
        games = data
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val game = getItem(position) as Game
        if( view ==  null){
            view = GameItem(context)
        }

        view.apply {
            findViewById<TextView>(R.id.player_one_name).text = game.playerOneName
            findViewById<TextView>(R.id.player_one_score).text = game.playerOneScore.toString()
            findViewById<TextView>(R.id.player_two_name).text = game.playerTwoName
            findViewById<TextView>(R.id.player_two_score).text = game.playerTwoScore.toString()

            findViewById<TextView>(R.id.date).text = with(SimpleDateFormat("mm/dd/yy", Locale.ENGLISH)){
                format(game.date.toDate())
            }
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return games[position]
    }

    override fun getItemId(position: Int): Long {
        return games[position].hashCode().toLong()
    }

    override fun getCount(): Int {
        return games.size
    }
}
