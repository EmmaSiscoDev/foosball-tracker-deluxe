package com.example.fooseballtrackerdeluxe.ui.main

import androidx.lifecycle.*
import com.example.fooseballtrackerdeluxe.model.Game
import com.example.fooseballtrackerdeluxe.model.Player
import com.example.fooseballtrackerdeluxe.service.FireStoreRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject

class MainViewModel : ViewModel() {
    val allGames = MutableLiveData<List<Game>>()
    val leaderboard = MediatorLiveData<List<Player>>()

    fun getAllGames(): LiveData<List<Game>> {
        FireStoreRepository.getAllGames()
            .orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { games, e ->
                if (e != null) {
                    allGames.value = null
                }
                allGames.value = unwrapGames(games)
            }
        return allGames
    }

    fun deleteGame(game: Game): Task<Void> {
        return FireStoreRepository.deleteGame(game.id)
    }

    fun updateGame(game: Game): Task<Void>  {
        return FireStoreRepository.updateGame(game)
    }

    fun addGame(game: Game): Task<DocumentReference> {
        return FireStoreRepository.addGame(game)
    }

    private val gamesObserver = Observer<List<Game>> { games ->
        leaderboard.value = unwrapPlayers(games).values.toList().sortedByDescending {
            it.winLossRatio
        }
    }
    fun getLeaderboard(): LiveData<List<Player>> {

        leaderboard.addSource(getAllGames(), gamesObserver)
        return leaderboard
    }


    private fun unwrapGames(games: QuerySnapshot?): ArrayList<Game> {
        val gameList = ArrayList<Game>()
        games?.documents?.forEach {
            val game = it.toObject<Game>()!!.apply { id = it.id }
            gameList.add(game)
        }
        return gameList
    }
    //Ideally this function would be stored in Google Functions that would listen to any db changes and generate a Player Profile in the db, which could be querried.
    private fun unwrapPlayers(games: List<Game>?): HashMap<String,Player> {
        val players = HashMap<String, Player>()
        games?.forEach {

            // Process Player One Stats
            if (!players.containsKey(it.playerOneName)) {
                val newPlayer = Player(name = it.playerOneName).apply {
                    gamesPlayed = 1
                    numGamesWon = if (it.playerOneScore > it.playerTwoScore) 1 else 0
                    winLossRatio = numGamesWon.toDouble() / gamesPlayed.toDouble()
                    totalGoalsScored = it.playerOneScore
                    totalGoalsAgainst = it.playerTwoScore
                    plusMinusGoals = totalGoalsScored - totalGoalsAgainst
                }
                players[it.playerOneName] = newPlayer
            }

            else {

                val tempPlayer = players[it.playerOneName]!!.copy().apply {
                    gamesPlayed ++
                    if (it.playerOneScore > it.playerTwoScore) numGamesWon++
                    winLossRatio = numGamesWon.toDouble() / gamesPlayed.toDouble()
                    totalGoalsScored += it.playerOneScore
                    totalGoalsAgainst += it.playerTwoScore
                    plusMinusGoals = totalGoalsScored - totalGoalsAgainst

                }
                players[it.playerOneName] = tempPlayer

            }

            // Process Player Two Stats
            if (!players.containsKey(it.playerTwoName)) {
                val newPlayer = Player(name = it.playerTwoName).apply {
                    gamesPlayed = 1
                    numGamesWon = if (it.playerTwoScore > it.playerOneScore) 1 else 0
                    winLossRatio = numGamesWon.toDouble() / gamesPlayed.toDouble()
                    totalGoalsScored = it.playerTwoScore
                    totalGoalsAgainst = it.playerOneScore
                    plusMinusGoals = totalGoalsScored - totalGoalsAgainst
                }
                players[it.playerTwoName] = newPlayer
            }
            else {
                val tempPlayer = players[it.playerTwoName]!!.copy().apply {
                    gamesPlayed ++
                    if (it.playerTwoScore > it.playerOneScore) numGamesWon++
                    winLossRatio = numGamesWon.toDouble() / gamesPlayed.toDouble()
                    totalGoalsScored += it.playerTwoScore
                    totalGoalsAgainst += it.playerOneScore
                    plusMinusGoals = totalGoalsScored - totalGoalsAgainst

                }
                players[it.playerTwoName] = tempPlayer
            }

        }
        return players
    }

}
