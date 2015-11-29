def quicksort(lst:List[Double]):List[Double] = lst match {
  case Nil => lst
  case h::Nil => lst
  case _ =>
    val pivot = lst.head
    val (less, greater) = lst.tail.partition(_<pivot)
    quicksort(less) ::: (pivot :: quicksort(greater))
}
