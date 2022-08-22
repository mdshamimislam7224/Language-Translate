package com.mdshamimislam.languagetranslate.activity

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.mdshamimislam.languagetranslate.R
import com.mdshamimislam.languagetranslate.adapter.searchSpinnerValueAdapter
import com.mdshamimislam.languagetranslate.interfaces.AdsCallback
import com.mdshamimislam.languagetranslate.interfaces.CallbackListener
import com.mdshamimislam.languagetranslate.model_class.LanguageTranslate
import com.mdshamimislam.languagetranslate.model_class.model_ClassMethod_Image_of_language
import com.mdshamimislam.languagetranslate.utils.CommonConstantAd
import com.mdshamimislam.languagetranslate.utils.NetworkCheck
import com.mdshamimislam.languagetranslate.utils.ad_class
import kotlinx.android.synthetic.main.activity_voice_to_text.*
import java.util.*

class VoiceToText : AppCompatActivity() ,View.OnClickListener , AdsCallback,CallbackListener{
    private var TAG:String="VoiceToText"

    private var textViewFrom:TextView?=null
    private var textViewTo:TextView?=null
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
    private var voicebutton_Clear:Button?=null
    private var mButtonSpeak1: Button? = null
    private var mic:ImageView?=null
    private var image_for_show_VoiceToTextActivityFrom:ImageView?=null
    private var image_for_show_VoiceToTextActivityTo:ImageView?=null
    private var name_for_show_VoiceToTextActivityFrom: TextView? = null
    private var name_for_show_VoiceToTextActivityTo: TextView? = null
    private var show_ImageAndName_LayoutFrom:LinearLayout?=null
    private var show_ImageAndName_LayoutTo:LinearLayout?=null
    private var voice_text_toSpeech: TextToSpeech? =null
    private var text_toSpeech1: TextToSpeech? =null
    private var options:TranslatorOptions?=null
    private var conditions:DownloadConditions?=null
    private var progressDialog: ProgressDialog? = null
    private var audioInput:String?=null
    private var getValueSoundSpinnerFrom:String?="af"
    private var getValueSoundSpinnerTo:String?="af"
    private  var editText_Searchview:SearchView?=null
    private  var spinnerValue_search_RV:RecyclerView?=null
    private var adapterTo: searchSpinnerValueAdapter?=null
    private var adapterFrom: searchSpinnerValueAdapter?=null
    private  var name:String?=null
    private  var image:Int?=null
    private  var positions:Int?=null
    private var sharedPreferences: SharedPreferences?=null
    private  var nameTo:String?=null
    private  var imageTo:Int?=null
    private  var positionsTo:Int?=null
    private var isLoaded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_to_text)

        textViewFrom =findViewById(R.id.voiceSpinnerFrom) as TextView
        textViewTo=findViewById(R.id.voiceSpinnerTo) as TextView
        mResultEt= findViewById(R.id.voiceEdText) as EditText
        show_ImageAndName_LayoutFrom= findViewById(R.id.show_ImageAndName_LayoutFrom) as LinearLayout
        show_ImageAndName_LayoutTo= findViewById(R.id.show_ImageAndName_LayoutTo) as LinearLayout

        name_for_show_VoiceToTextActivityFrom= findViewById(R.id. name_for_show_VoiceToTextActivityFrom) as TextView
        image_for_show_VoiceToTextActivityFrom= findViewById(R.id. image_for_show_VoiceToTextActivityFrom) as ImageView

        name_for_show_VoiceToTextActivityTo= findViewById(R.id. name_for_show_VoiceToTextActivityTo) as TextView
        image_for_show_VoiceToTextActivityTo= findViewById(R.id. image_for_show_VoiceToTextActivityTo) as ImageView

        mSeekBarPitch=findViewById(R.id.voiceseek_bar_pitch) as SeekBar
        mSeekBarSpeed=findViewById(R.id.voiceseek_bar_speed) as SeekBar
        mSeekBarPitch1=findViewById(R.id.voiceseek_bar_pitchTranslatedText) as SeekBar
        mSeekBarSpeed1=findViewById(R.id.voiceseek_bar_speedTranslatedText) as SeekBar
        translatedText = findViewById<View>(R.id.voicetranslatedText) as TextView
        voicebutton_Clear=findViewById(R.id.voicebutton_Clear) as Button
        translateBtn = findViewById<View>(R.id.voicebutton_Translate) as Button
        mButtonSpeak= findViewById(R.id.voicebutton_speak) as Button
        mButtonSpeak1= findViewById(R.id.voicebutton_speakTranslatedText) as Button
        mic= findViewById(R.id.voicemic) as ImageView

        getSharedPrefValueRecieveMethod()

        translateBtn!!.setOnClickListener(this)
        mButtonSpeak!!.setOnClickListener(this)
        mButtonSpeak1!!.setOnClickListener(this)
        mic!!.setOnClickListener(this)
        textViewFrom!!.setOnClickListener(this)
        textViewTo!!.setOnClickListener(this)
        show_ImageAndName_LayoutFrom!!.setOnClickListener(this)
        show_ImageAndName_LayoutTo!!.setOnClickListener(this)
        voicebutton_Clear!!.setOnClickListener(this)


        forAds()
        EditTextSpeak()
        translatedTextSpeak()
    }

    private fun getSharedPrefValueRecieveMethod() {
        sharedPreferences = this.getSharedPreferences("SpinnerValue", Context.MODE_PRIVATE)
        image = sharedPreferences!!.getInt("image",0)
        name = sharedPreferences!!.getString("nameFrom","")
        positions = sharedPreferences!!.getInt("positionFrom",0)

        imageTo = sharedPreferences!!.getInt("imageTo",0)
        nameTo = sharedPreferences!!.getString("nameTo","")
        positionsTo = sharedPreferences!!.getInt("positionTo",0)

        if (!image!!.equals(0) || !name!!.equals("") ||!positions!!.equals(0))
        {
            show_ImageAndName_LayoutFrom!!.visibility=View.VISIBLE
            textViewFrom!!.visibility=View.GONE
            textViewFrom!!.setText("")
            name_for_show_VoiceToTextActivityFrom!!.setText(name)
            image_for_show_VoiceToTextActivityFrom!!.setImageResource(image!!)


        }
        else
        {
            show_ImageAndName_LayoutFrom!!.visibility=View.GONE
        }
        if (!imageTo!!.equals(0) || !nameTo!!.equals("") ||!positionsTo!!.equals(0))
        {
            show_ImageAndName_LayoutTo!!.visibility=View.VISIBLE
            textViewTo!!.visibility=View.GONE
            textViewTo!!.setText("")
            name_for_show_VoiceToTextActivityTo!!.setText(nameTo)
            image_for_show_VoiceToTextActivityTo!!.setImageResource(imageTo!!)
        }
        else
        {

            show_ImageAndName_LayoutTo!!.visibility=View.GONE
        }



    }
    private fun forAds()
    {
        if (NetworkCheck.isNetworkConnected(this))
        {
            ad_class.loadBannerAd(this,googleBannerAdsVoiceTotext)
            var ad= CommonConstantAd.googlebeforloadAd(this)
            CommonConstantAd.showInterstitialAdsGoogle(this,this)
        }
        else
        {
            NetworkCheck.openInternetDialog(this,true,this)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(p0: View)
    {
        when(p0.id)
        {
            R.id.voiceSpinnerFrom ->
            {
                ClickspinnerMethod()

            }
            R.id.voiceSpinnerTo ->
            {
                ClickspinnerMethodTo()
            }
            R.id.show_ImageAndName_LayoutFrom ->
            {
                ClickspinnerMethod()
            }
            R.id.show_ImageAndName_LayoutTo ->
            {
                ClickspinnerMethodTo()
            }

            R.id.voicemic ->
            {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO),1)

                }
                else {
                    if (NetworkCheck.isNetworkConnected(this)) {
                        var textValueFrom=textViewFrom!!.getText().toString()
                        var textValueTo=textViewTo!!.getText().toString()
                       if (!textValueFrom.equals("Select Language From") || !textValueTo.equals("Select Language To"))
                       {
                           val i=positions
                           if (i != null) {
                               LanguageTranslate.spinnerFromMehod(i)
                           }
                           audioInput = LanguageTranslate.get_Audio_InputLanguageFrom
                           if (audioInput.equals(null)) {
                               Toast.makeText(this, "Please Select the Language", Toast.LENGTH_SHORT).show() }
                           else {
                               getAudioInput()
                           }
                           ///micTranslateLanguageAuto()
                       }
                        else
                       {
                           Toast.makeText(applicationContext, "Please Choose Your Language", Toast.LENGTH_SHORT).show()
                       }

                    }
                    else
                    {
                        NetworkCheck.openInternetDialog(this,true,this)
                    }
                }
            }
            R.id.voicebutton_Clear->
            {
                var textResult=mResultEt!!.text.toString()
                if (textResult.isEmpty())
                {
                    Toast.makeText(applicationContext, "All Ready Null No Text Here", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    mResultEt!!.setText("")
                }

            }

            R.id.voicebutton_speak ->
            {
                val i=positions
                if (i != null) {
                    LanguageTranslate.spinnerFromMehod(i)
                }
                 getValueSoundSpinnerFrom= LanguageTranslate.getlanguageFrom
                Log.d(TAG,"SoundSpinnerFrom=="+getValueSoundSpinnerFrom)
                editTextSpeakSeekBar()
                EditTextSpeak()

            }
            R.id. voicebutton_speakTranslatedText ->
            {
                Toast.makeText(this, "Translate sound", Toast.LENGTH_SHORT).show()
                val j=positionsTo
                if (j != null) {
                    LanguageTranslate.spinnerToMehod(j)
                }
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
                    if (NetworkCheck.isNetworkConnected(this))
                    {
                        val i=positions
                        val j=positionsTo
                        if (i != null) {
                            LanguageTranslate.spinnerFromMehod(i)
                        }
                        var getValueSpinnerFrom= LanguageTranslate.getlanguageFrom
                        if (j != null) {
                            LanguageTranslate.spinnerToMehod(j)
                        }
                        var getValueSpinnerTo= LanguageTranslate.getlanguageTo

                         options = TranslatorOptions.Builder()
                            .setSourceLanguage(getValueSpinnerFrom!!)
                            .setTargetLanguage(getValueSpinnerTo!!)
                            .build()
                        val translateGetLanguage = Translation.getClient(options!!)

                        progressDialog = ProgressDialog(this)
                        progressDialog!!.setTitle("Please Wait...")
                        progressDialog!!.setMessage("Language Downloading...")
                        progressDialog!!.show()
                        progressDialog!!.setCancelable(false)


                        conditions = DownloadConditions.Builder()
                            .build()
                        Log.d(TAG,"Condition="+conditions)
                        translateGetLanguage.downloadModelIfNeeded(conditions!!)
                            .addOnSuccessListener {

                                // Model downloaded successfully. Okay to start translating.
                                // (Set a flag, unhide the translation UI, etc.)
                                translatedText!!.setText("Wait Translator language Downloading...")
                                var text= mResultEt!!.text.toString()
                                translateGetLanguage.translate(text)
                                    .addOnSuccessListener { translated ->
                                        progressDialog!!.dismiss()
                                        // Translation successful.
                                        translatedText!!.setText(translated)
                                    }
                                    .addOnFailureListener { exception ->
                                        Toast.makeText(this, "Unuccessful"+exception, Toast.LENGTH_SHORT).show()
                                    }


                            }
                            .addOnFailureListener { exception ->
                                Toast.makeText(this, "Unuccessful Download Language"+exception, Toast.LENGTH_SHORT).show()
                            }
                    }
                    else
                    {
                        NetworkCheck.openInternetDialog(this , true, this)
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
            var textResult=mResultEt!!.getText().toString()
           mResultEt!!.setText(textResult+" "+spokenText)


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




private fun ClickspinnerMethod()
 {
     textViewFrom!!.visibility=View.GONE
     show_ImageAndName_LayoutFrom!!.visibility=View.VISIBLE
     val alert = LayoutInflater.from(this).inflate(R.layout.dialog_searchable_spinner, null)
     editText_Searchview = alert.findViewById<View>(R.id.editText_Searchview) as SearchView
     spinnerValue_search_RV = alert.findViewById<View>(R.id.spinnerValue_search_RV) as RecyclerView

     val mBuilder = AlertDialog.Builder(this).setView(alert)
     //show dialog
     val mAlertDialog = mBuilder.show()
     mAlertDialog.setCanceledOnTouchOutside(false)

     spinnerValue_search_RV!!.layoutManager= LinearLayoutManager(this)
     adapterFrom= searchSpinnerValueAdapter( model_ClassMethod_Image_of_language.getCountryAndImage(),this,"voiceSpinnerFrom")
     spinnerValue_search_RV!!.adapter=adapterFrom

     editText_Searchview!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
         override fun onQueryTextSubmit(s: String): Boolean {
             return false
         }

         override fun onQueryTextChange(s: String): Boolean {
             adapterFrom!!.filter.filter(s)
             return false
         }
     })
 }

   private fun ClickspinnerMethodTo()
    {
        textViewTo!!.visibility=View.GONE
        show_ImageAndName_LayoutTo!!.visibility=View.VISIBLE
        val alert = LayoutInflater.from(this).inflate(R.layout.dialog_searchable_spinner, null)
        editText_Searchview = alert.findViewById<View>(R.id.editText_Searchview) as SearchView
        spinnerValue_search_RV = alert.findViewById<View>(R.id.spinnerValue_search_RV) as RecyclerView

        val mBuilder = AlertDialog.Builder(this).setView(alert)
        //show dialog
        val mAlertDialog = mBuilder.show()
        mAlertDialog.setCanceledOnTouchOutside(false)

        spinnerValue_search_RV!!.layoutManager= LinearLayoutManager(this)
        adapterTo= searchSpinnerValueAdapter( model_ClassMethod_Image_of_language.getCountryAndImage(),this,"voiceSpinnerTo" +
                "")
        spinnerValue_search_RV!!.adapter=adapterTo

        editText_Searchview!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                adapterTo!!.filter.filter(s)
                return false
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

    override fun adLoadingFailed() {
        TODO("Not yet implemented")
    }

    override fun adClose() {
        TODO("Not yet implemented")
    }

    override fun startNextScreen() {
        startNextActivity(0)
    }
    override fun onLoaded() {
        isLoaded = true
    }

    private val handler = Handler(Looper.getMainLooper())
    private val myRunnable : Runnable = Runnable {
        if (NetworkCheck.isNetworkConnected(this)) {
            if (!this.isLoaded) {
                startNextActivity(0)
            }
        }
    }
    fun startNextActivity(time: Long) {
        handler.postDelayed({

        }, time)
    }

    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onCancel() {
        TODO("Not yet implemented")
    }

    override fun onRetry() {
        forAds()
    }

}