package com.example.fooseballtrackerdeluxe.ui.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.fooseballtrackerdeluxe.R



// I don't usually do views programmatically.
// I find that they can be useful in certain situations
// This is included here as an example of my coding style for programmatic views in Kotlin.
class GameItem(context: Context) : RelativeLayout(context) {
    var playerOneName: TextView
    var playerOneScore: TextView
    var playerTwoName: TextView
    var playerTwoScore: TextView
    var gameDate: TextView

    init {
        val rootView = LinearLayout(context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply {
                background = getRectangleBackground(context)
                orientation = LinearLayout.HORIZONTAL
            }
        }.also {
            addView(it)
        }

        playerOneName = TextView(context).apply {
            id = R.id.player_one_name
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 2f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }

        playerOneScore = TextView(context).apply {
            id = R.id.player_one_score
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START

        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 2f
                topMargin = 8.dp
                bottomMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }

        playerTwoName = TextView(context).apply {
            id = R.id.player_two_name
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START

        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 2f
                topMargin = 8.dp
                bottomMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }

        playerTwoScore = TextView(context).apply {
            id = R.id.player_two_score
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START

        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 2f
                topMargin = 8.dp
                bottomMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }

        gameDate = TextView(context).apply {
            id = R.id.date
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START

        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 2f
                topMargin = 8.dp
                bottomMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }
    }
}