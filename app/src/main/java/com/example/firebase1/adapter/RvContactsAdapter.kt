package com.example.firebase1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase1.databinding.RvContactItemBinding
import com.example.firebase1.Contacts

class RvContactsAdapter(private val contactList:java.util.ArrayList<Contacts>):RecyclerView.Adapter<RvContactsAdapter.ViewHolder>() {
    class ViewHolder(val binding: RvContactItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvContactItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return contactList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem= contactList[position]
        holder.apply {
            binding.apply {
                name.text= currentItem.name
                phone.text=currentItem.phoneNumber
                id.text=currentItem.id
            }
        }
    }
}