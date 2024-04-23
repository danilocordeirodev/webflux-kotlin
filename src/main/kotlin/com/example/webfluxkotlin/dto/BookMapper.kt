package com.example.webfluxkotlin.dto

fun BookRequest.toBook(): Book {
    return Book(
        title = this.title,
        status = this.status
    )
}

fun Book.toBookResponse(): BookResponse {
    return BookResponse(
        id = this.id!!,
        title = this.title,
        status = this.status
    )
}