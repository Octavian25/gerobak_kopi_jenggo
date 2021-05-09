package com.example.gerobak_kopi_jenggo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.gerobak_kopi_jenggo.databinding.ActivityTambahCabangBinding
import com.example.gerobak_kopi_jenggo.models.cabang.cabangModel
import com.example.gerobak_kopi_jenggo.models.pegawai.cabangResponse
import com.example.gerobak_kopi_jenggo.models.pegawai.pegawaiModel
import com.example.gerobak_kopi_jenggo.models.pegawai.pegawaiResponse
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.cabangAPI
import com.example.gerobak_kopi_jenggo.retrofit.pegawaiAPI
import com.example.gerobak_kopi_jenggo.utility.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class tambahCabang : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityTambahCabangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_cabang)

        binding = ActivityTambahCabangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTambahCabang.setOnClickListener(this)
    }

    fun postCabang(){
        var cabang = cabangModel()
       cabang.nama_cabang = binding.namaCabang.text.toString()
        cabang.alamat_cabang = binding.alamatCabang.text.toString()

        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(cabangAPI::class.java)
        retro.postCabang(cabang).enqueue(object : Callback<List<cabangResponse>> {
            override fun onResponse(call: Call<List<cabangResponse>>, response: Response<List<cabangResponse>>) {
                showToast(this@tambahCabang, "Berhasil Tambah Data", "success")
                binding.namaCabang.text.clear()
                binding.alamatCabang.text.clear()
                clearText()
//                Toast.makeText(this@Biaya_operasional, "onSuccess: Berhasil" + token.toString(), Toast.LENGTH_SHORT).show()

            }
            override fun onFailure(call: Call<List<cabangResponse>>, t: Throwable) {
                showToast(this@tambahCabang, "Berhasil Tambah Data", "success")
//                Toast.makeText(this@Biaya_operasional, "onFailure: " + t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun clearText() {
        binding.alamatCabang.setText("")
        binding.namaCabang.setText("")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_tambah_cabang -> {
                postCabang()
            }
        }
    }
}