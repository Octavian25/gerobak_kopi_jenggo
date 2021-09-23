package com.example.gerobak_kopi_jenggo.retrofit

import com.example.gerobak_kopi_jenggo.CabangModels
import com.example.gerobak_kopi_jenggo.models.cabang.cabangModel
import com.example.gerobak_kopi_jenggo.models.pegawai.cabangResponse
import com.example.gerobak_kopi_jenggo.models.pegawai.pegawaiResponse
import com.example.gerobak_kopi_jenggo.pegawai.PegawaiModel
import retrofit2.Call
import retrofit2.http.*

interface cabangAPI {
    @GET("api/get/cabang")
    fun getCabang() : Call<List<cabangResponse>>

    @GET("api/get/cabangList")
    fun getCabangList() : Call<CabangModels>

    @POST("api/cabang/add")
    fun postCabang(@Body request : cabangModel) : Call<List<cabangResponse>>

    @DELETE("api/delete/cabang/{id_cabang}")
    fun deleteCabang(@Path("id_cabang") id_pegawai: String) : Call<CabangModels>
}