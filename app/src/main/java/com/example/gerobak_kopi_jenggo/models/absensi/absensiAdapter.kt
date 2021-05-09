package com.example.gerobak_kopi_jenggo.models.absensi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gerobak_kopi_jenggo.R
import com.example.gerobak_kopi_jenggo.models.menu.masterAdapter
import com.example.gerobak_kopi_jenggo.models.menu.masterModel

class absensiAdapter(var results : ArrayList<absensiModel.Result>, val listener : OnAdapterListener) : RecyclerView.Adapter<absensiAdapter.absensiViewHolder>(){

    class absensiViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        var nama_pegawai : TextView = view.findViewById(R.id.list_nama_pegawai);
        var jam : TextView = view.findViewById(R.id.list_jam)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = absensiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_absensi, parent, false))

    override fun onBindViewHolder(holder: absensiAdapter.absensiViewHolder, position: Int) {
       val result = results[position]

        holder.nama_pegawai.text = result.nama_pegawai
        holder.jam.text = result.jam
    }

    override fun getItemCount(): Int {
       return results.size
    }
    fun setData(data: List<absensiModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(result: absensiModel.Result);
    }
}