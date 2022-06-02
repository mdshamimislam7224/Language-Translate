package com.mdshamimislam.languagetranslate.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mdshamimislam.languagetranslate.R
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.*
class CropperActivity : AppCompatActivity() {
    var result: String? = null
    var fileUri: Uri? = null
    private var TAG:String="CropperActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cropper)
        readIntent()

        val dest_uri = StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString()
        val options: UCrop.Options = UCrop.Options()
        UCrop.of(fileUri!!, Uri.fromFile(File(cacheDir, dest_uri)))
            .withOptions(options)
            .withAspectRatio(16F, 9F)
            .useSourceImageAspectRatio()
            .withMaxResultSize(2000, 1800)
            .start(this)
    }

    private fun readIntent() {
        val intent = intent
        if (intent.extras != null) {
            result = intent.getStringExtra("DATA")
            Log.d(TAG,"Data="+result.toString())
            fileUri = Uri.parse(result)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            val resultUri: Uri = UCrop.getOutput(data!!)!!
            val reteunIntent = Intent()
            reteunIntent.putExtra("RESULT", resultUri.toString() + "")
            setResult(-1, reteunIntent)
            finish()
        } else if (requestCode == UCrop.RESULT_ERROR) {
            val cropError: Throwable = UCrop.getError(data!!)!!
            Toast.makeText(this, cropError.toString(), Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this, "Please Add Clean Picture", Toast.LENGTH_SHORT).show()
        }
    }
}