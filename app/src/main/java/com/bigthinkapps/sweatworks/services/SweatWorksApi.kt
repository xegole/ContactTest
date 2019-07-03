package com.bigthinkapps.sweatworks.services

import com.bigthinkapps.sweatworks.data.responses.ResultPersonResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SweatWorksApi {

    @GET("api")
    fun getResults(@Query("results") results: Int): Observable<ResultPersonResponse>
}