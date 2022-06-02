package com.mdshamimislam.languagetranslate.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

import android.widget.ImageView
import android.widget.TextView
import com.mdshamimislam.languagetranslate.R
import com.mdshamimislam.languagetranslate.model_class.model_class_of_Country_Image
class countryImageAdapter : BaseAdapter {

    private var context: Context? = null
    var countryNameImages: List<model_class_of_Country_Image>? = null

    constructor(context: Context?, countryNameImages: List<model_class_of_Country_Image>?) : super() {
        this.context = context
        this.countryNameImages = countryNameImages
    }


    override fun getCount(): Int {
        return if (countryNameImages != null) countryNameImages!!.size else 0

    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val rootView: View = LayoutInflater.from(context)
            .inflate(R.layout.single_item_country_image, p2, false)
        val txtname = rootView.findViewById<TextView>(R.id.countryName)
        val image = rootView.findViewById<ImageView>(R.id.countryImage)
        txtname.setText(countryNameImages!![p0].getCountryName())
        image.setImageResource(countryNameImages!![p0].getCountryImage())
        return rootView
    }
}
