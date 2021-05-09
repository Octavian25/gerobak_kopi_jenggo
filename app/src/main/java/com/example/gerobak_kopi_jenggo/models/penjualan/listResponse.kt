package com.example.gerobak_kopi_jenggo.models.penjualan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class listResponse {
    @SerializedName("id_menu")
    @Expose
    var id_menu : String ? = null

    @SerializedName("nama_barang")
    @Expose
    var nama_barang : String ? = null

    @SerializedName("harga_barang")
    @Expose
    var harga : String ? = null
}