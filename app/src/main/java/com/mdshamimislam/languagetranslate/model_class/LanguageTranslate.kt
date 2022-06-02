package com.mdshamimislam.languagetranslate.model_class

import com.google.mlkit.nl.translate.TranslateLanguage

class LanguageTranslate
{
    companion object{
       var getlanguageFrom:String?=null
        var get_Audio_InputLanguageFrom:String?=null

        var getlanguageTo:String?=null
        var get_Audio_InputLanguageTo:String?=null

        fun spinnerFromMehod(value:Int)
        {
            if (value == 0) {
                getlanguageFrom= TranslateLanguage.AFRIKAANS
                get_Audio_InputLanguageFrom="af_ZA"
            }

            else if (value == 1) {
                getlanguageFrom= TranslateLanguage.ALBANIAN
                get_Audio_InputLanguageFrom="sq-AL"
            }


            else if (value == 2) {

                getlanguageFrom= TranslateLanguage.ARABIC
                get_Audio_InputLanguageFrom="ar_SA"

            } else if (value == 3) {
                getlanguageFrom= TranslateLanguage.BENGALI
                get_Audio_InputLanguageFrom="bn-BD"

            } else if (value == 4)
            {
                getlanguageFrom= TranslateLanguage.BELARUSIAN
                get_Audio_InputLanguageFrom="be_BY"

            } else if (value == 5) {
                getlanguageFrom= TranslateLanguage.BULGARIAN
                get_Audio_InputLanguageFrom="bg_BG"

            } else if (value == 6) {
                getlanguageFrom= TranslateLanguage.URDU
                get_Audio_InputLanguageFrom="ur-PK"

            } else if (value == 7) {
                getlanguageFrom= TranslateLanguage.CHINESE
                get_Audio_InputLanguageFrom="Zh-CN"

            } else if (value == 8) {
                getlanguageFrom= TranslateLanguage.CROATIAN
                get_Audio_InputLanguageFrom="hr-HR"


            }

            else if (value == 9) {
                getlanguageFrom= TranslateLanguage.CZECH
                get_Audio_InputLanguageFrom="cs-CZ"
            }

            else if (value == 10) {
                getlanguageFrom= TranslateLanguage.DANISH
                get_Audio_InputLanguageFrom="da-DK"

            }

            else if (value == 11) {
                getlanguageFrom= TranslateLanguage.DUTCH
                get_Audio_InputLanguageFrom="nl-NL"

            }
            else if (value == 12) {
                getlanguageFrom= TranslateLanguage.ENGLISH
                get_Audio_InputLanguageFrom="en-US"
            } else if (value == 13)
            {
                getlanguageFrom= TranslateLanguage.ESPERANTO
                get_Audio_InputLanguageFrom="eo_"

            }
            else if (value == 14) {
                getlanguageFrom= TranslateLanguage.ESTONIAN
                get_Audio_InputLanguageFrom="et-EE"

            }
            else if (value == 15) {
                getlanguageFrom= TranslateLanguage.FINNISH
                get_Audio_InputLanguageFrom="fi-FI"
            }
            else if (value == 16) {
                getlanguageFrom= TranslateLanguage.FRENCH
                get_Audio_InputLanguageFrom="fr-FR"
            }

            else if (value == 17) {
                getlanguageFrom= TranslateLanguage.GEORGIAN
                get_Audio_InputLanguageFrom="ka-GE"

            }
            else if (value == 18) {
                getlanguageFrom= TranslateLanguage.GERMAN
                get_Audio_InputLanguageFrom="de-DE"
            }
            else if (value == 19) {
                getlanguageFrom= TranslateLanguage.GREEK
                get_Audio_InputLanguageFrom="el-GR"
            }
            else if (value == 20) {
                getlanguageFrom= TranslateLanguage.HAITIAN_CREOLE
                get_Audio_InputLanguageFrom="ht"
            }

            else if (value == 21) {
                getlanguageFrom= TranslateLanguage.HEBREW
                get_Audio_InputLanguageFrom="iw-IL"
            }

            else if (value == 22) {
                getlanguageFrom= TranslateLanguage.HINDI
                get_Audio_InputLanguageFrom="hi-IN"
            }

            else if (value == 23) {
                getlanguageFrom= TranslateLanguage.HUNGARIAN
                get_Audio_InputLanguageFrom="hu-HU"
            }

            else if (value == 24) {
                getlanguageFrom= TranslateLanguage.ICELANDIC
                get_Audio_InputLanguageFrom="is-IS"
            }

            else if (value == 25) {
                getlanguageFrom= TranslateLanguage.INDONESIAN
                get_Audio_InputLanguageFrom="id-ID"
            }

            else if (value == 26) {
                getlanguageFrom= TranslateLanguage.IRISH
                get_Audio_InputLanguageFrom="ga-GL"
            }
            else if (value == 27) {
                getlanguageFrom= TranslateLanguage.ITALIAN
                get_Audio_InputLanguageFrom="it-IT"
            }

            else if (value == 28) {
                getlanguageFrom= TranslateLanguage.JAPANESE
                get_Audio_InputLanguageFrom="ja-JP"
            }

            else if (value == 29) {
                getlanguageFrom= TranslateLanguage.KOREAN
                get_Audio_InputLanguageFrom="ko-KR"
            }

            else if (value == 30) {
                getlanguageFrom= TranslateLanguage.LITHUANIAN
                get_Audio_InputLanguageFrom="lt-LT"

            }
            else if (value == 31) {
                getlanguageFrom= TranslateLanguage.LATVIAN
                get_Audio_InputLanguageFrom="lv-LV"
            }

            else if (value == 32) {
                getlanguageFrom= TranslateLanguage.MALAY
                get_Audio_InputLanguageFrom="ms-MY"
            }

            else if (value == 33) {
                getlanguageFrom= TranslateLanguage.NORWEGIAN
                get_Audio_InputLanguageFrom="no-NO"
            }

            else if (value == 34) {
                getlanguageFrom= TranslateLanguage.PERSIAN
                get_Audio_InputLanguageFrom="fa-IR"
            }

            else if (value == 35) {
                getlanguageFrom= TranslateLanguage.POLISH
                get_Audio_InputLanguageFrom="pl-PL"
            }

            else if (value == 36) {
                getlanguageFrom= TranslateLanguage.PORTUGUESE
                get_Audio_InputLanguageFrom="pt-PT"
            }

            else if (value == 37) {
                getlanguageFrom= TranslateLanguage.ROMANIAN
                get_Audio_InputLanguageFrom="ro-RO"
            }

            else if (value == 38) {
                getlanguageFrom= TranslateLanguage.RUSSIAN
                get_Audio_InputLanguageFrom="ru-RU"
            }
            else if (value == 39) {
                getlanguageFrom= TranslateLanguage.SLOVAK
                get_Audio_InputLanguageFrom="sk-SK"
            }

            else if (value == 40) {
                getlanguageFrom= TranslateLanguage.SLOVENIAN
                get_Audio_InputLanguageFrom="sl-SI"
            }

            else if (value == 41) {
                getlanguageFrom= TranslateLanguage.SPANISH
                get_Audio_InputLanguageFrom="es-ES"
            }
            else if (value == 42) {
                getlanguageFrom= TranslateLanguage.SWEDISH
                get_Audio_InputLanguageFrom="sv-SE"
            }

            else if (value == 43) {
                getlanguageFrom= TranslateLanguage.SWAHILI
                get_Audio_InputLanguageFrom="sw-TZ"
            }

            else if (value == 44) {
                getlanguageFrom= TranslateLanguage.TAGALOG
                get_Audio_InputLanguageFrom="tl-TL"
            }

            else if (value == 45) {
                getlanguageFrom= TranslateLanguage.TAMIL
                get_Audio_InputLanguageFrom="ta-SG"
            }

            else if (value == 46) {
                getlanguageFrom= TranslateLanguage.TELUGU
                get_Audio_InputLanguageFrom="te-IN"

            }

            else if (value == 47) {
                getlanguageFrom= TranslateLanguage.THAI
                get_Audio_InputLanguageFrom="th-TH"

            }
            else if (value == 48) {
                getlanguageFrom= TranslateLanguage.TURKISH
                get_Audio_InputLanguageFrom="tr-TR"
            }

            else if (value == 49) {
                getlanguageFrom= TranslateLanguage.UKRAINIAN
                get_Audio_InputLanguageFrom="uk-UA"

            }

            else if (value == 50) {
                getlanguageFrom= TranslateLanguage.VIETNAMESE
                get_Audio_InputLanguageFrom="vi-VN"

            }
        }



        fun spinnerToMehod(value:Int)
        {
            if (value == 0) {
                getlanguageTo= TranslateLanguage.AFRIKAANS
                get_Audio_InputLanguageTo="af_ZA"
            }

            else if (value == 1) {
                getlanguageTo= TranslateLanguage.ALBANIAN
                get_Audio_InputLanguageTo="sq-AL"
            }


            else if (value == 2) {

                getlanguageTo= TranslateLanguage.ARABIC
                get_Audio_InputLanguageTo="ar_SA"

            } else if (value == 3) {
                getlanguageTo= TranslateLanguage.BENGALI
                get_Audio_InputLanguageTo="bn-BD"

            } else if (value == 4)
            {
                getlanguageTo= TranslateLanguage.BELARUSIAN
                get_Audio_InputLanguageTo="be_BY"

            } else if (value == 5) {
                getlanguageTo= TranslateLanguage.BULGARIAN
                get_Audio_InputLanguageTo="bg_BG"

            } else if (value == 6) {
                getlanguageTo= TranslateLanguage.URDU
                get_Audio_InputLanguageTo="ur-PK"

            } else if (value == 7) {
                getlanguageTo= TranslateLanguage.CHINESE
                get_Audio_InputLanguageTo="Zh-CN"

            } else if (value == 8) {
                getlanguageTo= TranslateLanguage.CROATIAN
                get_Audio_InputLanguageTo="hr-HR"
            }

            else if (value == 9) {
                getlanguageTo= TranslateLanguage.CZECH
                get_Audio_InputLanguageTo="cs-CZ"
            }

            else if (value == 10) {
                getlanguageTo= TranslateLanguage.DANISH
                get_Audio_InputLanguageTo="da-DK"

            }

            else if (value == 11) {
                getlanguageTo= TranslateLanguage.DUTCH
                get_Audio_InputLanguageTo="nl-NL"

            }
            else if (value == 12) {
                getlanguageTo= TranslateLanguage.ENGLISH
                get_Audio_InputLanguageTo="en-US"
            } else if (value == 13)
            {
                getlanguageTo= TranslateLanguage.ESPERANTO
                get_Audio_InputLanguageTo="eo_"

            }
            else if (value == 14) {
                getlanguageTo= TranslateLanguage.ESTONIAN
                get_Audio_InputLanguageTo="et-EE"

            }
            else if (value == 15) {
                getlanguageTo= TranslateLanguage.FINNISH
                get_Audio_InputLanguageTo="fi-FI"
            }
            else if (value == 16) {
                getlanguageTo= TranslateLanguage.FRENCH
                get_Audio_InputLanguageTo="fr-FR"
            }

            else if (value == 17) {
                getlanguageTo= TranslateLanguage.GEORGIAN
                get_Audio_InputLanguageTo="ka-GE"

            }
            else if (value == 18) {
                getlanguageTo= TranslateLanguage.GERMAN
                get_Audio_InputLanguageTo="de-DE"
            }
            else if (value == 19) {
                getlanguageTo= TranslateLanguage.GREEK
                get_Audio_InputLanguageTo="el-GR"
            }
            else if (value == 20) {
                getlanguageTo= TranslateLanguage.HAITIAN_CREOLE
                get_Audio_InputLanguageTo="ht"
            }

            else if (value == 21) {
                getlanguageTo= TranslateLanguage.HEBREW
                get_Audio_InputLanguageTo="iw-IL"
            }

            else if (value == 22) {
                getlanguageTo= TranslateLanguage.HINDI
                get_Audio_InputLanguageTo="hi-IN"
            }

            else if (value == 23) {
                getlanguageTo= TranslateLanguage.HUNGARIAN
                get_Audio_InputLanguageTo="hu-HU"
            }

            else if (value == 24) {
                getlanguageTo= TranslateLanguage.ICELANDIC
                get_Audio_InputLanguageTo="is-IS"
            }

            else if (value == 25) {
                getlanguageTo= TranslateLanguage.INDONESIAN
                get_Audio_InputLanguageTo="id-ID"
            }

            else if (value == 26) {
                getlanguageTo= TranslateLanguage.IRISH
                get_Audio_InputLanguageTo="ga-GL"
            }
            else if (value == 27) {
                getlanguageTo= TranslateLanguage.ITALIAN
                get_Audio_InputLanguageTo="it-IT"
            }

            else if (value == 28) {
                getlanguageTo= TranslateLanguage.JAPANESE
                get_Audio_InputLanguageTo="ja-JP"
            }

            else if (value == 29) {
                getlanguageTo= TranslateLanguage.KOREAN
                get_Audio_InputLanguageTo="ko-KR"
            }

            else if (value == 30) {
                getlanguageTo= TranslateLanguage.LITHUANIAN
                get_Audio_InputLanguageTo="lt-LT"

            }
            else if (value == 31) {
                getlanguageTo= TranslateLanguage.LATVIAN
                get_Audio_InputLanguageTo="lv-LV"
            }

            else if (value == 32) {
                getlanguageTo= TranslateLanguage.MALAY
                get_Audio_InputLanguageTo="ms-MY"
            }

            else if (value == 33) {
                getlanguageTo= TranslateLanguage.NORWEGIAN
                get_Audio_InputLanguageTo="no-NO"
            }

            else if (value == 34) {
                getlanguageTo= TranslateLanguage.PERSIAN
                get_Audio_InputLanguageTo="fa-IR"
            }

            else if (value == 35) {
                getlanguageTo= TranslateLanguage.POLISH
                get_Audio_InputLanguageTo="pl-PL"
            }

            else if (value == 36) {
                getlanguageTo= TranslateLanguage.PORTUGUESE
                get_Audio_InputLanguageTo="pt-PT"
            }

            else if (value == 37) {
                getlanguageTo= TranslateLanguage.ROMANIAN
                get_Audio_InputLanguageTo="ro-RO"
            }

            else if (value == 38) {
                getlanguageTo= TranslateLanguage.RUSSIAN
                get_Audio_InputLanguageTo="ru-RU"
            }
            else if (value == 39) {
                getlanguageTo= TranslateLanguage.SLOVAK
                get_Audio_InputLanguageTo="sk-SK"
            }

            else if (value == 40) {
                getlanguageTo= TranslateLanguage.SLOVENIAN
                get_Audio_InputLanguageTo="sl-SI"
            }

            else if (value == 41) {
                getlanguageTo= TranslateLanguage.SPANISH
                get_Audio_InputLanguageTo="es-ES"
            }
            else if (value == 42) {
                getlanguageTo= TranslateLanguage.SWEDISH
                get_Audio_InputLanguageTo="sv-SE"
            }

            else if (value == 43) {
                getlanguageTo= TranslateLanguage.SWAHILI
                get_Audio_InputLanguageTo="sw-TZ"
            }

            else if (value == 44) {
                getlanguageTo= TranslateLanguage.TAGALOG
                get_Audio_InputLanguageTo="tl-TL"
            }

            else if (value == 45) {
                getlanguageTo= TranslateLanguage.TAMIL
                get_Audio_InputLanguageTo="ta-SG"
            }

            else if (value == 46) {
                getlanguageTo= TranslateLanguage.TELUGU
                get_Audio_InputLanguageTo="te-IN"

            }

            else if (value == 47) {
                getlanguageTo= TranslateLanguage.THAI
                get_Audio_InputLanguageTo="th-TH"

            }
            else if (value == 48) {
                getlanguageTo= TranslateLanguage.TURKISH
                get_Audio_InputLanguageTo="tr-TR"
            }

            else if (value == 49) {
                getlanguageTo= TranslateLanguage.UKRAINIAN
                get_Audio_InputLanguageTo="uk-UA"

            }

            else if (value == 50) {
                getlanguageTo= TranslateLanguage.VIETNAMESE
                get_Audio_InputLanguageTo="vi-VN"

            }
        }
    }
}


  /*private fun spinnerToMehod() {
    val i: Int = spinnerTo!!.getSelectedItemPosition()

    if (i == 0) {
        getlanguage= TranslateLanguage.AFRIKAANS
        get_Audio_InputLanguage="af_ZA"
        Toast.makeText(this, "Selected AFRIKAANS", Toast.LENGTH_SHORT).show()
    }

    else if (i == 1) {
        getlanguage= TranslateLanguage.ALBANIAN
        get_Audio_InputLanguage="sq-AL"
        Toast.makeText(this, "Selected ALBANIAN", Toast.LENGTH_SHORT).show()
    }


    else if (i == 2) {

        getlanguage= TranslateLanguage.ARABIC
        get_Audio_InputLanguage="ar_SA"
        Toast.makeText(this, "Selected ARABIC", Toast.LENGTH_SHORT).show()

    } else if (i == 3) {
        getlanguage= TranslateLanguage.BENGALI
        get_Audio_InputLanguage="bn-BD"
        Toast.makeText(this, "Selected BENGALI", Toast.LENGTH_SHORT).show()

    } else if (i == 4)
    {
        getlanguage= TranslateLanguage.BELARUSIAN
        get_Audio_InputLanguage="be_BY"
        Toast.makeText(this, "Selected BELARUSIAN", Toast.LENGTH_SHORT).show()

    } else if (i == 5) {
        getlanguage= TranslateLanguage.BULGARIAN
        get_Audio_InputLanguage="bg_BG"
        Toast.makeText(this, "Selected BULGARIAN", Toast.LENGTH_SHORT).show()

    } else if (i == 6) {
        getlanguage= TranslateLanguage.URDU
        get_Audio_InputLanguage="ur-PK"
        Toast.makeText(this, "Selected URDU", Toast.LENGTH_SHORT).show()

    } else if (i == 7) {
        getlanguage= TranslateLanguage.CHINESE
        get_Audio_InputLanguage="Zh-CN"
        Toast.makeText(this, "Selected CHINESE", Toast.LENGTH_SHORT).show()

    } else if (i == 8) {
        getlanguage= TranslateLanguage.CROATIAN
        get_Audio_InputLanguage="hr-HR"
        Toast.makeText(this, "Selected CROATIAN", Toast.LENGTH_SHORT).show()


    }

    else if (i == 9) {
        getlanguage= TranslateLanguage.CZECH
        get_Audio_InputLanguage="cs-CZ"
        Toast.makeText(this, "Selected CZECH", Toast.LENGTH_SHORT).show()
    }

    else if (i == 10) {
        getlanguage= TranslateLanguage.DANISH
        get_Audio_InputLanguage="da-DK"
        Toast.makeText(this, "Selected DANISH", Toast.LENGTH_SHORT).show()

    }

    else if (i == 11) {
        getlanguage= TranslateLanguage.DUTCH
        get_Audio_InputLanguage="nl-NL"
        Toast.makeText(this, "Selected DUTCH", Toast.LENGTH_SHORT).show()

    }
    else if (i == 12) {
        getlanguage= TranslateLanguage.ENGLISH
        get_Audio_InputLanguage="en-US"
        Toast.makeText(this, "Selected ENGLISH", Toast.LENGTH_SHORT).show()
    } else if (i == 13)
    {
        getlanguage= TranslateLanguage.ESPERANTO
        get_Audio_InputLanguage="eo_"
        Toast.makeText(this, "Selected ESPERANTO", Toast.LENGTH_SHORT).show()

    }
    else if (i == 14) {
        getlanguage= TranslateLanguage.ESTONIAN
        get_Audio_InputLanguage="et-EE"
        Toast.makeText(this, "Selected ESTONIAN", Toast.LENGTH_SHORT).show()

    }
    else if (i == 15) {
        getlanguage= TranslateLanguage.FINNISH
        get_Audio_InputLanguage="fi-FI"
        Toast.makeText(this, "Selected FINNISH", Toast.LENGTH_SHORT).show()
    }
    else if (i == 16) {
        getlanguage= TranslateLanguage.FRENCH
        get_Audio_InputLanguage="fr-FR"
        Toast.makeText(this, "Selected FRENCH", Toast.LENGTH_SHORT).show()
    }

    else if (i == 17) {
        getlanguage= TranslateLanguage.GEORGIAN
        get_Audio_InputLanguage="ka-GE"
        Toast.makeText(this, "Selected GEORGIAN", Toast.LENGTH_SHORT).show()

    }
    else if (i == 18) {
        getlanguage= TranslateLanguage.GERMAN
        get_Audio_InputLanguage="de-DE"
        Toast.makeText(this, "Selected GERMAN", Toast.LENGTH_SHORT).show()
    }
    else if (i == 19) {
        getlanguage= TranslateLanguage.GREEK
        get_Audio_InputLanguage="el-GR"
        Toast.makeText(this, "Selected GREEK", Toast.LENGTH_SHORT).show()
    }
    else if (i == 20) {
        getlanguage= TranslateLanguage.HAITIAN_CREOLE
        get_Audio_InputLanguage="ht"
        Toast.makeText(this, "Selected HAITIAN_CREOLE", Toast.LENGTH_SHORT).show()
    }

    else if (i == 21) {
        getlanguage= TranslateLanguage.HEBREW
        get_Audio_InputLanguage="iw-IL"
        Toast.makeText(this, "Selected HEBREW", Toast.LENGTH_SHORT).show()
    }

    else if (i == 22) {
        getlanguage= TranslateLanguage.HINDI
        get_Audio_InputLanguage="hi-IN"
        Toast.makeText(this, "Selected HINDI", Toast.LENGTH_SHORT).show()
    }

    else if (i == 23) {
        getlanguage= TranslateLanguage.HUNGARIAN
        get_Audio_InputLanguage="hu-HU"
        Toast.makeText(this, "Selected HUNGARIAN", Toast.LENGTH_SHORT).show()
    }

    else if (i == 24) {
        getlanguage= TranslateLanguage.ICELANDIC
        get_Audio_InputLanguage="is-IS"
        Toast.makeText(this, "Selected ICELANDIC", Toast.LENGTH_SHORT).show()
    }

    else if (i == 25) {
        getlanguage= TranslateLanguage.INDONESIAN
        get_Audio_InputLanguage="id-ID"
        Toast.makeText(this, "Selected INDONESIAN", Toast.LENGTH_SHORT).show()
    }

    else if (i == 26) {
        getlanguage= TranslateLanguage.IRISH
        get_Audio_InputLanguage="ga-GL"
        Toast.makeText(this, "Selected IRISH", Toast.LENGTH_SHORT).show()
    }
    else if (i == 27) {
        getlanguage= TranslateLanguage.ITALIAN
        get_Audio_InputLanguage="it-IT"
        Toast.makeText(this, "Selected ITALIAN", Toast.LENGTH_SHORT).show()
    }

    else if (i == 28) {
        getlanguage= TranslateLanguage.JAPANESE
        get_Audio_InputLanguage="ja-JP"
        Toast.makeText(this, "Selected JAPANESE", Toast.LENGTH_SHORT).show()
    }

    else if (i == 29) {
        getlanguage= TranslateLanguage.KOREAN
        get_Audio_InputLanguage="ko-KR"
        Toast.makeText(this, "Selected KOREAN", Toast.LENGTH_SHORT).show()
    }

    else if (i == 30) {
        getlanguage= TranslateLanguage.LITHUANIAN
        get_Audio_InputLanguage="lt-LT"
        Toast.makeText(this, "Selected LITHUANIAN", Toast.LENGTH_SHORT).show()

    }
    else if (i == 31) {
        getlanguage= TranslateLanguage.LATVIAN
        get_Audio_InputLanguage="lv-LV"
        Toast.makeText(this, "Selected LATVIAN", Toast.LENGTH_SHORT).show()
    }

    else if (i == 32) {
        getlanguage= TranslateLanguage.MALAY
        get_Audio_InputLanguage="ms-MY"
        Toast.makeText(this, "Selected MALAY", Toast.LENGTH_SHORT).show()
    }

    else if (i == 33) {
        getlanguage= TranslateLanguage.NORWEGIAN
        get_Audio_InputLanguage="no-NO"
        Toast.makeText(this, "Selected NORWEGIAN", Toast.LENGTH_SHORT).show()
    }

    else if (i == 34) {
        getlanguage= TranslateLanguage.PERSIAN
        get_Audio_InputLanguage="fa-IR"
        Toast.makeText(this, "Selected PERSIAN", Toast.LENGTH_SHORT).show()
    }

    else if (i == 35) {
        getlanguage= TranslateLanguage.POLISH
        get_Audio_InputLanguage="pl-PL"
        Toast.makeText(this, "Selected POLISH", Toast.LENGTH_SHORT).show()
    }

    else if (i == 36) {
        getlanguage= TranslateLanguage.PORTUGUESE
        get_Audio_InputLanguage="pt-PT"
        Toast.makeText(this, "Selected PORTUGUESE", Toast.LENGTH_SHORT).show()
    }

    else if (i == 37) {
        getlanguage= TranslateLanguage.ROMANIAN
        get_Audio_InputLanguage="ro-RO"
        Toast.makeText(this, "Selected ROMANIAN", Toast.LENGTH_SHORT).show()
    }

    else if (i == 38) {
        getlanguage= TranslateLanguage.RUSSIAN
        get_Audio_InputLanguage="ru-RU"
        Toast.makeText(this, "Selected RUSSIAN", Toast.LENGTH_SHORT).show()
    }
    else if (i == 39) {
        getlanguage= TranslateLanguage.SLOVAK
        get_Audio_InputLanguage="sk-SK"
        Toast.makeText(this, "Selected SLOVAK", Toast.LENGTH_SHORT).show()
    }

    else if (i == 40) {
        getlanguage= TranslateLanguage.SLOVENIAN
        get_Audio_InputLanguage="sl-SI"
        Toast.makeText(this, "Selected SLOVENIAN", Toast.LENGTH_SHORT).show()
    }

    else if (i == 41) {
        getlanguage= TranslateLanguage.SPANISH
        get_Audio_InputLanguage="es-ES"
        Toast.makeText(this, "Selected SPANISH", Toast.LENGTH_SHORT).show()
    }
    else if (i == 42) {
        getlanguage= TranslateLanguage.SWEDISH
        get_Audio_InputLanguage="sv-SE"
        Toast.makeText(this, "Selected SWEDISH", Toast.LENGTH_SHORT).show()
    }

    else if (i == 43) {
        getlanguage= TranslateLanguage.SWAHILI
        get_Audio_InputLanguage="sw-TZ"
        Toast.makeText(this, "Selected SWAHILI", Toast.LENGTH_SHORT).show()
    }

    else if (i == 44) {
        getlanguage= TranslateLanguage.TAGALOG
        get_Audio_InputLanguage="tl-TL"
        Toast.makeText(this, "Selected TAGALOG", Toast.LENGTH_SHORT).show()
    }

    else if (i == 45) {
        getlanguage= TranslateLanguage.TAMIL
        get_Audio_InputLanguage="ta-SG"
        Toast.makeText(this, "Selected TAMIL", Toast.LENGTH_SHORT).show()
    }

    else if (i == 46) {
        getlanguage= TranslateLanguage.TELUGU
        get_Audio_InputLanguage="te-IN"
        Toast.makeText(this, "Selected TELUGU", Toast.LENGTH_SHORT).show()

    }

    else if (i == 47) {
        getlanguage= TranslateLanguage.THAI
        get_Audio_InputLanguage="th-TH"
        Toast.makeText(this, "Selected THAI", Toast.LENGTH_SHORT).show()

    }
    else if (i == 48) {
        getlanguage= TranslateLanguage.TURKISH
        get_Audio_InputLanguage="tr-TR"
        Toast.makeText(this, "Selected TURKISH", Toast.LENGTH_SHORT).show()
    }

    else if (i == 49) {
        getlanguage= TranslateLanguage.UKRAINIAN
        get_Audio_InputLanguage="uk-UA"
        Toast.makeText(this, "Selected UKRAINIAN", Toast.LENGTH_SHORT).show()

    }

    else if (i == 50) {
        getlanguage= TranslateLanguage.VIETNAMESE
        get_Audio_InputLanguage="vi-VN"
        Toast.makeText(this, "Selected VIETNAMESE", Toast.LENGTH_SHORT).show()

    }  else {
        Toast.makeText(this, "Invalid Search", Toast.LENGTH_SHORT).show()
    }
}*/