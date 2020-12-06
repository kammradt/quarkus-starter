package common.annotation

import javax.ws.rs.NameBinding
import javax.ws.rs.core.Response

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@NameBinding
annotation class Status(val value: Response.Status = Response.Status.INTERNAL_SERVER_ERROR)