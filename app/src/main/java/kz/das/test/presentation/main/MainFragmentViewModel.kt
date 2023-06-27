package kz.das.test.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.das.test.data.model.MainResponse
import kz.das.test.domain.SimpleResult
import kz.das.test.domain.main.MainRepository
import kz.das.test.domain.presentation.ViewState

class MainFragmentViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<MainResponseViewState>>()
    val viewState: LiveData<ViewState<MainResponseViewState>> = _viewState

    init {
        retrieveImageList()
    }

    private fun retrieveImageList() {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            when (val response = mainRepository.getPhotoList()) {
                is SimpleResult.Error -> onError(response.errorMessage)
                is SimpleResult.NetworkError -> onNetworkError()
                is SimpleResult.Success -> onSuccess(response.data)
            }
        }
    }

    private fun onSuccess(data: List<MainResponse>) {
        _viewState.postValue(
            ViewState.Data(
                MainResponseViewState.OnImagesFetched(data)
            )
        )
    }

    private fun onError(errorMessage: String) = _viewState.postValue(ViewState.Error(errorMessage))

    private fun onNetworkError() =
        _viewState.postValue(ViewState.NetworkError)

}