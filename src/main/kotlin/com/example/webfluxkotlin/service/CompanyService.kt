package com.example.webfluxkotlin.service

import com.example.webfluxkotlin.entity.Company
import com.example.webfluxkotlin.entity.User
import com.example.webfluxkotlin.repository.CompanyRepository
import com.example.webfluxkotlin.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {

    suspend fun saveCompany(company: Company): Company? =
        companyRepository.save(company)

    suspend fun findAll(): Flow<Company> =
        companyRepository.findAll()

    suspend fun findById(id: Long): Company? =
        companyRepository.findById(id)

    suspend fun deleteById(id: Long) {
        val foundCompany = companyRepository.findById(id)
        if(foundCompany == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        } else {
            companyRepository.deleteById(id)
        }
    }

    suspend fun updateById(id: Long, requestedCompany: Company): Company {
        val foundCompany = companyRepository.findById(id)
        return if(foundCompany == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        } else {
            companyRepository.save(requestedCompany.copy(id=foundCompany.id))
        }
    }

    suspend fun findByName(name: String): Flow<Company> =
        companyRepository.findByNameContaining(name)
}