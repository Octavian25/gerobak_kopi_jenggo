package com.example.gerobak_kopi_jenggo.Menu

import com.example.gerobak_kopi_jenggo.R

object MenuOwner {
    private val nama = arrayOf(
        "Laporan Penjualan",
        "Laporan Gudang",
        "Laporan Penggajian",
        "Tambah Pegawai",
        "Tambah Cabang"
    )
    private val gambar = arrayOf(
        R.drawable.laporan_penjualan,
        R.drawable.laporan_penjualan,
        R.drawable.laporan_penjualan,
        R.drawable.absen,
        R.drawable.absen,
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