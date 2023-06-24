package com.dmarco.dmarcoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class PlatosAdapter (private val list: List<Platos>) : RecyclerView.Adapter<PlatosViewHolder>(){

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlatosViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PlatosViewHolder, position: Int) {
        val book: Platos = list[position]
        holder.bind(book)
    }
}