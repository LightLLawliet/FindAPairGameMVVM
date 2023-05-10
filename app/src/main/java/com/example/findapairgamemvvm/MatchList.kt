package com.example.findapairgamemvvm

class MatchList(private val finishCallback: FinishCallback) : ArrayList<Match>() {

    private val stateListener = object : StateListener {
        override fun updateState(newState: State) {
            state = newState
        }
    }
    private var state: State = State.Empty(stateListener)

    fun replace(item: Match, newType: Type) {
        val index = indexOf(item)
        this[index] = item.makeNew(newType)
    }

    fun handle(match: Match) {
        state.handle(match, this)
    }

    fun checkFinished() {
        if (filter { it.compareType(Type.CORRECT) }
                .size == this.size) {
            finishCallback.allPairsFound()
        }
    }
}

sealed class State {

    abstract fun handle(match: Match, list: MatchList)

    class AlreadyClicked(private val stateListener: StateListener) : State() {
        override fun handle(match: Match, list: MatchList) {
            val alreadyClicked = list.find { it.compareType(Type.CHECK) }!!
            val state =
                if (alreadyClicked.compareId(match))
                    PairMatch(alreadyClicked, stateListener)
                else
                    NoMatch(alreadyClicked, stateListener)

            state.handle(match, list)
        }
    }

    class PairMatch(
        private val alreadyClicked: Match,
        private val stateListener: StateListener
    ) : State() {
        override fun handle(match: Match, list: MatchList) {
            list.replace(alreadyClicked, Type.CORRECT)
            val item = list.find { it.compareContent(match) }!!
            list.replace(item, Type.CORRECT)
            stateListener.updateState(Empty(stateListener))
            list.checkFinished()
        }
    }

    class NoMatch(
        private val alreadyClicked: Match,
        private val stateListener: StateListener
    ) : State() {
        override fun handle(match: Match, list: MatchList) {
            list.replace(alreadyClicked, Type.INITIAL)
            stateListener.updateState(Empty(stateListener))
        }
    }

    class Empty(private val stateListener: StateListener) : State() {
        override fun handle(match: Match, list: MatchList) {
            val item = list.find { it.compareContent(match) }!!
            list.replace(item, Type.CHECK)
            stateListener.updateState(AlreadyClicked(stateListener))
        }
    }
}

interface StateListener {

    fun updateState(newState: State)
}

interface FinishCallback {

    fun allPairsFound()
}