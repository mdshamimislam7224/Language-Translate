package com.mdshamimislam.languagetranslate.activity

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.mdshamimislam.languagetranslate.R
import com.mdshamimislam.languagetranslate.adapter.countryImageAdapter
import com.mdshamimislam.languagetranslate.model_class.LanguageTranslate
import com.mdshamimislam.languagetranslate.model_class.model_ClassMethod_Image_of_language
import java.util.*

class VoiceToText : AppCompatActivity() ,View.OnClickListener {
    private var TAG:String="VoiceToText"

    private var spinnerFrom:Spinner?=null
    private var spinnerTo:Spinner?=null
    private var mResultEt: EditText? = null
    private var translatedText: TextView? = null
    private val mTTS: TextToSpeech? = null
    private  var mTTS1: TextToSpeech? = null
    private var translateBtn: Button? = null
    private var mSeekBarPitch: SeekBar? = null
    private var mSeekBarSpeed: SeekBar? = null
    private var mSeekBarPitch1: SeekBar? = null
    private var mSeekBarSpeed1: SeekBar? = null
    private var mButtonSpeak: Button? = null
    private var mButtonSpeak1: Button? = null
    private var mic:ImageView?=null
    private var voice_text_toSpeech: TextToSpeech? =null
    private var text_toSpeech1: TextToSpeech? =null

    private var countryNameAndImageAdpter: countryImageAdapter? = null
    private var audioInput:String?=null
    private var getValueSoundSpinnerFrom:String?="af"
    private var getValueSoundSpinnerTo:String?="af"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_to_text)

        spinnerFrom=findViewById(R.id.voiceSpinnerFrom) as Spinner
        spinnerTo=findViewById(R.id.voiceSpinnerTo) as Spinner
        mResultEt= findViewById(R.id.voiceEdText) as EditText

        mSeekBarPitch=findViewById(R.id.voiceseek_bar_pitch) as SeekBar
        mSeekBarSpeed=findViewById(R.id.voiceseek_bar_speed) as SeekBar
        mSeekBarPitch1=findViewById(R.id.voiceseek_bar_pitchTranslatedText) as SeekBar
        mSeekBarSpeed1=findViewById(R.id.voiceseek_bar_speedTranslatedText) as SeekBar

        translatedText = findViewById<View>(R.id.voicetranslatedText) as TextView

        translateBtn = findViewById<View>(R.id.voicebutton_Translate) as Button
        mButtonSpeak= findViewById(R.id.voicebutton_speak) as Button
        mButtonSpeak1= findViewById(R.id.voicebutton_speakTranslatedText) as Button
        mic= findViewById(R.id.voicemic) as ImageView


        translateBtn!!.setOnClickListener(this)
        mButtonSpeak!!.setOnClickListener(this)
        mButtonSpeak1!!.setOnClickListener(this)
        mic!!.setOnClickListener(this)


        //for Spinner getValue
        countryNameAndImageAdpter = countryImageAdapter(this, model_ClassMethod_Image_of_language.getCountryAndImage())
        spinnerFrom!!.setAdapter(countryNameAndImageAdpter)
        spinnerTo!!.setAdapter(countryNameAndImageAdpter)

        EditTextSpeak()
        translatedTextSpeak()

    }

    override fun onClick(p0: View)
    {
        when(p0.id)
        {
            R.id.voicemic ->
            {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO),1)

                }
                else
                {
                    val i: Int= spinnerFrom!!.getSelectedItemPosition()
                      LanguageTranslate.spinnerFromMehod(i)
                    audioInput= LanguageTranslate.get_Audio_InputLanguageFrom

                    if (audioInput.equals(null))
                    {
                        Toast.makeText(this, "Please Select the Language", Toast.LENGTH_SHORT).show()

                    }
                    else
                    {
                        getAudioInput()
                    }


                    }
            }

            R.id.voicebutton_speak ->
            {
                val i: Int= spinnerFrom!!.getSelectedItemPosition()
                LanguageTranslate.spinnerFromMehod(i)
                 getValueSoundSpinnerFrom= LanguageTranslate.getlanguageFrom
                Log.d(TAG,"SoundSpinnerFrom=="+getValueSoundSpinnerFrom)
                editTextSpeakSeekBar()
                EditTextSpeak()

            }
            R.id. voicebutton_speakTranslatedText ->
            {
                Toast.makeText(this, "Translate sound", Toast.LENGTH_SHORT).show()

                val j: Int= spinnerTo!!.getSelectedItemPosition()
                LanguageTranslate.spinnerToMehod(j)
                getValueSoundSpinnerTo= LanguageTranslate.getlanguageTo
                Log.d(TAG,"SoundSpinnerTo=="+getValueSoundSpinnerTo)

                translateTextSpeakSeekBar()
                translatedTextSpeak()

            }

            R.id.voicebutton_Translate ->
            {
                var edText=mResultEt!!.text.toString().trim()
                if (!edText.isEmpty())
                {
                    val i: Int= spinnerFrom!!.getSelectedItemPosition()
                    LanguageTranslate.spinnerFromMehod(i)
                    var getValueSpinnerFrom= LanguageTranslate.getlanguageFrom
                    val j: Int= spinnerTo!!.getSelectedItemPosition()
                    LanguageTranslate.spinnerToMehod(j)
                    var getValueSpinnerTo= LanguageTranslate.getlanguageTo

                    Log.d(TAG,"SpinnerFrom=="+getValueSpinnerFrom)
                    Log.d(TAG,"SpinnerTo=="+getValueSpinnerTo)





                    val options = TranslatorOptions.Builder()
                        .setSourceLanguage(getValueSpinnerFrom!!)
                        .setTargetLanguage(getValueSpinnerTo!!)
                        .build()
                    val englishBengaliTranslator = Translation.getClient(options)

                    var conditions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        DownloadConditions.Builder()
                            .requireCharging()
                            .requireWifi()
                            .build()
                    }
                    else {
                        TODO("VERSION.SDK_INT < N")
                    }
                    englishBengaliTranslator.downloadModelIfNeeded(conditions)
                        .addOnSuccessListener {
                            // Model downloaded successfully. Okay to start translating.
                            // (Set a flag, unhide the translation UI, etc.)
                            translatedText!!.setText("Wait Translator language Downloading...")
                            var text= mResultEt!!.text.toString()
                            //enterTextEDT!!.setText("")
                            englishBengaliTranslator.translate(text)
                                .addOnSuccessListener { translated ->
                                    // Translation successful.
                                    translatedText!!.setText(translated)
                                }
                                .addOnFailureListener { exception ->
                                    // Error.
                                    // ...
                                }


                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(this, "Unuccessful Download Language", Toast.LENGTH_SHORT).show()

                            // Model couldn’t be downloaded or other internal error.
                            // ...
                        }


                }
                else
                {
                    Toast.makeText(this, "Please Enter Your Text", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getAudioInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
           intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, audioInput)
           intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "I am Listening...")
        try {
            startActivityForResult(intent, 1)
        } catch (ignored: ActivityNotFoundException) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

       if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
           val spokenText: String? =
               data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                   results!![0]
               }
           // Do something with spokenText.
           mResultEt!!.setText(spokenText)


       }
    }

    private fun editTextSpeakSeekBar()
    {
        val text = mResultEt!!.text.toString()
        var pitch = mSeekBarPitch!!.progress.toFloat() / 50
        if (pitch < 0.1) pitch = 0.1f
        var speed = mSeekBarSpeed!!.progress.toFloat() / 50
        if (speed < 0.1) speed = 0.1f
        voice_text_toSpeech!!.setPitch(pitch)
        voice_text_toSpeech!!.setSpeechRate(speed)
        voice_text_toSpeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    private fun translateTextSpeakSeekBar() {
        val texttranslated=translatedText!!.text.toString()
        var pitch = mSeekBarPitch1!!.progress.toFloat() / 50
        if (pitch < 0.1) pitch = 0.1f
        var speed = mSeekBarSpeed1!!.progress.toFloat() / 50
        if (speed < 0.1) speed = 0.1f
        text_toSpeech1!!.setPitch(pitch)
        text_toSpeech1!!.setSpeechRate(speed)
        text_toSpeech1!!.speak(texttranslated, TextToSpeech.QUEUE_FLUSH, null)
    }


    private fun EditTextSpeak()
    {
        voice_text_toSpeech= TextToSpeech(this, TextToSpeech.OnInitListener { status ->

            if (status == TextToSpeech.SUCCESS)
            {
                val result= voice_text_toSpeech!!.setLanguage(Locale(getValueSoundSpinnerFrom!!))

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                {
                    Log.e("TTS", "Language not supported")
                } else {
                    mButtonSpeak!!.isEnabled = true

                }
            }
            else
            {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                Log.e("TTS", "Initialization failed")
            }
        })
    }

    private fun translatedTextSpeak() {
        text_toSpeech1= TextToSpeech(this, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result= text_toSpeech1!!.setLanguage(Locale(getValueSoundSpinnerTo!!))

                if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED
                ) {
                    Log.e("TTS", "Language not supported")
                } else {
                    mButtonSpeak1!!.isEnabled = true
                }
            }
            else
            {
                Log.e("TTS", "Initialization failed")
            }
        })
    }

    override fun onDestroy() {
        if (mTTS != null || mTTS1 != null) {
            mTTS!!.stop()
            mTTS1!!.stop()
            mTTS.shutdown()
            mTTS1!!.shutdown()
        }
        super.onDestroy()
    }

}