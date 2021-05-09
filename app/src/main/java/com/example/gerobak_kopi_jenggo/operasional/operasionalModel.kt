package com.example.gerobak_kopi_jenggo.operasional

data class operasionalModel(
    val result: ArrayList<Result>
){
    data class Result(var keterangan : String = "",
                      var tanggal : String = "",
                      var harga : String = "")
}