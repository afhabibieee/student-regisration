package com.example.studentregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Home"

        val btnTambahData : LinearLayout = findViewById(R.id.page_pendaftaran)
        btnTambahData.setOnClickListener(this)

        val btnDaftarSiswa : LinearLayout = findViewById(R.id.page_daftar_siswa)
        btnDaftarSiswa.setOnClickListener(this)

    }

    // menu override di action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMenu(itemId: Int) {
        when (itemId){
            R.id.action_profile -> {
                val intentProfile = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(intentProfile)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.page_pendaftaran -> {
                val intentRegist = Intent(this@MainActivity, RegistryActivity::class.java)
                startActivity(intentRegist)
            }
            R.id.page_daftar_siswa -> {
                val intentList = Intent(this@MainActivity, ListActivity::class.java)
                startActivity(intentList)
            }
        }
    }
}