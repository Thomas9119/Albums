package com.example.albums

import org.junit.Test

import org.junit.Assert.*

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
    fun testData() {
        assertEquals(solutions(arrayOf(1, 2, 4, 3, 6, 8), t = 9), arrayOf(1, 4))
    }

    fun solutions(agrs: Array<Int>, t: Int): Array<Int> {
        val map: HashMap<Int, Int> = HashMap()
        map[1] = 4
        map[2] = 3
        map[4] = 3
        map[3] = 3
        for (i in agrs.indices) {
            val key = t - agrs[i]
            if (map[key] != null) {
                return arrayOf(key, agrs[i])
            } else {
                map[agrs[i]] = t - agrs[i];
            }
        }
        return arrayOf(-1, -1)
    }
}