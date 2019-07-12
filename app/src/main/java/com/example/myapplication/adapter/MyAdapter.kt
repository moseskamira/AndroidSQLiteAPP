package com.example.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.model.Player

class MyAdapter(var context: Context, private var allPlayers: ArrayList<Player>) : RecyclerView.Adapter<MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)
        return MyHolder(view)

    }

    override fun getItemCount(): Int = allPlayers.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.playerImage!!.setImageResource(R.drawable.ic_launcher_foreground)
        holder.playerName!!.text = allPlayers[position].playerName
        holder.playerPosition!!.text = allPlayers[position].playerPosition
    }

    fun setItems(newItems: ArrayList<Player>) {
        allPlayers = newItems
        notifyDataSetChanged()
    }
}