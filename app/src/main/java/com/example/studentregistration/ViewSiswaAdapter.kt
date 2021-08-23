package com.example.studentregistration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewSiswaAdapter(private val siswaList: MutableList<Siswa>) : RecyclerView.Adapter<ViewSiswaAdapter.CardViewViewHolder>() {
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvEmail: TextView = itemView.findViewById(R.id.tv_item_email)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater
            .from(viewGroup.context)
            .inflate(R.layout.item_view_list, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val siswa = siswaList[position]

        holder.tvNama.text = siswa.nama
        holder.tvEmail.text = siswa.email

        // while item clicked
        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(siswaList[holder.adapterPosition])}

    }

    override fun getItemCount(): Int {
        return siswaList.size
    }

    // item clicked
    interface OnItemClickCallback {
        fun onItemClicked(data: Siswa)
    }

    private  lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
}