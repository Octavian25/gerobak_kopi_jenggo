package com.example.gerobak_kopi_jenggo.models.operasional

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OperasionalResponse {
    @SerializedName("status")
    @Expose
    var status : String ? = null
}