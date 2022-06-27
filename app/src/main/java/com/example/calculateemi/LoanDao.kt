package com.example.calculateemi

import androidx.room.*
import com.example.calculateemi.database.entity.Loan
import kotlinx.coroutines.flow.Flow

@Dao
interface LoanDao {

    @Query("SELECT*FROM InterestRates LIMIT 20")
    fun fetchAllInterestRates() : Flow<List<Loan>>

    @Insert //(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInterestRates(loan: Loan)

    //@Query("DELETE FROM InterestRates where productName = :loanType")
    //suspend fun deleteInterestRatesByType(loanType:String)

    @Query ("DELETE FROM InterestRates")
    suspend fun deleteAllInterestRates()
}