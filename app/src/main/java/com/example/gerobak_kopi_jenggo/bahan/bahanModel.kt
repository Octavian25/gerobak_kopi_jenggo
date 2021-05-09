package com.example.gerobak_kopi_jenggo.bahan

import com.example.gerobak_kopi_jenggo.operasional.operasionalModel

data class bahanModel (
    var result : ArrayList<Result>){
    data class Result(
        var tanggal : String = "",
        var status : String  ="",
        var house_blend : String = "",
        var choco : String = "",
        var vanilla : String = "",
        var coffe_blend : String = "",
        var single_origin : String = "",
        var id_cabang : String = "",
        var id_order : String = ""
    )
}
