package org.kammradt.account

import org.eclipse.microprofile.jwt.JsonWebToken
import org.kammradt.account.domain.Account
import org.kammradt.account.dto.AccountCreationRequest
import org.kammradt.account.dto.AccountLoginRequest
import org.kammradt.account.dto.TokenResponse
import org.kammradt.account.exceptions.AccountNotFound
import org.kammradt.account.security.TokenService
import org.mindrot.jbcrypt.BCrypt
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid

@ApplicationScoped
class AccountService {
    @Inject
    lateinit var token: TokenService

    @Inject
    lateinit var validator: AccountValidator

    @Inject
    lateinit var jsonWebToken: JsonWebToken

    @Transactional
    fun create(@Valid accountCreation: AccountCreationRequest): Account {
        validator.validateCreation(accountCreation)
        val account = Account()
        updateAccount(account, accountCreation)
        return account
    }

    @Transactional
    fun update(id: Long, @Valid accountUpdate: AccountCreationRequest): Account {
        val account: Account = findById(id)
        validator.validateUpdate(accountUpdate)
        updateAccount(account, accountUpdate)
        return account
    }

    fun login(@Valid accountLogin: AccountLoginRequest): TokenResponse {
        val account: Account = findByEmail(accountLogin.email)
        if (!BCrypt.checkpw(accountLogin.password, account.password)) {
            throw AccountNotFound()
        }
        return token.generate(account)
    }

    @Transactional
    fun delete(id: Long) = findById(id).delete()

    fun findAll(): List<Account> {
        return Account.listAll()
    }

    fun findById(id: Long): Account = Account.findById(id) ?: throw AccountNotFound()

    private fun updateAccount(account: Account, accountCreation: AccountCreationRequest) {
        account.email = accountCreation.email
        account.password = BCrypt.hashpw(accountCreation.password, BCrypt.gensalt())
        account.role = accountCreation.role
        account.persist()
    }

    private fun findByEmail(email: String): Account =
            Account.find("email", email).firstResult() ?: throw AccountNotFound()
}