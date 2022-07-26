package com.shreyashkore.modularuisample.cart

import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime


interface CartRepository {

    fun getObservableCart(): Flow<Cart>

    fun getCartCount(): Flow<Int>

    suspend fun addBookToCart(book: Book)

    suspend fun removeBook(book: Book)

    suspend fun clearAll()
}

object CartRepositoryMock : CartRepository {
    private val cartSource = MutableStateFlow(Cart(LocalDateTime.now(), SAMPLE_BOOKS))

    override fun getObservableCart(): Flow<Cart> = cartSource

    override fun getCartCount(): Flow<Int> = cartSource.map {
        it.books.size
    }

    override suspend fun addBookToCart(book: Book) {
        cartSource.value = cartSource.value.copy(books = cartSource.value.books + book)
    }

    override suspend fun removeBook(book: Book) {
        cartSource.value = cartSource.value.copy(books = cartSource.value.books - book)
    }

    override suspend fun clearAll() {
        cartSource.value = Cart(LocalDateTime.now(), emptyList())
    }

}

data class Cart(
    val createdAt: LocalDateTime,
    val books: List<Book>,
)
