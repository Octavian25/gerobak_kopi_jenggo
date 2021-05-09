package com.example.gerobak_kopi_jenggo.models.penjualan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class laporanModel(
    val result: ArrayList<Result>
){
    data class Result(var nama_barang : String = "",
                      var harga_barang : String = "",
                      var total : String = "",
                      var id_cabang : String = "",
                      var qty : String = "")
}