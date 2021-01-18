package com.example.fooseballtrackerdeluxe.model

data class Player (
    var name: String = "",
    var gamesPlayed: Int = 0,
    var numGamesWon: Int = 0,
    var winLossRatio: Double = 0.0,
    var totalGoalsScored: Int = 0,
    var totalGoalsAgainst: Int = 0,
    var plusMinusGoals: Int = 0
)