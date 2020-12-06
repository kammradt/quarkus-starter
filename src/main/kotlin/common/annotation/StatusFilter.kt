package common.annotation

import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.core.Response
import javax.ws.rs.ext.Provider

@Provider
class StatusFilter {
    fun filter(ctx: ContainerResponseContext) {
        if (ctx.status == Response.Status.OK.statusCode) {
            for (annotation in ctx.entityAnnotations) {
                if (annotation is Status) {
                    ctx.status = annotation.value.statusCode
                    break
                }
            }
        }
    }
}