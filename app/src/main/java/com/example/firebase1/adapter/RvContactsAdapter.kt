package com.example.firebase1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase1.databinding.RvContactItemBinding
import com.example.firebase1.Contacts
import com.example.firebase1.Update
import com.example.firebase1.show_activity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class RvContactsAdapter(private val contactList:java.util.ArrayList<Contacts>, var context: Context):RecyclerView.Adapter<RvContactsAdapter.ViewHolder>() {
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
rvContainer.setOnClickListener {

    context.startActivity(Intent(context, Update::class.java))
    //startActivity(Intent(this, show_activity::class.java))
//    startActivity()
}
                rvContainer.setOnLongClickListener {
                    MaterialAlertDialogBuilder(holder.itemView.context)
                        .setTitle("Delete item permanently")
                        .setMessage("Are you sure you want to delete this item?")
                        .setPositiveButton("Yes"){_,_ ->
                            val firebaseRef = FirebaseDatabase.getInstance().getReference("contacts")
                         //   val storageRef = FirebaseStorage.getInstance().getReference("Images")
                            //storage
                       //     storageRef.child(currentItem.id.toString()).delete()

                            // realtime database
                            firebaseRef.child(currentItem.id.toString()).removeValue()
                                .addOnSuccessListener {
                                    Toast.makeText(holder.itemView.context,"Item removed successfully" ,Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener {error ->
                                    Toast.makeText(holder.itemView.context,"error ${error.message}" ,Toast.LENGTH_SHORT).show()
                                }
                        }
                        .setNegativeButton("No"){_,_ ->
                            Toast.makeText(holder.itemView.context,"canceled" ,Toast.LENGTH_SHORT).show()
                        }
                        .show()

                    return@setOnLongClickListener true
                }
            }

        }
       // holder.binding.

    }
}