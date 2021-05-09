package com.example.gerobak_kopi_jenggo.models.cabang

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class cabangModel {
    @SerializedName("alamat_cabang")
    @Expose
    var alamat_cabang : String ? = null

    @SerializedName("nama_cabang")
    @Expose
    var nama_cabang : String ? = null

}