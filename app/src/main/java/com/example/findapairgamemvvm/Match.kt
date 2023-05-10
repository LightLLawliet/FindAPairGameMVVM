package com.example.findapairgamemvvm

import android.widget.TextView

data class Match(
    private val id: String,
    private val text: String,
    private val type: Type = Type.INITIAL
) {
    fun show(textView: TextView) {
        textView.text = this.text
    }

    fun compareType(type: Type): Boolean {
        return this.type == type
    }

    fun compareId(other: Match): Boolean {
        return this.id == other.id
    }

    fun compareContent(other: Match): Boolean {
        return compareId(other) && this.text == other.text
    }

    fun makeNew(type: Type): Match {
        return Match(id, text, type)
    }

    fun colorId() = when (type) {
        Type.INITIAL -> R.color.white
        Type.CORRECT -> R.color.teal_700
        Type.CHECK -> R.color.purple_200
    }
}