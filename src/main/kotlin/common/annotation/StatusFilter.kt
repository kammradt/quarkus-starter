package common.annotation

import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter
import javax.ws.rs.core.Response
import javax.ws.rs.ext.Provider

@Status
@Provider
class StatusFilter : ContainerResponseFilter {
    override fun filter(request: ContainerRequestContext, response: ContainerResponseContext) {
        when (response.status) {
            Response.Status.OK.statusCode -> {
                when (val status = response.entityAnnotations.find { it is Status }) {
                    is Status -> response.status = status.value.statusCode
                }
            }
        }
    }
}