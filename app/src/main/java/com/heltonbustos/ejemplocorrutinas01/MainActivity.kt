package com.heltonbustos.ejemplocorrutinas01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var progressBar: ProgressBar = findViewById(R.id.progressBar)
        var txtUser: EditText = findViewById(R.id.txtUser)
        var txtPass: EditText = findViewById(R.id.txtPass)
        var btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            var user:String = txtUser.text.toString()
            var pass:String = txtPass.text.toString()

            progressBar.visibility = View.VISIBLE

            GlobalScope.launch(Dispatchers.Main) {
                var x:Boolean = validarUser(user, pass) //tarea larga y bloqueada
                progressBar.visibility = View.GONE
                if(x){

                    val intent = Intent(applicationContext, Bienvenida::class.java)
                    intent.putExtra("usuario", user)
                    startActivity(intent)

                }
                else{
                    Toast.makeText(applicationContext, "Inválido", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }

    suspend fun validarUser(user: String, pass: String): Boolean{
        delay(timeMillis = 7000)
        if(user == "admin" && pass == "123"){
            return true
        }
        return false
    }
}