package com.example.webfluxkotlin.controller

import com.example.webfluxkotlin.dto.*
import com.example.webfluxkotlin.entity.Company
import com.example.webfluxkotlin.entity.User
import com.example.webfluxkotlin.service.CompanyService
import com.example.webfluxkotlin.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/api/companies")
class CompanyController(
    private val companyService: CompanyService,
    private val userService: UserService
) {
    @PostMapping
    suspend fun createUser(
        @RequestBody companyRequest: CompanyRequest
    ): CompanyResponse =
        companyService.saveCompany(
            company = companyRequest.toModel()
        )?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)

    @GetMapping
    suspend fun findAll(
        @RequestParam("name", required = false) name: String?
    ): Flow<CompanyResponse> {
        val companies = name?.let { companyService.findByName(it) } ?: companyService.findAll()
        return companies.map{
            company -> company.toResponse(
                users = findCompanyUsers(company)
            )
        }
    }

    @GetMapping("/{id}")
    suspend fun findById(
        @PathVariable id: Long
    ): CompanyResponse =
        companyService.findById(id)
            ?.let {
                company -> company.toResponse(
                    users = findCompanyUsers(company)
                )
            }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @DeleteMapping("/{id}")
    suspend fun deleteById(@PathVariable id: Long) {
        companyService.deleteById(id)
    }

    @PutMapping("/{id}")
    suspend fun updateById(
        @PathVariable id: Long,
        @RequestBody companyRequest: CompanyRequest
    ): CompanyResponse =
        companyService.updateById(id, companyRequest.toModel())
            .let {
                company -> company.toResponse(
                    users = findCompanyUsers(company)
                )
            }

    private suspend fun findCompanyUsers(company: Company): List<User> =
        userService.findByCompanyId(company.id!!).toList()
}