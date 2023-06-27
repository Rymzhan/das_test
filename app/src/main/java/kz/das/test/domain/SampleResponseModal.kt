package kz.das.test.domain

class SampleResponseModal<T>(
    private val success: Boolean = false,
    private val status: Boolean = false,
    val message: String = "",
    val data: T? = null,
    private val msgType: String? = null,
    val code: Int? = null
) {

    fun getSimpleResult(): SimpleResult<T> {
        return if (data != null || success || status) {
            SimpleResult.Success(data!!)
        } else if (msgType == "426") {
            SimpleResult.NetworkError
        } else {
            SimpleResult.Error(message)
        }
    }
}