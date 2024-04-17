package utils

import java.io.FileInputStream
import java.io.IOException
import java.util.Properties

object ConfProperties {
    private lateinit var fileStream: FileInputStream

    private val config: Properties? by lazy {
        try {
            fileStream = FileInputStream("conf.properties")
            val props = Properties().also { it.load(fileStream) }
            props
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } finally {
            try {
                fileStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun getProperty(prop: String): String = config!!.getProperty(prop)
}