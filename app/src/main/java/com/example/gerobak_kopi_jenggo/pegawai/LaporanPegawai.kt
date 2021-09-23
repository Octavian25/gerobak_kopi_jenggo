package com.example.gerobak_kopi_jenggo.pegawai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.R
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.absensiAPI
import com.example.gerobak_kopi_jenggo.utility.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaporanPegawai : AppCompatActivity() {
    private lateinit var  pegawaiadapters: LaporanPegawaiAdapter
    private lateinit var rv_list_laporan_pegawai : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan_pegawai)

        rv_list_laporan_pegawai = findViewById(R.id.rv_list_laporan_pegawai)
        rv_list_laporan_pegawai.setHasFixedSize(true)
        setupRecyclerView()
        getBahan()
    }

    private fun setupRecyclerView(){
        pegawaiadapters = LaporanPegawaiAdapter(arrayListOf())
        rv_list_laporan_pegawai.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pegawaiadapters
        }

        pegawaiadapters.setOnItemClickCallback(object : LaporanPegawaiAdapter.OnItemClickCallback{
            override fun onItemClicked(data: PegawaiModel.Result) {
                val builder: AlertDialog.Builder? = this@LaporanPegawai?.let {
                    AlertDialog.Builder(it)
                }

                builder!!.setMessage("Anda Yakin Ingin Menghapus Pegawai ${data.nama_pegawai}")
                    .setTitle("Hapus Data !!")

                builder.apply {
                    setPositiveButton("YES") { dialog, id ->
                        deletePegawai(data.id_pegawai);
                    }
                    setNegativeButton("TIDAK") { dialog, id ->
                        val selectedId = id
                    }
                }
                val dialog: AlertDialog? = builder.create()

                dialog!!.show()
            }

        })
    }
    fun getBahan(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(absensiAPI::class.java)
        retro.getPegawai().enqueue(object : Callback<PegawaiModel> {
            override fun onResponse(
                call: Call<PegawaiModel>,
                response: Response<PegawaiModel>
            ) {
                if (response.isSuccessful) {
                    showResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<PegawaiModel>, t: Throwable) {
                showToast(this@LaporanPegawai, "Terjadi kesalahan Saat Mengambil data", "info")
            }

        })
    }
    fun deletePegawai(id_pegawai: String){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(absensiAPI::class.java)
        retro.deletePegawai(id_pegawai).enqueue(object : Callback<PegawaiModel> {
            override fun onResponse(
                call: Call<PegawaiModel>,
                response: Response<PegawaiModel>
            ) {
                if (response.isSuccessful) {
                    showResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<PegawaiModel>, t: Throwable) {
                showToast(this@LaporanPegawai, "Terjadi kesalahan Saat Mengambil data", "info")
            }

        })
    }

    private fun showResult(results: PegawaiModel){
        for (result in results.result)
            pegawaiadapters.setData(results.result)
    }
}