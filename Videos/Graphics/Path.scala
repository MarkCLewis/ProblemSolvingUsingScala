import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.scene.paint.{Color, LinearGradient, RadialGradient, CycleMethod, Stop}
import scalafx.event.ActionEvent

val app = new JFXApp {
	stage = new JFXApp.PrimaryStage {
		title = "Paths"
		scene = new Scene(600, 600) {
			val svg = new SVGPath
			svg.content = "M10,10 L10,190 C190,190 190,10 10,10"
			svg.fill = Color.Tomato

			val path = new Path
			path.elements += MoveTo(210, 10)
			path.elements += VLineTo(210)
			path.elements += HLineTo(30)
			path.elements += LineTo(210, 290)
			path.elements += QuadCurveTo(600, 600, 590, 10)
			path.elements += new ClosePath
			path.fill = LinearGradient(250, 250, 450, 50, false, CycleMethod.Reflect,
				Stop(0, Color.Yellow), Stop(0.1, Color.Red), Stop(0.9, Color.Green),
				Stop(1.0, Color.White))
			path.strokeWidth = 15
			path.strokeType = StrokeType.Centered
			path.stroke = Color.Blue
			path.strokeDashArray = List(40, 40, 20, 20)
			path.strokeLineCap = StrokeLineCap.ROUND
			path.strokeLineJoin = StrokeLineJoin.ROUND

			val path2 = new Path
			path2.elements += MoveTo(10, 250)
			path2.elements += VLineTo(590)
			path2.elements += HLineTo(300)
			path2.elements += VLineTo(250)
			path2.elements += CubicCurveTo(-100, 590, 400, 590, 10, 250)
			path2.fill = RadialGradient(45, 10, 150, 500, 100, false,
				CycleMethod.Repeat, Stop(0, Color.Black), Stop(0.1, Color.Red),
				Stop(0.9, Color.Green), Stop(1.0, Color.White))
			path2.fillRule = FillRule.EVEN_ODD

			content = List(svg, path, path2)
		}
	}
}

app.main(args)
