package org.kammradt.account.exceptions

import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response

class AccountNotFound : WebApplicationException("Account not found", Response.Status.NOT_FOUND)