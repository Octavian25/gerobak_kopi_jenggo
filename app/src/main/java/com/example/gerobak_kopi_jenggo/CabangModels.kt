package com.example.gerobak_kopi_jenggo

data class CabangModels (
    var result : ArrayList<Result>){
    data class Result(
        var id_cabang : String = "",
        var nama_cabang : String  ="",
        var alamat_cabang : String = ""
    )
}