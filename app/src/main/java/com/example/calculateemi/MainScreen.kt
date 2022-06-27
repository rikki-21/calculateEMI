package com.example.calculateemi

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.calculateemi.database.entity.Loan
import com.example.calculateemi.viewmodel.LoansViewmodel
import com.example.calculateemi.viewmodel.LoansViewmodelFactory
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun MainScreen(navController: NavHostController) {

    val context = LocalContext.current
    val mLoansViewmodel : LoansViewmodel = viewModel(
        factory = LoansViewmodelFactory(context.applicationContext as Application)
    )
    val items = mLoansViewmodel.readAllData

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.CenterStart,

    ) {
        var mortgage by remember { mutableStateOf(TextFieldValue("")) }
        var interest by remember { mutableStateOf(TextFieldValue("")) }
        var months by remember { mutableStateOf(TextFieldValue("")) }


        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextField(
                    value = mortgage,
                    onValueChange = { mortgage = it },
                    label = { Text(text = "mortgage")},
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                TextField(
                    value = interest,
                    onValueChange = { interest = it },
                    label = { Text(text = "interest")},
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
                TextField(
                    value = months,
                    onValueChange = { months = it },
                    label = { Text(text = "months")},
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
            Button(onClick = { navController.navigate("CalculateEMI/${mortgage.text.toInt()}/${interest.text.toInt()}/${months.text.toInt()}")}) {
                Text(text = "calculate emi")
            }
        }
    }
}