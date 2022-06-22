package com.example.calculateemi

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import com.example.calculateemi.viewmodel.LoansViewmodel
import com.example.calculateemi.viewmodel.LoansViewmodelFactory

@Composable
fun CalculateEMI(mortgage: Int?, interest: Int?, months: Int?, navController: NavHostController) {

    val context = LocalContext.current
    val mLoansViewmodel: LoansViewmodel = viewModel(
        factory = LoansViewmodelFactory(context.applicationContext as Application)
    )
    val items = mLoansViewmodel.readAllData.value


    var emi: Double? = null
    var firstPart: Double?
    var fraction: Double?
    var numerator: Double?
    var denominator: Double?
    if (mortgage != null && interest != null && months != null) {
        var mortgage = mortgage.toDouble()
        var interest = interest.toDouble() / 1200

        firstPart = (mortgage * interest)
        numerator = Math.pow(1 + interest, months.toDouble())
        denominator = (Math.pow(1 + interest, months.toDouble()) - 1)
        fraction = numerator / denominator
        emi = firstPart * fraction
    }
    items
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Text(emi.toString().format())
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        LazyColumn() {
            if (items != null)
                items(items) { item ->
                    Row() {
                        Text(text = item.productName)
                        Text(text = item.mortgage.toString())
                        Text(text = item.interestRate.toString())
                        Text(text = item.months.toString())
                    }
                }
        }
    }
}