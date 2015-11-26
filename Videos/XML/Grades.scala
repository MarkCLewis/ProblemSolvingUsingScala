import xml._

case class Grade(value:Int, comment:String)
case class Student(fname:String, lname:String, quizzes:List[Grade], 
		assignments:List[Grade], tests:List[Grade])

def gradeFromNode(n:Node):Grade = {
	Grade((n \ "@grade").text.toInt, n.text)
}

def studentFromNode(n:Node):Student = {
	val fname = (n \ "@fname").text
	val lname = (n \ "@lname").text
	val quizzes = (n \ "quiz").map(gradeFromNode).toList
	val assignments = (n \ "assignment").map(gradeFromNode).toList
	val tests = (n \ "test").map(gradeFromNode).toList
	Student(fname, lname, quizzes, assignments, tests)
}

def nodeFromStudent(s:Student):Node = {
	<student fname={s.fname} lname={s.lname}>
		{s.quizzes.map(g => <quiz grade={g.value.toString}>{g.comment}</quiz>)}
		{s.assignments.map(g => <assignment grade={g.value.toString}>{g.comment}</assignment>)}
		{s.tests.map(g => <test grade={g.value.toString}>{g.comment}</test>)}
	</student>
}

val xmlData = XML.loadFile("Grades.xml")
val courseName = (xmlData \ "courseName").text
println(courseName)
val students = (xmlData \ "student").map(studentFromNode).toArray

students(0) = students(0).copy(tests = Grade(95,"")::students(0).tests)
students(1) = students(1).copy(tests = Grade(93,"")::students(1).tests)
students(2) = students(2).copy(tests = Grade(97,"")::students(2).tests)

XML.save("Grades2.xml", <gradebook>
<courseName>{courseName}</courseName>
{students.map(nodeFromStudent)}
</gradebook>)
