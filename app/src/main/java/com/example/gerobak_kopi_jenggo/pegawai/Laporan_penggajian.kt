package com.example.gerobak_kopi_jenggo.pegawai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.R
import com.example.gerobak_kopi_jenggo.bahan.bahanModel
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.absensiAPI
import com.example.gerobak_kopi_jenggo.retrofit.bahanBakuAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var rv_list_bahan : RecyclerView
class Laporan_penggajian : AppCompatActivity() {
    private lateinit var  pegawaiadapters: PegawaiAdapter
    private lateinit var total_gaji : TextView
    private lateinit var cv_lihat_data_pegawai : CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan_penggajian)

        total_gaji = findViewById(R.id.total_gaji)
        cv_lihat_data_pegawai = findViewById(R.id.cv_lihat_data_pegawai)
        rv_list_bahan = findViewById(R.id.rv_list_pegawai)
        rv_list_bahan.setHasFixedSize(true)
        setupRecyclerView()
        getBahan()
    }

    private fun setupRecyclerView(){
        pegawaiadapters = PegawaiAdapter(arrayListOf())
        rv_list_bahan.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pegawaiadapters
        }
    }
    fun getBahan(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(absensiAPI::class.java)
        retro.getPegawai().enqueue(object : Callback<PegawaiModel> {
            override fun onResponse(
                call: Call<PegawaiModel>,
                response: Response<PegawaiModel>
            ) {
                if (response.isSuccessful) {
                    val hasil = response.body()
                    showResult(response.body()!!)
                    var total = hasil?.result?.sumBy { it.gaji.toInt() }
                    var rp = total?.toDouble()?.let { helper().rupiah(it) }
                    total_gaji.text = "Total Gaji Harus Dibayar : $rp"
                }
            }

            override fun onFailure(call: Call<PegawaiModel>, t: Throwable) {
                showToast(this@Laporan_penggajian, "Terjadi kesalahan Saat Mengambil data", "info")
            }

        })
    }

    private fun showResult(results: PegawaiModel){
        for (result in results.result)
            pegawaiadapters.setData(results.result)
    }
}