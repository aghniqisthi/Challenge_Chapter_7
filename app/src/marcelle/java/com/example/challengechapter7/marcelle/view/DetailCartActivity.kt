package com.example.challengechapter7.marcelle.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.challengechapter7.databinding.ActivityDetailCartBinding
import com.example.challengechapter7.marcelle.model.ResponseDataCartItem
import com.example.challengechapter7.marcelle.viewmodel.ViewModelCart
import com.example.challengechapter7.marcelle.viewmodel.ViewModelUser

class DetailCartActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailCartBinding
    lateinit var dataCart : ResponseDataCartItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataCart = intent.getSerializableExtra("details") as ResponseDataCartItem

        if(dataCart.hexValue == null || dataCart.hexValue == "") {
            binding.layoutDetailFavorit.setBackgroundColor(Color.parseColor("#E6DFF3"))
        } else binding.layoutDetailFavorit.setBackgroundColor(Color.parseColor(dataCart.hexValue))

        binding.txtTitle.text = dataCart.name
        binding.txtPrice.text = dataCart.price
        binding.txtDesc.text = "${dataCart.description}\n"
        Glide.with(this).load(dataCart.imageLink).into(binding.imgProduct)

        binding.btnBackDet.setOnClickListener {
            val pindah = Intent(this, com.example.challengechapter7.marcelle.view.CartActivity::class.java)
            startActivity(pindah)
        }

        binding.btnRemoveCart.setOnClickListener {
            var viewModel = ViewModelProvider(this).get(ViewModelCart::class.java)
            var viewModelUser = ViewModelProvider(this).get(ViewModelUser::class.java)

            viewModelUser.dataUser.observe(this, {
                viewModel.callDeleteCart(it.id, dataCart.id.toInt())
            })

            Toast.makeText(this, "${dataCart.name} removed from Cart!", Toast.LENGTH_SHORT).show()
            val pindah = Intent(this, com.example.challengechapter7.marcelle.view.CartActivity::class.java)
            startActivity(pindah)
        }
    }
}