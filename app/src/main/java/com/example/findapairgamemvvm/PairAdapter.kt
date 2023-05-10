package com.example.findapairgamemvvm

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class PairAdapter(private val callback: ItemCallback) : RecyclerView.Adapter<PairViewHolder>() {

    private val list = mutableListOf<Match>()

    fun update(newList: List<Match>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()//todo diffutilcallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PairViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.initial, parent, false)
        return PairViewHolder(view, callback)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: PairViewHolder, position: Int) =
        holder.bind(list[position])


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