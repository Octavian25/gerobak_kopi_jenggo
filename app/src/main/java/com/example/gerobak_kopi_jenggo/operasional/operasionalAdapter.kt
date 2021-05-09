package com.example.gerobak_kopi_jenggo.operasional

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.R
import com.example.gerobak_kopi_jenggo.models.menu.masterAdapter
import com.example.gerobak_kopi_jenggo.models.menu.masterModel

class operasionalAdapter(var results : ArrayList<operasionalModel.Result>,val listener: masterAdapter.OnAdapterListener) :
    RecyclerView.Adapter<operasionalAdapter.operasionalViewHolder>() {
    class operasionalViewHolder ( view : View) : RecyclerView.ViewHolder(view) {
            var keteragan : TextView = view.findViewById(R.id.operasional_keterangan)
        var tanggal : TextView = view.findViewById(R.id.operasional_tanggal)
        var harga : TextView = view.findViewById(R.id.operasional_harga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): operasionalViewHolder = operasionalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_operator, parent, false))

    override fun onBindViewHolder(holder: operasionalViewHolder, position: Int) {
        val result = results[position]
        holder.keteragan.text = result.keterangan.toString()
        holder.tanggal.text = result.tanggal.toString()
        holder.harga.text = result.harga.toString()
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun setData(data: List<operasionalModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(result: masterModel.Result)
    }
}