package org.kammradt.account

import org.kammradt.account.domain.Account
import org.kammradt.account.dto.AccountCreationRequest
import org.kammradt.account.dto.AccountLoginRequest
import org.kammradt.account.dto.AccountResponse
import org.kammradt.account.dto.TokenResponse
import javax.inject.Inject

class AccountResource : IAccountResource {

    @Inject
    lateinit var service: AccountService

    override fun create(accountCreationRequest: AccountCreationRequest): AccountResponse = service.create(accountCreationRequest).toResponse()

    override fun update(id: Long, account: AccountCreationRequest): AccountResponse = service.update(id, account).toResponse()

    override fun delete(id: Long) = service.delete(id)

    override fun findById(id: Long): AccountResponse = service.findById(id).toResponse()

    override fun findAll(): List<AccountResponse> = service.findAll().map(Account::toResponse)

    override fun login(account: AccountLoginRequest): TokenResponse = service.login(account)

}