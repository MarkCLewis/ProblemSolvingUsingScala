

def permute(nums:List[Int], f: List[Int]=>Unit, p:List[Int]):Unit = {
	if(nums.isEmpty) {
		f(p)
	} else {
		var before = List[Int]()
		var after = nums
		while(after.nonEmpty) {
			permute(before ::: after.tail, f, after.head::p)
			before ::= after.head
			after = after.tail
		}
	}
}
