def entryCost(age:Int, cooler:Boolean, waterPark:Boolean):Double = {
  (if(age < 13 || age > 65) 20 else 35) +
  (if(cooler) 5 else 0) +
  (if(waterPark) 10 else 0)
}

def individualAdding(num:Int, cooler:Boolean, waterPark:Boolean):Double = {
  if(num > 0) {
    println("What is the person’s age?")
    val age = readInt()
    entryCost(age, cooler, waterPark)+individualAdding(num-1, false, waterPark)
  } else 0.0
}

def groupSizeCost():(Int,Double) = {
  println("How many people are in your group?")
  val numPeople = readInt()
  println("Is the group bringing a cooler? (Y/N)")
  val cooler = readLine() == "Y"
  println("Will they be going to the water park? (Y/N)")
  val waterPark = readLine() == "Y"
  (numPeople,individualAdding(numPeople,cooler,waterPark))
}

def doAdmission():(Int,Int,Double) = {
  println("Is there another group for the day? (Y/N)")
  val another = readLine()
  if(another == "Y") {
    val (people, cost) = groupSizeCost()
    val (morePeople, moreGroups, moreCost) = doAdmission()
    (people+morePeople, 1+moreGroups, cost+moreCost)
  } else (0,0,0.0)
}

val (totalPeople, totalGroups, totalCost) = doAdmission()
println("There were "+totalPeople+" people in "+totalGroups+
  " groups who paid a total of "+totalCost)
