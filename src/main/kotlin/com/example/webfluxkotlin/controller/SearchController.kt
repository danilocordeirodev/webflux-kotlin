package com.example.webfluxkotlin.controller

import com.example.webfluxkotlin.dto.IdNameTypeResponse
import com.example.webfluxkotlin.dto.toIdNameTypeResponse
import com.example.webfluxkotlin.entity.Company
import com.example.webfluxkotlin.entity.User
import com.example.webfluxkotlin.service.CompanyService
import com.example.webfluxkotlin.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/search")
class SearchController(
    private val companyService: CompanyService,
    private val userService: UserService
) {

    @GetMapping
    suspend fun searchByName(
        @RequestParam("query") query: String
    ): Flow<IdNameTypeResponse> {
        val usersFlow = userService.findByNameLike(query)
            .map(User::toIdNameTypeResponse)
        val companiesFlow = companyService.findByName(query)
            .map(Company::toIdNameTypeResponse)

        return merge(usersFlow, companiesFlow)
    }
}