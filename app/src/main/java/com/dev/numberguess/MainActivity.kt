package com.dev.numberguess

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View

import android.util.Log
import android.widget.Toast
import com.dev.numberguess.common.Constants
import com.dev.numberguess.model.GameUser
import com.dev.numberguess.random.RandomNumberGenerator
import com.dev.numberguess.random.impl.StdRandom
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var started = false
    private var number = 0
    private var tries = 0
    val rnd: RandomNumberGenerator = StdRandom()


    var gameUser = GameUser(
        firstName = "Gavin", lastName = "Gavnah", birthday = "2098-10-23", userName = "ggavnah",
        registrationNumber = 0, userRank = 0.0
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchSavedInstanceData(savedInstanceState)
        doGuess.isEnabled = started



        fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            putInstanceData(outState)
        }
    }


    fun start(view: View) {

        log("Game started")
        num.setText("")
        started = true
        doGuess.isEnabled = true
        //status.text = getString(R.string.guess_hint, 1,7)
        status.text = getString(R.string.guess_hint, Constants.LOWER_BOUND,
            Constants.UPPER_BOUND)
        //val span = Constants.UPPER_BOUND-Constants.LOWER_BOUND +1
       // number = Constants.LOWER_BOUND + floor(Math.random()*span).toInt()
        number = rnd.rnd(
            Constants.LOWER_BOUND,
            Constants.UPPER_BOUND)
        //number = 1 + floor(Math.random()*7).toInt()
        tries = 0
        val  r = Random()
        r.nextInt(7)

        //toast to display user
        val text="hello ${gameUser.firstName} ${gameUser.lastName} your rank is ${gameUser.userRank}"
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext,text,duration)
        toast.show()
    }
    fun guess(view: View) {
        if(num.text.toString() == "") return
        tries++
        log("Guessed ${num.text} (tries:${tries})")
        val g = num.text.toString().toInt()
        if(g < number) {
            status.setText(R.string.status_too_low)
            num.setText("")
        } else if(g > number){
            status.setText(R.string.status_too_high)
            num.setText("")
        } else {
            status.text = getString(R.string.status_hit,
                tries)
            started = false
            doGuess.isEnabled = false
        }
    }

    private  fun putInstanceData(outState: Bundle) {
        with(outState){
            putBoolean("started", started)
            putInt("number", number)
            putInt("tries", tries)
            putString("statusMsg", status.text.toString())
            putStringArrayList("logs",
                ArrayList(console.text.split("\n")))
        }

    }
    private fun fetchSavedInstanceData(
        savedInstanceState: Bundle?) {
        if (savedInstanceState != null)
            with(savedInstanceState) {
                started = getBoolean("started")
                number = getInt("number")
                tries = getInt("tries")
                status.text = getString("statusMsg")
                console.text = getStringArrayList("logs")!!.
                joinToString("\n")
            }
    }
    private fun log(msg:String) {
        Log.d("LOG", msg)
        console.log(msg)
    }



}





