package com.example.gerobak_kopi_jenggo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.gerobak_kopi_jenggo.Menu.menu_utama
import com.example.gerobak_kopi_jenggo.models.LoginModel
import com.example.gerobak_kopi_jenggo.models.login.loginCabangResponse
import com.example.gerobak_kopi_jenggo.models.login.loginResponse
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.loginAPI
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import io.easyprefs.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private lateinit var btn_login: Button;
private lateinit var btn_forget: TextView;
private lateinit var et_username : EditText;
private lateinit var et_password : EditText;
private lateinit var progress : ProgressBar

var TAG = "LOGIN"
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var username : String? = null
    private var password : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Prefs.initializeApp(this)

        btn_login = findViewById(R.id.btn_login);
        btn_forget = findViewById(R.id.btn_forget_password);
        et_password = findViewById(R.id.et_password);
        et_username = findViewById(R.id.et_username);
        progress = findViewById(R.id.progressBar)

        btn_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_login -> {
                progress.visibility = View.VISIBLE
                btn_login.visibility = View.GONE
                postLogin()
            }
        }
    }

    fun postLogin(){
        var loginReq = LoginModel()
        loginReq.username = et_username.text.toString();
        loginReq.password = et_password.text.toString();
        val retro = Retro().postRetroClient("https://riniocta.my.id/api/").create(loginAPI::class.java)
        retro.loginUser(loginReq).enqueue(object : Callback<loginResponse> {
            override fun onResponse(call: Call<loginResponse>, response: Response<loginResponse>) {
                val token = response.body();
                Log.d(TAG, "onResponse: Tokennya" + token?.token)
                Prefs.write().content("token", token?.token ?: "kosong").commit()
                Prefs.write().content("id_cabang", token?.cabang ?: "kosong").commit()
                Prefs.write().content("level", token?.level.toString()).commit()
                val retro = Retro().postRetroClient("https://riniocta.my.id/api/").create(loginAPI::class.java)
                retro.getCabang(et_username.text.toString()).enqueue(object : Callback<loginCabangResponse>{
                    override fun onResponse(
                        call: Call<loginCabangResponse>,
                        response: Response<loginCabangResponse>
                    ) {
                        val token = response.body();
                        Prefs.write().content("id_cabang", token?.id_cabang.toString()).commit()
                        Prefs.write().content("level", token?.level.toString()).commit()
                        Toast.makeText(this@MainActivity, "Selamat Datang " + token?.id_cabang.toString(), Toast.LENGTH_SHORT).show()
                        progress.visibility = View.GONE
                        btn_login.visibility = View.VISIBLE
                        var intent = Intent(this@MainActivity, menu_utama::class.java)
                        startActivity(intent);
                        finish()
                    }

                    override fun onFailure(call: Call<loginCabangResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }

            override fun onFailure(call: Call<loginResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: "+t.message.toString() )
                progress.visibility = View.GONE
                btn_login.visibility = View.VISIBLE
            }

        })
    }
}

