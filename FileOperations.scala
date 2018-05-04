import scala.io.{Source}
//import java.io.{FileNotFoundException, IOException}
import java.io._

object FileOperations {
  def main(args: Array[String]): Unit = {
    var filename = args(0)
    try {
      var bufReader = Source.fromFile(filename)
      for (line <- bufReader.getLines) {
        println(line)
      }
      bufReader.close
      
      filename = args(1)
      val file = new File(filename)
      val bw = new BufferedWriter(new FileWriter(file))
      bw.write("Some text")
      bw.close()
    } 
    catch {
      case e: FileNotFoundException => println("Couldn't find file: $filename")
      case e: IOException => println("Got an IOException on $filename")
    }
  }
}
