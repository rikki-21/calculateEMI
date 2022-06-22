package com.example.calculateemi.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.room.RoomDatabase
import com.example.calculateemi.InterestRates
import com.example.calculateemi.database.AppDatabase
import com.example.calculateemi.database.InterestRatesRepository
import kotlinx.coroutines.launch

class LoansViewmodel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<InterestRates>>
    private val repository: InterestRatesRepository

    init {
        val loanDao = AppDatabase.getInstance(application) //getInstance(application).loa
        repository = InterestRatesRepository(loanDao)
        readAllData = repository.readAllInterestRates
    }
    //private val interestRatesRepository : InterestRatesRepository = InterestRatesRepository(application)

   /* fun fetchAllInterestRates(): LiveData> {
        return interestRatesRepository.readAllInterestRates
    }*/
    fun insertInterestRates(interestRates: InterestRates){
        viewModelScope.launch {
            repository.insertLoan(interestRates = interestRates)
        }
    }
    fun deleteInterestRatesByType(productName : String){
        viewModelScope.launch {
            repository.deleteInterestRatesType(productName)
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