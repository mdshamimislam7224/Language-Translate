package com.mdshamimislam.languagetranslate.model_class

 open class model_class_of_Country_Image
{
    private var countryName: String? = null
    private var countryImage = 0

    fun getCountryName(): String? {
        return countryName
    }

    fun getCountryImage(): Int {
        return countryImage
    }

    fun setCountryName(countryName: String?) {
        this.countryName = countryName
    }

    fun setCountryImage(countryImage: Int) {
        this.countryImage = countryImage
    }

}