package com.example.gerobak_kopi_jenggo.retrofit

import com.example.gerobak_kopi_jenggo.models.cabang.cabangModel
import com.example.gerobak_kopi_jenggo.models.pegawai.cabangResponse
import com.example.gerobak_kopi_jenggo.models.pegawai.pegawaiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface cabangAPI {
    @GET("api/get/cabang")
    fun getCabang() : Call<List<cabangResponse>>

    @POST("api/cabang/add")
    fun postCabang(@Body request : cabangModel) : Call<List<cabangResponse>>
}