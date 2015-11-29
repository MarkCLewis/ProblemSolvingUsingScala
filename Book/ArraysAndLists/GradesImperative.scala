import io.StdIn._

def average(nums:List[Double]):Double = nums.sum/nums.length

def averageDropMin(nums:List[Double]):Double = (nums.sum-nums.min)/(nums.length-1)

def fullAve(tests:Double, assignments:Double, quizzes:Double):Double = 0.4*tests + 0.4*assignments + 0.2*quizzes

def courseAverage(tests:List[Double],assns:List[Double], 
    quizzes:List[Double]):Double = { 
  val aveTest=average(tests) 
  val aveAssn=average(assns) 
  val aveQuiz=averageDropMin(quizzes) 
  fullAve(aveTest,aveAssn,aveQuiz) 
}

def printMenu:Unit = {
    println("""Select one of the following options:
 1. Add a test grade.
 2. Add a quiz grade.
 3. Add an assignment grade.
 4. Calculate average.
 5. Quit.""")
}

var tests = List[Double]()
var quizzes = List[Double]()
var assignments = List[Double]()

def mainGrades:Unit = {
  printMenu
  readInt() match {
    case 1 =>
      println("Enter a test grade.")
      tests = readDouble() :: tests
    case 2 =>
      println("Enter a quiz grade.")
      quizzes = readDouble() :: quizzes
    case 3 =>
      println("Enter an assignment grade.")
      assignments = readDouble() :: assignments
    case 4 =>
      println("The average is "+courseAverage(tests,assignments,quizzes))
    case 5 =>
      return
    case _ =>
      println("Unknown option.  Try again.")
  }
  mainGrades
} 

mainGrades
