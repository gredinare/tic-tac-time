package com.gredi.tictactime

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

enum class Player(val digit: String) {
    X("X"),
    O("O");

    fun switch(): Player {
        return if (this == X) O else X
    }
}

class MainActivity : AppCompatActivity() {

    private lateinit var buttonA1: Button
    private lateinit var buttonA2: Button
    private lateinit var buttonA3: Button

    private lateinit var buttonB1: Button
    private lateinit var buttonB2: Button
    private lateinit var buttonB3: Button

    private lateinit var buttonC1: Button
    private lateinit var buttonC2: Button
    private lateinit var buttonC3: Button

    private var player: Player = Player.X

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setButtons()
    }

    private fun setButtons() {
        buttonA1 = findViewById(R.id.btn_a1)
        buttonA2 = findViewById(R.id.btn_a2)
        buttonA3 = findViewById(R.id.btn_a3)

        buttonB1 = findViewById(R.id.btn_b1)
        buttonB2 = findViewById(R.id.btn_b2)
        buttonB3 = findViewById(R.id.btn_b3)

        buttonC1 = findViewById(R.id.btn_c1)
        buttonC2 = findViewById(R.id.btn_c2)
        buttonC3 = findViewById(R.id.btn_c3)

        buttonA1.setOnClickListener { playerAction(it) }
        buttonA2.setOnClickListener { playerAction(it) }
        buttonA3.setOnClickListener { playerAction(it) }
        buttonB1.setOnClickListener { playerAction(it) }
        buttonB2.setOnClickListener { playerAction(it) }
        buttonB3.setOnClickListener { playerAction(it) }
        buttonC1.setOnClickListener { playerAction(it) }
        buttonC2.setOnClickListener { playerAction(it) }
        buttonC3.setOnClickListener { playerAction(it) }
    }

    private fun playerAction(view: View) {
        val button = view as Button
        button.text = player.digit
        button.isEnabled = false
        player = player.switch()

        val win = checkWinner()
         Log.d("GREDI-TEST", "$win")
    }

    private fun checkWinner(): Boolean =
        checkLabels(buttonA1, buttonA2, buttonA3) ||
        checkLabels(buttonB1, buttonB2, buttonB3) ||
        checkLabels(buttonC1, buttonC2, buttonC3) ||
        checkLabels(buttonA1, buttonB1, buttonC1) ||
        checkLabels(buttonA2, buttonB2, buttonC2) ||
        checkLabels(buttonA3, buttonB3, buttonC3) ||
        checkLabels(buttonA1, buttonB2, buttonC3) ||
        checkLabels(buttonA3, buttonB2, buttonC1)

    private fun checkLabels(vararg buttons: Button): Boolean {
        return buttons.all { it.text == "X" } || buttons.all { it.text == "O" }
    }
}