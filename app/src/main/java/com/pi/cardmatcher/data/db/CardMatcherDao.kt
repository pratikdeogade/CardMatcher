package com.pi.cardmatcher.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pi.cardmatcher.data.model.CardMatcherModel

@Dao
interface CardMatcherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(batchModels: List<CardMatcherModel>)

    @Query("SELECT * from shadi_card_matcher")
    fun getAllList(): LiveData<List<CardMatcherModel>>

    @Query("UPDATE shadi_card_matcher SET isAccept = :selection WHERE email =:emailId")
    fun updateOnAccept(selection: Int, emailId: String)

    @Query("UPDATE shadi_card_matcher SET isReject = :selection WHERE email =:emailId")
    fun updateOnReject(selection: Int, emailId: String)

}