package com.example.gerobak_kopi_jenggo.utility

import io.easyprefs.Prefs
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class helper {
    fun getDate() : String{
        val sdf = SimpleDateFormat("yyyy-M-dd")
        val currentDate = sdf.format(Date())
        return currentDate.toString()
    }

    fun convertDate() : String{
        val sdf = SimpleDateFormat("yyyy-M-dd")
        val currentDate = sdf.format(Date())
        val date = currentDate.toString()
        val hasil = date.split("-")
        var bulan = ""
        when(hasil[1]){
            "1" -> bulan = "JANUARI"
            "2" -> bulan = "FEBRUARI"
            "3" -> bulan = "MARET"
            "4" -> bulan = "APRIL"
            "5" -> bulan = "MEI"
            "6" -> bulan = "JUNI"
            "7" -> bulan = "JULI"
            "8" -> bulan = "AGUSTUS"
            "9" -> bulan = "SEPTEMBER"
            "10" -> bulan = "OKTOBER"
            "11" -> bulan = "NOVEMBER"
            "12" -> bulan = "DESEMBER"
        }

        return "${hasil[2]} $bulan ${hasil[0]}";
    }
    fun getPref(nama :String) : String {
        return Prefs.read().content(nama, "KOSONG")
    }
    fun rupiah(number: Double): String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        if(number == null) return "0" else return numberFormat.format(number).toString()
    }

}
