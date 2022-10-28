package com.example.twofragmentcontainer.ui

import com.example.twofragmentcontainer.data.entities.DatabaseTeam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.twofragmentcontainer.R
import com.example.twofragmentcontainer.data.entities.DatabaseLeague
import com.example.twofragmentcontainer.databinding.ItemViewBinding
import com.example.twofragmentcontainer.databinding.ItemViewTeamBinding

class TeamsAdapter(private val dataset: List<DatabaseTeam>, private val onClick: (Int)->Unit) :
    RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemViewTeamBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(team: DatabaseTeam) {
            binding.apply {
                position.text = team.position.toString()
                itemImage.load(team.crest)
                itemName.text = team.name
                itemPoints.text = team.points.toString()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemViewTeamBinding.inflate(LayoutInflater.from(viewGroup.context))

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = dataset[position]
        viewHolder.itemView.setOnClickListener {
            onClick(item.id)
        }
        viewHolder.bind(item)
    }

    override fun getItemCount(): Int = dataset.size


}