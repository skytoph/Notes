package com.github.skytoph.note.feature.note.domain.order

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}