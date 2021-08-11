package com.titusnangi.chucknorrisproject.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.titusnangi.chucknorrisproject.R
import com.titusnangi.chucknorrisproject.models.RecyclerList
import com.titusnangi.chucknorrisproject.viewmodels.JokeRecyclerActivityViewModel
import kotlinx.android.synthetic.main.activity_joke_recycler_view.*

class JokeRecyclerViewActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke_recycler_view)

        initRecyclerView()
        createData()
    }


    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@JokeRecyclerViewActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
            recyclerViewAdapter.setOnItemClickListener(object :
                RecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {

                    Toast.makeText(
                        this@JokeRecyclerViewActivity,
                        "You clicked on joke # $position",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            })

            val decoration =
                DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(decoration)

        }
    }


    fun createData() {
        val viewModel = ViewModelProviders.of(this).get(JokeRecyclerActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList> {

            progressbar.visibility = View.GONE

            if (it != null) {
                recyclerViewAdapter.setListData(it.result)
                recyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@JokeRecyclerViewActivity, "Error generated in getting jokes.",
                    Toast.LENGTH_LONG
                ).show()
            }

        })

        viewModel.makeApiCall("Food")

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                viewModel.makeApiCall(query)

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }
}