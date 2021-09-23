package com.example.gerobak_kopi_jenggo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.models.pegawai.cabangResponse
import com.example.gerobak_kopi_jenggo.pegawai.LaporanPegawaiAdapter
import com.example.gerobak_kopi_jenggo.pegawai.PegawaiModel
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.absensiAPI
import com.example.gerobak_kopi_jenggo.retrofit.cabangAPI
import com.example.gerobak_kopi_jenggo.utility.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LihatCabang : AppCompatActivity() {
    private lateinit var  cabangAdapter: LaporanCabangAdapter
    private lateinit var rv_list_laporan_cabang : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_cabang)

        rv_list_laporan_cabang = findViewById(R.id.rv_list_cabang)
        rv_list_laporan_cabang.setHasFixedSize(true)
        getCabang()
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        cabangAdapter = LaporanCabangAdapter(arrayListOf())
        rv_list_laporan_cabang.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cabangAdapter
        }

        cabangAdapter.setOnItemClickCallback(object : LaporanCabangAdapter.OnItemClickCallback{
            override fun onItemClicked(data: CabangModels.Result) {
                val builder: AlertDialog.Builder? = this@LihatCabang?.let {
                    AlertDialog.Builder(it)
                }

                builder!!.setMessage("Anda Yakin Ingin Menghapus Pegawai ${data.nama_cabang}")
                    .setTitle("Hapus Data !!")

                builder.apply {
                    setPositiveButton("YES") { dialog, id ->
                        deletePegawai(data.id_cabang);
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
    fun getCabang(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(cabangAPI::class.java)
        retro.getCabangList().enqueue(object : Callback<CabangModels> {
            override fun onResponse(
                call: Call<CabangModels>,
                response: Response<CabangModels>
            ) {
                if (response.isSuccessful) {
                    showResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<CabangModels>, t: Throwable) {
                showToast(this@LihatCabang, "Terjadi kesalahan Saat Mengambil data", "info")
            }

        })
    }
    fun deletePegawai(id_cabang: String){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(cabangAPI::class.java)
        retro.deleteCabang(id_cabang).enqueue(object : Callback<CabangModels> {
            override fun onResponse(
                call: Call<CabangModels>,
                response: Response<CabangModels>
            ) {
                if (response.isSuccessful) {
                    showResult(response.body()!!)
                } else if (response.code().equals(500)){
                    showToast(this@LihatCabang, "Cabang masih digunakan oleh pegawai, silahkan hapus pegawai dahulu", "error")
                }
            }

            override fun onFailure(call: Call<CabangModels>, t: Throwable) {
                showToast(this@LihatCabang, "Terjadi kesalahan Saat Mengambil data", "info")
            }

        })
    }

    private fun showResult(results: CabangModels){
        for (result in results.result)
            cabangAdapter.setData(results.result)
    }
}
