package com.example.appservitec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.textclassifier.TextLinks
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity2 : AppCompatActivity() {
    var txtNombre: EditText?=null
    var txtCorreo: EditText?=null
    var txtTelefono: EditText?=null
    var txtCodigos: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        txtNombre=findViewById(R.id.txtNombre)
        txtCorreo=findViewById(R.id.txtCorreo)
        txtTelefono=findViewById(R.id.txtTelefono)
        txtCodigos=findViewById(R.id.txtCodigos)
        val txtCodigo=intent.getStringExtra("txtCodigo").toString()

        val queue=Volley.newRequestQueue(this)
//        val url="http://192.168.1.10:8080/servitec/clientes/obtenerporcodigo/"+txtCodigo;
        /*     val jsonObjectRequest = JsonObjectRequest(
                 Request.Method.GET,url,null,
                 { response ->
                     txtNombre?.setText(response.getString("nombreCliente"))
                     txtCorreo?.setText(response.getString("correo"))
                     txtTelefono?.setText(response.getString("telefono"))
                     txtCodigos?.setText(response.getString("codigoCliente"))

                 }, { error ->
                     Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
                 }*/
        
    }
    fun clickEditar(view: View){
        val url="http://192.168.1.10:8080/servitec/clientes/"
        val queue=Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.PUT,url,

            Response.Listener { response ->
                Toast.makeText(this,"El usuario ha sido editado de forma exitosa",Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Error al editar el usuario $error",Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("codigoCliente",txtCodigos?.text.toString())
                parametros.put("nombreCliente",txtNombre?.text.toString())
                parametros.put("telefono",txtTelefono?.text.toString())
                parametros.put("correo",txtCorreo?.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
    fun clickRegresar(view:View){
        var intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}