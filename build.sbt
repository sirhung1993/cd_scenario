name := "ssrs_dw_spark"

version := "0.1"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "2.4.0",
    "org.apache.spark" %% "spark-sql" % "2.4.0",
    "org.scala-lang" % "scala-library" % "2.11.1",
    "com.microsoft.sqlserver" % "mssql-jdbc" % "6.1.0.jre8",
    "com.typesafe" % "config" % "1.2.1",
    "mysql" % "mysql-connector-java" % "8.0.11",
    "com.crealytics" %% "spark-excel" % "0.8.2",
    "com.vdurmont" % "emoji-java" % "3.1.3"
)

//retrieveManaged := true
//XitrumPackage.copy()