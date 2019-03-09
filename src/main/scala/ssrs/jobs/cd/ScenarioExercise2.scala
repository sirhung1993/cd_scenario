package ssrs.jobs.cd

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import ssrs.`trait`.TraitScenarioJob
import ssrs.initialize.ScenarioConfigInitialize
import org.apache.spark.sql.functions._

//Return Lead having qualification level is HOT
object ScenarioExercise2 extends App with ScenarioConfigInitialize with TraitScenarioJob {
  private val jobName  = "ScenarioExercise2"
  override val spark: SparkSession = getSparkSession(jobName, confLoader.getString("SPARK_HOST"))
  val lowerCasefilterKeyword = "hot"
  val filterCol = "Qualification Level"
  val tempCol = "toLowerQualificationLevel"
  override def pre(jobName: String) : Unit = {

  }
  override def transform(): DataFrame = {
    println(s"Read file from: $listLeadExcelFilePath")
    readExcel(spark)
            .withColumn(tempCol, lower(col(filterCol)))
            .filter(col(tempCol) === "hot")
            .drop(tempCol)
  }

  override def load(transform: DataFrame): Unit = {
//    transform.printSchema()
    transform.show()
    transform.write
            .mode(SaveMode.Overwrite)
            .json(s"$outputFile$jobName")
  }

  override def post(jobName: String) : Unit = {

  }
  run(jobName)
}
