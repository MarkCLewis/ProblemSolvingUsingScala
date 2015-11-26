import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.{Scene, Group}
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.scene.transform._
import scalafx.event.ActionEvent

def makeLines:Group = {
	new Group(Line(-50,-50,-50,50), Line(50,-50,50,50))
}

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Transforms"
		scene = new Scene(600, 600) {
			val lines1 = makeLines
			lines1.transforms = List(Transform.translate(100,100))

			val lines2 = makeLines
			lines2.transforms = List(Transform.translate(300,100), Transform.rotate(45, 0, 0))

			val lines3 = makeLines
			lines3.transforms = List(Transform.translate(100,300), Transform.scale(0.5, 0.5))

			val lines4 = makeLines
			lines4.transforms = List(Transform.translate(300,300), Transform.shear(0.5, 0.2))

			content = List(lines1, lines2, lines3, lines4)
		}
	}
}

app.main(args)
