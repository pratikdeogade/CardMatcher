package com.pi.cardmatcher.ui

import androidx.lifecycle.ViewModel
import com.pi.cardmatcher.data.CardMatcherRepository
import com.pi.cardmatcher.data.model.CardMatcherModel
import javax.inject.Inject

class CardMatcherViewModel @Inject constructor(
    private val shadiCardMatcherRepository: CardMatcherRepository
) : ViewModel() {

    val users = shadiCardMatcherRepository.users


    fun onAccept(shadiCardMatcherModel: CardMatcherModel) {
        shadiCardMatcherRepository.onAccept(shadiCardMatcherModel)
    }

    fun onReject(shadiCardMatcherModel: CardMatcherModel) {
        shadiCardMatcherRepository.onReject(shadiCardMatcherModel)
    }

    override fun onCleared() {
        super.onCleared()
        shadiCardMatcherRepository.cancelAllRequests()
    }
}