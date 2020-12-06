package org.kammradt.account.dto

import org.kammradt.account.domain.Role

data class AccountResponse(val email: String, val role: Role)
