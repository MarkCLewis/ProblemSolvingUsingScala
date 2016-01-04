def merge(lst1:List[Int], lst2:List[Int]):List[Int] = (lst1,lst2) match {
  case (Nil,_) => lst2
  case (_,Nil) => lst1
  case (h1::t1, h2::t2) =>
    if (h1<h2) h1 :: merge(t1, lst2)
    else h2 :: merge(lst1, t2)
}

def mergeSort(lst:List[Int]):List[Int] = lst match {
  case Nil => lst
  case h::Nil => lst
  case _ =>
    val (l1, l2) = lst.splitAt(lst.length/2)
    merge(mergeSort(l1), mergeSort(l2))
}
