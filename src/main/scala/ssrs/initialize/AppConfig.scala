package ssrs.initialize

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

import com.typesafe.config.{Config, ConfigFactory}

trait  AppConfig {
    private val fileConfig = "app.conf"
    protected val confLoader : Config = getLoader(fileConfig)
    protected val today = new SimpleDateFormat("ddMMMyyyy").format(new Date)

    private def getLoader(fileConfig: String): Config= {
        val rs = getClass.getClassLoader.getResource(fileConfig)
        val file = new File(rs.getFile)
        ConfigFactory.parseFile(file)
    }

}
