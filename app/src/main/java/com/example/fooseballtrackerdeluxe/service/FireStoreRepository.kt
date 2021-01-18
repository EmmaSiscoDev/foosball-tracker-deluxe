package com.example.fooseballtrackerdeluxe.service

import android.util.Log
import com.example.fooseballtrackerdeluxe.model.Game
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FireStoreRepository {

    val db = Firebase.firestore

    fun getAllGames(): CollectionReference {
        return db.collection("games")
    }

    fun deleteGame(documentId: String): Task<Void> {
        return db.collection("games")
            .document(documentId)
            .delete()
    }
    fun updateGame(game: Game): Task<Void> {
        return db.collection("games").document(game.id).set(game)
    }

    fun addGame(game: Game): Task<DocumentReference> {
         return db.collection("games").add(game)
    }
}