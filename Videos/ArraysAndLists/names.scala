import io.StdIn._

type NameData = (String,Int,String,Int)

def parseLine(line:String):NameData = {
	val parts = line.split(",")
	(parts(1),parts(2).toInt,parts(3),parts(4).toInt)
}

val nameLines = Array.fill(43858)(readLine)
val nameData = nameLines.map(parseLine)

val f1988 = nameData.filter(nd => nd._1=="F" && nd._2==1988)
val maxNames = f1988.map(_._4).max
println(maxNames)
f1988.filter(_._4==maxNames).foreach(println)
