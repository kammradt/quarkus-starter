package org.kammradt.account

import common.annotation.Status
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.kammradt.account.domain.Account
import org.kammradt.account.dto.*
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

    @Tag(name = "Account creation", description = "Allow users to create an account")
    @POST
    @Status(Response.Status.CREATED)
    fun create(accountCreationRequest: AccountCreationRequest): AccountResponse =
            service.create(accountCreationRequest).toResponse()

    @Tag(name = "Login", description = "Allow accounts to get a JWT token")
    @POST
    @Path("login")
    fun login(account: AccountLoginRequest): TokenResponse = service.login(account)

    @Tag(name = "Update", description = "Allow ADMINS to update accounts")
    @PUT
    @Path("{id}")
    @RolesAllowed("ADMIN")
    fun update(@PathParam("id") id: Long, account: AccountUpdateRequest): AccountResponse =
            service.update(id, account).toResponse()

    @Tag(name = "Delete", description = "Allow ADMINS to delete an account")
    @DELETE
    @Path("{id}")
    @RolesAllowed("ADMIN")
    fun delete(@PathParam("id") id: Long) = service.delete(id)

    @Tag(name = "Find by ID", description = "Allow ADMINS to view an account")
    @GET
    @Path("{id}")
    @RolesAllowed("ADMIN")
    fun findById(@PathParam("id") id: Long): AccountResponse = service.findById(id).toResponse()

    @Tag(name = "Find all", description = "Allow ADMINS to view all accounts")
    @GET
    @RolesAllowed("ADMIN")
    fun findAll(): List<AccountResponse> = service.findAll().map(Account::toResponse)

}