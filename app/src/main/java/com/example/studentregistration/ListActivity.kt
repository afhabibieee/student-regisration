package com.example.studentregistration

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ListActivity : AppCompatActivity() {
    private lateinit var reference: DatabaseReference
    private lateinit var siswaList: MutableList<Siswa>
    private lateinit var rvSiswa: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        reference = FirebaseDatabase.getInstance().getReference("siswa")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        supportActionBar?.title = "Students"

        siswaList = mutableListOf()
        rvSiswa = findViewById(R.id.rv_list)
        rvSiswa.setHasFixedSize(true)

        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    siswaList.clear()
                    for(h:DataSnapshot in p0.children){
                        val siswa: Siswa? = h.getValue(Siswa::class.java)
                        if (siswa != null){
                            siswaList.add(siswa)
                        }
                    }
                }

                showRecyclerView()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun showRecyclerView() {
        rvSiswa.layoutManager = LinearLayoutManager(this)
        val viewSiswaAdapter = ViewSiswaAdapter(siswaList)
        rvSiswa.adapter = viewSiswaAdapter

        viewSiswaAdapter.setOnItemClickCallback(object : ViewSiswaAdapter.OnItemClickCallback {
            override fun onItemClicked(siswa: Siswa) {
                showPopup(siswa)
            }
        })
    }

    private fun showPopup(siswa: Siswa) {
        val ctx: Context = this@ListActivity
        val builder = AlertDialog.Builder(ctx)
        builder.setTitle("Update Biodata")
        val inflater = LayoutInflater.from(ctx)
        val view = inflater.inflate(R.layout.activity_update, null)

        val et_nama = view.findViewById<EditText>(R.id.nama)
        val et_tempat = view.findViewById<EditText>(R.id.tempat)
        val et_tanggal = view.findViewById<EditText>(R.id.tanggal)
        val et_phone = view.findViewById<EditText>(R.id.phone)
        val et_email = view.findViewById<EditText>(R.id.email)

        et_nama.setText(siswa.nama)
        et_tempat.setText(siswa.tempat)
        et_tanggal.setText(siswa.tanggal)
        et_phone.setText(siswa.phone)
        et_email.setText(siswa.email)

        builder.setView(view)

        builder.setPositiveButton("Update"){p0,p1 ->
            val dbSiswa = FirebaseDatabase.getInstance().getReference("siswa")

            val nama = et_nama.text.toString().trim()
            val tempat = et_tempat.text.toString().trim()
            val tanggal = et_tanggal.text.toString().trim()
            val phone = et_phone.text.toString().trim()
            val email = et_email.text.toString().trim()

            if(nama.isEmpty()){
                et_nama.error = "Please fill this field!"
                et_nama.requestFocus()
                return@setPositiveButton
            }
            if(tempat.isEmpty()){
                et_tempat.error = "Please fill this field!"
                et_tempat.requestFocus()
                return@setPositiveButton
            }
            if(tanggal.isEmpty()){
                et_tanggal.error = "Please fill this field!"
                et_tanggal.requestFocus()
                return@setPositiveButton
            }
            if(phone.isEmpty()){
                et_phone.error = "Please fill this field!"
                et_phone.requestFocus()
                return@setPositiveButton
            }
            if(email.isEmpty()){
                et_email.error = "Please fill this field!"
                et_email.requestFocus()
                return@setPositiveButton
            }

            val siswaData = Siswa(siswa.id, nama, tempat, tanggal, phone, email)
            dbSiswa.child(siswa.id!!).setValue(siswaData)

            Toast.makeText(ctx, "Data berhasil diperbaharui", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Cancel"){p0,p1 ->
        }

        builder.setNeutralButton("Delete"){po,p1 ->
            val dbSiswa = FirebaseDatabase.getInstance().getReference("siswa").child(siswa.id).removeValue()
            Toast.makeText(ctx, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
        }

        val alert = builder.create()
        alert.show()
    }
}