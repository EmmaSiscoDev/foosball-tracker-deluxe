package com.example.fooseballtrackerdeluxe.ui.view

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.fooseballtrackerdeluxe.R

class PlayerItem(context: Context) : RelativeLayout(context) {
    init {
        val rootView = LinearLayout(context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT).apply {
                background = getRectangleBackground(context)
                orientation = LinearLayout.HORIZONTAL
            }
        }.also {
            addView(it)
        }

        TextView(context).apply {
            id = R.id.player_name
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }

        TextView(context).apply {
            id = R.id.games_played
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }

        TextView(context).apply {
            id = R.id.num_games_won
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }

        TextView(context).apply {
            id = R.id.win_loss_ratio
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }

        TextView(context).apply {
            id = R.id.total_goals_scored
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }

        TextView(context).apply {
            id = R.id.total_goals_against
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }
        TextView(context).apply {
            id = R.id.plus_minus_goals
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
            }
        }.also {
            rootView.addView(it)
        }
    }
}