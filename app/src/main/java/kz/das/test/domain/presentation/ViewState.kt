package kz.das.test.domain.presentation

sealed class ViewState<out T : Any> {
    data class Data<out T : Any>(val data: T) : ViewState<T>()
    object Loading : ViewState<Nothing>()
    object NetworkError : ViewState<Nothing>()
    data class Error(val error: String) : ViewState<Nothing>()
}
