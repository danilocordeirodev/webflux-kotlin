package com.example.webfluxkotlin.repository

import com.example.webfluxkotlin.entity.Company
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CompanyRepository: CoroutineCrudRepository<Company, Long> {
    fun findByNameContaining(name: String): Flow<Company>
}