package com.example.fooseballtrackerdeluxe.ui.main

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.fooseballtrackerdeluxe.R
import com.example.fooseballtrackerdeluxe.model.Game
import com.example.fooseballtrackerdeluxe.ui.view.dp
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat


class AddGameDialogFragment : DialogFragment() {
    private var isEdit: Boolean = false
    private var game: Game? = null
    private lateinit var enterDateEditText: EditText
    private lateinit var playerTwoScoreEditText: EditText
    private lateinit var playerTwoNameEditText: EditText
    private lateinit var playerOneScoreEditText: EditText
    private lateinit var playerOneNameEditText: EditText
    private lateinit var viewModel: MainViewModel


    fun setData(game: Game, isEdit: Boolean) {
        this.game = game
        this.isEdit = isEdit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }

        //Player One
        TextView(context).apply {
            id = R.id.player_one_title
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            text = context.getString(R.string.enter_player_one)
        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                WRAP_CONTENT,
                WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 24.dp
                rightMargin = 24.dp
            }
        }.also {
            rootView.addView(it)
        }

        //Player One Enter Name
        playerOneNameEditText = EditText(context).apply {
            id = R.id.player_one_edit_text
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            hint = getString(R.string.player)
            game?.let {
                setText(it.playerOneName)
            }

        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 24.dp
                rightMargin = 24.dp
            }
        }.also {
            rootView.addView(it)
        }

        //Player One Score
        TextView(context).apply {
            id = R.id.player_one_score_title
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            text = context.getString(R.string.enter_player_one_score)


        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                WRAP_CONTENT,
                WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 24.dp
                rightMargin = 24.dp
            }
        }.also {
            rootView.addView(it)
        }

        //Player One Enter Score
        playerOneScoreEditText = EditText(context).apply {
            id = R.id.player_one_score_edit_text
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            inputType = InputType.TYPE_CLASS_NUMBER
            hint = getString(R.string.score)
            game?.let {
                setText(it.playerOneScore.toString())
            }

        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 24.dp
                rightMargin = 24.dp
            }
        }.also {
            rootView.addView(it)
        }
        //Player Two Name
        TextView(context).apply {
            id = R.id.player_two_title
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            text = context.getString(R.string.enter_player_two)
        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                WRAP_CONTENT,
                WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 8.dp
                leftMargin = 24.dp
                rightMargin = 24.dp
            }
        }.also {
            rootView.addView(it)
        }

        //Player Two Enter Name
        playerTwoNameEditText = EditText(context).apply {
            id = R.id.player_two_edit_text
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            hint = getString(R.string.player)
            game?.let {
                setText(it.playerTwoName)
            }

        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 24.dp
                rightMargin = 24.dp
            }
        }.also {
            rootView.addView(it)
        }

        // Player Two Score
        TextView(context).apply {
            id = R.id.player_two_score_title
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            text = context.getString(R.string.enter_player_two_score)

        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                WRAP_CONTENT,
                WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 24.dp
                rightMargin = 24.dp
            }
        }.also {
            rootView.addView(it)
        }
        //Player Two Enter Score
        playerTwoScoreEditText = EditText(context).apply {
            id = R.id.player_two_score_edit_text
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            inputType = InputType.TYPE_CLASS_NUMBER
            hint = getString(R.string.score)
            game?.let {
                setText(it.playerTwoScore.toString())
            }

        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 24.dp
                rightMargin = 24.dp
            }
        }.also {
            rootView.addView(it)
        }

        // Date
        TextView(context).apply {
            id = R.id.enter_date_title
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            text = context.getString(R.string.enter_date)


        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                WRAP_CONTENT,
                WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 24.dp
                rightMargin = 24.dp
            }
        }.also {
            rootView.addView(it)
        }

        //Enter Date
        enterDateEditText = EditText(context).apply {
            id = R.id.player_two_score_edit_text
            textSize = 18f
            setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            inputType = InputType.TYPE_CLASS_DATETIME
            hint = getString(R.string.date_format)
            game?.let {
                setText(timeStampToDate(it.date))
            }

        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 24.dp
                rightMargin = 24.dp
            }
        }.also {
            rootView.addView(it)
        }

        //Submit
        Button(context).apply {
            id = R.id.submit_new_game
            text = context.getString(R.string.submit)
            textSize = 24f
            setOnClickListener(submitButtonListener)

        }.apply {
            layoutParams = LinearLayout.LayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT
            ).apply {
                weight = 1f
                topMargin = 8.dp
                bottomMargin = 8.dp
                leftMargin = 24.dp
                rightMargin = 24.dp
            }
        }.also {
            rootView.addView(it)
        }

        return rootView
    }

    private val submitButtonListener = View.OnClickListener {
        val game = Game().apply {
            playerOneName = playerOneNameEditText.text.toString()
            playerOneScore = playerOneScoreEditText.text.toString().toInt()
            playerTwoName = playerTwoNameEditText.text.toString()
            playerTwoScore = playerTwoScoreEditText.text.toString().toInt()
            date = formatToTimeStamp(enterDateEditText.text.toString())
            game?.let { id = it.id }
        }
        if(isEdit){
            viewModel.updateGame(game)
                .addOnSuccessListener {
                dismiss()
            }
        }
        else {
            viewModel.addGame(game)
                .addOnSuccessListener {
                    dismiss()
                }
        }
    }

    private fun formatToTimeStamp(dateString: String): Timestamp {
        return with(SimpleDateFormat("mm/dd/yy")) {
            parse(dateString)
        }.let {
            Timestamp(it!!)
        }
    }

    private fun timeStampToDate(timestamp: Timestamp): String {

        return with(SimpleDateFormat("mm/dd/yy")) {
            format(timestamp.toDate())
        }
    }


    companion object {
        val TAG = "AddGameDialogFragment"
    }
}