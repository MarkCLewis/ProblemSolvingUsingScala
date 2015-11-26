// This will convert a String with time to total seconds.

/*
Code written by Mark Lewis.
This is a long comment.
It has multiple lines.
*/

import io.StdIn._

def breakDownTime(time:String):(Int, Int, Int) = {
	val firstColon = time.indexOf(":")
	val secondColon = time.lastIndexOf(":")
	val hours = time.substring(0,firstColon).toInt
	val minutes = time.substring(firstColon+1,secondColon).toInt
	val seconds = time.substring(secondColon+1).toInt
	(hours, minutes, seconds)
}

def calcTotalSeconds(hours:Int, minutes:Int, seconds:Int):Int = {
	hours*3600 + minutes*60 + seconds
}

println("Enter a time with colons between the values of hours, minutes, and seconds.")
val time = readLine
val (hours, minutes, seconds) = breakDownTime(time)
val totalSeconds = calcTotalSeconds(hours, minutes, seconds)

println(totalSeconds)
