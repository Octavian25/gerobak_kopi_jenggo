package com.example.gerobak_kopi_jenggo.models.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class loginCabangResponse {
    @SerializedName("id_cabang")
    @Expose
    var id_cabang : String ? = null

    @SerializedName("level")
    @Expose
    var level : String ? = null
}