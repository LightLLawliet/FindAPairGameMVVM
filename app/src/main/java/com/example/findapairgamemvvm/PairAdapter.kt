package com.example.findapairgamemvvm

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class PairAdapter() : RecyclerView.Adapter<PairViewHolder>() {

    private val list = mutableListOf<Match>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PairViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PairViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = list.size
}

class PairViewHolder(view: View, private val itemCallback: ItemCallback) :
    RecyclerView.ViewHolder(view) {

    private val button = itemView.findViewById<Button>(R.id.button)

    @RequiresApi(Build.VERSION_CODES.M)
    fun bind(match: Match) {
        match.show(button)
        button.setOnClickListener {
            if (match.compareType(Type.INITIAL))
                itemCallback.itemClicked(match)
        }
        val color = match.colorId()
        button.setBackgroundColor(button.context.resources.getColor(color, null))
    }
}