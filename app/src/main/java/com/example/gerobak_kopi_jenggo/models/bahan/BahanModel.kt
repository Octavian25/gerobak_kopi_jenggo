package com.example.gerobak_kopi_jenggo.models.bahan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BahanModel {
    @SerializedName("tanggal")
    @Expose
    var tanggal : String ? = null

    @SerializedName("id_cabang")
    @Expose
    var id_cabang : String ? = null

    @SerializedName("status")
    @Expose
    var status : String ? = null

    @SerializedName("house_blend")
    @Expose
    var house_blend : Int ? = null

    @SerializedName("choco")
    @Expose
    var choco : Int ? = null

    @SerializedName("vanilla")
    @Expose
    var vanilla : Int ? = null

    @SerializedName("single_origin")
    @Expose
    var single_origin : Int ? = null

    @SerializedName("coffe_blend")
    @Expose
    var coffe_blend : Int ? = null
}