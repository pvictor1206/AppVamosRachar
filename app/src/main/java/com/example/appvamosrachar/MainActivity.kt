package com.example.appvamosrachar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import com.example.appvamosrachar.presenter.IPresenter
import com.example.appvamosrachar.presenter.Presenter
import com.example.appvamosrachar.view.AppView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AppView {

    internal  lateinit var viewPresenter: IPresenter



    lateinit var mTTS: TextToSpeech

    var som_string = "err"
    var mensagem = "err"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //MVP
        //Model -> Modelo data
        //View -> UI
        //Presenter -> Conexão entre Model/data com a View



        audio.setOnClickListener(this)

        viewPresenter = Presenter(this)


        buttonCalcular.setOnClickListener {
            (viewPresenter as Presenter).onPresent(editTextNumber.text,editTextNumberDecimal.text)
        }

        compartilhar.setOnClickListener{

            val message: String = "O Valor é $mensagem"

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"


            startActivity(Intent.createChooser(intent, "Share to : "))

        }

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

    override fun onResult(res: String) {
        mensagem = res
        mensagem = res
        textValue.text = res

    }


}