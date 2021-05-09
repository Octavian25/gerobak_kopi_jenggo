package com.example.gerobak_kopi_jenggo.retrofit

import com.example.gerobak_kopi_jenggo.models.pegawai.pegawaiModel
import com.example.gerobak_kopi_jenggo.models.pegawai.pegawaiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface pegawaiAPI {
    @POST("api/pegawai/add")
    fun postPegawai(@Body request: pegawaiModel) : Call<pegawaiResponse>
}