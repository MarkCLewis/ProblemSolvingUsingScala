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
