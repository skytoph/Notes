package com.github.skytoph.note.feature.note.domain.cache

interface MutableCache<T> {
    fun cache(data: T)
    fun getCached(): T
    fun clear()
}