package org.kammradt.account.domain

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import org.kammradt.account.dto.AccountResponse
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Account : PanacheEntity() {
    companion object : PanacheCompanion<Account, Long> {
        fun countByEmail(email: String): Long = find("email", email).count()
    }

    @Column(nullable = false, unique = true)
    lateinit var email: String

    @Column(nullable = false)
    lateinit var password: String

    @Column(nullable = false)
    lateinit var role: Role

    fun toResponse(): AccountResponse = AccountResponse(this.email, this.role)
}
