//import scala.util.{Random} 

object MathUtils {
  def ~= (v1:Double, v2:Double, p:Double=0.0000001) = {
    if((v1 - v2).abs < p) true else false
  }
}

object NumberOperations {
  def main(args: Array[String]): Unit = {
    println(s"Float min value: ${Float.MinValue}, Int max value: ${Int.MaxValue}")
    /*--------------------------------*/
    /* Parsing a Number from a String */
    /*--------------------------------*/
    println("toDouble: " + "100".toDouble + ", BigDecimal: " + BigDecimal("3.14"))
    def toInt1(s:String):Option[Int] = {
      try {
        Some(s.toInt)
      }
      catch {
        case e: NumberFormatException => None
      }
    }
    println("toInt1(\"23\"):" + toInt1("23"))
    println("toInt1(\"az\"):" + toInt1("az").getOrElse(0))
    
    val result = toInt1("AZ1") match {
      case Some(x) => x
      case None => 0 // however you want to handle this
    }
    println(s"result = ${result}")
    
    @throws(classOf[NumberFormatException])
    def toInt3(s: String): Int = s.toInt
    try {
      println(s"toInt3 = ${toInt3("35")}")
      println(s"toInt3 = ${toInt3("az")}")
    }
    catch {
      case e: NumberFormatException => { println("conversion failed!"); /*e.printStackTrace*/ } 
    }
    /*----------------------------------------*/
    /* Overriding default & Converting Types */
    /*----------------------------------------*/
    val num1 = 100L
    println(s"num1 = ${if (num1.isValidShort) num1.toShort}")
    var num2: Long = 12 ; val num3 = 0:Short; println(f"num2=${num2}%.2f, num3=${num3}")
    var strVal = null.asInstanceOf[String] ; strVal = "Hi!"
    /*-----------------------------------------------*/
    /* ++/-- & Comparing Floats & very large Numbers */
    /*-----------------------------------------------*/
    var v4 = 1; v4 += 1; println("v4="+v4)
    println("Compare Double's 0.3 and 0.2+0.1 : " + MathUtils.~=(0.3, 0.2+0.1))
    println("Compare Double's 0.3 and 0.2+0.1 with precision=0.000000000000000001 : " 
        + MathUtils.~= (0.3, 0.2+0.1, p=0.000000000000000001))
    var v5: BigDecimal = 123433332555777.0 ; println(s"v5+1 = ${v5 += 1; v5}")
    /*---------------------------------------*/
    /* Handling for Random Numbers/Sequences */
    /*---------------------------------------*/
    val rndGen = new scala.util.Random(1000) // seed=1000
    println("Random Int (<100): "+rndGen.nextInt(100)+", Float: "+ rndGen.nextFloat
        +", Char: "+rndGen.nextPrintableChar)
    val seq1 = for(i <- 1 to rndGen.nextInt(50) if (i%2!=0)) yield rndGen.nextPrintableChar
    println("<<<<SEQ1>>>>"); seq1.toArray.map(_.toUpper).foreach(println)  
         
                      
  }
}