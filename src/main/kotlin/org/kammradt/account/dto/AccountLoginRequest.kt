package org.kammradt.account.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class AccountLoginRequest {
    @NotBlank
    @Email
    lateinit var email: String

    @NotBlank
    @Size(min = 8)
    lateinit var password: String
}
