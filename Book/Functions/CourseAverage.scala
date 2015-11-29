def courseAverage(test1:Double,test2:Double,assn1:Double,
    assn2:Double,assn3:Double,quiz1:Double,quiz2:Double,
    quiz3:Double,quiz4:Double):Double = { 
  val testAve = (test1+test2)/2 
  val assnAve = (assn1+assn2+assn3)/3 
  val minQuiz = quiz1 min quiz2 min quiz3 min quiz4 
  val quizAve = (quiz1+quiz2+quiz3+quiz4-minQuiz)/3 
  testAve*0.4+assnAve*0.4+quizAve*0.2 
}
