package com.example.firebase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        firebaceRef = FirebaseDatabase.getInstance().getReference("path")
        binding.hello.setOnClickListener {
            firebaceRef.setValue("First message")
                .addOnCompleteListener {
                    Toast.makeText(this, "data stored ", Toast.LENGTH_SHORT).show()
                }

        }
    }
}