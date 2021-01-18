package com.example.fooseballtrackerdeluxe.model

import com.google.firebase.Timestamp
import java.io.Serializable

data class Game (
    @Transient var id: String = "",
    var playerOneName: String = "",
    var playerTwoName: String = "",
    var playerOneScore: Int = 0,
    var playerTwoScore:Int = 0,
    var date: Timestamp = Timestamp.now()
): Serializable