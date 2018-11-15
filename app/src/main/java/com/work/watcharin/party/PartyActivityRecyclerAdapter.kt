package com.work.watcharin.party

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.watcharin.model.Person
import com.work.watcharin.myapplication.R
import kotlinx.android.synthetic.main.person_box.view.*

class PartyActivityRecyclerAdapter(private val data: List<String>): RecyclerView.Adapter<PartyActivityRecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, index: Int) = holder.onBind(data[index], index)

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.person_box, parent, false))

    class ViewHolder(private var v : View) : RecyclerView.ViewHolder(v) {
        fun onBind(member: String, i: Int) {
            with(v) {
                person_index.text = (i+1).toString()
                person_name.text = member
            }
        }
    }
}

