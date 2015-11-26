

def fillArray(arr:Array[Int],v:Int,i:Int):Unit = {
  if(i < arr.length) {
    arr(i) = v
    fillArray(arr,v,i+1)
  }
}

def operateOnArray(arr:Array[Int], i:Int, f: (Int,Int) => Int):Int = {
  if(i < arr.length-1) {
    f(arr(i), operateOnArray(arr, i+1, f))
  } else {
    arr(i)
  }
}

val nums = Array(1,1,1,1,1,1)
fillArray(nums, 99, 0)
println(nums)
val nums2 = Array(1,2,3,4,5,6)
println(operateOnArray(nums2,0,_*_))
