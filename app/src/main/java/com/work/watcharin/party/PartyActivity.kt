package com.work.watcharin.party

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.work.watcharin.model.Party
import com.work.watcharin.myapplication.R
import com.work.watcharin.myapplication.databinding.ActivityPartyBinding
import kotlinx.android.synthetic.main.activity_party.*

class PartyActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        val intent = intent
        super.onCreate(savedInstanceState)
        val binding: ActivityPartyBinding = DataBindingUtil.setContentView(this, R.layout.activity_party)
        val party = intent.getBundleExtra("party").getParcelable<Party>("party")!!
        val recyclerAdapter = PartyActivityRecyclerAdapter(party.members)
        party_recycler.layoutManager = LinearLayoutManager(this)
        party_recycler.adapter = recyclerAdapter
        binding.party = party
    }
}
