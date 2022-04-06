package com.example.mytest.view.listSchedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.R
import com.example.mytest.model.Schedule

class ListAdapter(private var myOnClickListener: MyOnClickListener?) :
    RecyclerView.Adapter<ListAdapter.MyListViewHolder>() {

    private var listSchedule: List<Schedule> = listOf()

    fun setSchedule(data: List<Schedule>) {
        this.listSchedule = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        return MyListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_list_schedule, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        holder.bind(this.listSchedule[position])
    }

    override fun getItemCount() = listSchedule.size

    inner class MyListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(schedule: Schedule) {
            with(itemView) {
                findViewById<TextView>(R.id.stationFromRecycler).text = schedule.fromCity
                findViewById<TextView>(R.id.stationToRecycler).text = schedule.toCity
                findViewById<TextView>(R.id.timeFromRecycler).text = schedule.fromTime
                findViewById<TextView>(R.id.timeToRecycler).text = schedule.toTime

                setOnClickListener {
                    myOnClickListener?.onItemViewClick(schedule)
                }
            }


        }

    }
}