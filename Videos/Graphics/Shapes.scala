import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.scene.text._
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Shapes"
		scene = new Scene(600, 600) {
			val rect = Rectangle(10, 10, 80, 80)
			val circle = Circle(150, 50, 40)
			val ellipse = Ellipse(250, 50, 30, 40)
			val line = Line(310, 10, 390, 90)
			val text = new Text(410, 50, "Some text. jg")

			val polygon = Polygon(10, 110, 10, 190, 90, 190)
			val polyline = Polyline(110, 110, 110, 190, 190, 190, 190, 110, 150, 150)

			val arc = Arc(250, 150, 40, 30, 0, 270)
			arc.`type` = ArcType.Round

			val quad = QuadCurve(310, 110, 310, 190, 390, 110)

			val cubic = new CubicCurve
			cubic.startX = 410
			cubic.startY = 110
			cubic.controlX1 = 410
			cubic.controlY1 = 290
			cubic.controlX2 = 490
			cubic.controlY2 = 10
			cubic.endX = 490
			cubic.endY = 190

			content = List(rect, circle, ellipse, line, text, polygon, polyline, 
				arc, quad, cubic)
		}
	}
}

app.main(args)
