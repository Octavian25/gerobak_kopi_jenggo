package com.example.gerobak_kopi_jenggo.retrofit

import com.example.gerobak_kopi_jenggo.models.menu.masterModel
import com.example.gerobak_kopi_jenggo.models.menu.menuModel
import com.example.gerobak_kopi_jenggo.models.menu.menuResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface menuAPI {
    @POST("api/menu/add")
    fun postMenu(@Body request : menuModel ) : Call<menuResponse>

    @GET("api/menu/get/bycabang/{id_cabang}")
    fun getMenu(@Path("id_cabang") id_cabang : String) : Call<masterModel>
}