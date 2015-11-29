def calcHMS(totalSeconds:Int):(Int,Int,Int) = { 
  val displaySeconds = totalSeconds%60 
  val totalMinutes = totalSeconds/60 
  val displayMinutes = totalMinutes%60 
  val displayHours = totalMinutes/60 
  (displayHours,displayMinutes,displaySeconds) 
}

def formatHMS(numHours:Int,numMinutes:Int,numSeconds:Int):String = { 
  val sec = numSeconds.toString 
  val min = numMinutes.toString 
  numHours+":"+("0"*(2-min.length))+min+":"+ ("0"*(2-sec.length))+sec 
}

def secondsToTimeString(totalSeconds:Int):String = { 
  val (h,m,s) = calcHMS(totalSeconds) 
  formatHMS(h,m,s) 
} 

