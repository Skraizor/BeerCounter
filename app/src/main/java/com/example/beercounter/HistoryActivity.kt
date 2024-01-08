package com.example.beercounter

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.ComponentActivity

class HistoryActivity : ComponentActivity() {
    private lateinit var listView: ListView
    private lateinit var backButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        listView = findViewById(R.id.lvHistory)
        backButton = findViewById(R.id.btnBack)
        sharedPreferences = getSharedPreferences("BeerTrackerPrefs", MODE_PRIVATE)

        backButton.setOnClickListener {
            finish()
        }

        loadHistory()
    }

    private fun loadHistory() {
        val history = sharedPreferences.getStringSet("BeerHistory", setOf()) ?: setOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, history.toList())
        listView.adapter = adapter
    }
}
