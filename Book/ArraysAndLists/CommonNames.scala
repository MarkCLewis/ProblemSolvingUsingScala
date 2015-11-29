import io.StdIn._

val linesInFile = 324133 //43858

// Define shortened name for the tuple that represents each line
type NameData = (String, String, Int, String, Int)

// This function parses a line of text from the file to a NameData
def parseLine(line:String):NameData = {
  val parts = line.split(",")
  (parts(0), parts(1), parts(2).toInt, parts(3), parts(4).toInt)
}

// Read the contents of the file and parse all the lines
val lines = Array.fill(linesInFile)(readLine)
val data = lines.map(parseLine)

// Function to find the most common names in aa Array
def mostCommonNames(nameData:Array[NameData]):Array[NameData] = {
   val maxTimes = nameData.map(_._5).max
  nameData.filter(_._5 == maxTimes)
}

// Find the years the the data that has been read
val years = data.map(_._3).distinct

// Find most common female and male names
val femaleNames = years.flatMap(year => 
  mostCommonNames(data.filter(nd => nd._2=="F" && nd._3==year)))
val maleNames = years.flatMap(year => 
  mostCommonNames(data.filter(nd => nd._2=="M" && nd._3==year)))

// Output the results
femaleNames.foreach(println)
maleNames.foreach(println)
