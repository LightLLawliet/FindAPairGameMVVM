package com.example.findapairgamemvvm

import androidx.lifecycle.MutableLiveData

class MainViewModel {

    val finished = MutableLiveData<Boolean>()
    private val phrasesList = MatchList(object : FinishCallback {
        override fun allPairsFound() {
            finished.value = true
        }
    }).apply {
        add(Match("1", "dog"))
        add(Match("2", "cat"))
        add(Match("3", "rat"))
        add(Match("4", "racoon"))
        add(Match("1", "собака"))
        add(Match("2", "кошка"))
        add(Match("3", "крыса"))
        add(Match("4", "енот"))
        shuffle()
    }

    val liveData = MutableLiveData<List<Match>>()

    init {
        liveData.value = phrasesList
    }

    fun click(match: Match) {
        phrasesList.handle(match)
        liveData.value = phrasesList
    }
}