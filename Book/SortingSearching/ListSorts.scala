def bubbleSort(lst:List[Double]):List[Double] = {
  def swapper(lst:List[Double]):(Boolean, List[Double]) = lst match {
    case Nil => (false, lst)
    case h::Nil => (false, lst)
    case h1::h2::t =>
      if(h1 <= h2) {
        val (swap, rest) = swapper(h2::t)
        (swap, h1 :: rest)
      } else (true, h2 :: swapper(h1::t)._2)
  }

  val (swap, swapped) = swapper(lst)
  if(swap) bubbleSort(swapped)
  else swapped
}

def minSort(lst:List[Double]):List[Double] = {
  def findAndRemoveMin(lst:List[Double]):(Double, List[Double]) = lst match {
    case Nil => throw new RuntimeException("Find and remove from Nil.")
    case h::Nil => (h, Nil)
    case h::t =>
      val (min, rest) = findAndRemoveMin(t)
      if(h < min) (h, min::rest) else (min, h::rest)
  }

  lst match {
    case Nil => lst
    case h::Nil => lst
    case h::t =>
      val (min, rest) = findAndRemoveMin(lst)
      min::minSort(rest)
  }
}

def insertionSort(lst:List[Double]):List[Double] = {
  def insert(x:Double, sorted:List[Double]):List[Double] = sorted match {
    case Nil => x::Nil
    case h::t => if(x < h) x::sorted else h::insert(x, t)
  }

  def helper(sorted:List[Double], unsorted:List[Double]):List[Double] = unsorted match {
    case Nil => sorted
    case h::t => helper(insert(h, sorted), t)
  }

  helper(Nil, lst)
}

