package com.heltonbustos.ejemplocorrutinas01.app2suspension

import kotlinx.coroutines.delay

fun main(){
    println("Tarea1 " + Thread.currentThread().name)
    //delayCorrutina("Tarea2 ")
    println("Tarea3 " + Thread.currentThread().name)
}

fun tareaLargaDuracion(mensaje: String){
    println(mensaje + Thread.currentThread().name)
    Thread.sleep(10000)
}

suspend fun delayCorrutina(mensaje: String){
    println(mensaje + Thread.currentThread().name)
    delay(timeMillis = 10000)
}