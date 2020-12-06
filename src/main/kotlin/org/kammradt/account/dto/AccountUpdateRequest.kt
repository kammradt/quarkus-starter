package org.kammradt.account.dto

import org.kammradt.account.domain.Role
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class AccountUpdateRequest {
    @NotBlank
    @Email
    lateinit var email: String

    @NotBlank
    @Size(min = 8)
    lateinit var password: String

    @NotNull
    lateinit var role: Role
}
