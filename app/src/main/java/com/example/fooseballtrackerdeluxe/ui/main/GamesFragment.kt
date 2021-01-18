package com.example.fooseballtrackerdeluxe.ui.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.AdapterView.AdapterContextMenuInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fooseballtrackerdeluxe.R
import com.example.fooseballtrackerdeluxe.model.Game
import com.example.fooseballtrackerdeluxe.ui.adapter.GameAdapter
import kotlinx.android.synthetic.main.games_fragment.*


class GamesFragment : Fragment() {

    companion object {
        fun newInstance() =
            GamesFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var gamesAdapter: GameAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.games_fragment, container, false)
        return rootView
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu.add(R.string.delete)
        menu.add(R.string.edit)
        super.onCreateContextMenu(menu, v, menuInfo)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterContextMenuInfo

        when (item.title) {
            getString(R.string.delete) -> {
                AlertDialog.Builder(context)
                    .setTitle(R.string.are_you_sure)
                    .setPositiveButton("Delete") { dialog, which ->
                        viewModel.deleteGame(gamesAdapter.getItem(info.position - 1) as Game).addOnSuccessListener {
                            dialog.dismiss()
                        }
                    }
                    .setNegativeButton("Cancel") { dialog, which ->
                        dialog.dismiss()
                    }.show()
            }
            getString(R.string.edit) -> {
                AddGameDialogFragment().apply {
                    setData(gamesAdapter.getItem(info.position - 1) as Game, true)
                }.show(childFragmentManager, AddGameDialogFragment.TAG)

            }
        }

        return super.onContextItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            gamesAdapter = GameAdapter(it)
        }
        with(games){
            val gamesHeaderView = layoutInflater.inflate(R.layout.games_header_view, games,false)
            addHeaderView(gamesHeaderView)
            adapter = gamesAdapter
            registerForContextMenu(this)
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllGames().observe(this, Observer {
            gamesAdapter.setData(it)
        })
    }
}
