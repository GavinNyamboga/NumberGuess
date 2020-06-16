package com.dev.numberguess.model

//constructor
class GameUser(val firstName:String,
               val lastName:String,
               val birthday: String = "",
               val userName: String,
               val registrationNumber: Int,
               val userRank: Double = 0.0
               ) {

/*    val user = GameUser(
        firstName = "Gavin", lastName = "Gavnah", birthday = "2098-10-23", userName = "ggavnah",
        registrationNumber = 765, userRank = 0.5
    )*/
}