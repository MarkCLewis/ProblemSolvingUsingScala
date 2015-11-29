
// O(n log n)
def quicksort(lst:List[Double]):List[Double] = lst match {
	case Nil => lst
	case h::Nil => lst
	case pivot::t =>
		val (less, greater) = t.partition(_ < pivot)
		quicksort(less) ::: (pivot :: quicksort(greater))
}
