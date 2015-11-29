def testAve(test1:Double,test2:Double):Double = (test1+test2)/2 

def assnAve(assn1:Double,assn2:Double,assn3:Double):Double = 
  (assn1+assn2+assn3)/3 

def quizAve(quiz1:Double,quiz2:Double,quiz3:Double,quiz4:Double):Double = { 
  val minQuiz = quiz1 min quiz2 min quiz3 min quiz4 
  (quiz1+quiz2+quiz3+quiz4-minQuiz)/3 
}

def fullAve(test:Double,assn:Double,quiz:Double):Double = 
  test*0.4+assn*0.4+quiz*0.2 

def courseAverage(test1:Double,test2:Double,assn1:Double,
    assn2:Double,assn3:Double,quiz1:Double,quiz2:Double,
    quiz3:Double,quiz4:Double):Double = { 
  val test=testAve(test1,test2) 
  val assn=assnAve(assn1,assn2,assn3) 
  val quiz=quizAve(quiz1,quiz2,quiz3,quiz4) 
  fullAve(test,assn,quiz) 
}
