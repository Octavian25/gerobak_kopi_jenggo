package com.example.gerobak_kopi_jenggo.pegawai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import com.example.gerobak_kopi_jenggo.*
import com.example.gerobak_kopi_jenggo.databinding.ActivityMainBinding
import com.example.gerobak_kopi_jenggo.databinding.ActivityTambahPegawaiBinding
import com.example.gerobak_kopi_jenggo.models.bahan.BahanModel
import com.example.gerobak_kopi_jenggo.models.bahan.BahanResponse
import com.example.gerobak_kopi_jenggo.models.pegawai.cabangResponse
import com.example.gerobak_kopi_jenggo.models.pegawai.pegawaiModel
import com.example.gerobak_kopi_jenggo.models.pegawai.pegawaiResponse
import com.example.gerobak_kopi_jenggo.retrofit.*
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class tambahPegawai : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityTambahPegawaiBinding
    private var jenis_kelamin: String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pegawai)

        binding = ActivityTambahPegawaiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCabang()
        binding.rgJenisKelamin.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            jenis_kelamin = radio.text.toString()
        }
        binding.btnTambahPegawai.setOnClickListener(this)

    }
    fun getCabang(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(cabangAPI::class.java)
        retro.getCabang().enqueue(object : Callback<List<cabangResponse>> {
            override fun onResponse(
                call: Call<List<cabangResponse>>,
                response: Response<List<cabangResponse>>
            ) {
                var data : MutableList<String> = ArrayList()
                val id = response.body()
                for(id_barang in id!!){
                    var idnya = id_barang.id_cabang.toString()
                    var namanya = id_barang.nama_cabang.toString()
                    data.add("$idnya - $namanya")
                }
                binding.spinnerCabang.adapter = ArrayAdapter<String>(this@tambahPegawai, R.layout.support_simple_spinner_dropdown_item, data)
            }

            override fun onFailure(call: Call<List<cabangResponse>>, t: Throwable) {
                showToast(this@tambahPegawai, "Gagal Ambil Cabang", "info")
            }
        })
    }

    fun postBahan(){
        var pegawai = pegawaiModel()
        pegawai.alamat = binding.alamat.text.toString()
        pegawai.gaji = binding.gaji.text.toString()
        pegawai.id_cabang = binding.spinnerCabang.selectedItem.toString().split("-")[0]
        pegawai.jenis_kelamin  = jenis_kelamin
        pegawai.no_telp = binding.noHp.text.toString()
        pegawai.nama_pegawai = binding.namaPegawai.text.toString()

        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(pegawaiAPI::class.java)
        retro.postPegawai(pegawai).enqueue(object : Callback<pegawaiResponse> {
            override fun onResponse(call: Call<pegawaiResponse>, response: Response<pegawaiResponse>) {
                val token = response.body();
                showToast(this@tambahPegawai, "Berhasil Tambah Data", "success")
                clearText()
//                Toast.makeText(this@Biaya_operasional, "onSuccess: Berhasil" + token.toString(), Toast.LENGTH_SHORT).show()

            }
            override fun onFailure(call: Call<pegawaiResponse>, t: Throwable) {
                showToast(this@tambahPegawai,"Gagal Tambah Data", "error")
//                Toast.makeText(this@Biaya_operasional, "onFailure: " + t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun clearText() {
        binding.alamat.setText("")
        binding.noHp.setText("")
        binding.namaPegawai.setText("")
        binding.gaji.setText("")

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_tambah_pegawai -> {
                postBahan()
            }
        }
    }
}