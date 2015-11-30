import scalafx.scene.paint.Color

class Fruit(
    val x:Double, 
    val y:Double, 
    val fruitType:Int) {
}

object Fruit {
  val Cherry = 0
  val Strawberry = 1
  val Orange = 2
  val Scores = List(100, 300, 500)
  val Colors = List(Color.Red, Color.Fuchsia, Color.Orange)
}
