package com.work.watcharin.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.watcharin.model.Party
import com.work.watcharin.party.PartyActivity
import kotlinx.android.synthetic.main.view_box.view.*

class MainActivityRecyclerAdapter(private val data: List<Party>): RecyclerView.Adapter<MainActivityRecyclerAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, index: Int) = holder.onBind(data[index])

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_box, parent, false))

    class ViewHolder(private val v : View) : RecyclerView.ViewHolder(v) {
        fun onBind(party: Party) {
            with(v) {
                textView_message.text = party.topic
                join_button.setOnClickListener{
                    val bundle = Bundle().apply { putParcelable("party",party) }
                    val intent = Intent(v.context , PartyActivity::class.java).putExtra("party", bundle)
                    v.context.startActivity(intent)
                }
            }
        }
    }
}

