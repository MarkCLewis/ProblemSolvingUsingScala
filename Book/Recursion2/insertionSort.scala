def insertionSort(a:Array[Double],i:Int=1) {
  def insert(v:Double,i:Int) {
    if(i<=0 || v>=a(i-1)) a(i)=v else {
      a(i)=a(i-1)
      insert(v,i-1)
    }
  }

  if(i<a.length) {
    insert(a(i),i)
    insertionSort(a,i+1)
  }
}
