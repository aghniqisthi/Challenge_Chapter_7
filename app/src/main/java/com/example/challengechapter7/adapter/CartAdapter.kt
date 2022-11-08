package com.example.challengechapter7.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter7.databinding.ItemProductBinding
import com.example.challengechapter7.model.ResponseDataCartItem
import com.example.challengechapter7.view.DetailCartActivity

class CartAdapter (var listCart : List<ResponseDataCartItem>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtJudul.text = listCart[position].name
        holder.binding.txtHarga.text = listCart[position].price
        Glide.with(holder.itemView.context).load(listCart[position].imageLink).into(holder.binding.ivItem)

        holder.binding.cardItem.setOnClickListener {
            val pindah = Intent(it.context, DetailCartActivity::class.java)
            pindah.putExtra("details", listCart[position])
            it.context.startActivity(pindah)
        }
    }

    override fun getItemCount(): Int = listCart.size

}