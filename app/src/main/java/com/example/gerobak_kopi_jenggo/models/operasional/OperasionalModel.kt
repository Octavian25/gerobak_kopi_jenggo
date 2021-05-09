package com.example.gerobak_kopi_jenggo.models.operasional

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OperasionalModel {
    @SerializedName("keterangan")
    @Expose
    var keterangan : String ? = null

    @SerializedName("harga")
    @Expose
    var harga:String? = null

    @SerializedName("id_cabang")
    @Expose
    var id_cabang : String ? = null

    @SerializedName("input_by")
    @Expose
    var input_by : String ? = null

    @SerializedName("tanggal")
    @Expose
    var tanggal : String ? = null
}