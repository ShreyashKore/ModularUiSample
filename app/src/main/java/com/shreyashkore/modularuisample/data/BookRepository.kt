package com.shreyashkore.modularuisample.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter

interface BookRepository {
    fun getObservableBooks(): Flow<List<Book>>

    suspend fun getBook(id: Long): Book
}

object BookRepositoryMock : BookRepository {

    private val books = MutableStateFlow(SAMPLE_BOOKS)

    override fun getObservableBooks(): Flow<List<Book>> = books

    override suspend fun getBook(id: Long): Book {
        return books.value.filter { it.id == id }[0]
    }
}