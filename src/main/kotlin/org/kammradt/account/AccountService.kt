package org.kammradt.account

import org.kammradt.account.domain.Account
import org.kammradt.account.domain.Role
import org.kammradt.account.dto.AccountCreationRequest
import org.kammradt.account.dto.AccountLoginRequest
import org.kammradt.account.dto.AccountUpdateRequest
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

    @Transactional
    fun create(@Valid accountCreation: AccountCreationRequest): Account {
        validator.validateCreation(accountCreation)
        return createAccount(accountCreation)
    }

    @Transactional
    fun update(id: Long, @Valid accountUpdate: AccountUpdateRequest): Account {
        val account = findById(id)
        validator.validateUpdate(accountUpdate)
        updateAccount(account, accountUpdate)
        return account
    }

    fun login(@Valid accountLogin: AccountLoginRequest): TokenResponse {
        val account = findByEmail(accountLogin.email)
        if (!BCrypt.checkpw(accountLogin.password, account.password)) throw AccountNotFound()
        return token.generate(account)
    }

    @Transactional
    fun delete(id: Long) = findById(id).delete()

    fun findAll(): List<Account> = Account.listAll()

    fun findById(id: Long): Account = Account.findById(id) ?: throw AccountNotFound()

    fun findByEmail(email: String): Account = Account.find("email", email).firstResult() ?: throw AccountNotFound()

    private fun updateAccount(account: Account, accountUpdate: AccountUpdateRequest) {
        account.email = accountUpdate.email
        account.password = BCrypt.hashpw(accountUpdate.password, BCrypt.gensalt())
        account.role = accountUpdate.role
        account.persist()
    }

    private fun createAccount(accountCreation: AccountCreationRequest): Account {
        val account = Account()
        account.email = accountCreation.email
        account.password = BCrypt.hashpw(accountCreation.password, BCrypt.gensalt())
        account.role = Role.USER
        account.persist()
        return account
    }

}