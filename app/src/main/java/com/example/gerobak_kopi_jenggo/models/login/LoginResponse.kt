package com.example.gerobak_kopi_jenggo.models.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class loginResponse {
    @SerializedName("status")
    @Expose
    var status : String ? = null

    @SerializedName("token")
    @Expose
    var token : String ? = null

    @SerializedName("cabang")
    @Expose
    var cabang : String ? = null

    @SerializedName("level")
    @Expose
    var level : String ? = null
}