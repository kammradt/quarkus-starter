package common.dto

data class Error(val message: String, val field: String) {
    constructor(message: String?) : this(message ?: "", "")
}