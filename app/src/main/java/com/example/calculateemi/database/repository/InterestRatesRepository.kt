package com.example.calculateemi.database.repository

import androidx.lifecycle.LiveData
import com.example.calculateemi.LoanDao
import com.example.calculateemi.database.entity.Loan
import com.example.calculateemi.database.room.AppDatabase
import kotlinx.coroutines.flow.Flow

class InterestRatesRepository(application: AppDatabase) {
    var loanDao:LoanDao = application.loanDao()

    val readAllLoan: Flow<List<Loan>> = loanDao.fetchAllInterestRates()

    suspend fun insertInterestRates(loan: Loan){
        loanDao.insertInterestRates(loan)
    }

    suspend fun deleteAllInterestRates(){
        loanDao.deleteAllInterestRates()
    }

    /*init {
        val database = AppDatabase.getDatabase(application)
        loanDao = database.loanDao()
    }*/
}