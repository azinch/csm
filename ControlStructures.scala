object ControlStructures {
  def main(args: Array[String]): Unit = {
    /*--------------------------*/
    /* Looping with for/foreach */
    /*--------------------------*/
    val a = Array("apple9", "banana", "orange")
    val newArray = for (e <- a) yield { /*some operations...*/ e.toUpperCase }
    for ((e, count) <- newArray.zipWithIndex) println(s"Elem: ${e}, Index: ${count}")
    /* Array buffer */
    import scala.collection.mutable.ArrayBuffer
    val buf = new ArrayBuffer[String]()
    buf += "Andrey"; buf += "programmer"
    def Func(str:String) = str.exists(_.isUpper)
    println(s"length= ${buf.length}, ${buf.filter(Func)}")   
    
    val numPtrn = "[0-9]+".r ; 
    newArray.filter(numPtrn.findFirstIn(_).isDefined!=true).map(e=>{var z=e.toLowerCase; z.capitalize}).foreach(println) 
    
    val names = Map("fname" -> "Robert", "lname" -> "Goren")
    for ((k,v) <- names) println(s"key: $k, value: $v")
    /* 2-dimensional array */
    val array = Array.ofDim[Int](2,2)
    array(0)(0) = 0; array(0)(1) = 1; array(1)(0) = 2; array(1)(1) = 3
    for {
          i <- 0 to 1
          j <- 0 to 1
        } println(s"($i)($j) = ${array(i)(j)}")
    
    /*-------------------*/    
    /* Break and Continue*/
    /*-------------------*/
    val searchMe = "peter piper picked a peck of pickled peppers"
    println("searchMe = " + searchMe)
    var numPs = 0
    import util.control._
    import util.control.Breaks._
    for (i <- 0 until searchMe.length) {
      breakable {
        if (searchMe.charAt(i) != 'p') {
          break // break out of the 'breakable', continue the outside loop
        } 
        else {
          numPs += 1
        }
      }
    }
    println("Found " + numPs + " p's in the string.")
    
    /* Labeled Breaks */
    val Inner = new Breaks
    val Outer = new Breaks
    Outer.breakable {
      for (i <- 1 to 5) {
        Inner.breakable {
          for (j <- 'a' to 'e') {
            if (i == 1 && j == 'c') Inner.break else println(s"i: $i, j: $j")
            if (i == 2 && j == 'b') Outer.break
          }
        }
      }
    }
    
    /* Scala approach: if or transform methods or tail-recursion instead of a break */
    import scala.annotation.tailrec
    def factorial(n: Int): BigInt = {
      @tailrec def factorialAcc(acc: Int, n: Int): BigInt = {
      if (n <= 1) acc
      else factorialAcc(n * acc, n - 1)
      }
      factorialAcc(1, n)
    }
    
    println(s"factorial(50): ${factorial(20)}")
    /*-------------------------------------*/
    /* Match expression: switch annotation */
    /*-------------------------------------*/
    import scala.annotation.switch
    class SwitchDemo {
      val i = 1
      val two = 2
      val x = (i: @switch) match {
        case 1 => println("One")
        case `two` => println("Two")
        case _ => println("Default")
      }
    }
    val swtInst = new SwitchDemo
    /* A few objects in case */
    trait Command
    case object Start extends Command
    case object Go extends Command
    case object Stop extends Command
    def runCommand (cmd: Command) = cmd match {
      case Start|Go => runStart
      case Stop => "Running a Stop command..."
      case _ => "Default"
    }
    def runStart = "Running a Start command..."
    println(s"TEST1:\n\t${runCommand(Go)}")
    /* Custom class, List, Map, etc. */
    case class Person(name: String, var age: Int) {
      override def toString = name+"/"+age
    }
    def runMisc (misc: Any):String = misc match {
      case Person(a, b) => s"${a} ${b}"
      case list @ List("one", _*) => list.mkString(",")
      case m: Map[a, b] => m.keys.mkString(",")
      case _ => "Default"
    }
    println(s"TEST2:\n\tPerson - ${runMisc(new Person("Andrey", 47))}")
    println(s"\tList - ${runMisc(List("one", "77", "87"))}")
    println(s"\tMap - ${runMisc(Map("Andrey1"->1, "Alexey2"->2))}")
    /* Using IF guard */
    val ii = 8
    ii match {
      case a if 0 to 9 contains a => println("0-9 range: " + a)
      case b if 10 to 19 contains b => println("10-19 range: " + b)
      case c if 20 to 29 contains c => println("20-29 range: " + c)
      case _ => println("Hmmm...")
    }
    /* Misc... */
    def sum(list: List[Int]): Int = list match {
      case Nil => 0
      case n :: rest => n + sum(rest)
    }
    println(s"Sum of 3: ${sum(List(1, 2, 3))}")
  }
  
}
