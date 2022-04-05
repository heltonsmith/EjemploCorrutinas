package com.heltonbustos.ejemplocorrutinas01.app5launch

import kotlinx.coroutines.*

fun main(){
    println("Tarea1 " + Thread.currentThread().name)

    GlobalScope.launch {
        delayCorrutina("Tarea2 ")
    }

    println("Tarea3 " + Thread.currentThread().name)

    Thread.sleep(11000)
}

suspend fun delayCorrutina(mensaje: String){
    delay(timeMillis = 10000)
    println(mensaje + Thread.currentThread().name)
}