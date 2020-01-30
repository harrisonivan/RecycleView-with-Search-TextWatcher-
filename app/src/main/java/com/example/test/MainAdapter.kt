package com.example.test

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.model.ResponsRespons
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recycle_main.view.*


class MainAdapter(var dataList: List<ResponsRespons>): RecyclerView.Adapter<MainAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycle_main, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(dataList!![position])
        holder.bind(dataList!![position])
    }

    //set item
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bind(item: ResponsRespons){
            itemView.tv_item_name.text = item.login
            Log.e("item","${item.login}")

            Picasso.get().load(item.avatar).into(itemView.iv_item_layout_image_recycler)
        }
    }

}