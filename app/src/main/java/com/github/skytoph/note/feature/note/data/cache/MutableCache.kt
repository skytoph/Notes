package com.github.skytoph.note.feature.note.data.cache

interface MutableCache<T> {
    fun cache(data: T)
    fun getCached(): T
    fun clear()

    abstract class Abstract<T>(protected var data: T) : MutableCache<T> {

        override fun cache(data: T) {
            this.data = data
        }

        override fun getCached(): T = data
    }

    abstract class Nullable<T>(data: T? = null) : Abstract<T?>(data) {

        override fun clear() {
            data = null
        }
    }
}