package com.example.gerobak_kopi_jenggo.Menu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gerobak_kopi_jenggo.R

class MenuAdapter(private val list : ArrayList<Menu>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun OnItemClickCallback(onItemClickCallback:OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
   inner class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var gambar : ImageView = view.findViewById(R.id.menu_gambar)
       var nama : TextView = view.findViewById(R.id.menu_nama)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.MenuViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuAdapter.MenuViewHolder, position: Int) {
        val menu = list[position]

        Glide.with(holder.itemView.context).load(menu.gambar).into(holder.gambar);
        holder.nama.text = menu.name.toString()
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
      return list.size
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Menu)
    }
}