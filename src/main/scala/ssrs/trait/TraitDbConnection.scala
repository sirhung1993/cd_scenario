package ssrs.`trait`

import java.util.Properties

trait TraitDbConnection {
  val Host: String
  val Database: String
  val Url: String
  val Driver: String
  val User: String
  val Password: String
  val Prop: Properties
}
