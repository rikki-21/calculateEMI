package com.example.calculateemi.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.calculateemi.InterestRates
import com.example.calculateemi.LoanDao

class InterestRatesRepository(application: AppDatabase) {
    var loanDao:LoanDao = application.loanDao()

    /*init {
        val database = AppDatabase.getDatabase(application)
        loanDao = database.l
    }*/

    val readAllInterestRates: LiveData<List<InterestRates>> = loanDao.fetchAllInterestRates()
    suspend fun insertLoan(interestRates: InterestRates){
        loanDao.insertInterestRates(interestRates)
    }
    suspend fun deleteInterestRatesType(productName: String){
        loanDao.deleteInterestRatesByType(productName)
    }
    suspend fun deleteAllInterestRates(){
        loanDao.deleteAllInterestRates()
    }
    /*init {
        val database = AppDatabase.getDatabase(application)
        loanDao = database.loanDao()
    }*/
}