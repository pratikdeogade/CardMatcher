package com.pi.cardmatcher.data.api

import com.pi.cardmatcher.base.BaseRemoteDataSource
import javax.inject.Inject

class CardMatcherRemoteDataSource @Inject constructor(private val service: CardMatcherApiService) :

    BaseRemoteDataSource() {
    suspend fun fetchUsers() = getResult {
        service.fetchRandomUsers(10)
    }
}