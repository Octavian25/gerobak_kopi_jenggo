package com.example.gerobak_kopi_jenggo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.gerobak_kopi_jenggo.*
import com.example.gerobak_kopi_jenggo.Menu.menu_utama
import com.example.gerobak_kopi_jenggo.models.LoginModel
import com.example.gerobak_kopi_jenggo.models.login.loginResponse
import com.example.gerobak_kopi_jenggo.models.operasional.OperasionalModel
import com.example.gerobak_kopi_jenggo.models.operasional.OperasionalResponse
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.loginAPI
import com.example.gerobak_kopi_jenggo.retrofit.operasionalAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import com.prathameshmore.toastylibrary.Toasty
import id.ionbit.ionalert.IonAlert
import io.easyprefs.Prefs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

private lateinit var item : EditText
private lateinit var harga : EditText
private lateinit var btn_operasional : Button
private lateinit var tv_info : TextView
private lateinit var cv_laporan_operasional : CardView
class Biaya_operasional : AppCompatActivity(), View.OnClickListener {
    private var id_cabang : String = "";
    private var token : String = "";
    private var TAG = "OPERASIONAL"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biaya_operasional)


        item = findViewById(R.id.et_keterangan_operasional)
        harga = findViewById(R.id.et_harga_operasional)
        btn_operasional = findViewById(R.id.btn_biaya_operasi)
        tv_info = findViewById(R.id.tv_info_operasional)
        cv_laporan_operasional = findViewById(R.id.cv_laporan_operasional)
        tv_info.text = helper().convertDate()

        btn_operasional.setOnClickListener(this)
        cv_laporan_operasional.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_biaya_operasi -> {
                if (validate()){
                    postOperasional()
                }
            }
            R.id.cv_laporan_operasional -> {
                var intent = Intent(this@Biaya_operasional, listDataOperasional::class.java)
                startActivity(intent)
            }
        }
    }

    fun validate() : Boolean{
        if (item.text.isEmpty()){
            showToast(this@Biaya_operasional,"Harap isi Keterangan", "info")
            return false
        } else if (harga.text.isEmpty()){
            showToast(this@Biaya_operasional,"Harap isi Harga", "info")
            return false
        } else {
            return true
        }
    }
    fun postOperasional(){
        var operasionalReq = OperasionalModel()
        operasionalReq.keterangan = item.text.toString();
        operasionalReq.harga = harga.text.toString();
        operasionalReq.id_cabang = helper().getPref("id_cabang")
        operasionalReq.input_by = "ADMIN"
        operasionalReq.tanggal = helper().getDate()

        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(operasionalAPI::class.java)
        retro.postOperasional(operasionalReq).enqueue(object : Callback<OperasionalResponse> {
            override fun onResponse(call: Call<OperasionalResponse>, response: Response<OperasionalResponse>) {
                val token = response.body();
        showToast(this@Biaya_operasional, "Berhasil Tambah Data", "success")
                clearText()
//                Toast.makeText(this@Biaya_operasional, "onSuccess: Berhasil" + token.toString(), Toast.LENGTH_SHORT).show()

            }
            override fun onFailure(call: Call<OperasionalResponse>, t: Throwable) {
                    showToast(this@Biaya_operasional,"Gagal Tambah Data", "error")
//                Toast.makeText(this@Biaya_operasional, "onFailure: " + t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun clearText(){
        item.setText("");
        harga.setText("");
    }

}