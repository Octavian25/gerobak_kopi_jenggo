package com.example.gerobak_kopi_jenggo.models.menu

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class menuModel {
    @SerializedName("id_barang")
    @Expose
    var id_barang : String ? = null

    @SerializedName("nama_barang")
    @Expose
    var nama_barang : String ? = null
    @SerializedName("harga_barang")
    @Expose
    var harga_barang : Int ? = null
    @SerializedName("id_cabang")
    @Expose
    var id_cabang : String ? = null
    @SerializedName("jenis")
    @Expose
    var jenis : String ? = null
}