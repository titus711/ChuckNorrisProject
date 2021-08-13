package com.titusnangi.chucknorrisproject.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.titusnangi.chucknorrisproject.R
import com.titusnangi.chucknorrisproject.models.JokeRecyclerData
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener

    }

    private var items = ArrayList<JokeRecyclerData>()

    fun setListData(data: ArrayList<JokeRecyclerData>) {
        this.items = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(inflater, mListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class MyViewHolder(view: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(view) {

        val jokeId = view.jokeId
        val jokeValue = view.jokeValue
        val jokeIcon = view.jokeIcon

        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        fun bind(data: JokeRecyclerData) {

            jokeId.text = data.id

            if (!TextUtils.isEmpty(data.value)) {
                jokeValue.text = data.value
            } else {
                val myText = "No Joke displayed."
                jokeValue.text = myText
            }

            val url = data.icon_url
            Glide.with(jokeIcon)
                .load(url)
                .placeholder(R.drawable.chuck_norris)
                .error(R.drawable.chuck_norris)
                .fallback(R.drawable.chuck_norris)
                .into(jokeIcon)
        }

    }

}