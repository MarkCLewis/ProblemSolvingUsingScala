import scala.annotation.tailrec

@tailrec
def merge(l1:List[Double], l2:List[Double], lst:List[Double]):List[Double] = 
	(l1, l2) match {
		case (Nil, _) => lst.reverse ::: l2
		case (_, Nil) => lst.reverse ::: l1
		case (h1::t1, h2::t2) =>
			if(h1 < h2) merge(t1, l2, h1::lst)
			else merge(l1, t2, h2::lst)
	}

// O(n log n)
def mergesort(lst:List[Double]):List[Double] = lst match {
	case Nil => lst
	case h::Nil => lst
	case _ =>
		val (l1, l2) = lst.splitAt(lst.length/2)
		merge(mergesort(l1), mergesort(l2), Nil)
}
