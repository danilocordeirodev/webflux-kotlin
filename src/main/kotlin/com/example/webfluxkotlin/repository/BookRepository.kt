package com.example.webfluxkotlin.repository

import com.example.webfluxkotlin.entity.Book
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: CoroutineCrudRepository<Book, Long?> {
    @Query("SELECT * FROM application.books WHERE title = :title")
    suspend fun findByTitle(title: String): List<Book>
}