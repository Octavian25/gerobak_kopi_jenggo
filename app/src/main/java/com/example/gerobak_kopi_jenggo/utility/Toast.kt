package com.example.gerobak_kopi_jenggo.utility

import android.content.Context
import com.prathameshmore.toastylibrary.Toasty

fun showToast(context: Context, message : String, jenis : String){
    val toasty = Toasty(context)
    when(jenis){
        "success" -> {
            toasty.successToasty(context, message, Toasty.LENGTH_LONG, Toasty.TOP);
        }
        "error" -> {
            toasty.dangerToasty(context, message, Toasty.LENGTH_LONG, Toasty.TOP);
        }
        "info" -> {
            toasty.primaryToasty(context, message, Toasty.LENGTH_LONG, Toasty.TOP);
        }
    }
}