package org.kammradt.account.security

import org.eclipse.microprofile.jwt.JsonWebToken
import org.kammradt.account.AccountService
import org.kammradt.account.domain.Account
import org.kammradt.account.domain.Role
import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
class SecurityService {
    private lateinit var currentAccount: Account;

    @Inject
    lateinit var jsonWebToken: JsonWebToken

    @Inject
    lateinit var accountService: AccountService

    @Inject
    fun init() {
        accountService.findByEmail(jsonWebToken.name).also { currentAccount = it }
    }

    fun has(roles: List<Role>): Boolean {
        return roles.contains(currentAccount.role)
    }

}