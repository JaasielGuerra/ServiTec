package com.example.appservitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    var txtNombre:EditText?=null
    var txtEmail:EditText?=null
    var txtTelefono:EditText?=null
    var txtPass:EditText?=null
    var btnAcceder:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtNombre=findViewById(R.id.txtNombre)
        txtTelefono=findViewById(R.id.txtTelefono)
        btnAcceder=findViewById(R.id.btnAcceder);



    }

    fun cickVer(view: View){
        var txtCodigo=findViewById<EditText>(R.id.txtCodigo)
        var intent= Intent(this,MainActivity3::class.java)
        intent.putExtra("txtCodigo",txtCodigo.text.toString())
        startActivity(intent)
    }
}