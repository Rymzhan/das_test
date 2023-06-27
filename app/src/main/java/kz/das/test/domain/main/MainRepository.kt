package kz.das.test.domain.main

import kz.das.test.data.model.MainResponse
import kz.das.test.domain.SimpleResult

interface MainRepository {

    suspend fun getPhotoList() : SimpleResult<List<MainResponse>>

}