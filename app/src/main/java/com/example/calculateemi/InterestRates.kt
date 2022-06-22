package com.example.calculateemi

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "InterestRates")
data class InterestRates (

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "productName")
    var productName : String = "",

    @ColumnInfo(name = "interestRate")
    var interestRate : Int = 0,
    @ColumnInfo(name = "mortgage")
    var mortgage : Int = 0,
    @ColumnInfo(name = "months")
    var months : Int = 0
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