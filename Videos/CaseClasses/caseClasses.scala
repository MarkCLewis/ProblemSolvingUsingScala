import io.StdIn._

case class Point3D(var x:Double, var y:Double, var z:Double)

case class Student(name:String, tests:List[Int], assns:List[Int], quizzes:List[Int])

def distance(p1:Point3D, p2:Point3D):Double = {
	val dx = p1.x-p2.x
	val dy = p1.y-p2.y
	val dz = p1.z-p2.z
	math.sqrt(dx*dx+dy*dy+dz*dz)
}

def classAverage(s:Student):Double = {
	0.4*s.tests.sum/s.tests.length + 0.5*s.assns.sum/s.assns.length + 0.1*s.quizzes.sum/s.quizzes.length
}
