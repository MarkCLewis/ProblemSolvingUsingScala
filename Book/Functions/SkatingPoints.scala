def skillPoints(numSkills:Int):Int = 5*numSkills

def endurancePoints(numLaps:Int):Int = {
  if (numLaps < 20) 0
  else if (numLaps > 40) 20
  else numLaps-20
}

val skills = 9
val laps = 36
val points = skillPoints(skills) + endurancePoints(laps)
