package common.handler

import common.dto.ErrorResponse
import javax.validation.ConstraintViolationException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ContraintValidationException : ExceptionMapper<ConstraintViolationException> {
    override fun toResponse(e: ConstraintViolationException): Response {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ErrorResponse.fromContraint(e))
                .build()
    }
}