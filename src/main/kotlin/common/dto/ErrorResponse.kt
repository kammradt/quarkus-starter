package common.dto

import javax.validation.ConstraintViolationException
import javax.ws.rs.WebApplicationException

class ErrorResponse(val errors: List<Error>) {
    companion object {
        fun fromContraint(e: ConstraintViolationException): ErrorResponse {
            return ErrorResponse(e.constraintViolations.map { Error(it.message, it.propertyPath.last().toString()) })
        }

        fun fromWeb(e: WebApplicationException): ErrorResponse {
            return ErrorResponse(listOf(Error(e.message)))
        }
    }
}