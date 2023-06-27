package kz.das.test.utils.extensions

import com.google.gson.Gson
import kz.das.test.data.model.APIError
import kz.das.test.domain.SimpleResult
import retrofit2.HttpException

fun <T> Throwable.simpleError(): SimpleResult<T> {
    this.printStackTrace()
    return when (this) {
        is HttpException -> {
            try {
                val message: APIError = Gson().fromJson(
                    this.response()?.errorBody()?.charStream(),
                    APIError::class.java
                )
                message.error()?.let {
                    SimpleResult.Error(it)
                } ?: SimpleResult.Error("")
            } catch (e: Exception) {
                SimpleResult.Error("Что-то пошло не так")
            }
        }
        is IllegalStateException -> {
            SimpleResult.Error("Что-то пошло не так")
        }
        else -> {
            SimpleResult.NetworkError
        }
    }
}