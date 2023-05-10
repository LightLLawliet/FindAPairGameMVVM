package com.example.findapairgamemvvm

class MainViewModel {

    private val phrasesList = MatchList().apply {
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

    fun click(match: Match) {
        val alreadyClicked = phrasesList.find { it.compareType(Type.CHECK) }
        if (alreadyClicked == null) {
            val item = phrasesList.find { it.compareContent(match) }!!
            phrasesList.replace(item, Type.CHECK)
        } else {
            if (alreadyClicked.compareId(match)) {
                phrasesList.replace(alreadyClicked, Type.CORRECT)
                val item = phrasesList.find { it.compareContent(match) }!!
                phrasesList.replace(item, Type.CORRECT)
            } else {
                phrasesList.replace(alreadyClicked, Type.INITIAL)
            }
        }
    }
}