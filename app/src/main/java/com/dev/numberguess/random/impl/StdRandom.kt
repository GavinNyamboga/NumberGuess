package com.dev.numberguess.random.impl

import com.dev.numberguess.random.RandomNumberGenerator

//class implementing randomNumberGenerator interface
class StdRandom: RandomNumberGenerator {

    override fun rnd(minInt: Int, maxInt: Int): Int {
        val span = maxInt-minInt +1
        return minInt + Math.floor(Math.random()*span).toInt()
    }
}