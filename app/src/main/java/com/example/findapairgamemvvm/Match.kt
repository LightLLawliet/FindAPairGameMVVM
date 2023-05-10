package com.example.findapairgamemvvm

data class Match(
    private val id: String,
    private val text: String,
    private val type: Type = Type.INITIAL
) {
}