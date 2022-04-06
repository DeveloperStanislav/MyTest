package com.example.mytest.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.R
import com.example.mytest.model.Schedule

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    private var listHistory: List<Schedule> = listOf()

    fun setListHistory(data: List<Schedule>) {
        this.listHistory = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_history_schedule, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listHistory[position])
    }

    override fun getItemCount() =
        listHistory.size

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(schedule: Schedule) {

            with(itemView) {
                findViewById<TextView>(R.id.stationFromRecyclerHistory).text = schedule.fromCity
                findViewById<TextView>(R.id.stationToRecyclerHistory).text = schedule.toCity
                findViewById<TextView>(R.id.timeFromRecyclerHistory).text = schedule.fromTime
                findViewById<TextView>(R.id.timeToRecyclerHistory).text = schedule.toTime
            }
        }
    }
}