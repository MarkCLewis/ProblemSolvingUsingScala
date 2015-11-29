def maxSort(a:Array[Double],end:Int) {
  def maxIndex(end2:Int):Int = 
    if(end2==0) 0 else {
      val m=maxIndex(end2-1)
      if(a(m) > a(end2)) m else end2
    }

  if(end>0) {
    val index=maxIndex(end)
    if(index!=end) {
      val tmp=a(index)
      a(index)=a(end)
      a(end)=tmp
    }
    maxSort(a,end-1)
  }
}

