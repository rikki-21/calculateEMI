package com.example.calculateemi.database

import com.example.calculateemi.LoanDao
import com.example.calculateemi.database.entity.Loan
import kotlinx.coroutines.Dispatchers

class LoanDataSource(private val loanDao: LoanDao) {
    val fetchAll = loanDao.fetchAllInterestRates()

    suspend fun insertLoan(loan : Loan){
        Dispatchers.IO.apply {
            loanDao.insertInterestRates(loan)
        }
    }
    suspend fun deleteAllLoan(){
        Dispatchers.IO.apply {
            loanDao.deleteAllInterestRates()
        }
    }
}