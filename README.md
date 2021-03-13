# foosball-tracker-deluxe

This app was built with MVVM architecture, using Google Firebase Firestore Sqless DB, which includes offline capabilities as well as cloud syncing.
This purpose of this app is to keep track of scores in foosball within a small group. The Main Activity holds a viewpager with fragments for the leaderboard, and for a list of games in historical order. The user can add, edit or delete a game.
The data between fragments is shared by a viewModel.
