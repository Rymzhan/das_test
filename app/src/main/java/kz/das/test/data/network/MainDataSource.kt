package kz.das.test.data.network

import kz.das.test.data.model.MainResponse
import retrofit2.Response
import retrofit2.http.GET

interface MainDataSource {

    @GET("category/article")
    suspend fun getPhotoList(): Response<List<MainResponse>>

}