package com.example.webfluxkotlin.repository

import com.example.webfluxkotlin.entity.User
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository : CoroutineCrudRepository<User, Long> {
    fun findByNameContaining(name: String): Flow<User>

    fun findByCompanyId(companyId: Long): Flow<User>

    @Query("select * from application.app_user where email = :email")
    fun byEmail(email: String): Flow<User>
}