package common.handler

import common.dto.ErrorResponse
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class DefaultWebExceptionHandler : ExceptionMapper<WebApplicationException> {
    override fun toResponse(e: WebApplicationException): Response {
        return Response.status(e.response.status)
                .entity(ErrorResponse.fromWeb(e))
                .build()
    }
}
