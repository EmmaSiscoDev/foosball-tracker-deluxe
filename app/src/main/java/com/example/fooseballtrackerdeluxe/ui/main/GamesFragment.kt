package com.example.fooseballtrackerdeluxe.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fooseballtrackerdeluxe.R
import com.example.fooseballtrackerdeluxe.databinding.GamesFragmentBinding
import com.example.fooseballtrackerdeluxe.ui.adapter.GameRecyclerAdapter


class GamesFragment : Fragment() {

    companion object {
        fun newInstance() =
            GamesFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var gamesAdapter: GameRecyclerAdapter
    private lateinit var gameBinding: GamesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        gameBinding = GamesFragmentBinding.inflate(layoutInflater)
        return gameBinding.root
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when (item.title) {
            getString(R.string.delete) -> {
                //This is a really simple alert and I build it programmatically
                AlertDialog.Builder(context)
                    .setTitle(R.string.are_you_sure)
                    .setPositiveButton("Delete") { dialog, which ->
                        gamesAdapter.getItem(item.order)?.let {
                            viewModel.deleteGame(it).addOnSuccessListener {
                                dialog.dismiss()
                            }
                        }
                    }
                    .setNegativeButton("Cancel") { dialog, which ->
                        dialog.dismiss()
                    }.show()
            }
            getString(R.string.edit) -> {
                //This dialog is more complicated and needs it's own fragment
                gamesAdapter.getItem(item.order)?.let {
                    viewModel.editGame(it, true)
                    AddGameDialogFragment().show(childFragmentManager, AddGameDialogFragment.TAG)
                }
            }
        }

        return super.onContextItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            gamesAdapter = GameRecyclerAdapter()
            gameBinding.games.adapter = gamesAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllGames().observe(this, Observer {
            gamesAdapter.setData(it)
        })
    }
}
