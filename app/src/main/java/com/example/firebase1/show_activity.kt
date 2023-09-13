package com.example.firebase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase1.adapter.RvContactsAdapter
import com.example.firebase1.databinding.ActivityShowBinding
import com.google.firebase.database.*

class show_activity : AppCompatActivity() {
    lateinit var binding: ActivityShowBinding
    lateinit var contactList:java.util.ArrayList<Contacts>
    lateinit var FirebaseRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseRef= FirebaseDatabase.getInstance().getReference("contacts")
        contactList= arrayListOf()
        fetchData()
        binding.rvList.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@show_activity)
        }
    }

    private fun fetchData() {
        FirebaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                contactList.clear()
                if(snapshot.exists()){
                    for(contactSnap in snapshot.children){
                        val contacts= contactSnap.getValue(Contacts::class.java)
                        contactList.add(contacts!!)
                    }
                }
                val rvAdapter= RvContactsAdapter(contactList)
                binding.rvList.adapter=rvAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@show_activity,"$error ", Toast.LENGTH_SHORT).show()

            }

        })
    }
}