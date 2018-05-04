
import java.sql._
//import java.util._
import java.text._ //{Connection,DriverManager}
object  ScalaJdbcAccess extends App {

  val url = "jdbc:oracle:thin:@snv8903:1521:vens1123"
  val driver = "oracle.jdbc.driver.OracleDriver"
  val username = "vmptapp69"
  val password = "vmptapp69"
  var connection:Connection = _
  try 
  {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    /*---------------*/
    /* Read database */
    /*---------------*/
    val statement = connection.createStatement
    val rs = statement.executeQuery("SELECT ERROR_CODE, SHORT_DESCRIPTION from NAPIERR")
    while (rs.next) 
    {
      val errCode = rs.getString("ERROR_CODE")
      val shortDesc = rs.getString("SHORT_DESCRIPTION")
      println("error_code = %s, short_description = %s".format(errCode,shortDesc))
    }
    statement.close
    /*---------------------------------------------*/
    /* Write to database (with a batch processing) */
    /*---------------------------------------------*/
    println("Inserting 100 rows...")
    val batchSize = 10
    val startMs = System.currentTimeMillis
    
    val insSQL = "insert into AZ_TABLE1" +
          " (IDX, MY_USER, USER_ATTR, CREATE_DATE, TEST_NUM) " +
          " VALUES(:1, :2, :3, :4, :5)"
    val prepStmt = connection.prepareStatement(insSQL)
    
    for (idx <- 1 to 100) {
      prepStmt.setInt(1, idx)
      prepStmt.setString(2, "andrey")
      prepStmt.setString(3, s"rownum-$idx")
      prepStmt.setDate(4, new java.sql.Date(System.currentTimeMillis))
      prepStmt.setInt(5, idx)
      
      prepStmt.addBatch
      
      if (idx % batchSize == 0) {
        prepStmt.executeBatch
      }
    }
    
    prepStmt.close
    connection.commit
    
    println(s"The rows inserted, time elapsed (ms): ${System.currentTimeMillis - startMs}")
    
  } 
  catch {
    case e: Exception => e.printStackTrace
  }
  connection.close
}

/*
CREATE TABLE AZ_TABLE1 (
    IDX NUMBER(9),
    MY_USER CHAR(6),
    USER_ATTR VARCHAR2(12),
    CREATE_DATE DATE,
    TEST_NUM NUMBER(6,2)
    )
*/
