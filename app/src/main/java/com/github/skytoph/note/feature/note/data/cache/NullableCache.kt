package com.github.skytoph.note.feature.note.data.cache

import com.github.skytoph.note.feature.note.domain.cache.MutableCache

abstract class AbstractMutableCache<T>(protected var data: T) : MutableCache<T> {

    override fun cache(data: T) {
        this.data = data
    }

    override fun getCached(): T = data
}

abstract class NullableCache<T>(data: T? = null) : AbstractMutableCache<T?>(data) {

    override fun clear() {
        data = null
    }
}