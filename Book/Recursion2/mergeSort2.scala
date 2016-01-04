def merge(lst1:List[Int], lst2:List[Int]):List[Int] = {
  var l1 = lst1
  var l2 = lst2
  var ret = List[Int]()
  while (l1.nonEmpty && l2.nonEmpty) {
    if (l1.head<l2.head) {
      ret ::= l1.head
      l1 = l1.tail
    } else {
      ret ::= l2.head
      l2 = l2.tail
    }
  }
  if (l1.nonEmpty) ret :::= l1.reverse
  else ret :::= l2.reverse
  ret.reverse
}

def mergeSort(lst:List[Int]):List[Int] = lst match {
  case Nil => lst
  case h::Nil => lst
  case _ =>
    val (l1, l2) = lst.splitAt(lst.length/2)
    merge(mergeSort(l1), mergeSort(l2))
}
