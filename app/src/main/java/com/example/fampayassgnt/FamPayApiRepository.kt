package com.example.fampayassgnt

import com.example.fampayassgnt.Objects.NO_INTERNET_MESSAGE
import com.example.fampayassgnt.network.FamPayAPI
import com.example.fampayassgnt.network.helper.InternetChecker
import com.example.fampayassgnt.network.helper.Resource
import com.example.fampayassgnt.dataModels.MainApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FamPayApiRepository @Inject constructor(private val famPayAPI: FamPayAPI,
                                              private val internetChecker: InternetChecker,){

    suspend fun getFamPayApiResponse()  = withContext(Dispatchers.IO){

        if(internetChecker.hasInternetConnection()){
         var data: MainApiResponse? = null
            data = famPayAPI.getApiResponse()

            if(data!=null)
                return@withContext Resource.Success(data, "Api Call Success")
            else
                return@withContext Resource.Error(
                    errorType = Objects.ERROR_TYPE.UNKNOWN
                )

        }
        else{
            return@withContext Resource.Error(
                errorType = Objects.ERROR_TYPE.NO_INTERNET,
                message = NO_INTERNET_MESSAGE,
                data = null
            )
        }
    }
}