package com.ddanilov.convention

import java.io.File
import java.io.FileInputStream
import java.util.Properties

object KeyHelper {

    const val KEY_STORE_FILE = "storeFile"
    const val KEY_STORE_PASSWORD = "storePassword"
    const val KEY_ALIAS = "keyAlias"
    const val KEY_PASSWORD = "keyPassword"
    private val properties by lazy {
        Properties().apply { load(FileInputStream(File("signing.properties"))) }
    }

    fun getValue(key: String): String {
        return properties.getProperty(key)
    }
}
