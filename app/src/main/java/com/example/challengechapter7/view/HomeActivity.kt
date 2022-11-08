package com.example.challengechapter7.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter7.R
import com.example.challengechapter7.adapter.ProductAdapter
import com.example.challengechapter7.databinding.ActivityHomeBinding
import com.example.challengechapter7.viewmodel.ViewModelProduct
import com.example.challengechapter7.viewmodel.ViewModelUser
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var binding: ActivityHomeBinding
    lateinit var viewModelUser : ViewModelUser
    lateinit var viewModelProduct: ViewModelProduct

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelUser = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModelProduct = ViewModelProvider(this).get(ViewModelProduct::class.java)
        firebaseAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        showData()

        binding.btnProfile.setOnClickListener {
            val pindah = Intent(this, ProfileActivity::class.java)
            startActivity(pindah)
        }

        binding.btnFavorit.setOnClickListener {
            val pindah = Intent(this, CartActivity::class.java)
            startActivity(pindah)
        }
    }

    fun showData(){
        viewModelUser.dataUser.observe(this, Observer {
            binding.txtWelcomeUser.text = it.username
        })
        viewModelProduct.getliveDataProduct().observe(this, Observer {
            if (it != null) {
                binding.rvProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvProduct.adapter = ProductAdapter(it)
            } else {
                Toast.makeText(this, "There is no data to show", Toast.LENGTH_SHORT).show()
            }
        })
        viewModelProduct.callApiProduct()
    }
}