package com.example.studentregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistryActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var nama: EditText
    private lateinit var tempat: EditText
    private lateinit var tanggal: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)

        // action tittle bar
        supportActionBar?.title = "Registration"

        // drop frame layout initially
        BottomSheetBehavior.from(findViewById(R.id.sheet)).apply {
            peekHeight=200
            this.state= BottomSheetBehavior.STATE_COLLAPSED
        }

        nama = findViewById(R.id.et_name)
        tempat = findViewById(R.id.et_tempat)
        tanggal = findViewById(R.id.et_tanggal)
        phone = findViewById(R.id.et_phone)
        email = findViewById(R.id.et_email)
        submit = findViewById(R.id.btn_submit)

        submit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_submit -> {
                saveData()
                val intent = Intent(this@RegistryActivity, ListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun saveData() {
        val nama: String = this.nama.text.toString().trim()
        val tempat: String = this.tempat.text.toString().trim()
        val tanggal: String = this.tanggal.text.toString().trim()
        val phone: String = this.phone.text.toString().trim()
        val email: String = this.email.text.toString().trim()

        if(nama.isEmpty()){
            this.nama.error = "Please fill this field!"
            return
        }
        if(tempat.isEmpty()){
            this.tempat.error = "Please fill this field!"
            return
        }
        if(tanggal.isEmpty()){
            this.tanggal.error = "Please fill this field!"
            return
        }
        if(phone.isEmpty()){
            this.phone.error = "Please fill this field!"
            return
        }
        if(email.isEmpty()){
            this.email.error = "Please fill this field!"
            return
        }

        val reference: DatabaseReference = FirebaseDatabase.getInstance().getReference("siswa")
        val siswaId: String? = reference.push().key
        val siswa = Siswa(siswaId!!, nama, tempat, tanggal, phone, email)

        if (siswaId != null) {
            reference.child(siswaId).setValue(siswa).addOnCompleteListener{
                Toast.makeText(applicationContext, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}