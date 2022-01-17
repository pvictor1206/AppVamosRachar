package com.example.appvamosrachar.model

import android.speech.tts.TextToSpeech
import android.text.Editable
import android.widget.EditText

interface IModel {

    val moneyAct: Editable
    val peopleAct: Editable

    fun calcule():String


}