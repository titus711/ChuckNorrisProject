package com.titusnangi.chucknorrisproject.request

import com.titusnangi.chucknorrisproject.models.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("jokes/search")
    fun getDataFromAPI(@Query("query") query: String): Call<RecyclerList>

}