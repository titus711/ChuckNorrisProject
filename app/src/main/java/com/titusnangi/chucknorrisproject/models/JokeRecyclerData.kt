package com.titusnangi.chucknorrisproject.models

data class RecyclerList(val result: ArrayList<JokeRecyclerData>)
data class JokeRecyclerData(val id:String ,val value:String, val icon_url:String) {
}