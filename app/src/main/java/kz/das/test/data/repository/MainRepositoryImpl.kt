package kz.das.test.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.das.test.data.model.MainResponse
import kz.das.test.data.network.MainDataSource
import kz.das.test.domain.SampleResponseModal
import kz.das.test.domain.SimpleResult
import kz.das.test.domain.main.MainRepository
import kz.das.test.utils.extensions.simpleError

class MainRepositoryImpl(private val mainDataSource: MainDataSource) : MainRepository{
    override suspend fun getPhotoList(): SimpleResult<List<MainResponse>> = try {
        withContext(Dispatchers.IO) {
            val response = mainDataSource.getPhotoList()
            if(response.isSuccessful && !response.body().isNullOrEmpty()){
                SimpleResult.Success(response.body()!!)
            }else {
                SimpleResult.Error("Empty response body")
            }
        }
    } catch (e: Throwable) {
        e.simpleError()
    }


}