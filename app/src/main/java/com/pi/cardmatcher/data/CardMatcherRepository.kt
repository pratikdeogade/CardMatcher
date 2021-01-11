package com.pi.cardmatcher.data


import com.pi.cardmatcher.data.api.CardMatcherRemoteDataSource
import com.pi.cardmatcher.data.db.CardMatcherLocalSource
import com.pi.cardmatcher.data.model.CardMatcherModel
import com.pi.cardmatcher.utils.CoroutinesDispatcherProvider
import com.pi.cardmatcher.utils.resultLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardMatcherRepository @Inject constructor(
    private val localSource: CardMatcherLocalSource,
    private val remoteDataSource: CardMatcherRemoteDataSource,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) {

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.mainDispatcher + parentJob)


    fun onAccept(shadiCardMatcherModel: CardMatcherModel) {
        scope.launch(dispatcherProvider.ioDispatcher) {
            val updateSelection = if (shadiCardMatcherModel.isAccept) 0 else 1
            val email: String = shadiCardMatcherModel.email
            localSource.updateOnAccept(updateSelection, email)
        }
    }

    fun onReject(cardMatcherModel: CardMatcherModel) {
        scope.launch(dispatcherProvider.ioDispatcher) {
            val updateSelection = if (cardMatcherModel.isReject) 0 else 1
            val email: String = cardMatcherModel.email
            localSource.updateOnReject(updateSelection, email)
        }
    }

    fun cancelAllRequests() {
        parentJob.cancelChildren()
    }

    val users =
        resultLiveData(
            databaseQuery = { localSource.getAllList() },
            networkCall = { remoteDataSource.fetchUsers() },
            saveCallResult = { localSource.insertAll(it.results) })
}