// n
// 
// n-1 + n-2 + n-3 + n-4 + ... + 2 + 1
// n(n-1)/2 = (n^2)/2 - n/2
// O(n^2)


def bubbleSort(a:Array[Double]):Unit = {
	for(i <- 0 until a.length-1) {
		for(j <- 0 until a.length-1-i) {
			if(a(j) > a(j+1)) {
				val tmp = a(j)
				a(j) = a(j+1)
				a(j+1) = tmp
			}
		}
	}
}

def bubbleSortInt(a:Array[Int]):Unit = {
	for(i <- 0 until a.length-1) {
		for(j <- 0 until a.length-1-i) {
			if(a(j) > a(j+1)) {
				val tmp = a(j)
				a(j) = a(j+1)
				a(j+1) = tmp
			}
		}
	}
}

case class Student(lname:String, fname:String, avg:Double)

def bubbleSortStudent(a:Array[Student], gt: (Student, Student) => Boolean):Unit = {
	for(i <- 0 until a.length-1) {
		for(j <- 0 until a.length-1-i) {
			if(gt(a(j), a(j+1))) {
				val tmp = a(j)
				a(j) = a(j+1)
				a(j+1) = tmp
			}
		}
	}
}

def minSort(a:Array[Double]):Unit = {
	for(i <- 0 until a.length-1) {
		var min = i
		for(j <- i+1 until a.length) {
			if(a(j) < a(min)) min = j
		}
		val tmp = a(i)
		a(i) = a(min)
		a(min) = tmp
	}
}

def insertionSort(a:Array[Double]):Unit = {
	for(i <- 1 until a.length) {
		var j = i-1
		var tmp = a(i)
		while(j>=0 && tmp < a(j)) {
			a(j+1) = a(j)
			j -= 1
		}
		a(j+1) = tmp
	}
}

def insertionSortList(lst:List[Double]):List[Double] = {
	def insert(x:Double, sorted:List[Double]):List[Double] = sorted match {
		case Nil => x::Nil
		case h::t => if(x < h) x::sorted else h::insert(x, t)
	}

	def helper(sorted:List[Double], unsorted:List[Double]):List[Double] = 
		unsorted match {
			case Nil => sorted
			case h::t => helper(insert(h, sorted), t)
		}

	helper(Nil, lst)
}

def shellSort(a:Array[Double]):Unit = {
	var gap = a.length/2
	while(gap >= 1) {
		for(i <- gap until a.length) {
			var j = i-gap
			var tmp = a(i)
			while(j>=0 && tmp < a(j)) {
				a(j+gap) = a(j)
				j -= gap
			}
			a(j+gap) = tmp
		}
		gap = (gap/2.2).round.toInt
	}
}

def bucketSort(a:Array[Double]):Unit = {
	val min = a.min
	val max = a.max
	val buckets = Array.fill(a.length)(List[Double]())
	for(x <- a) {
		val b = ((x-min)*(buckets.length-1)/(max-min)).toInt
		buckets(b) ::= x
	}
	var i = 0
	for(bucket <- buckets; x <- bucket) {
		a(i) = x
		i += 1
	}
	insertionSort(a)
}

def isSorted(a:Array[Double]):Boolean = {
//	a.zip(a.tail).forall(t => t._1 <= t._2)
	(a, a.tail).zipped.forall(_ <= _)
}

val arr = Array.fill(30000)(math.random)

def timeFunc(sortFunc: Array[Double] => Unit, a:Array[Double]):Double = {
	val copy = a.map(x => x)
	val start = System.nanoTime()
	sortFunc(copy)
	val end = System.nanoTime()
	(end-start)/1e9
}

println("Bubble sort "+timeFunc(bubbleSort, arr))
println("Min sort "+timeFunc(minSort, arr))
println("Insertion sort "+timeFunc(insertionSort, arr))
println("Shell sort "+timeFunc(shellSort, arr))
println("Bucket sort "+timeFunc(bucketSort, arr))
