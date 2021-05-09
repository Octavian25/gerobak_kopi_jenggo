package com.example.gerobak_kopi_jenggo.models.bahan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BahanResponse {
    @SerializedName("status")
    @Expose
    var status : String ? = null
}