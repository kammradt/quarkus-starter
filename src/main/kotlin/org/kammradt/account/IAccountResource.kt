package org.kammradt.account

import common.annotation.Status
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.kammradt.account.dto.AccountCreationRequest
import org.kammradt.account.dto.AccountLoginRequest
import org.kammradt.account.dto.AccountResponse
import org.kammradt.account.dto.TokenResponse
import javax.annotation.security.RolesAllowed
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("accounts")
interface IAccountResource {

    @Tag(name = "Account creation", description = "Public endpoint to account creation")
    @POST
    @Status(Response.Status.CREATED)
    fun create(accountCreationRequest: AccountCreationRequest): AccountResponse

    @PUT
    @Path("{id}")
    fun update(@PathParam("id") id: Long, account: AccountCreationRequest): AccountResponse

    @DELETE
    @Path("{id}")
    @RolesAllowed("ADMIN")
    fun delete(@PathParam("id") id: Long)

    @GET
    @Path("{id}")
    @RolesAllowed("ADMIN")
    fun findById(@PathParam("id") id: Long): AccountResponse

    @GET
    @RolesAllowed("ADMIN")
    fun findAll(): List<AccountResponse>

    @POST
    @Path("login")
    fun login(account: AccountLoginRequest): TokenResponse
}