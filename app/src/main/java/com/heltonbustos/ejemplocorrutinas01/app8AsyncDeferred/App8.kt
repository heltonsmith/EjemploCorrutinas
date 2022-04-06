package com.heltonbustos.ejemplocorrutinas01.app8AsyncDeferred

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(){
    asyncAwaitDeferred()
}

fun asyncAwaitDeferred(){
    runBlocking {
        println("Paso 1 " + System.currentTimeMillis().toString())
        val num1:Deferred<Int> = async {
            tareaLargaDuracion()
        }

        println("Paso 2 " + System.currentTimeMillis().toString())
        val num2:Deferred<Int> = async {
            tareaLargaDuracion()
        }

        println("Paso 3 " + System.currentTimeMillis().toString())
        val resultado: Int = num1.await() + num2.await()
        println(resultado)
    }
}

suspend fun tareaLargaDuracion(): Int{
    delay(timeMillis = 2000)
    println(Thread.currentThread().name)
    return 15
}