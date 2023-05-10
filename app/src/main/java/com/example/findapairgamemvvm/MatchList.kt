package com.example.findapairgamemvvm

class MatchList : ArrayList<Match>() {

    fun replace(item: Match, newType: Type) {
        val index = indexOf(item)
        this[index] = item.makeNew(Type.CHECK)
    }
}