package com.mdshamimislam.languagetranslate.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.mdshamimislam.languagetranslate.R
import kotlinx.android.synthetic.main.activity_image_to_text.*
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*


class Image_To_Text : AppCompatActivity() ,View.OnClickListener {

    private var TAG:String="Image_To_Text"
    private var getlanguageFrom:String?=""
    private var InputLanguageFrom:String?=""

    private var getlanguageTo:String?=""
    private var InputLanguageTo:String?=""

    var mGetContent: ActivityResultLauncher<String>? = null
    var mGetContent1: ActivityResultLauncher<Uri>? = null

    var mResultEt: EditText? = null
    private var addImageBtn: Button? = null
    var mPreviewIv: ImageView? = null
    var translatedText: TextView? = null
    private val mTTS: TextToSpeech? = null
    private  var mTTS1:TextToSpeech? = null
    private var translateBtn: Button? = null
    var mstring: String? = null
    private var mSeekBarPitch: SeekBar? = null
    private var mSeekBarSpeed: SeekBar? = null
    private var mSeekBarPitch1: SeekBar? = null
    private var mSeekBarSpeed1: SeekBar? = null

    private var mButtonSpeak: Button? = null
    private var mButtonSpeak1: Button? = null
    private var sb:StringBuilder?=null
    private val CAMERA_REQUEST_CODE = 200
    private val STORAGE_REQUEST_CODE = 400
    private val IMAGE_PICK_GALLERY_CODE = 1000
    private val IMAGE_PICK_CAMERA_CODE = 1001
    private val WRITE_EXTERNAL_STORAGE_CODE = 1
    var text_toSpeech: TextToSpeech? =null
    var text_toSpeech1: TextToSpeech? =null

    private lateinit var cameraPermission: Array<String>
    private lateinit var storagePermission: Array<String>

    var image_uri: Uri? = null
    var path:Uri?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_to_text)

        mResultEt= findViewById(R.id.resultEt) as EditText
        translatedText = findViewById<View>(R.id.translatedText) as TextView
        mPreviewIv = findViewById<View>(R.id.imageIv) as ImageView
        addImageBtn = findViewById<View>(R.id.buttonclick) as Button
        translateBtn = findViewById<View>(R.id.button_Translate) as Button
        mButtonSpeak= findViewById(R.id.button_speak) as Button
        mButtonSpeak1= findViewById(R.id.button_speakTranslatedText) as Button

        mSeekBarPitch=findViewById(R.id.seek_bar_pitch) as SeekBar
        mSeekBarSpeed=findViewById(R.id.seek_bar_speed) as SeekBar
        mSeekBarPitch1=findViewById(R.id.seek_bar_pitchTranslatedText) as SeekBar
        mSeekBarSpeed1=findViewById(R.id.seek_bar_speedTranslatedText) as SeekBar

        getlanguageFrom= intent.getStringExtra("getlanguageFrom")
        InputLanguageFrom=  intent.getStringExtra("InputLanguageFrom")
        getlanguageTo= intent.getStringExtra("getlanguageTo")
        InputLanguageTo=  intent.getStringExtra("InputLanguageTo")

        addImageBtn!!.setOnClickListener(this)
        translateBtn!!.setOnClickListener(this)
        mButtonSpeak!!.setOnClickListener(this)
        mButtonSpeak1!!.setOnClickListener(this)

        imageIntent()
        image_uri= createImageUrl()
        EditTextSpeak()
        translatedTextSpeak()

    }

    override fun onClick(p0: View?) {
        if(p0!=null)
        {
        when(p0.id)
        {
            R.id.buttonclick ->
            {
                ShowImageImportDialog()
            }

            R.id.button_Translate ->
            {
                var entertextEDT: String = mResultEt!!.getText().toString()
                if (entertextEDT.isEmpty()  || image_uri!!.equals(null))
                {

                    Toast.makeText(this, "Please Choose Your Image", Toast.LENGTH_SHORT).show()

                }
                else
                {

                    val options = TranslatorOptions.Builder()
                        .setSourceLanguage(getlanguageFrom!!)
                        .setTargetLanguage(getlanguageTo!!)
                        .build()
                    val englishBengaliTranslator = Translation.getClient(options)

                    var conditions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        DownloadConditions.Builder()
                            .requireCharging()
                            .requireWifi()
                            .build()
                    }
                    else
                    {
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
            }

            R.id.button_speak ->
            {
                editTextSpeakSeekBar()
            }
            R.id.button_speakTranslatedText ->
            {
                translateTextSpeakSeekBar()
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
                val result= text_toSpeech!!.setLanguage(Locale(getlanguageFrom))

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
                val result= text_toSpeech1!!.setLanguage(Locale(getlanguageTo))

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


    private fun saveToText(mstring: String) {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(
            System.currentTimeMillis()
        )
        try {
            val path = Environment.getExternalStorageDirectory()
            val dir = File("$path/Image To Text/")
            dir.mkdir()
            val filename = "ImageFile_ $timestamp.txt"
            val file = File(dir, filename)
            val fw = FileWriter(file.absoluteFile)
            val bf = BufferedWriter(fw)
            bf.write(mstring)
            bf.close()
            Toast.makeText(this, "$filename is saved to\n $dir", Toast.LENGTH_LONG)
                .show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
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
