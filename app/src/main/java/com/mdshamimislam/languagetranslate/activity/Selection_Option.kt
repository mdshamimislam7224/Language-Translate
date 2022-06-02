package com.mdshamimislam.languagetranslate.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.mdshamimislam.languagetranslate.R
import com.mdshamimislam.languagetranslate.adapter.countryImageAdapter
import com.mdshamimislam.languagetranslate.model_class.LanguageTranslate
import com.mdshamimislam.languagetranslate.model_class.model_ClassMethod_Image_of_language
import kotlinx.android.synthetic.main.language_select.view.*

class Selection_Option : AppCompatActivity() ,View.OnClickListener {
    private var card3: CardView? = null
    private  var card2:CardView? = null
    private  var card1:CardView? = null
    private  var card4:CardView? = null
    private var getlanguage:String?=null
    private var get_Audio_InputLanguage:String?=null
    private var countryNameAndImageAdpter: countryImageAdapter? = null
    var spinnerTo: Spinner? =null
    var spinnerFrom: Spinner? = null
    var imageToTextSubmitBtn: Button? = null
    var imageToTextCancelBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_option)
        card3 = findViewById<View>(R.id.card1234) as CardView
        card2 = findViewById<View>(R.id.card12345) as CardView
        card4 = findViewById<View>(R.id.card123456) as CardView
        card1 = findViewById<View>(R.id.card1234567) as CardView


        card3!!.setOnClickListener(this)
        card2!!.setOnClickListener(this)
        card4!!.setOnClickListener(this)
        card1!!.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.card1234 -> {

                val alert = LayoutInflater.from(this).inflate(R.layout.language_select, null)
                spinnerFrom = alert.findViewById<View>(R.id.spinnerFrom) as Spinner
                spinnerTo = alert.findViewById<View>(R.id.spinnerTo) as Spinner
                imageToTextCancelBtn =
                    alert.findViewById<View>(R.id.selectLanguageCancelBtn) as Button
                imageToTextSubmitBtn =
                    alert.findViewById<View>(R.id.selectLanguageSubmitBtn) as Button
                //for Spinner
                countryNameAndImageAdpter = countryImageAdapter(
                    this,
                    model_ClassMethod_Image_of_language.getCountryAndImage()
                )
                alert.spinnerFrom!!.setAdapter(countryNameAndImageAdpter)
                alert.spinnerTo!!.setAdapter(countryNameAndImageAdpter)

                val mBuilder = AlertDialog.Builder(this)
                    .setView(alert)
                //show dialog
                val mAlertDialog = mBuilder.show()
                mAlertDialog.setCanceledOnTouchOutside(false)

                imageToTextSubmitBtn!!.setOnClickListener {
                    var intent = Intent(this, Image_To_Text::class.java)
                    val i: Int = spinnerFrom!!.getSelectedItemPosition()
                    LanguageTranslate.spinnerFromMehod(i)
                    val j: Int = spinnerTo!!.getSelectedItemPosition()
                    LanguageTranslate.spinnerToMehod(j)

                    intent.putExtra("getlanguageFrom", LanguageTranslate.getlanguageFrom)
                    intent.putExtra("InputLanguageFrom", LanguageTranslate.get_Audio_InputLanguageFrom)

                    intent.putExtra("getlanguageTo", LanguageTranslate.getlanguageTo)
                    intent.putExtra("InputLanguageTo", LanguageTranslate.get_Audio_InputLanguageTo)
                    startActivity(intent)
                    finish()

                }
            }

            R.id.card12345 ->
            {
                var intent = Intent(this, VoiceToText::class.java)
                startActivity(intent)
            }
        }
    }
}