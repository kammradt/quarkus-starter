package common.annotation

import javax.ws.rs.NameBinding
import javax.ws.rs.core.Response

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@NameBinding
annotation class Status(val value: Response.Status)