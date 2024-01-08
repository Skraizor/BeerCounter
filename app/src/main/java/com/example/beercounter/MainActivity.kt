package com.example.beercounter

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    private var beerCount = 0
    private lateinit var sharedPreferences: SharedPreferences
    private var historySet: MutableSet<String> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("BeerTrackerPrefs", MODE_PRIVATE)
        beerCount = sharedPreferences.getInt("BeerCount", 0)

        val tvBeerCount: TextView = findViewById(R.id.tvBeerCount)
        val btnAddBeer: Button = findViewById(R.id.btnAddBeer)
        val btnReset: Button = findViewById(R.id.btnReset)
        val btnSave: Button = findViewById(R.id.btnSave)
        val btnViewHistory: Button = findViewById(R.id.btnViewHistory)

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

        btnSave.setOnClickListener {
            saveBeerCount()
            addHistoryEntry()
            beerCount = 0
            tvBeerCount.text = beerCount.toString()
        }

        btnViewHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveBeerCount() {
        with(sharedPreferences.edit()) {
            putInt("BeerCount", beerCount)
            apply()
        }
    }

    private fun addHistoryEntry() {
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        historySet.add("$timestamp: $beerCount beers")

        with(sharedPreferences.edit()) {
            putStringSet("BeerHistory", historySet)
            apply()
        }
    }
}
