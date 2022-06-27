package com.example.calculateemi.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "InterestRates")
data class Loan (

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "interestRate")
    var interestRate : Int,
    @ColumnInfo(name = "mortgage")
    var mortgage : Int,
    @ColumnInfo(name = "months")
    var months : Int,
    @ColumnInfo(name = "emi")
    var emi : Int
)
    /*constructor() {}

    constructor(productName : String, interestRate : Int, mortgage : Int, months : Int){

        this.interestRate = interestRate
        this.mortgage = mortgage
        this.months = months
    }

    constructor(interestRate : Int, mortgage : Int, months : Int){

        this.interestRate = interestRate
        this.mortgage = mortgage
        this.months = months
    }
}*/