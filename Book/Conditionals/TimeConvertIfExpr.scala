import io.StdIn._
print("Enter the number of seconds. ")
val totalSeconds = readInt()
val response = if (totalSeconds > 0) {
  val displaySeconds = totalSeconds%60
  val totalMinutes = totalSeconds/60 
  val displayMinutes = totalMinutes%60 
  val displayHours = totalMinutes/60 
  val sec = displaySeconds.toString 
  val min = displayMinutes.toString 
  displayHours+":"+("0"*(2-min.length))+min+":"+("0"*(2-sec.length))+sec 
} else {
  "This only works for a positive number of seconds."
}
println(response)
