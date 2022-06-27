package com.example.calculateemi.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.calculateemi.database.entity.Loan
import com.example.calculateemi.database.room.AppDatabase
import com.example.calculateemi.database.repository.InterestRatesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LoansViewmodel(application: Application) : AndroidViewModel(application) {

    val readAllData: Flow<List<Loan>>
    private val repository: InterestRatesRepository

    init {
        val loanDao = AppDatabase.getInstance(application) //getInstance(application).loa
        repository = InterestRatesRepository(loanDao)
        readAllData = repository.readAllLoan
    }

    fun fetchAllInterestRates(): Flow<List<Loan>> {
        return readAllData
    }
    fun insertInterestRates(loan: Loan){
        viewModelScope.launch {
            repository.insertInterestRates(loan = loan)
        }
    }
}

class LoansViewmodelFactory(
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(LoansViewmodel::class.java)) {
            return LoansViewmodel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}