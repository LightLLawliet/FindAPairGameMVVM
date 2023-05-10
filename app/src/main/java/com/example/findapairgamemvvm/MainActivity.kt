package com.example.findapairgamemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = MainViewModel()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = PairAdapter(object : ItemCallback {
            override fun itemClicked(match: Match) {
                viewModel.click(match)
            }
        })
        recyclerView.adapter = adapter

        viewModel.liveData.observe(this) {
            adapter.update(it)
        }

        viewModel.liveData.observe(this) {
            Toast.makeText(this, "finished", Toast.LENGTH_LONG).show()
        }
    }
}