package com.heltonbustos.ejemplocorrutinas01.app6GlobalScopeJob

import kotlinx.coroutines.*

fun main(){
    println("Tarea1 " + Thread.currentThread().name)

    val job = GlobalScope.launch {
        delayCorrutina("Tarea2 ")
    }
    job.cancel()

    println("Tarea3 " + Thread.currentThread().name)

    Thread.sleep(11000)
}

suspend fun delayCorrutina(mensaje: String){
    delay(timeMillis = 10000)
    println(mensaje + Thread.currentThread().name)
}