package com.example.firebase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.firebase1.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private  lateinit var  firebaceRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaceRef = FirebaseDatabase.getInstance().getReference("contacts")

        binding.sendBNT.setOnClickListener {
            saveData()
        }
        binding.showList.setOnClickListener {
            startActivity(Intent(this, show_activity::class.java))
        }
    }

    private fun saveData() {
        val name = binding.name.text.toString()
        val phone = binding.ph.text.toString()
        if (name.isEmpty()){
            binding.name.error=" write a name"
            Toast.makeText(this, "NNNNNAMMMEEEEEEEE", Toast.LENGTH_SHORT).show()

        }
        else if (phone.isEmpty()) {
            binding.ph.error = " write a phone number"
            Toast.makeText(this, "PHOOONEEEE", Toast.LENGTH_SHORT).show()


        } else if(!name.isEmpty()&&!phone.isEmpty()) {
            val contactID = firebaceRef.push().key!!
            val contacts = Contacts(contactID, name, phone)
            firebaceRef.child(contactID).setValue(contacts)
                .addOnCompleteListener {
                    Toast.makeText(this, "data stored successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "error ${it.message}", Toast.LENGTH_SHORT).show()

                }
        }
    }
}