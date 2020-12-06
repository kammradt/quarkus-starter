package org.kammradt.account

import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.kammradt.account.dto.AccountCreationRequest
import org.kammradt.account.dto.AccountLoginRequest
import org.kammradt.account.dto.AccountResponse
import org.kammradt.account.dto.TokenResponse

interface IAccountResource {

    @Tag(name = "Account creation", description = "Public endpoint to account creation")
    fun create(accountCreationRequest: AccountCreationRequest): AccountResponse

    @Tag(name = "Account creation", description = "Public endpoint to account creation")
    fun update(id: Long, account: AccountCreationRequest): AccountResponse

    @Tag(name = "Account creation", description = "Public endpoint to account creation")
    fun delete(id: Long)

    @Tag(name = "Account creation", description = "Public endpoint to account creation")
    fun findById(id: Long): AccountResponse

    @Tag(name = "Account creation", description = "Public endpoint to account creation")
    fun findAll(): List<AccountResponse>

    @Tag(name = "Account creation", description = "Public endpoint to account creation")
    fun login(account: AccountLoginRequest): TokenResponse
}