class Student(name:String, id:String) {
  var tests = List[Double]()
  var quizzes = List[Double]()
  var assignments = List[Double]()

  def testAverage = tests.sum/tests.size
  def quizAverage = quizzes.sum/quizzes.size
  def assignmentAverage = assignments.sum/assignments.size
  def courseAverage = testAverage*0.4 + quizAverage*0.1 + assignmentAverage*0.5
}

val john = new Student("John Doe","0123456")
john.tests ::= 78
john.tests ::= 85
println(john.testAverage)
