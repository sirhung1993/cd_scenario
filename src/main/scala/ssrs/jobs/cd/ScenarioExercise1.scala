package ssrs.jobs.cd

//import org.apache.spark.sql.functions._
//import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import ssrs.`trait`.TraitScenarioJob
import ssrs.initialize.ScenarioConfigInitialize

object ScenarioExercise1 extends App with ScenarioConfigInitialize with TraitScenarioJob {
  private val jobName  = "ScenarioExercise1"
  override val spark: SparkSession = getSparkSession(jobName, confLoader.getString("SPARK_HOST"))

  override def pre(jobName: String) : Unit = {

  }
  override def transform(): DataFrame = {
    println(s"Read file from: $listLeadExcelFilePath")
    readExcel(spark)
  }

  override def load(transform: DataFrame): Unit = {
//    transform.printSchema()
    transform.show()
    transform.write
            .mode(SaveMode.Overwrite)
            .json(outputFile)
  }

  override def post(jobName: String) : Unit = {

  }
  run(jobName)
}
