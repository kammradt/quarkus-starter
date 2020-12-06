package common.annotation

import javax.ws.rs.core.Response

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Status(val value: Response.Status)