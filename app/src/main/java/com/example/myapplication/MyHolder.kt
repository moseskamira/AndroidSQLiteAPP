package com.example.myapplication

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list_view.view.*

class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private lateinit var itemClickListner: ItemClickListner

    val playerImage: ImageView? = itemView.player_image
    val playerName: TextView? = itemView.player_name
    val playerPosition: TextView? = itemView.player_position



    override fun onClick(newView: View){
        this.itemClickListner.onItemClick(newView, layoutPosition)
    }

    fun setItemOnClickListner(icl: ItemClickListner) {
        this.itemClickListner = icl
    }
}