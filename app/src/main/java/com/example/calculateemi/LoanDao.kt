package com.example.calculateemi

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoanDao {

    @Query("SELECT*FROM InterestRates")
    fun fetchAllInterestRates() : LiveData<List<InterestRates>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInterestRates(interestRates: InterestRates )

    @Query("DELETE FROM InterestRates where productName = :loanType")
    suspend fun deleteInterestRatesByType(loanType:String)

    @Query("DELETE FROM InterestRates")
    suspend fun deleteAllInterestRates()
}