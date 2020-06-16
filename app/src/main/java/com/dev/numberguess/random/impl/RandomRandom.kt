package com.dev.numberguess.random.impl

import com.dev.numberguess.random.RandomNumberGenerator
import java.util.*

class RandomRandom: RandomNumberGenerator {
    val rdm: Random = Random()
    override fun rnd(minInt: Int, maxInt: Int): Int {
        val span = maxInt - minInt + 1
        return minInt + rdm.nextInt(7)
    }
}