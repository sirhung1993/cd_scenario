package ssrs.`trait`

import java.sql.{DriverManager, PreparedStatement}

import org.apache.spark.sql.{DataFrame, SaveMode, functions}

trait TraitWriter {
  /*
* @input dataframe
* @return: Unit
* @output: Nhet nguyen dataframe vao table
* neu trung primary key se bao loi va ko nhet them dc data
* neu data moi thi no nhet xuong duoi cung
* */
  def append(df: DataFrame, dbConnection: TraitDbConnection, tableName: String): Unit = {
    if (df.count() > 0) {
      df
        .write
        .mode(SaveMode.Append)
        .jdbc(dbConnection.Url, tableName, dbConnection.Prop)

    }
  }

  def overwrite(df: DataFrame, dbConnection: TraitDbConnection, tableName: String): Unit = {
    if (df.count() > 0) {
      df
        .write
        .mode(SaveMode.Overwrite)
        .jdbc(dbConnection.Url, tableName, dbConnection.Prop)
    }
  }

  def updateOne(df: DataFrame, tableName: String, filterColumn: String, setColumn: String, dbConnection: TraitDbConnection): Unit = {

    df.foreach(
      row => {
        Class.forName(dbConnection.Driver)
        val conn = DriverManager.getConnection(
          dbConnection.Url,
          dbConnection.User,
          dbConnection.Password)
        val query = s"UPDATE $tableName SET $setColumn = ? WHERE $filterColumn = ?"
        val preparedStatement: PreparedStatement = conn.prepareStatement(query)

        preparedStatement.setObject(1, row.getAs(setColumn))
        preparedStatement.setObject(2, row.getAs(filterColumn))

        val effectRows: Int = preparedStatement.executeUpdate()
        println(s"Effected $effectRows row")
        if (!preparedStatement.isClosed) preparedStatement.close()
        if (!conn.isClosed) conn.close()
      }
    )
  }

  def updateMany(df: DataFrame, tableName: String, filterColumn: Array[String],
                 setColumn: Array[String], dbConnection: TraitDbConnection): Unit = {
    var PreparedFilterColumns: Seq[String] = null
    var PreparedFilters = ""
    PreparedFilterColumns = filterColumn.map(i => s"$i = ? ")
    PreparedFilters = PreparedFilterColumns.mkString(" AND ")
    val PreparedSet = setColumn.map(i => s"$i = ?").mkString(",")

    df.foreach(
      row => {
        Class.forName(dbConnection.Driver)
        val conn = DriverManager.getConnection(
          dbConnection.Url,
          dbConnection.User,
          dbConnection.Password)

        val query = s"UPDATE $tableName SET " + PreparedSet + " WHERE " + PreparedFilters
        val preparedStatement: PreparedStatement = conn.prepareStatement(query)
        var i = 0
        setColumn.foreach(value => {
          i = i + 1
          preparedStatement.setObject(i, row.getAs(value))
        })
        filterColumn.foreach(key => {
          i = i + 1
          preparedStatement.setObject(i, row.getAs(key))
        })
        val effectRows: Int = preparedStatement.executeUpdate()
        if (!preparedStatement.isClosed) preparedStatement.close()
        if (!conn.isClosed) conn.close()
      }
    )

  }

  def insertAndUpdate(df: DataFrame, dfTarget: DataFrame, tableName: String,  filterColumns: Array[String], dbConnection: TraitDbConnection): Unit = {
    val filterCols = filterColumns.map(functions.col)
    val existedDf: DataFrame = df.select(filterCols: _*).intersect(dfTarget.select(filterCols: _*))
    val nonExistedDf: DataFrame = df.select(filterCols: _*).except(dfTarget.select(filterCols: _*))
    if (existedDf.count() > 0) {
      val dfNonExisted = nonExistedDf.join(df, filterColumns)
      append(dfNonExisted, dbConnection, tableName)
      val dfExisted = existedDf.join(df, filterColumns)
      updateMany(dfExisted, tableName, filterColumns, dbConnection)
    } else {
      append(df, dbConnection, tableName)
    }
  }

  def updateMany(df: DataFrame, tableName: String,  filterColumn: Array[String], dbConnection: TraitDbConnection): Unit = {
    val Columns = df.columns.filter(!filterColumn.contains(_))
    updateMany(df, tableName, filterColumn, Columns, dbConnection)
  }
}
