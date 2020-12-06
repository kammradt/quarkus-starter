package org.kammradt.account

import common.annotation.Owner
import common.annotation.Status
import org.kammradt.account.domain.Account
import org.kammradt.account.dto.AccountCreationRequest
import org.kammradt.account.dto.AccountLoginRequest
import org.kammradt.account.dto.AccountResponse
import org.kammradt.account.dto.TokenResponse
import javax.annotation.security.RolesAllowed
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class AccountResource {

    @Inject
    lateinit var service: AccountService

    @POST
    @Status(Response.Status.CREATED)
    fun create(accountCreationRequest: AccountCreationRequest): AccountResponse =
            service.create(accountCreationRequest).toResponse()

    @PUT
    @Path("{id}")
    fun update(@PathParam("id") id: Long, account: AccountCreationRequest): AccountResponse =
            service.update(id, account).toResponse()

    @DELETE
    @Path("{id}")
    @RolesAllowed("ADMIN")
    fun delete(@PathParam("id") id: Long) = service.delete(id)

    @GET
    @Path("{id}")
    @RolesAllowed("ADMIN")
    fun findById(@PathParam("id") id: Long): AccountResponse = service.findById(id).toResponse()

    @GET
    @Owner
    @RolesAllowed("ADMIN")
    fun findAll(): List<AccountResponse> = service.findAll().map(Account::toResponse)

    @POST
    @Path("login")
    fun login(account: AccountLoginRequest): TokenResponse = service.login(account)

}