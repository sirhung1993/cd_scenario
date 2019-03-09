package ssrs.`trait`

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import ssrs.initialize.AppConfig

trait TraitSessions extends AppConfig {
    /* turn off spark logging */
    Logger.getLogger("org").setLevel(Level.ERROR)
    val isProduction : Boolean = confLoader.getBoolean("IS_PRODUCTION")
    val sparkHost : String = confLoader.getString("SPARK_HOST")
    val spark: SparkSession

    def getSparkSession(appName: String, sparkHost: String = sparkHost) : SparkSession = {
        if(!isProduction) {
            SparkSession
                    .builder()
                    .appName(appName)
                    .master(sparkHost)
                    .getOrCreate()
        } else {
            SparkSession
                    .builder()
                    .appName(appName)
                    .getOrCreate()
        }

    }
}
