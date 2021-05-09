package com.example.gerobak_kopi_jenggo.bahan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.Menu.MenuAdapter
import com.example.gerobak_kopi_jenggo.R

class bahanAdapter (var results: ArrayList<bahanModel.Result>) :
    RecyclerView.Adapter<bahanAdapter.bahanViewHolder>() {
    private var onItemClickCallback: bahanAdapter.OnItemClickCallback? = null
    class bahanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var house_blend : TextView = view.findViewById(R.id.list_house_blend)
        var choco : TextView = view.findViewById(R.id.list_choco)
        var vanilla : TextView = view.findViewById(R.id.list_vanilla)
        var single_origin : TextView = view.findViewById(R.id.list_single_origin)
        var coffe_blend : TextView = view.findViewById(R.id.list_coffe_blend)
        var cabang : TextView = view.findViewById(R.id.list_cabang)
        var status : TextView = view.findViewById(R.id.list_status);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bahanViewHolder = bahanViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_bahan, parent, false))

    override fun onBindViewHolder(holder: bahanViewHolder, position: Int) {
        val result = results[position]
        holder.choco.text = result.choco.toString()
        holder.house_blend.text = result.house_blend.toString()
        holder.vanilla.text = result.vanilla.toString()
        holder.single_origin.text = result.single_origin.toString()
        holder.coffe_blend.text = result.coffe_blend.toString()
        holder.cabang.text = result.id_cabang
        holder.status.text = result.status

        holder.itemView.setOnClickListener { onItemClickCallback?.onItemClicked(result) }
    }

    override fun getItemCount(): Int {
        return results.size
    }
    fun setData(data: List<bahanModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }
    fun clear(){
        val size = this.results.size
        this.results.clear()
//        notifyItemRangeRemoved(0, size)
        notifyDataSetChanged()
    }


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: bahanModel.Result)
    }
}