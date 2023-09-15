package com.example.firebase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.firebase1.constant.Extra_Contact
import com.example.firebase1.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Update : AppCompatActivity() {
    lateinit var contact:Contacts
    lateinit var  binding: ActivityUpdateBinding
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
         contact= intent.getParcelableExtra(Extra_Contact)!!
        databaseReference= FirebaseDatabase.getInstance().getReference("contacts")
binding.apply {
    nameUpdate.setText(contact.name.toString())
    phoneUpdate.setText(contact.phoneNumber.toString())

    UpdateBNT.setOnClickListener {
        val name= nameUpdate.text.toString()

        val phone = phoneUpdate.text.toString()
        if(name.isEmpty())nameUpdate.error="Empty string"
       else if(phone.isEmpty())phoneUpdate.error="Empty string"
else {
            val UPDTcontact = Contacts(contact.id, name, phone)

            databaseReference.child(contact.id.toString()).setValue(UPDTcontact)
                .addOnCompleteListener {
                    Toast.makeText(this@Update, "${contact.name} updated successfully", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this@Update,show_activity::class.java))

                }
        }
    }

}
    }
}