package com.example.webfluxkotlin.controller

import com.example.webfluxkotlin.dto.BookRequest
import com.example.webfluxkotlin.dto.BookResponse
import com.example.webfluxkotlin.dto.toBook
import com.example.webfluxkotlin.dto.toBookResponse
import com.example.webfluxkotlin.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    suspend fun save(@RequestBody book: BookRequest): BookResponse {
        return bookService.save(book.toBook()).toBookResponse()
    }

    @GetMapping
    suspend fun findAll(): List<BookResponse> {
        return bookService.findAll().map { it.toBookResponse() }
    }

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Long): BookResponse {
        return bookService.findById(id).toBookResponse()
    }
}