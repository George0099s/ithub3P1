package com.ithub.lesson2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ithub.lesson2.databinding.ItemPersonBinding

interface PersonClickListener {
    fun onPersonClick(item: Person)
}

class MyAdapter(private var list: List<Person>, private val listener: PersonClickListener) : RecyclerView.Adapter<MyAdapter.MyVH>() {



    inner class MyVH(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person) {
            binding.text.text = "${item.name} ${item.age}"
            binding.root.setOnClickListener {
                listener.onPersonClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyVH(binding)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun update(list: List<Person>){
        this.list = list
        notifyDataSetChanged()
    }
}