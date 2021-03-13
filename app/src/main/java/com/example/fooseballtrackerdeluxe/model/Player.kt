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

// Ideally this function would be stored in Google Functions, another Firebase Service that would listen to any db changes
// and generate a Player Profile in the db, which could be queried.
fun List<Game>.playerProfiles():List<Player>{
    val players = HashMap<String, Player>()
    this.forEach {
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
    return players.values.toList()
}