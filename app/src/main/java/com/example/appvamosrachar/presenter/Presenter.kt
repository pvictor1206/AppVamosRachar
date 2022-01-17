package com.example.appvamosrachar.presenter

import android.text.Editable
import com.example.appvamosrachar.model.Model
import com.example.appvamosrachar.view.AppView

class Presenter (internal  var appView: AppView): IPresenter{
    override fun onPresent(moneyAct: Editable, peopleAct: Editable) {
        val user = Model(moneyAct,peopleAct)
        val userResult = user.calcule()
        appView.onResult(userResult)


    }

}