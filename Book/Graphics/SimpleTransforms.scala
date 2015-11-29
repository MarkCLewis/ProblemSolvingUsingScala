import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.{Scene, Group}
import scalafx.scene.shape.Line
import scalafx.scene.transform.Transform

def makeParallelLines:Group = {
  new Group(Line(-50, -50, -50, 50), Line(50, -50, 50, 50))
}

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Transforms"
    scene = new Scene(400,400) {
      val lines1 = makeParallelLines
      lines1.transforms = List(Transform.translate(100,100))

      val lines2 = makeParallelLines
      lines2.transforms = List(Transform.translate(300,100), Transform.rotate(45, 0, 0))

      val lines3 = makeParallelLines
      lines3.transforms = List(Transform.translate(100,300), Transform.scale(0.5, 0.5))

      val lines4 = makeParallelLines
      lines4.transforms = List(Transform.translate(300,300), Transform.shear(0.1,0.2))

      content = List(lines1, lines2, lines3, lines4)
    }
  }
}

app.main(args)
