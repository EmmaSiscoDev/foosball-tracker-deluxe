package com.example.fooseballtrackerdeluxe.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fooseballtrackerdeluxe.model.Game
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

// Firebase Firestore has offline capabilities and provides both a local cache and a cloud db sync with the same db reference
object FireStoreRepository {

    fun getAllGames(): LiveData<List<Game>> {
        val listOfGamesData = MutableLiveData<List<Game>>()
        Firebase.firestore.collection("games")
            .orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { games, e ->
                if (e != null) {
                    listOfGamesData.postValue(null)
                }
                listOfGamesData.postValue(unwrapGames(games))
            }
        return listOfGamesData
    }

    fun deleteGame(documentId: String): Task<Void> {
        return Firebase.firestore.collection("games")
            .document(documentId)
            .delete()
    }

    fun updateGame(game: Game): Task<Void> {
        return Firebase.firestore.collection("games")
            .document(game.id)
            .set(game)
    }

    fun addGame(game: Game): Task<DocumentReference> {
        return Firebase.firestore.collection("games")
            .add(game)
    }

    private fun unwrapGames(games: QuerySnapshot?): ArrayList<Game> {
        val gameList = ArrayList<Game>()
        games?.documents?.forEach {
            //The Game object id is set to the Document id in the db so we can update it later
            it.toObject<Game>()?.apply { id = it.id }?.let { game ->
                gameList.add(game)
            }
        }
        return gameList
    }
}