package com.bita.lost

import org.junit.Test

import org.junit.Assert.*
import java.lang.StringBuilder
import java.text.DecimalFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun doubleToString() {
        val doubleList = arrayOf(6.1708348E7, 6.170824E7, 6.1707868E7, 6.1707857E7, 6.1707856E7, 6.1707734E7, 6.1707696E7, 6.1707647E7, 6.1707574E7, 6.170753E7)
        doubleList.forEach {
            println("$it : ${(DecimalFormat("#").format(it))}")
        }

    }

    @Test
    fun parseDateMain() {
        println(parseDate("2020.5.1"))
        println(parseDate("2020.5.12"))
    }


    fun parseDate(date: String): String {
        val parts = date.split(".")
        val result = StringBuilder()
        parts.forEach { part -> result.append(DecimalFormat("00").format(part.toInt())) }
        return result.toString()
    }
}
