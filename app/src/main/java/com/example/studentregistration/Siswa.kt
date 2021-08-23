package com.example.studentregistration

data class Siswa(
    val id: String,
    val nama: String,
    val tempat: String,
    val tanggal: String,
    val phone: String,
    val email: String
){
    constructor():this("", "", "", "", "", "")
}
