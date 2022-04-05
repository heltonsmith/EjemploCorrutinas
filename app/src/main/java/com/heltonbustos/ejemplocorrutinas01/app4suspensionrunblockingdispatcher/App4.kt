package com.heltonbustos.ejemplocorrutinas01.app4suspensionrunblockingdispatcher

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main(){
    println("Tarea1 " + Thread.currentThread().name)

    //Dispatchers.Unconfined para que el hilo se ejecute (da igual donde)
    runBlocking(Dispatchers.Unconfined) {
        delayCorrutina("Tarea2 ")
    }

    //Dispatchers.Default para que el hilo se ejecute con operaciones de uso intensivo de la CPU (algoritmo de encriptación)
    runBlocking(Dispatchers.Default){
        delayCorrutina("Tarea3 ")
    }

    //Dispatchers.IO para que el hilo se ejecute con entrada y salida de datos (WebService, BD, Archivo)
    runBlocking(Dispatchers.IO) {
        delayCorrutina("Tarea4 ")
    }

    //Hilo con nombre propio
    runBlocking(newSingleThreadContext("MiHilo")){
        delayCorrutina("Tarea5 ")
    }

    /*
    //Hilo para el Main de android
    runBlocking(Dispatchers.Main){
        delayCorrutina("Tarea6 ")
    }
    */

    println("Tarea7 " + Thread.currentThread().name)
}

suspend fun delayCorrutina(mensaje: String){
    println(mensaje + Thread.currentThread().name)
    delay(timeMillis = 10000)
}