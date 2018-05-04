
object  StringOperations {
  case class MyClass1(name:String,age:Int,weight:Double) {
      override def toString = name+"/"+age.toString+"/"+weight.toString
    }
  
  def main(args: Array[String]): Unit = {
    /*------------------------*/
    println("String Equality")
    /*------------------------*/
    var a = "Marisa"
    var b = "mariSa"
    var f = a.equalsIgnoreCase(b)
    f = (a.toUpperCase == b.toLowerCase)
    println(s"f1 = $f")
    a = null
    f = (a == b)
    println(s"f2 = $f")
    /*------------------------*/
    println("Multiline strings")
    /*------------------------*/
    val mStr = """ This is known as 
    |a "multiline string" or
       |'here-doc' """.stripMargin.replaceAll("\n", " ")
    println(mStr)
    /*------------------------*/
    println("Splitting strings")
    /*------------------------*/
    var s = "hello world".split(" ").isInstanceOf[Array[String]]
    println(s"Is it array? ${s}")
    "hello world".split(" ").foreach(println)
    val s2 = "eggs, cream, butter, Coco nuts".split(",").map(_.trim); println
    s2.foreach(println)
    /*--------------------------------------------*/
    println("Substituting variables into strings")
    /*--------------------------------------------*/
    val v1 = 25
    val az = MyClass1(args(0),args(1).toInt,args(2).toDouble)
    println(s"""<Using s>My name : ${az.name}, Age+1 : ${az.age + 1}, Weight : ${az.weight},
      |Default : $v1""".stripMargin.replaceAll("\n", " "))
    println(f"<Using f>My weight : ${az.weight}%.2f")
    /*--------------------------------------------*/
    println("Using Java")
    /*--------------------------------------------*/
    import java.time.LocalDate
    println("JAVA: " + LocalDate.now())
    import java.io.File
    import java.util.Date
    val myFile = new File("C://Work//Proj//MyProj//Scala//az_test.txt")
    if (myFile.exists) {
      val lastModified = new Date(myFile.lastModified);
      println(s"Name: ${myFile.getCanonicalFile}, Last modified: ${lastModified}")
    }
    /*--------------------------------------------*/
    println("Processing strings as characters sequence")
    /*--------------------------------------------*/
    val s1 = "scala is a great !".filter(c=>c!='l').map(_.toUpper)
    println(s1)
    var s3 = for {
      c <- s1 
      if c != 'A'
    }  yield c.toLower
    s3.foreach(println)
    /*--------------------------------------------*/
    println("Finding patterns in strings")
    /*--------------------------------------------*/
    val numPtrn = "[0-9]+".r
    val addr = "123 house, 97- Street"
    numPtrn.findFirstIn(addr).getOrElse("no pattern found").foreach(println)
    numPtrn.findAllIn(addr).foreach {
      elem => println(s"elem = $elem")
    }
    /*-------------------------------------------------------*/
    println("Extracting parts of string that match patterns")
    /*-------------------------------------------------------*/
    val moviesZipRE = "movies (\\d{5})".r
    val moviesNearCityStateRE = "movies near ([a-zA-Z]+), ([a-z]{2})".r
    def cityStatePrn(city:String, state:String) = println(s"City = $city, State of the movie : $state")
    def matchFunc1(text:String) = text match {
      case moviesZipRE(zip) => { val zipInt = zip.toInt; println(s"Zip code+1 = ${zipInt+1}") }
      case moviesNearCityStateRE(city, state) => cityStatePrn(city, state)
      case _ => println("Pattern not found!")
    }
    matchFunc1("movies 12345"); matchFunc1("movies near NewYork, ny"); matchFunc1("movies 123456")
  }
}
