package com.pi.cardmatcher.data.api

import com.pi.cardmatcher.data.model.MatcherModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CardMatcherApiService {

    @GET("api")
    suspend fun fetchRandomUsers(@Query("results") resultsCount: Int): Response<MatcherModelResponse>
}