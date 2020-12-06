package org.kammradt.account

import org.kammradt.account.domain.Account
import org.kammradt.account.dto.AccountCreationRequest
import org.kammradt.account.dto.AccountUpdateRequest
import org.kammradt.account.exceptions.AccountAlreadyExists
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.context.RequestScoped

@ApplicationScoped
class AccountValidator {
    fun validateCreation(request: AccountCreationRequest) {
        if (Account.countByEmail(request.email) > 0) {
            throw AccountAlreadyExists()
        }
    }

    fun validateUpdate(request: AccountUpdateRequest) {
        if (Account.countByEmail(request.email) >= 1) {
            throw AccountAlreadyExists()
        }
    }
}