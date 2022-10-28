package com.example.twofragmentcontainer.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.twofragmentcontainer.R
import com.example.twofragmentcontainer.data.entities.DatabaseLeague
import com.example.twofragmentcontainer.databinding.ItemViewBinding

class LeagueAdapter(private val dataset: List<DatabaseLeague>, private val onClick: (String)->Unit) :
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(league: DatabaseLeague) {
            binding.apply {
                itemImage.load(league.emblem)
                itemTitle.text = league.name
                itemDesc.text = league.code
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.item_view, viewGroup, false)
        val view = ItemViewBinding.inflate(LayoutInflater.from(viewGroup.context))

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = dataset[position]
        viewHolder.itemView.setOnClickListener {
            onClick(item.code!!)
        }
        viewHolder.bind(item)
    }

    override fun getItemCount(): Int = dataset.size


}