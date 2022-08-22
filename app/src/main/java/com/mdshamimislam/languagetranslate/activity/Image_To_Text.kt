package com.mdshamimislam.languagetranslate.activity

import android.Manifest
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.*
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
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
import kotlinx.android.synthetic.main.activity_image_to_text.*
import java.io.File

import java.util.*


class Image_To_Text : AppCompatActivity() ,View.OnClickListener,AdsCallback,CallbackListener {

    private var TAG:String="Image_To_Text"
    private var getlanguageFrom:String?=""
    private var getlanguageTo:String?=""
    var mGetContent: ActivityResultLauncher<String>? = null
    var mGetContent1: ActivityResultLauncher<Uri>? = null

    var mResultEt: EditText? = null
    private var addImageBtn: Button? = null
    private var clear_text:Button?=null
    var mPreviewIv: ImageView? = null
    private var image_for_show_imageToTextActivityFrom:ImageView?=null
    private var image_for_show_imageToTextActivityTo:ImageView?=null
    private var name_for_show_imageToTextActivityFrom: TextView? = null
    private var name_for_show_imageToTextActivityTo: TextView? = null
    private var image_show_ImageAndName_LayoutFrom:LinearLayout?=null
    private var image_show_ImageAndName_LayoutTo:LinearLayout?=null
    private  var editText_Searchview:SearchView?=null
    private  var spinnerValue_search_RV:RecyclerView?=null
    private var adapterTo: searchSpinnerValueAdapter?=null
    private var adapterFrom: searchSpinnerValueAdapter?=null
    private var imageSpinnerFrom:TextView?=null
    private var imageSpinnerTo:TextView?=null
    var translatedText: TextView? = null
    private val mTTS: TextToSpeech? = null
    private  var mTTS1:TextToSpeech? = null
    private var translateBtn: Button? = null
    private var mSeekBarPitch: SeekBar? = null
    private var mSeekBarSpeed: SeekBar? = null
    private var mSeekBarPitch1: SeekBar? = null
    private var mSeekBarSpeed1: SeekBar? = null
    private var sharedPreferences: SharedPreferences?=null
    private var mButtonSpeak: Button? = null
    private var mButtonSpeak1: Button? = null
    private var progressDialog: ProgressDialog? = null
    private var sb:StringBuilder?=null
    private val CAMERA_REQUEST_CODE = 200
    private val STORAGE_REQUEST_CODE = 400
    private var text_toSpeech: TextToSpeech? =null
    private var text_toSpeech1: TextToSpeech? =null
    private var options:TranslatorOptions?=null
    private var conditions:DownloadConditions?=null
    private  var name:String?=null
    private  var image:Int?=null
    private  var positions:Int?=null
    private  var nameTo:String?=null
    private  var imageTo:Int?=null
    private  var positionsTo:Int?=null
    private var isLoaded = false


    var image_uri: Uri? = null
    var path:Uri?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_to_text)

        mResultEt= findViewById(R.id.resultEt) as EditText
        translatedText = findViewById<View>(R.id.translatedText) as TextView
        mPreviewIv = findViewById<View>(R.id.imageIv) as ImageView
        addImageBtn = findViewById<View>(R.id.buttonclick) as Button
        clear_text=findViewById<View>(R.id.clear_text) as Button
        translateBtn = findViewById<View>(R.id.button_Translate) as Button
        mButtonSpeak= findViewById(R.id.button_speak) as Button
        mButtonSpeak1= findViewById(R.id.button_speakTranslatedText) as Button
        mSeekBarPitch=findViewById(R.id.seek_bar_pitch) as SeekBar
        mSeekBarSpeed=findViewById(R.id.seek_bar_speed) as SeekBar
        mSeekBarPitch1=findViewById(R.id.seek_bar_pitchTranslatedText) as SeekBar
        mSeekBarSpeed1=findViewById(R.id.seek_bar_speedTranslatedText) as SeekBar

        image_show_ImageAndName_LayoutFrom= findViewById(R.id.image_show_ImageAndName_LayoutFrom) as LinearLayout
        image_show_ImageAndName_LayoutTo= findViewById(R.id.image_show_ImageAndName_LayoutTo) as LinearLayout
        name_for_show_imageToTextActivityFrom= findViewById(R.id. name_for_show_imageToTextActivityFrom) as TextView
        image_for_show_imageToTextActivityFrom= findViewById(R.id. image_for_show_imageToTextActivityFrom) as ImageView
        name_for_show_imageToTextActivityTo= findViewById(R.id. name_for_show_imageToTextActivityTo) as TextView
        image_for_show_imageToTextActivityTo= findViewById(R.id. image_for_show_imageToTextActivityTo) as ImageView
        imageSpinnerFrom=findViewById(R.id.imageSpinnerFrom) as TextView
        imageSpinnerTo=findViewById(R.id.imageSpinnerTo) as TextView
        addImageBtn!!.setOnClickListener(this)
        translateBtn!!.setOnClickListener(this)
        mButtonSpeak!!.setOnClickListener(this)
        mButtonSpeak1!!.setOnClickListener(this)
        imageSpinnerFrom!!.setOnClickListener(this)
        imageSpinnerTo!!.setOnClickListener(this)
        image_show_ImageAndName_LayoutFrom!!.setOnClickListener(this)
        image_show_ImageAndName_LayoutTo!!.setOnClickListener(this)
        clear_text!!.setOnClickListener(this)

        forAds()
        imageIntent()
        image_uri= createImageUrl()
        EditTextSpeak()
        translatedTextSpeak()
        getSharedPrefValueRecieveMethod()


    }

    private fun getSharedPrefValueRecieveMethod()
    {
        sharedPreferences = this.getSharedPreferences("SpinnerValue", Context.MODE_PRIVATE)
        image = sharedPreferences!!.getInt("imageToimageFrom",0)
        name = sharedPreferences!!.getString("imagenameFrom","")
        positions = sharedPreferences!!.getInt("imagepositionFrom",0)

        imageTo = sharedPreferences!!.getInt("imageToimageTo",0)
        nameTo = sharedPreferences!!.getString("imagenameTo","")
        positionsTo = sharedPreferences!!.getInt("imagepositionTo",0)

        if (!image!!.equals(0) || !name!!.equals("") ||!positions!!.equals(0))
        {
            image_show_ImageAndName_LayoutFrom!!.visibility=View.VISIBLE
            imageSpinnerFrom!!.visibility=View.GONE
            imageSpinnerFrom!!.setText("")
            name_for_show_imageToTextActivityFrom!!.setText(name)
            image_for_show_imageToTextActivityFrom!!.setImageResource(image!!)


        }
        else
        {
            image_show_ImageAndName_LayoutFrom!!.visibility=View.GONE
        }
        if (!imageTo!!.equals(0) || !nameTo!!.equals("") ||!positionsTo!!.equals(0))
        {
            image_show_ImageAndName_LayoutTo!!.visibility=View.VISIBLE
            imageSpinnerTo!!.visibility=View.GONE
            imageSpinnerTo!!.setText("")
            name_for_show_imageToTextActivityTo!!.setText(nameTo)
            image_for_show_imageToTextActivityTo!!.setImageResource(imageTo!!)
        }
        else
        {

            image_show_ImageAndName_LayoutTo!!.visibility=View.GONE
        }
    }

    private fun forAds()
    {
        if (NetworkCheck.isNetworkConnected(this))
        {
            ad_class.loadBannerAd(this,googleBannerAdsImageTotext)
            CommonConstantAd.showInterstitialAdsGoogle(this,this)

        }
        else
        {
            NetworkCheck.openInternetDialog(this,true,this)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(p0: View?) {
        if(p0!=null)
        {
        when(p0.id)
        {
            R.id.buttonclick ->
            {
                ShowImageImportDialog()
            }
            R.id.imageSpinnerFrom ->
            {
                ClickspinnerMethod()
            }
            R.id.imageSpinnerTo ->
            {
                ClickspinnerMethodTo()
            }
            R.id.image_show_ImageAndName_LayoutFrom ->
            {
                ClickspinnerMethod()
            }
            R.id.image_show_ImageAndName_LayoutTo ->
            {
                ClickspinnerMethodTo()
            }
            R.id.clear_text ->
            {
               if (mResultEt!!.text.toString().equals(""))
               {
                   Toast.makeText(applicationContext, "Please Select Image Or Write Text", Toast.LENGTH_SHORT).show()
               }
                else
               {
                  mResultEt!!.setText("")
               }
            }

            R.id.button_Translate -> {
                var edText=mResultEt!!.text.toString().trim()
                if (!edText.isEmpty())
                {
                    if (NetworkCheck.isNetworkConnected(this))
                    {
                        val i=positions
                        val j=positionsTo
                        //val i: Int= textViewFrom!!.getSelectedItemPosition()
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

            R.id.button_speak ->
            {
                var entertextEDT: String = mResultEt!!.getText().toString()
                if (entertextEDT.isEmpty()  || image_uri!!.equals(null))
                {

                    Toast.makeText(this, "Please Choose Your Image Or Text", Toast.LENGTH_SHORT).show()

                }
                else
                {
                    editTextSpeakSeekBar()
                    EditTextSpeak()
                }

            }
            R.id.button_speakTranslatedText ->
            {
                var translateText: String = translatedText!!.getText().toString()
                if (translateText.isEmpty()  || image_uri!!.equals(null))
                {

                    Toast.makeText(this, "Please Translate Your Text", Toast.LENGTH_SHORT).show()

                }
                else
                {
                    translateTextSpeakSeekBar()
                    translatedTextSpeak()
                }


            }
        }
        }
        else
        {
          Toast.makeText(this, "Oncliked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun editTextSpeakSeekBar()
    {
        val text = mResultEt!!.text.toString()
        var pitch = mSeekBarPitch!!.progress.toFloat() / 50
        if (pitch < 0.1) pitch = 0.1f
        var speed = mSeekBarSpeed!!.progress.toFloat() / 50
        if (speed < 0.1) speed = 0.1f
        text_toSpeech!!.setPitch(pitch)
        text_toSpeech!!.setSpeechRate(speed)
        text_toSpeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
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
        text_toSpeech= TextToSpeech(this, TextToSpeech.OnInitListener { status ->

            if (status == TextToSpeech.SUCCESS)
            {
                val result= text_toSpeech!!.setLanguage(Locale(getlanguageFrom!!))

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
                val result= text_toSpeech1!!.setLanguage(Locale(getlanguageTo!!))

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
        imageSpinnerFrom!!.visibility=View.GONE
        image_show_ImageAndName_LayoutFrom!!.visibility=View.VISIBLE
        val alert = LayoutInflater.from(this).inflate(R.layout.dialog_searchable_spinner, null)
        editText_Searchview = alert.findViewById<View>(R.id.editText_Searchview) as SearchView
        spinnerValue_search_RV = alert.findViewById<View>(R.id.spinnerValue_search_RV) as RecyclerView

        val mBuilder = AlertDialog.Builder(this).setView(alert)
        //show dialog
        val mAlertDialog = mBuilder.show()
        mAlertDialog.setCanceledOnTouchOutside(false)

        spinnerValue_search_RV!!.layoutManager= LinearLayoutManager(this)
        adapterFrom= searchSpinnerValueAdapter( model_ClassMethod_Image_of_language.getCountryAndImage(),this,"imageSpinnerFrom" +
                "")
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
        imageSpinnerTo!!.visibility=View.GONE
        image_show_ImageAndName_LayoutTo!!.visibility=View.VISIBLE
        val alert = LayoutInflater.from(this).inflate(R.layout.dialog_searchable_spinner, null)
        editText_Searchview = alert.findViewById<View>(R.id.editText_Searchview) as SearchView
        spinnerValue_search_RV = alert.findViewById<View>(R.id.spinnerValue_search_RV) as RecyclerView

        val mBuilder = AlertDialog.Builder(this).setView(alert)
        //show dialog
        val mAlertDialog = mBuilder.show()
        mAlertDialog.setCanceledOnTouchOutside(false)

        spinnerValue_search_RV!!.layoutManager= LinearLayoutManager(this)
        adapterTo= searchSpinnerValueAdapter( model_ClassMethod_Image_of_language.getCountryAndImage(),this,"imageSpinnerTo" +
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


    private fun ShowImageImportDialog() {
        val items = arrayOf(" Camera", " Gallery")
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Select Image")

        dialog.setItems(items) { dialog, which ->
            if (which == 0) {
                checkCameraPermission()
            }
            if (which == 1) {
                checkStoragePermission()
            }
        }
        dialog.create().show()
    }

    private fun checkCameraPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        } else {
            pickCamera()
        }
    }

    private fun pickCamera()
    {
        mGetContent1!!.launch(image_uri)
    }

    private fun checkStoragePermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_REQUEST_CODE)
        }
        else
        {
            pickGallery()
        }
    }

    private fun pickGallery() {
        mGetContent!!.launch("image/*")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
                CAMERA_REQUEST_CODE ->
                {
                    if (requestCode == CAMERA_REQUEST_CODE) {
                        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            pickCamera()
                        } else {
                            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            STORAGE_REQUEST_CODE ->
            {
                if (requestCode==STORAGE_REQUEST_CODE)
                {
                    if (grantResults.size > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                       pickGallery()
                        } else {
                            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == -1 && requestCode == 101) {
            val result = data!!.getStringExtra("RESULT")
            val resultUri: Uri

            if (result != null) {

                resultUri = Uri.parse(result)
                imageIv.setImageURI(resultUri)

                var bitmapDrawable:BitmapDrawable= imageIv.getDrawable() as BitmapDrawable
                val bitmap = (imageIv.getDrawable() as BitmapDrawable).bitmap

                var recognizer:TextRecognizer=TextRecognizer.Builder(applicationContext).build()

                if (!recognizer.isOperational()) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                } else {

                    var  frame:Frame=Frame.Builder().setBitmap(bitmap).build()
                    val items: SparseArray<TextBlock> = recognizer.detect(frame)
                     sb = StringBuilder()
                    for (i in 0 until items.size()) {
                        val myItem: TextBlock = items.valueAt(i)
                        sb!!.append(myItem.getValue())
                        sb!!.append("\n")
                    }
                    mResultEt!!.setText(sb.toString())

                }
            } else {
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }


        }

        if (resultCode == -1 && requestCode == 102)
        {
            val result = data!!.getStringExtra("RESULT")
            val resultUri: Uri

            if (result != null) {

                resultUri = Uri.parse(result)
                imageIv.setImageURI(resultUri)

                var bitmapDrawable:BitmapDrawable= imageIv.getDrawable() as BitmapDrawable
                val bitmap = (imageIv.getDrawable() as BitmapDrawable).bitmap

                var recognizer:TextRecognizer=TextRecognizer.Builder(applicationContext).build()

                if (!recognizer.isOperational()) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                } else {

                    var  frame:Frame=Frame.Builder().setBitmap(bitmap).build()
                    val items: SparseArray<TextBlock> = recognizer.detect(frame)
                    val sb = StringBuilder()
                    for (i in 0 until items.size()) {
                        val myItem: TextBlock = items.valueAt(i)
                        sb.append(myItem.getValue())
                        sb.append("\n")
                    }
                    mResultEt!!.setText(sb.toString())
                }
            } else {
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun imageIntent() {
        mGetContent = registerForActivityResult(GetContent())
        { result ->
            val intent = Intent(this, CropperActivity::class.java)
            intent.putExtra("DATA", result.toString())
            startActivityForResult(intent, 101)
        }

         mGetContent1 = registerForActivityResult(ActivityResultContracts.TakePicture())
         {

             val intent = Intent(this, CropperActivity::class.java)
             intent.putExtra("DATA", image_uri.toString())
             startActivityForResult(intent, 102)

    }


    }

    private fun createImageUrl():Uri{
        val image=File(applicationContext.filesDir,"camera_photo.png")
        return FileProvider.getUriForFile(applicationContext,"com.mdshamimislam.languagetranslate.fileprovider",image)
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
