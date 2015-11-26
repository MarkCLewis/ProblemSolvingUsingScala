

def ident[A](o:A):A = o

def makeTuple[A,B](a:A, b:B):(A,B) = (a,b)

def threeList[A](a1:A, a2:A, a3:A):List[A] = List(a1, a2, a3)

def ourFold[A,B](lst:List[A],base:B)(f:(A,B)=>B):B = lst match {
	case Nil => base
	case h::t => f(h,ourFold(t,base)(f))
}
