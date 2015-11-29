def radixSort(a:Array[Int]) {
  var max = a.max
  var powerOf10 = 1
  while(max>0) {
    val byDigit = Array.fill(19)(List[Int]())
    for(num <- a) {
      val digit = num/powerOf10%10+9
      byDigit(digit) ::= num
    }
    var i = 0
    for(bin <- byDigit; num <- bin.reverse) {
      a(i) = num
      i += 1
    }
    powerOf10 *= 10
    max /= 10
  }
}

val arr = Array.fill(1000)(util.Random.nextInt)
radixSort(arr)
println(arr.mkString(" "))
println((0 until arr.length-1).forall(i => arr(i)<=arr(i+1)))
