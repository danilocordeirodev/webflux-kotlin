package com.example.webfluxkotlin.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "company", schema = "application")
data class Company(
    @Id val id: Long? = null,
    val name: String,
    val address: String
)
