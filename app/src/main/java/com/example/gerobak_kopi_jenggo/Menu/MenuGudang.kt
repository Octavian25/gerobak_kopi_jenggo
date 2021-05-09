package com.example.gerobak_kopi_jenggo.Menu

import com.example.gerobak_kopi_jenggo.R

object MenuGudang {
    private val nama = arrayOf(
        "Order Pesanan"
    )
    private val gambar = arrayOf(
        R.drawable.laporan_penjualan,
    )

    val listData: ArrayList<Menu>
        get() {
            val list = arrayListOf<Menu>()
            for (index in nama.indices){
                val menu = Menu()
                menu.name = nama[index]
                menu.gambar = gambar[index]
                list.add(menu)
            }
            return list
        }
}