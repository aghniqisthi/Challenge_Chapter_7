package com.example.challengechapter7.marcelle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.challengechapter7.marcelle.model.ResponseDataProductItem
import com.example.challengechapter7.marcelle.network.RetrofitClientProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelProduct (application: Application) : AndroidViewModel(application) {
    var liveDataProduct : MutableLiveData<List<ResponseDataProductItem>>

    init {
        liveDataProduct = MutableLiveData()
    }
    fun getliveDataProduct() : MutableLiveData<List<ResponseDataProductItem>> {
        return liveDataProduct
    }

    fun callApiProduct(){
        RetrofitClientProduct.instance.getAllProduct().enqueue(object :
            Callback<List<ResponseDataProductItem>> {
            override fun onResponse(call: Call<List<ResponseDataProductItem>>, response: Response<List<ResponseDataProductItem>>) {
                if (response.isSuccessful){
                    liveDataProduct.postValue(response.body())
                }
                else{

                }
            }
            override fun onFailure(call: Call<List<ResponseDataProductItem>>, t: Throwable) {

            }
        })
    }
}