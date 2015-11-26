
def countDown(n:Int):Unit = {
	if(n > 0) {
		println(n)
		countDown(n-1)
	}
}

def countUp(n:Int):Unit = {
	if(n > 0) {
		countUp(n-1)
		println(n)
	}
}
