package com.example.calculateemi

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import com.example.calculateemi.database.entity.Loan
import com.example.calculateemi.viewmodel.LoansViewmodel
import com.example.calculateemi.viewmodel.LoansViewmodelFactory
import kotlinx.coroutines.flow.first

@Composable
fun CalculateEMI(mortgage: Int?, interest: Int?, months: Int?, navController: NavHostController) {

    val context = LocalContext.current
    val mLoansViewmodel: LoansViewmodel = viewModel(
        factory = LoansViewmodelFactory(context.applicationContext as Application)
    )

    var emi: Double? = null
    val firstPart: Double?
    val fraction: Double?
    val numerator: Double?
    val denominator: Double?
    if (mortgage != null && interest != null && months != null) {

        val mortgage1 = mortgage.toDouble()
        val interest1 = interest.toDouble() / 1200

        firstPart = (mortgage1 * interest1)
        numerator = Math.pow(1 + interest1, months.toDouble())
        denominator = (Math.pow(1 + interest1, months.toDouble()) - 1)
        fraction = numerator / denominator
        emi = firstPart * fraction

        mLoansViewmodel.insertInterestRates(
            Loan(
            id = 1,
            interestRate = interest,
            mortgage = mortgage,
            months = months,
            emi = emi.toInt()
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Magenta)
    ) {
        Text(emi.toString().format())
        Spacer(modifier = Modifier.padding(bottom = 16.dp))

        val loans = mLoansViewmodel.readAllData.collectAsState(initial = emptyList()).value //.asLiveData().value

        LazyColumn(modifier = Modifier
            .background(color = Color.Blue)
            .wrapContentHeight()
            .fillMaxWidth()) {

            Log.d("history size", loans.toString())
            items(loans) { item ->
                Text(text = "history")
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                Row(modifier = Modifier
                    .background(color = Color.Cyan)
                    .wrapContentHeight()) {
                    Text(text = item.id.toString())
                    Text(text = item.mortgage.toString())
                    Text(text = item.interestRate.toString())
                    Text(text = item.months.toString())
                }
            }
        }
    }
}