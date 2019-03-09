package ssrs.initialize

import org.apache.spark.sql.{DataFrame, SparkSession}
import ssrs.`trait`.TraitSessions

trait ScenarioConfigInitialize extends AppConfig with TraitSessions {
  protected val listLeadExcelFilePath = confLoader.getString("SCENARIO.LIST_LEAD_PATH")
  val outputFile = confLoader.getString("SCENARIO.OUTPUT_PATH")
  def readExcel(spark: SparkSession, filePath: String = listLeadExcelFilePath, driver: String = "com.crealytics.spark.excel"): DataFrame = {
    spark.read
            .format(driver)
            .option("location", listLeadExcelFilePath)
            .option("sheetName", "Sheet1") // Required
            .option("useHeader", "true") // Required
            .option("treatEmptyValuesAsNulls", "false") // Optional, default: true
            .option("inferSchema", "false") // Optional, default: false
            .option("addColorColumns", "false") // Optional, default: false
            .option("startColumn", 0) // Optional, default: 0
            //            .option("endColumn", 99) // Optional, default: Int.MaxValue
            .option("timestampFormat", "MM-dd-yyyy HH:mm:ss") // Optional, default: yyyy-mm-dd hh:mm:ss[.fffffffff]
            .option("maxRowsInMemory", 1000) // Optional, default None. If set, uses a streaming reader which can help with big files
            .option("excerptSize", 1) // Optional, default: 10. If set and if schema inferred, number of rows to infer schema from
            .load()
  }
}
