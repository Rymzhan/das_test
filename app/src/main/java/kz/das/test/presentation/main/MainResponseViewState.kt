package kz.das.test.presentation.main

import kz.das.test.data.model.MainResponse

sealed class MainResponseViewState {

    data class OnImagesFetched(val photoList: List<MainResponse>) : MainResponseViewState()

}