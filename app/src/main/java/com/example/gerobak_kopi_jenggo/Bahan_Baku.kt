package com.example.gerobak_kopi_jenggo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.gerobak_kopi_jenggo.models.bahan.BahanModel
import com.example.gerobak_kopi_jenggo.models.bahan.BahanResponse
import com.example.gerobak_kopi_jenggo.models.operasional.OperasionalModel
import com.example.gerobak_kopi_jenggo.models.operasional.OperasionalResponse
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.bahanBakuAPI
import com.example.gerobak_kopi_jenggo.retrofit.operasionalAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var house_blend : EditText
private lateinit var choco : EditText
private lateinit var vanilla : EditText
private lateinit var single_origin : EditText
private lateinit var coffe_blend    : EditText
private lateinit var btn_pesan : Button
private lateinit var btn_lihat_pesanan : Button
class Bahan_Baku : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bahan_baku)

        house_blend = findViewById(R.id.et_house_blend)
        choco = findViewById(R.id.et_choco)
        vanilla = findViewById(R.id.et_vanilla)
        single_origin = findViewById(R.id.et_single_origin)
        coffe_blend = findViewById(R.id.et_coffe_bled)
        btn_pesan = findViewById(R.id.btn_bahan_baku)
        btn_lihat_pesanan = findViewById(R.id.btn_lihat_pesanan)

        btn_pesan.setOnClickListener(this)
        btn_lihat_pesanan.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_bahan_baku -> {
                if(checkEmpty())
                    postBahan()
                else
                    showToast(this, "Tidak Boleh Kosong Semua", "error")
            }
            R.id.btn_lihat_pesanan -> {
                print("INI DI BUTTON")
                val intent = Intent(this, lihat_bahan::class.java )
                startActivity(intent);
            }
        }
    }

    fun checkEmpty() : Boolean{
        if(house_blend.text.isEmpty() && choco.text.isEmpty() && vanilla.text.isEmpty() && single_origin.text.isEmpty() && coffe_blend.text.isEmpty())
            return false;
        else return true
    }

    fun postBahan(){
        var bahanReq = BahanModel()
       bahanReq.choco = choco.text.toString().toIntOrNull();
       bahanReq.house_blend = house_blend.text.toString().toIntOrNull();
       bahanReq.vanilla = vanilla.text.toString().toIntOrNull();
       bahanReq.coffe_blend = coffe_blend.text.toString().toIntOrNull();
       bahanReq.single_origin = single_origin.text.toString().toIntOrNull();
        bahanReq.status = "Menunggu";
        bahanReq.id_cabang = helper().getPref("id_cabang");
        bahanReq.tanggal = helper().getDate();

        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(bahanBakuAPI::class.java)
        retro.postBahan(bahanReq).enqueue(object : Callback<BahanResponse> {
            override fun onResponse(call: Call<BahanResponse>, response: Response<BahanResponse>) {
                val token = response.body();
                print(token?.status);
                showToast(this@Bahan_Baku, "Berhasil Tambah Data", "success")
                clearText()
//                Toast.makeText(this@Biaya_operasional, "onSuccess: Berhasil" + token.toString(), Toast.LENGTH_SHORT).show()

            }
            override fun onFailure(call: Call<BahanResponse>, t: Throwable) {
                showToast(this@Bahan_Baku,"Gagal Tambah Data", "error")
//                Toast.makeText(this@Biaya_operasional, "onFailure: " + t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun clearText() {
       house_blend.text.clear();
        vanilla.text.clear();
        choco.text.clear();
        single_origin.text.clear();
        coffe_blend.text.clear();
    }
}