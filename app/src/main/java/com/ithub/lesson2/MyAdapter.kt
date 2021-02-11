package com.ithub.lesson2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ithub.lesson2.databinding.ItemPersonBinding

interface PersonClickListener {
    fun onPersonClick(item: Person)
}

class MyDiffUtil(private val oldList: List<Person>, private val newList: List<Person>): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]

        return newItem.name == oldItem.name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newItem = newList[newItemPosition]
        val oldItem = oldList[oldItemPosition]

        return  newItem.age == oldItem.age && newItem.pos == oldItem.pos
    }
}

class MyAdapter(var list: MutableList<Person>, private val listener: PersonClickListener) : RecyclerView.Adapter<MyAdapter.MyVH>() {

    inner class MyVH(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person, position: Int) {
            binding.text.text = "${item.name} ${item.age}"
            binding.root.setOnClickListener {
//                list.removeAt(0)
//                notifyItemRemoved(0)
//                notifyDataSetChanged()
                listener.onPersonClick(item)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyVH(binding)
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    fun update(list: MutableList<Person>){
        this.list = list
        notifyDataSetChanged()
    }
}