package com.android.data.mappers

interface BaseMapper<in T, out I> {
    fun map(input: T): I
}