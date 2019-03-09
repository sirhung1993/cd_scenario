package ssrs.`trait`

import org.apache.spark.sql.DataFrame

trait TraitScenarioJob {
  def pre(jobName: String) : Unit
  def post(jobName: String) : Unit
  def transform() : DataFrame
  def load(transform: DataFrame)
  def run(jobName: String): Unit = {
      pre(jobName)
      load(transform())
      post(jobName)
  }
}
