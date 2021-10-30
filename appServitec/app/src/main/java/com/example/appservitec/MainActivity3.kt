package com.example.appservitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity3 : AppCompatActivity() {
    var tbEstadosFin:TableLayout?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        tbEstadosFin=findViewById(R.id.tbEstadosFin)
        tbEstadosFin?.removeAllViews()
        val txtCodigo=intent.getStringExtra("txtCodigo").toString()


        var queue=Volley.newRequestQueue(this)
        for (i in 0 until 5){
            val registro=LayoutInflater.from(this).inflate(R.layout.table_row_sv,null,false)
            val ColNombre=registro.findViewById<View>(R.id.ColNombre) as TextView
            val ColVer=registro.findViewById<View>(R.id.ColVer)
            ColNombre.text="Nombre $i"
            ColVer.id=i
            tbEstadosFin?.addView(registro)
        }


    }
    fun cickVerProcesos(view: View){
        var intent= Intent(this,MainActivity4::class.java)
        startActivity(intent)
    }
    fun cickVers(view: View){
        var intent= Intent(this,MainActivity2::class.java)
        startActivity(intent)
    }
    }

