def entryCost(age:Int, cooler:Boolean, waterPark:Boolean): Double = {
  (if (age<13 || age>65) 20 else 35) +
  (if (cooler) 5 else 0) +
  (if (waterPark) 10 else 0)
}

def itemCost(item:String, size:String):Double = {
  if (item=="Drink") {
    if (size=="S") 0.99
    else if (size=="M") 1.29
    else 1.39
  } else if (item=="Side") {
    if (size=="S") 1.29
    else if (size=="M") 1.49
    else 1.59
  } else if (item=="Main") {
    if (size=="S") 1.99
    else if (size=="M") 2.59
    else 2.99
  } else {
    if (size=="S") 4.09
    else if (size=="M") 4.99
    else 5.69
  }
}

def individualCost(cooler:Boolean, waterPark:Boolean): Double = {
  println("What is the person's age?")
  val age = readInt()
  println("What size combo are they ordering?")
  val size = readLine()
  entryCost(age,cooler,waterPark)+itemCost("Combo",size)
}

println("How many people are in your group? (1-4)")
val numPeople = readInt()
if (numPeople<1 || numPeople>4) {
  println("This script can not handle "+numPeople+" people.")
} else {
  println("Is the group bringing a cooler? (Y/N)")
  val cooler = readLine()=="Y"
  println("Will they be going to the water park? (Y/N)")
  val waterPark = readLine()=="Y"
  val totalCost = individualCost(cooler,waterPark) +
    (if (numPeople>1) individualCost(cooler,waterPark) else 0) +
    (if (numPeople>2) individualCost(cooler,waterPark) else 0) +
    (if (numPeople>3) individualCost(cooler,waterPark) else 0)
  println("The group cost is $"+totalCost+".")
}
