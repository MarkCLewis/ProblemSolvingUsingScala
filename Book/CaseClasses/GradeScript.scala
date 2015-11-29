import io.StdIn._
import io.Source
import java.io.PrintWriter

case class Student(name:String, assignments:List[Double], tests:List[Double],
  quizzes:List[Double]) 

case class Section(students:Array[Student])

def classAverage(s:Student):Double = {
  val assnAve = if(s.assignments.isEmpty) 0.0
    else s.assignments.sum/s.assignments.length
  val quizAve = if(s.quizzes.length<2) 0.0
    else (s.quizzes.sum-s.quizzes.min)/(s.quizzes.length-1)
  val testAve = if(s.tests.isEmpty) 0.0
    else s.tests.sum/s.tests.length
  0.5*assnAve + 0.3*testAve + 0.2*quizAve
}

def createSection:(String,Section) = {
  println("What file would you like to save this as?")
  val fileName = readLine()
  println("How many students are in the class?")
  val numStudents = readInt()
  println("Enter the student names, one per line.")
  (fileName,Section(Array.
    fill(numStudents)(Student(readLine(),Nil,Nil,Nil))))
} 

def saveSection(fileName:String, section:Section):Unit = {
  val pw = new PrintWriter(fileName)
  pw.println(section.students.length)
  for(s <- section.students) {
    pw.println(s.name)
    pw.println(s.assignments.mkString(" "))
    pw.println(s.tests.mkString(" "))
    pw.println(s.quizzes.mkString(" "))
  }
  pw.close()
}

def loadSection(fileName:String):(String,Section) = {
  val src = Source.fromFile(fileName)
  val lines = src.getLines
  val section = Section(Array.fill(lines.next().toInt)(Student(
    lines.next(),
    lines.next().split(" ").filter(_.length>0).map(_.toDouble).toList,
    lines.next().split(" ").filter(_.length>0).map(_.toDouble).toList,
    lines.next().split(" ").filter(_.length>0).map(_.toDouble).toList
  )))
  src.close
  (fileName,section)
}

def addTest(section:Section):Unit = {
  for(i <- 0 until section.students.length) {
    println("Enter the grade for "+section.students(i).name+".")
    section.students(i) = section.students(i).
      copy(tests=readInt()::section.students(i).tests)
  }
} 
 
def addAssignment(section:Section):Unit = {
  for(i <- 0 until section.students.length) {
    println("Enter the grade for "+section.students(i).name+".")
    section.students(i) = section.students(i).
      copy(assignments=readInt()::section.students(i).assignments)
  }
} 

def addQuiz(section:Section):Unit = {
  for(i <- 0 until section.students.length) {
    println("Enter the grade for "+section.students(i).name+".")
    section.students(i) = section.students(i).
      copy(quizzes=readInt()::section.students(i).quizzes)
  }
} 

def printAverages(section:Section):Unit = {
  for(s <- section.students) {
    println(s.name)
    val assnAve = if(s.assignments.isEmpty) 0.0
      else s.assignments.sum/s.assignments.length
    println(s.assignments.mkString("Assignments:",", "," = "+assnAve))
    val quizAve = if(s.quizzes.length<2) 0.0
      else (s.quizzes.sum-s.quizzes.min)/(s.quizzes.length-1)
    println(s.quizzes.mkString("Quizzes:",", "," = "+quizAve))
    val testAve = if(s.tests.isEmpty) 0.0
      else s.tests.sum/s.tests.length
    println(s.tests.mkString("Tests:",", "," = "+testAve))
    println("Average = "+(0.5*assnAve+0.3*testAve+0.2*quizAve))
  }
} 

def printMenu:Unit = {
  println("""Select an option:
1. Add Test Grade
2. Add Assignment Grade
3. Add Quiz Grade
4. PrintAverages
5. Save and Quit""")
}

def mainMenu(section:Section):Unit = {
  var option = 0
  do {
    printMenu
    option = readInt()
    option match {
      case 1 => addTest(section)
      case 2 => addAssignment(section)
      case 3 => addQuiz(section)
      case 4 => printAverages(section)
      case 5 => println("Goodbye!")
      case _ => println("Invalid option. Try again.")
    }
  } while(option!=5)
} 

val (fileName, section) = if(args.length<1) createSection
  else loadSection(args(0))
mainMenu(section)
saveSection(fileName, section)

