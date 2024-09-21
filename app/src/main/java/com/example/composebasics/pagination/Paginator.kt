package com.example.composebasics.pagination

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    suspend fun reset()
}