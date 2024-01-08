package com.example.beercounter

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private var beerCount = 0
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("BeerTrackerPrefs", MODE_PRIVATE)
        beerCount = sharedPreferences.getInt("BeerCount", 0)

        val tvBeerCount: TextView = findViewById(R.id.tvBeerCount)
        val btnAddBeer: Button = findViewById(R.id.btnAddBeer)
        val btnReset: Button = findViewById(R.id.btnReset)

        tvBeerCount.text = beerCount.toString()

        btnAddBeer.setOnClickListener {
            beerCount++
            tvBeerCount.text = beerCount.toString()
            saveBeerCount()
        }

        btnReset.setOnClickListener {
            beerCount = 0
            tvBeerCount.text = beerCount.toString()
            saveBeerCount()
        }
    }

    private fun saveBeerCount() {
        with(sharedPreferences.edit()) {
            putInt("BeerCount", beerCount)
            apply()
        }
    }
}
