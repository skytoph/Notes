package com.github.skytoph.note.feature.note.data.cache

interface MutableCache<T> {
    fun cache(data: T)
    fun getCached(): T
    fun clear()
}