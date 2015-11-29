import scala.io.Source

case class DailyData(ride:String, operators:Array[String], numRiders:Int)
case class RideAverage(ride:String, avNum:Double)
case class OperatorDailyData(name:String, ride:String, numRiders:Int)
case class OperatorRideAverages(name:String, rideAvs:Array[RideAverage])
case class OperatorEfficiencyFactor(name:String,factor:Double)

def parseDailyData(line:String):DailyData = {
  val parts = line.split(" *; *")
  DailyData(parts(0), parts.slice(1, parts.length-1), parts.last.toInt)
}

def readData(fileName:String):Array[DailyData] = {
  val source = Source.fromFile(fileName)
  val lines = source.getLines
  val ret = (lines.map(parseDailyData)).toArray
  source.close
  ret
}

def insertionSortByEfficiency(a:Array[OperatorEfficiencyFactor]):Unit = {
  for(j <- 1 until a.length) {
    var i=j-1
    val tmp=a(j)
    while(i>=0 && a(i).factor>tmp.factor) {
      a(i+1) = a(i)
      i -= 1
    }
    a(i+1) = tmp
  }
}

val data = readData(args(0))
val rides = data.map(_.ride).distinct
val averages = for(ride <- rides) yield {
  val days = data.filter(_.ride==ride)
  RideAverage(ride, days.map(_.numRiders).sum.toDouble/days.length)
}
val dataByOperator = for(day <- data; op <- day.operators) yield {
  OperatorDailyData(op, day.ride, day.numRiders)
}
val operators = dataByOperator.map(_.name).distinct
val opRideAverages = for(op <- operators) yield {
  val opDays = dataByOperator.filter(_.name == op)
  val rideAvs = for(ride <- rides; if opDays.exists(_.ride==ride)) yield {
    val opRides = opDays.filter(_.ride == ride)
    RideAverage(ride, opRides.map(_.numRiders).sum.toDouble/opRides.length)
  }
  OperatorRideAverages(op, rideAvs)
}
val operatorFactors = for(OperatorRideAverages(op, rideAvs) <- opRideAverages) yield {
  val factors = for(RideAverage(ride,av) <- rideAvs) yield {
    av/averages.filter(_.ride==ride).head.avNum
  }
  OperatorEfficiencyFactor(op,factors.sum/factors.length)
}
insertionSortByEfficiency(operatorFactors)
operatorFactors.foreach(println)
