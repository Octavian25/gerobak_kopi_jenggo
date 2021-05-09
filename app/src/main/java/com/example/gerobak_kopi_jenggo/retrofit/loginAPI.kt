package com.example.gerobak_kopi_jenggo.retrofit

import com.example.gerobak_kopi_jenggo.models.LoginModel
import com.example.gerobak_kopi_jenggo.models.login.loginCabangResponse
import com.example.gerobak_kopi_jenggo.models.login.loginResponse
import retrofit2.Call
import retrofit2.http.*
import java.net.CacheRequest

interface loginAPI {
    @POST("login")
    fun loginUser(@Body request: LoginModel): Call<loginResponse>

    @GET("getcabang/{user_id}")
    fun getCabang(@Path("user_id") user_id : String) : Call<loginCabangResponse>
}