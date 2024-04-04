package com.satya.exerciseweek6_160421048.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.satya.exerciseweek6_160421048.model.Cake

class CakeViewModel(application: Application):AndroidViewModel(application) {
    val cakesLD = MutableLiveData<ArrayList<Cake>>()
    val cakeLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        cakeLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.12.142.95/cake/cake.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Cake>>() { }.type
                val result = Gson().fromJson<List<Cake>>(it, sType)
                cakesLD.value = result as ArrayList<Cake>?
                loadingLD.value = false

                Log.d("showcake", result.toString())
            },{
                Log.d("showcake", it.toString())
                cakeLoadErrorLD.value = false
                loadingLD.value = false
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}