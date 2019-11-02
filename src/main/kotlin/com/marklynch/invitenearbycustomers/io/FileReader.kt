package com.marklynch.invitenearbycustomers.io

import java.io.FileInputStream
import java.io.IOException

val PROD_RESOURCES_BASE_PATH = getBaseResourcesPath(false)
val TEST_RESOURCES_BASE_PATH = getBaseResourcesPath(true)

private fun getBaseResourcesPath(testing: Boolean): String {
    return if (testing) "./src/test/resources/" else "./src/main/resources/"
}

@Throws(IOException::class)
fun readFile(path: String) =
    FileInputStream(path).bufferedReader().use {
        it.readText()
    }
