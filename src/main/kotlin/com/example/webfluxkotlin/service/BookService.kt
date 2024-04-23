package com.example.webfluxkotlin.service

import com.example.webfluxkotlin.entity.Book
import com.example.webfluxkotlin.repository.BookRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    suspend fun save(book: Book): Book {
        return bookRepository.save(book)
    }

    suspend fun findAll(): List<Book> {
        return bookRepository.findAll().toList()
    }

    suspend fun findById(id: Long): Book {
        return bookRepository.findById(id)
            ?: throw Exception("Not found")
    }

    suspend fun findByTitle(title: String): List<Book> {
        return bookRepository.findByTitle(title)
    }

    suspend fun deleteById(id: Long) {
        return bookRepository.deleteById(id)
    }
}