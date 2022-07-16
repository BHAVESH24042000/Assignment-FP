package com.example.fampayassgnt.network

import com.example.fampayassgnt.dataModels.MainApiResponse
import retrofit2.http.GET

interface FamPayAPI {

    @GET("04a04703-5557-4c84-a127-8c55335bb3b4")
    suspend fun getApiResponse(): MainApiResponse?

}
