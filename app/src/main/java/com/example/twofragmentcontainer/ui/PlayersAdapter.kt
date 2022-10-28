package com.example.twofragmentcontainer.ui

import com.example.twofragmentcontainer.data.entities.DatabaseTeam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.twofragmentcontainer.R
import com.example.twofragmentcontainer.data.entities.DatabaseLeague
import com.example.twofragmentcontainer.data.entities.DatabasePlayer
import com.example.twofragmentcontainer.databinding.ItemViewBinding

class PlayersAdapter(private val dataset: List<DatabasePlayer>, private val onClick: (DatabasePlayer)->Unit) :
    RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: DatabasePlayer) {
            binding.apply {
                itemTitle.text = player.name
                itemDesc.text = player.position
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemViewBinding.inflate(LayoutInflater.from(viewGroup.context))

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = dataset[position]
        viewHolder.itemView.setOnClickListener {
            onClick(item)
        }
        viewHolder.bind(item)
    }

    override fun getItemCount(): Int = dataset.size


}