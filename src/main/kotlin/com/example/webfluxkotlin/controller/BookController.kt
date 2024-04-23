package com.example.webfluxkotlin.controller

import com.example.webfluxkotlin.entity.Book
import com.example.webfluxkotlin.service.BookService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookService: BookService
) {
    @PostMapping
    suspend fun save(@RequestBody book: Book): Book {
        return bookService.save(book)
    }
}