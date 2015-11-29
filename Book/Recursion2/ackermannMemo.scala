import collection.mutable

val Zero = BigInt(0)
val One = BigInt(1)
val Two = BigInt(2)
val Three = BigInt(3)

def ack(m:BigInt,n:BigInt):BigInt = (m,n) match {
  case (Zero,n) => n+1
  case (m,Zero) => ack(m-1,1)
  case (m,n) => ack(m-1,ack(m,n-1))
}

def ack(m:BigInt,n:BigInt,memo:mutable.Map[(BigInt,BigInt),BigInt]):BigInt = (m,n) match {
  case (Zero,n) => n+1
  case (One,n) => n+2
  case (Two,n) => 2*n+3
  case (Three,n) => 8*bigPow(2,n)-3
  case (m,Zero) =>
    memo.getOrElseUpdate((m-1,1),ack(m-1,1,memo))
  case (m,n) =>
    val nn = memo.getOrElseUpdate((m,n-1),ack(m,n-1,memo))
    memo.getOrElseUpdate((m-1,nn),ack(m-1,nn,memo))
}

def bigPow(x:BigInt,y:BigInt):BigInt = {
  if(y==0) 1
  else if(y==1) x
  else (if(y%2==1) x else One)*bigPow(x*x,y/2)
}

def ack(m:BigInt,n:BigInt,memo:mutable.Buffer[mutable.Buffer[BigInt]]):BigInt = (m,n) match {
  case (Zero,n) => n+1
  case (One,n) => n+2
  case (Two,n) => 2*n+3
  case (Three,n) => 
    8*bigPow(2,n)-3
  case (m,Zero) =>
    if(memo.length<m+1) {
      memo.padTo(m.toInt+1,mutable.Buffer())
      memo(m.toInt) += ack(m-1,1,memo)
      memo(m.toInt)(0)
    } else memo(m.toInt)(0)
  case (m,n) =>
    if(memo.length<m+1) {
      memo.padTo(m.toInt+1,mutable.Buffer())
    }
    Zero
    
}
/*
for(n <- 4 to 0 by -1) {
  for(m <- 0 to 3) print(ack(m,n)+" ")
  println
}

val memo = mutable.Map[(BigInt,BigInt),BigInt]()
for(n <- 0 to 4) {
  for(m <- 0 to 4) print(ack(m,n,memo)+" ")
  println
}

val memo2 = mutable.Buffer(mutable.Buffer[BigInt]())
for(n <- 4 to 0 by -1) {
  for(m <- 0 to 3) print(ack(m,n,memo2)+" ")
  println
}
*/
val memo = mutable.Map[(BigInt,BigInt),BigInt]()
println("f(4,0) = "+ack(4,0,memo))
println("f(4,1) = "+ack(4,1,memo))
println("f(4,2) = "+ack(4,2,memo))
//println("f(4,3) = "+ack(4,3,memo))
