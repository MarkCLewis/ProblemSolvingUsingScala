
def indexOf(a:Array[Int], c:Int):Int = {
	var i = 0
	while(i < a.length && a(i)!=c) i += 1
	if(i < a.length) i else -1
}

def find(a:Array[Int], p: Int => Boolean):Option[Int] = {
	var i = 0
	while(i < a.length && !p(a(i))) i += 1
	if(i < a.length) Some(a(i)) else None
}

def binarySearch(a:Array[Int], value:Int):Int = {
	var start = 0
	var end = a.length
	var mid = (end+start)/2
	while(end > start && a(mid)!=value) {
		if(value < a(mid)) {
			end = mid
		} else {
			start = mid+1
		}
		mid = (start+end)/2
	}
	if(end > start) mid else -1
}

def binarySearch2(a:Array[Int], value:Int):Int = {
	def binarySearchRecur(a:Array[Int], value:Int, start:Int, end:Int):Int = {
		if(end <= start) -1 else {
			val mid = (end+start)/2
			if(a(mid) == value) mid
			else if(value < a(mid)) binarySearchRecur(a, value, start, mid)
			else binarySearchRecur(a, value, mid+1, end)
		}
	}
	binarySearchRecur(a, value, 0, a.length)
}



