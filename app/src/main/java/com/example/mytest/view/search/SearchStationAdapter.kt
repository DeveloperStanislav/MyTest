package com.example.mytest.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.R
import com.example.mytest.model.CodeStationAPI

class SearchStationAdapter(private var myOnClickListener: MyOnClickListener?) :
    RecyclerView.Adapter<SearchStationAdapter.MySearchViewHolder>() {

    private var localListStation: List<CodeStationAPI> = listOf()

    fun setListStation(data: List<CodeStationAPI>) {
        this.localListStation = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchStationAdapter.MySearchViewHolder {
        return MySearchViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_station_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchStationAdapter.MySearchViewHolder, position: Int) {
        holder.bind(localListStation[position])
    }

    override fun getItemCount() =
        localListStation.size


    inner class MySearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(codeStationAPI: CodeStationAPI) {
            itemView.findViewById<TextView>(R.id.stationListRecycler).text =
                codeStationAPI.nameStation

            itemView.setOnClickListener {
                myOnClickListener?.myOnClickItem(codeStationAPI)
            }
        }
    }
}