package com.work.watcharin.myapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.work.watcharin.createparty.CreatePartyActivity
import com.work.watcharin.model.Party


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getPartyList().observe(this, Observer {
            showPartyList(it!!)
        })
        createButton.setOnClickListener { createParty() }
    }

    private fun createParty() {
        val intent = Intent(this, CreatePartyActivity::class.java)
        startActivity(intent)
    }

    private fun showPartyList(data: List<Party>) {
        val recyclerAdapter = MainActivityRecyclerAdapter(data)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = recyclerAdapter
    }
}

