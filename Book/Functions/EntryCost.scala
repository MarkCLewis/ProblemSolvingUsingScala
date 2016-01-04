def entryCost(age:Int, cooler:Boolean, waterPark:Boolean): Double = {
  (if (age<13 || age>65) 20 else 35) +
  (if (cooler) 5 else 0) +
  (if (waterPark) 10 else 0)
}
