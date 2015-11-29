def individualCost(cooler:Boolean, waterPark:Boolean): Double = {
  println("What is the person's age?")
  val age = readInt()
  println("What size combo are they ordering?")
  val size = readLine()
  entryCost(age,cooler,waterPark)+itemCost("Combo",size)
}

println("How many people are in your group? (1-4)")
val numPeople = readInt()
if(numPeople<1 || numPeople>4) {
  println("This script can not handle "+numPeople+" people.")
} else {
  println("Is the group bringing a cooler? (Y/N)")
  val cooler = readLine()=="Y"
  println("Will they be going to the water park? (Y/N)")
  val waterPark = readLine()=="Y"
  val totalCost = individualCost(cooler,waterPark) +
    (if(numPeople>1) individualCost(cooler,waterPark) else 0) +
    (if(numPeople>2) individualCost(cooler,waterPark) else 0) +
    (if(numPeople>3) individualCost(cooler,waterPark) else 0)
  println("The group cost is $"+totalCost+".")
}
