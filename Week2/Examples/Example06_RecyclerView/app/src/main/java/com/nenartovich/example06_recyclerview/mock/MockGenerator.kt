package com.nenartovich.example06_recyclerview.mock

import java.util.*
import kotlin.collections.ArrayList

class MockGenerator {
    companion object {
        private val random = Random()
        fun generate(count: Int): List<Mock> {
            val list = arrayListOf<Mock>()
            for (i in 0 until count) {
                list.add(Mock(UUID.randomUUID().toString(), random.nextInt(200)))
            }
            return list
        }
    }
}