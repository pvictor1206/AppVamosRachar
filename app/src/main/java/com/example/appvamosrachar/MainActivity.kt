package com.example.appvamosrachar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var moneyAct: EditText
    lateinit var peopleAct: EditText
    lateinit var mTTS: TextToSpeech

    var som_string = "err"
    var mensagem = "err"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        audio.setOnClickListener(this)

        moneyAct = findViewById(R.id.editTextNumber)
        peopleAct = findViewById(R.id.editTextNumberDecimal)


        compartilhar.setOnClickListener{

            val message: String = "O Valor é $mensagem"

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"


            startActivity(Intent.createChooser(intent, "Share to : "))

        }



        moneyAct.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {



                try{
                    val totalValue = editTextNumber.text.toString().toFloat() / editTextNumberDecimal.text.toString().toFloat()
                    textValue.text = "R$: ${"%.2f".format(totalValue)}"

                    som_string = "$totalValue"
                    mensagem = "$totalValue"




                }catch (nfe: NumberFormatException){
                    textValue.text = "R$: 0,00"
                }





            }

        })


        peopleAct.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                try{
                    val totalValue = editTextNumber.text.toString().toFloat() / editTextNumberDecimal.text.toString().toFloat()
                    textValue.text = "R$: ${"%.2f".format(totalValue)}"

                    som_string = "$totalValue"
                    mensagem = "$totalValue"

                }catch (nfe: NumberFormatException){
                    textValue.text = "R$: 0.00"
                }

            }

        })


    }

    override fun onClick(view: View) {
        val id = view.id
        if(id == R.id.audio){
            AdicionarSom()
        }

    }

    private fun AdicionarSom() {

        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if(status != TextToSpeech.ERROR){
                mTTS.language = Locale.ENGLISH
            }

        })


        audio.setOnClickListener {
            val toSpeak = "The value is $som_string"
            mTTS.speak(toSpeak,TextToSpeech.QUEUE_FLUSH, null)
        }



    }





}