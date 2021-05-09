package com.example.gerobak_kopi_jenggo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import com.example.gerobak_kopi_jenggo.R
import com.example.gerobak_kopi_jenggo.models.absensi.absensiResponse
import com.example.gerobak_kopi_jenggo.models.pegawai.pegawaiResponse
import com.example.gerobak_kopi_jenggo.models.penjualan.listResponse
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.absensiAPI
import com.example.gerobak_kopi_jenggo.retrofit.pegawaiAPI
import com.example.gerobak_kopi_jenggo.retrofit.penjualanAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import io.easyprefs.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Absensi : AppCompatActivity(), View.OnClickListener {
    private lateinit var id_pegawai : Spinner;
    private lateinit var btn_absensi : Button;
    private lateinit var tv_info : TextView;
    private lateinit var laporan : CardView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_absensi)
        id_pegawai = findViewById(R.id.snipper_id_pegawai_absen);
        btn_absensi = findViewById(R.id.btn_absensi);
        laporan = findViewById(R.id.cv_laporan_absensi);
        tv_info = findViewById(R.id.tv_info_absensi)
        tv_info.text = helper().convertDate().toString()
//        getPegawai()
        getBarang()
        btn_absensi.setOnClickListener(this)
        laporan.setOnClickListener(this)
    }



    fun postMenu(){
        val id_pegawai = id_pegawai.selectedItem.toString().take(5)
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(absensiAPI::class.java)
        retro.postAbsensi(id_pegawai).enqueue(object : Callback<absensiResponse>{
            override fun onResponse(call: Call<absensiResponse>, response: Response<absensiResponse>) {
                showToast(this@Absensi, "Berhasil Menambahkan Data", "success")
            }

            override fun onFailure(call: Call<absensiResponse>, t: Throwable) {
                showToast(this@Absensi, "Gagal menambahkan Data", "error")
            }

        })
    }
//    fun getPegawai(){
//        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(absensiAPI::class.java)
//        retro.getPegawai(helper().getPref("id_cabang")).enqueue(object : Callback<List<pegawaiResponse>> {
//            override fun onResponse(
//                call: Call<List<pegawaiResponse>>,
//                response: Response<List<pegawaiResponse>>
//            ) {
//                var data : MutableList<String> = ArrayList()
//                val id = response.body()
//                for(id_barang in id!!){
//                    var idnya = id_barang.id_pegawai.toString()
//                    var namanya = id_barang.nama_pegawai.toString()
//                    data.add("$idnya - $namanya")
//                }
////                id_pegawai.adapter = ArrayAdapter<String>(this@Absensi, R.layout.support_simple_spinner_dropdown_item, data)
//            }
//
//            override fun onFailure(call: Call<List<pegawaiResponse>>, t: Throwable) {
//                showToast(this@Absensi, "Gagal Ambil Pegawai", "info")
//            }
//        })
//    }

    private fun getBarang(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(absensiAPI::class.java)
        retro.getPegawai(helper().getPref("id_cabang")).enqueue(object :
            Callback<List<pegawaiResponse>> {
            override fun onResponse(
                call: Call<List<pegawaiResponse>>,
                response: Response<List<pegawaiResponse>>
            ) {
                Log.d(TAG, "onResponse: " + response.body().toString())
                var data: MutableList<String> = ArrayList()
                val id = response.body()
                for (id_barang in id!!) {
                    var namanya = id_barang.nama_pegawai
                    var id_menu = id_barang.id_pegawai
                    data.add("$id_menu - $namanya");
                }
                id_pegawai.adapter = ArrayAdapter<String>(
                    this@Absensi,
                    R.layout.support_simple_spinner_dropdown_item,
                    data
                )
            }

            override fun onFailure(call: Call<List<pegawaiResponse>>, t: Throwable) {
                Toast.makeText(this@Absensi, "GAGAL AMBIL DATA", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_absensi -> {
                postMenu()
            }
            R.id.cv_laporan_absensi -> {
                    val intent = Intent(this@Absensi, lihat_absensi::class.java);
                    startActivity(intent);
            }
        }
    }
}