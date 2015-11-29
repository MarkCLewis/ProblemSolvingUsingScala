import scala.io.Source
import scala.xml._

case class DayData(ride:String, dayOfWeek:String, operators:Array[String], numRiders:Int)
case class MonthData(month:Int, days:List[DayData])
case class YearData(year:Int, months:List[MonthData])
case class RideData(name:String, numberOfOperators:Int, heavyCount:Int)
case class EmployeeData(name:String, rides:List[String])

def parseDay(node:Node):DayData = {
  val ride = (node \ "@ride").text
  val dow = (node \ "@dayOfWeek").text
  val num = (node \ "@numRiders").text.toInt
  val ops = (node \ "operator").map(_.text).toArray
  DayData(ride, dow, ops, num)
}

def dayToXML(day:DayData):Node = {
  <day ride={day.ride} dayOfWeek={day.dayOfWeek} numRiders={day.numRiders.toString}>
    {day.operators.map(op => <operator>{op}</operator>)}
  </day>
}

def parseMonth(node:Node):MonthData = {
  val month = (node \ "@month").text.toInt
  val days = (node \ "day").map(parseDay).toList
  MonthData(month, days)
}

def monthToXML(month:MonthData):Node = {
  <month month={month.month.toString}>
    {month.days.map(dayToXML)}
  </month>
}

def parseYear(node:Node):YearData = {
  val year = (node \ "@year").text.toInt
  val months = (node \ "month").map(parseMonth).toList
  YearData(year, months)
}

def yearToXML(year:YearData):Node = {
  <year year={year.year.toString}>
    {year.months.map(monthToXML)}
  </year>
}

def parseRideData(node:Node):RideData = {
  val name = (node \ "@name").text
  val numOps = (node \ "@numberOfOperators").text.toInt
  val heavy = (node \ "@heavyCount").text.toInt
  RideData(name, numOps, heavy)
}

def rideDataToXML(rd:RideData):Node = {
  <ride name={rd.name} numberOfOperators={rd.numberOfOperators.toString} heavyCount={rd.heavyCount.toString}/>
}

def parseEmployeeData(node:Node):EmployeeData = {
  val name = (node \ "@name").text
  val rides = (node \ "trainedRide").map(_.text).toList
  EmployeeData(name, rides)
}

def employeeToXML(ed:EmployeeData):Node = {
  <employee name={ed.name}>
    {ed.rides.map(r => <trainedRide>{r}</trainedRide>)}
  </employee>
}

val xmlData = XML.loadFile(args(0))
var years = (xmlData \ "year").map(parseYear).toList
var rideInfo = (xmlData \ "ride").map(parseRideData).toList
var employeeInfo = (xmlData \ "employee").map(parseEmployeeData).toList

def buildWeeklySchedules:Unit = {
  val daysInfo = for(y <- years; m <- y.months; d <- m.days) yield d
  val days = daysInfo.map(_.dayOfWeek).distinct
  for(day <- days) {
    val thisDay = daysInfo.filter(_.dayOfWeek==day)
    val rides = thisDay.map(_.ride).distinct
    val operatorRides = rides.flatMap(ride => {
      val nums = thisDay.filter(_.ride==ride).map(_.numRiders)
      val avg = nums.sum/nums.length
      val rideData = rideInfo.find(_.name==ride).get
      Array.fill(rideData.numberOfOperators+(if(avg>=rideData.heavyCount) 1 else 0))(ride)
    })
    val totalOps = operatorRides.length
    for(choice <- employeeInfo.combinations(totalOps)) {
      val perms = operatorRides.permutations
      var works = false
      while(!works && perms.hasNext) {
        val perm = perms.next
        if((perm,choice).zipped.forall((r,op) => op.rides.contains(r)))
          works = true
      }
      if(works) {
        println(day+" - "+choice.map(_.name).mkString(", "))
      }
    }
  }
}

case class RideAverage(ride:String, avNum:Double)
case class OperatorDailyData(name:String, ride:String, numRiders:Int)
case class OperatorRideAverages(name:String, rideAvs:List[RideAverage])
case class OperatorEfficiencyFactor(name:String,factor:Double)

def insertionSortByEfficiency(a:Array[OperatorEfficiencyFactor]):Unit = {
  for(j <- 1 until a.length) {
    var i = j-1
    val tmp = a(j)
    while(i>=0 && a(i).factor>tmp.factor) {
      a(i+1) = a(i)
      i -= 1
    }
    a(i+1) = tmp
  }
}

def rankEmployees(data:List[DayData]):Array[OperatorEfficiencyFactor] = {
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
  val operatorFactors = (for(OperatorRideAverages(op, rideAvs) <- opRideAverages) yield {
    val factors = for(RideAverage(ride,av) <- rideAvs) yield {
      av/averages.filter(_.ride==ride).head.avNum
    }
    OperatorEfficiencyFactor(op,factors.sum/factors.length)
  }).toArray
  insertionSortByEfficiency(operatorFactors)
  operatorFactors
}

def rideInput(ri:RideData):Array[String] = {
  println(ri.name)
  println(employeeInfo.filter(_.rides.contains(ri.name)).map(_.name).zipWithIndex.mkString(" "))
  readLine().split(" +")
}

def inputDay:List[DayData] = {
  println("What day of the week is this for?")
  val dow = readLine()
  println("For each ride displayed, enter the number of riders for the day followed by employee numbers from this list with spaces in between.")
  for(ri <- rideInfo; 
      val input = rideInput(ri)
      if input.head.toInt>=0) yield {
    DayData(ri.name, dow, input.tail.map(_.toInt).map(employeeInfo).map(_.name), input.head.toInt)
  }
}

def inputRideDayData:Unit = {
  println("What month/year do you want to enter data for?")
  readLine().trim.split("/") match {
    case Array(monthText, yearText) =>
      val (month, year) = (monthText.toInt, yearText.toInt)
      if(years.exists(_.year==year)) {
        years = for(y <- years) yield {
          if(y.year==year) {
            y.copy(months = {
              if(y.months.exists(_.month==month)) {
                for(m <- y.months) yield {
                  if(m.month==month) {
                    m.copy(days = inputDay ::: m.days)
                  } else m
                }
              } else MonthData(month, inputDay) :: y.months
            })
          } else y
        }
      } else {
        years ::= YearData(year,MonthData(month, inputDay)::Nil)
      }
    case _ =>
      println("Improper format. Needs to be numeric month followed by numeric year with a / between them.")
  }
}

def hireEmployee:Unit = {
  println("What is the new employees name?")
  val name = readLine()
  employeeInfo ::= EmployeeData(name,Nil)
}

def trainEmployee:Unit = {
  println("Which employee is training for a new ride?")
  println(employeeInfo.map(_.name).zipWithIndex.mkString(" "))
  val empNum = readInt()
  employeeInfo = for((e,i) <- employeeInfo.zipWithIndex) yield {
    if(i==empNum) {
      val avail = rideInfo.map(_.name).diff(e.rides)
      println("Which rides should be added? (Enter space separated numbers.)")
      println(avail.zipWithIndex.mkString(" "))
      e.copy(rides = (readLine().split(" +").map(_.toInt)).map(avail).toList ::: e.rides)
    } else e
  }
}

def addRide:Unit = {
  println("What is the name of the new ride?")
  val name = readLine()
  println("How many operators does it need?")
  val ops = readInt()
  println("At what rider count should another operator be added?")
  val heavy = readInt()
  rideInfo ::= RideData(name, ops, heavy)
}

var input = 0
do {
  println("""What would you like to do?
 1) Add ridership for a day.
 2) Add an Employee.
 3) Add training to an employee.
 4) Add a ride.
 5) Get schedule options for a week.
 6) Rank Employees.
 7) Quit.""")
  input = readInt()
  input match {
    case 1 => inputRideDayData
    case 2 => hireEmployee
    case 3 => trainEmployee
    case 4 => addRide
    case 5 => buildWeeklySchedules
    case 6 => 
      println("What month/year or year do you want to rank for?")
      println(readLine().trim.split("/") match {
        case Array(monthText,yearText) =>
          val year = yearText.toInt
          val month = monthText.toInt
          val y = years.filter(_.year==year)
          if(y.isEmpty) "Year not found."
          else {
            val m = y.head.months.filter(_.month==month)
            if(m.isEmpty) "Month not found."
            else {
              rankEmployees(m.head.days).mkString("\n")
            }
          }
        case Array(yearText) =>
          val year = yearText.toInt
          val y = years.filter(_.year==year)
          if(y.isEmpty) "Year not found."
          else {
            rankEmployees(y.head.months.flatMap(_.days)).mkString("\n")
          }
        case _ => "Invalid input"
      })
    case _ =>
  }
} while(input!=7)

XML.save(args(0), <themeParkData>
  {years.map(yearToXML)}
  {rideInfo.map(rideDataToXML)}
  {employeeInfo.map(employeeToXML)}
</themeParkData>)
