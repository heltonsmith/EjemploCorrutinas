package com.heltonbustos.ejemplocorrutinas01

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = SupervisorJob()

        var progressBar: ProgressBar = findViewById(R.id.progressBar)
        var txtUser: EditText = findViewById(R.id.txtUser)
        var txtPass: EditText = findViewById(R.id.txtPass)
        var btnLogin: Button = findViewById(R.id.btnLogin)
        var chkDatos: CheckBox = findViewById(R.id.chkDatos)

        val storage = getSharedPreferences("login", Context.MODE_PRIVATE)
        val userGuardado = storage.getString("user", "")
        val passGuardada = storage.getString("pass", "")
        val chkRecordar = storage.getBoolean("chk", false)

        if(userGuardado!!.length > 0 && passGuardada!!.length > 0){
            txtUser.setText(userGuardado)
            txtPass.setText(passGuardada)
            chkDatos.isChecked = chkRecordar
        }

        btnLogin.setOnClickListener {
            var user:String = txtUser.text.toString()
            var pass:String = txtPass.text.toString()

            if(chkDatos.isChecked){
                val storage = getSharedPreferences("login", Context.MODE_PRIVATE)
                val editor = storage.edit()
                editor.putString("user", user)
                editor.putString("pass", pass)
                editor.putBoolean("chk", true)
                editor.commit()
            }
            else{
                val storage = getSharedPreferences("login", Context.MODE_PRIVATE)
                val editor = storage.edit()
                editor.putString("user", "")
                editor.putString("pass", "")
                editor.putBoolean("chk", false)
                editor.commit()
            }

            progressBar.visibility = View.VISIBLE

           launch {
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

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

}