package com.example.fooseballtrackerdeluxe

import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.fooseballtrackerdeluxe.ui.main.AddGameDialogFragment
import com.example.fooseballtrackerdeluxe.ui.main.GamesFragment
import com.example.fooseballtrackerdeluxe.ui.main.LeaderBoardFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        viewPager.adapter = ViewPagerAdapter(resources, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        setSupportActionBar(main_toolbar)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_game_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_game -> {
                AddGameDialogFragment().show(supportFragmentManager, AddGameDialogFragment.TAG)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}

class ViewPagerAdapter(val context: Resources, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {

        return when( position){
            0 -> {
                LeaderBoardFragment.newInstance()
            }
            1 -> {
                GamesFragment.newInstance()
            }
            else ->{
                LeaderBoardFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when( position){
            0 -> {
                context.getString(R.string.leader_board)
            }
            1 -> {
                context.getString(R.string.games)
            }
            else ->{
                context.getString(R.string.leader_board)
            }
        }
    }
}
