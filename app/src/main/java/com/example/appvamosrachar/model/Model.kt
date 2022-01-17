package com.example.appvamosrachar.model

import android.text.Editable
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class Model(override val moneyAct: Editable, override val peopleAct: Editable): IModel{
    override fun calcule(): String {

        var totalValue:String
        totalValue = "error"



        try{
            totalValue = (moneyAct.toString().toFloat() / peopleAct.toString().toFloat()).toString()


        }catch (nfe: NumberFormatException){
            totalValue = "0,00"
        }




        return  totalValue
    }


}