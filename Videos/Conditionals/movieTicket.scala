//  && - and
//  || - inclusive or/logical or
//  ^  - exclusive or
//  !  - not

import io.StdIn._

println("How old is the movie goer?")
val age = readInt
//val cost = if(age < 13 || age >= 55) 8 else 12
val cost = if(age >= 13 && age < 55) 12 else 8

val n = readInt
val d = readInt
val qualify = d>0 && n/d>10 
