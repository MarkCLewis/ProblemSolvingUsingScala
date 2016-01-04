import io.StdIn._

def readRide():(String, Int, Int) = {
  val name = readLine()
  val numOps = readInt()
  val heavyRiders = readInt()
  (name, numOps, heavyRiders)
}

def readEmploy():(String, List[String]) = {
  val name = readLine()
  val num = readInt()
  val rides = List.fill(num)(readLine())
  (name, rides)
}

def readDay():(String, String, Int) = {
  val day = readLine()
  val ride = readLine()
  val numRiders = readInt()
  (day, ride, numRiders)
}

val numRides = readInt()
val rideInfo = Array.fill(numRides)(readRide())
val numEmploys = readInt()
val employInfo = Array.fill(numEmploys)(readEmploy())
val numDays = readInt()
val daysInfo = Array.fill(numDays)(readDay())

val days = daysInfo.map(_._1).distinct

for (day <- days) {
  val thisDay = daysInfo.filter(_._1==day)
  val rides = thisDay.map(_._2).distinct
  val operatorRides = rides.flatMap(ride => {
    val nums = thisDay.filter(_._2==ride).map(_._3)
    val avg = nums.sum/nums.length
    val rideData = rideInfo.find(_._1==ride).get
    Array.fill(rideData._2+(if (avg>=rideData._3) 1 else 0))(ride)
  })
  val totalOps = operatorRides.length
  for (choice <- employInfo.combinations(totalOps)) {
    val perms = operatorRides.permutations
    var works = false
    while (!works && perms.hasNext) {
      val perm = perms.next
      if ((perm,choice).zipped.forall((r,op) => op._2.contains(r)))
        works = true
    }
    if (works) {
      println(day+" : "+choice.map(_._1).mkString(", "))
    }
  }
}
