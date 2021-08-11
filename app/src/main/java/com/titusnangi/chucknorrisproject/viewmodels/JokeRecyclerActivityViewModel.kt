package com.titusnangi.chucknorrisproject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.titusnangi.chucknorrisproject.models.RecyclerList
import com.titusnangi.chucknorrisproject.request.RetroInstance
import com.titusnangi.chucknorrisproject.request.RetroService
import retrofit2.Call
import retrofit2.Response

class JokeRecyclerActivityViewModel : ViewModel() {

    var recyclerListData: MutableLiveData<RecyclerList> = MutableLiveData()

    fun getRecyclerListDataObserver(): MutableLiveData<RecyclerList> {
        return recyclerListData
    }

    fun makeApiCall(input: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromAPI(input)
        call.enqueue(object : retrofit2.Callback<RecyclerList> {

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                recyclerListData.postValue(null)
            }
        })
    }
}