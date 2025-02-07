package com.example.activitytest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FruitAdapter(val fruitList: List<Fruit>): RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        val viewHolder = ViewHolder(view)
        // Example of setting a click listener on the entire item
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val fruit = fruitList[position]
                // Handle item click here, using the 'position' and 'fruit'
                Toast.makeText(parent.context, "You clicked on fruit image ${fruit.name} at position $position", Toast.LENGTH_SHORT).show()
                println("You clicked on fruit image: ${fruit.name} at position: $position")
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name

        // Example of setting a click listener on a specific view within the item
        holder.fruitName.setOnClickListener {
            // You can use the 'position' here directly
            Toast.makeText(holder.itemView.context, "You clicked on fruit name ${fruit.name} at position: $position", Toast.LENGTH_SHORT).show()
            println("Clicked on fruit name: ${fruit.name} at position: $position")
        }
    }

    override fun getItemCount() = fruitList.size

}