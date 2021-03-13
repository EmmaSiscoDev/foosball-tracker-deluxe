package com.example.fooseballtrackerdeluxe.ui.main

import androidx.lifecycle.*
import com.example.fooseballtrackerdeluxe.model.Game
import com.example.fooseballtrackerdeluxe.model.Player
import com.example.fooseballtrackerdeluxe.model.playerProfiles
import com.example.fooseballtrackerdeluxe.service.FireStoreRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference

class MainViewModel : ViewModel() {
    private val leaderboard = MediatorLiveData<List<Player>>()
    private var isEditingGame: Boolean = false
    var selectedGame = MutableLiveData<Game>()

    fun getAllGames(): LiveData<List<Game>> {
        return FireStoreRepository.getAllGames()
    }

    fun deleteGame(game: Game): Task<Void> {
        return FireStoreRepository.deleteGame(game.id)
    }

    private fun updateGame(game: Game): Task<Void>  {
        return FireStoreRepository.updateGame(game)
    }

    private fun addGame(game: Game): Task<DocumentReference> {
        return FireStoreRepository.addGame(game)
    }

    private val gamesObserver = Observer<List<Game>> { games ->
        leaderboard.value = games.playerProfiles().sortedByDescending {
            it.winLossRatio
        }
    }
    fun getLeaderboard(): LiveData<List<Player>> {
        leaderboard.addSource(getAllGames(), gamesObserver)
        return leaderboard
    }
    fun editGame(gameSelected: Game, isEdit: Boolean){
        isEditingGame = isEdit
        selectedGame.value = gameSelected
    }

    fun submitGame(playerOneName: String, playerOneScore: Int, playerTwoName: String, playerTwoScore: Int, date: Timestamp) {
        val gameToSubmit = Game().apply {
            this.playerOneName = playerOneName
            this.playerOneScore = playerOneScore
            this.playerTwoName = playerTwoName
            this.playerTwoScore = playerTwoScore
            this.date = date
            //If we are editing the game then use the same id as the previous game
            selectedGame.value?.let {
                this.id = it.id
            }
        }
        if (isEditingGame){
            updateGame(gameToSubmit)
        }
        else {
            addGame(gameToSubmit)
        }
        selectedGame.value = null
    }
}
