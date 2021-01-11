package com.pi.cardmatcher.data.db

import androidx.lifecycle.LiveData
import com.pi.cardmatcher.utils.CoroutinesDispatcherProvider
import com.pi.cardmatcher.data.model.CardMatcherModel
import kotlinx.coroutines.withContext

class CardMatcherLocalSource(
    val reposDao: CardMatcherDao,
    val dispatcherProvider: CoroutinesDispatcherProvider
) {

    suspend fun insertAll(shadiCardMatcherModels: List<CardMatcherModel>) =
        withContext(dispatcherProvider.ioDispatcher) {
            reposDao.insertAll(shadiCardMatcherModels)
        }

    suspend fun updateOnAccept(selection: Int, emailId: String) {
        withContext(dispatcherProvider.ioDispatcher) {
            reposDao.updateOnAccept(selection, emailId)
        }
    }

    suspend fun updateOnReject(selection: Int, emailId: String) {
        withContext(dispatcherProvider.ioDispatcher) {
            reposDao.updateOnReject(selection, emailId)
        }
    }

    fun getAllList(): LiveData<List<CardMatcherModel>> {
        return reposDao.getAllList()
    }
}