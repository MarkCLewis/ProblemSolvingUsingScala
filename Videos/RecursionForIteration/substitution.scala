def fact(n:Int):Int = if(n<2) 1 else n*fact(n-1)

def sumSquares(n:Int):Int = if(n<2) 1 else n*n+sumSquares(n-1)


fact(5)
if(5<2) 1 else 5*fact(5-1)
5*fact(5-1)
5*fact(4)
5*(if(4<2) 1 else 4*fact(4-1))
5*(4*fact(4-1))
5*4*fact(3)
5*4*(if(3<2) 1 else 3*fact(3-1))
5*4*(3*fact(3-1))
...
5*4*3*2*fact(1)
5*4*3*2*(if(1<2) 1 else 1*fact(1-1))
5*4*3*2*1
120

sumSquares(3)
if(3<2) 1 else 3*3+sumSquares(3-1)
3*3+sumSquares(2)
3*3+(if(2<2) 1 else 2*2+sumSquares(2-1))
3*3+2*2+sumSquares(1)
3*3+2*2+(if(1<2) 1 else 1*1+sumSquares(1-1))
3*3+2*2+1
14
