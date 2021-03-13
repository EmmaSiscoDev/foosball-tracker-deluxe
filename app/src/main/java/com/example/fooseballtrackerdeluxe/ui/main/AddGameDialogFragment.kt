package com.example.fooseballtrackerdeluxe.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.fooseballtrackerdeluxe.databinding.AddGameDialogFragmentBinding
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class AddGameDialogFragment : DialogFragment() {
    private lateinit var viewModel: MainViewModel
    lateinit var addEditGameBinding: AddGameDialogFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addEditGameBinding = AddGameDialogFragmentBinding.inflate(layoutInflater)
        return addEditGameBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addEditGameBinding.submitNewGame.setOnClickListener(submitButtonListener)
    }

    override fun onResume() {
        super.onResume()
        viewModel.selectedGame.observe(viewLifecycleOwner, { game ->
            game?.let {
                addEditGameBinding.playerOneEditText.setText(it.playerOneName)
                addEditGameBinding.playerTwoEditText.setText(it.playerTwoName)
                addEditGameBinding.playerOneScoreEditText.setText(it.playerOneScore.toString())
                addEditGameBinding.playerTwoScoreEditText.setText(it.playerTwoScore.toString())
                addEditGameBinding.enterDateEditText.setText(timeStampToDate(it.date))
            }
        })
    }

    private val submitButtonListener = View.OnClickListener {
        val playerOneName = addEditGameBinding.playerOneEditText.text.toString()
        val playerOneScore = addEditGameBinding.playerOneScoreEditText.text.toString().toInt()
        val playerTwoName = addEditGameBinding.playerTwoEditText.text.toString()
        val playerTwoScore = addEditGameBinding.playerTwoScoreEditText.text.toString().toInt()
        val date = formatToTimeStamp(addEditGameBinding.enterDateEditText.text.toString())
        viewModel.submitGame(playerOneName, playerOneScore, playerTwoName, playerTwoScore, date)
        dismiss()
    }

    companion object {
        val TAG = "AddGameDialogFragment"

        private fun formatToTimeStamp(dateString: String): Timestamp {
            return with(SimpleDateFormat("mm/dd/yy", Locale.getDefault())) {
                parse(dateString)
            }.let {
                Timestamp(it!!)
            }
        }

        private fun timeStampToDate(timestamp: Timestamp): String {
            return with(SimpleDateFormat("mm/dd/yy", Locale.getDefault())) {
                format(timestamp.toDate())
            }
        }
    }
}