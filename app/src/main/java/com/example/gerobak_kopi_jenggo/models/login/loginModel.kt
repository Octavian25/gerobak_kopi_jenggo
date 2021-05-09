package com.example.gerobak_kopi_jenggo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginModel{
    @SerializedName("username")
    @Expose
    var username : String ? = null

    @SerializedName("password")
    @Expose
    var password:String? = null
}