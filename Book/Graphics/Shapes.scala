import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.shape._
import scalafx.scene.text.Text

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Shapes"
    scene = new Scene(500, 200) {
      val arc = Arc(50, 50, 40, 30, 0, 270)
      arc.fill = Color.Black
      arc.`type` = ArcType.Open

      val circle = Circle(150, 50, 40, Color.Red)

      val cubic = new CubicCurve
      cubic.startX = 210
      cubic.startY = 10
      cubic.controlX1 = 210
      cubic.controlY1 = 90
      cubic.controlX2 = 290
      cubic.controlY2 = 10
      cubic.endX = 290
      cubic.endY = 90

      val ellipse = Ellipse(350, 50, 40, 30)

      val line = Line(410, 10, 490, 90)

      val polygon = Polygon(10, 110, 50, 190, 90, 150)
      
      val polyline = Polyline(110, 110, 150, 190, 190, 150)

      val quad = QuadCurve(210, 110, 250, 190, 290, 110)

      val rectangle = Rectangle(310, 110, 80, 80)

      val path = new SVGPath
      path.content = "M410,110 L410,190 C490,190 490,110 410,110"

      val text = new Text(210, 100, "Shapes")

      content = List(arc, circle, cubic, ellipse, line, polygon, polyline, quad, rectangle, path, text)
    }
  }
}

app.main(args)
