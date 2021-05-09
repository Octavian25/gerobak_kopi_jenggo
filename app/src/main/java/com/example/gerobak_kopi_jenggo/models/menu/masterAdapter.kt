package com.example.gerobak_kopi_jenggo.models.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gerobak_kopi_jenggo.R

class masterAdapter(var results : ArrayList<masterModel.Result>,val listener: OnAdapterListener) :
    RecyclerView.Adapter<masterAdapter.masterViewHolder>() {

    class masterViewHolder (val view : View) :RecyclerView.ViewHolder(view) {
            var nama_menu :TextView = view.findViewById(R.id.list_nama);
        var harga_menu : TextView = view.findViewById(R.id.list_harga)
        var jenis : ImageView = view.findViewById(R.id.list_jenis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = masterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_master, parent, false))

    override fun onBindViewHolder(holder: masterViewHolder, position: Int) {
        val result = results[position]

        holder.nama_menu.text = result.nama_barang
        holder.harga_menu.text = result.harga_barang
        if(result.jenis == "panas"){
            Glide.with(holder.view).load(R.drawable.hot).into(holder.jenis)
        } else {
            Glide.with(holder.view).load(R.drawable.cold).into(holder.jenis)
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }
    fun setData(data: List<masterModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(result: masterModel.Result)
    }
}