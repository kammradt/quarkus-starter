package org.kammradt.account.exceptions

import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response

class AccountAlreadyExists : WebApplicationException("Account already exists", Response.Status.FORBIDDEN)