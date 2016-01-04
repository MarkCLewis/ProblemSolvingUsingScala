import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.shape._
import scalafx.scene.paint.Color

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Path"
    scene = new Scene(500, 300) {
      val path = new Path
      path.elements += MoveTo(50, 50)
      path.elements += VLineTo(250)
      path.elements += HLineTo(250)
      path.elements += LineTo(300, 200)
      path.elements += CubicCurveTo(500, 300, 500, 0, 400, 100)
      path.elements += QuadCurveTo(250,0,200,100)
      path.elements += ArcTo(100, 50, 45, 100, 50,false, true)
      path.elements += new ClosePath

      val path2 = new Path
      path2.elements += MoveTo(75,75)
      path2.elements += VLineTo(175)
      path2.elements += HLineTo(175)
      path2.elements += VLineTo(75)
      path2.elements += CubicCurveTo(0,175,250,175,75,75)
      path2.fillRule = FillRule.EvenOdd
      path2.fill = Color.Blue

      val path3 = new Path
      path3.elements += MoveTo(275,75)
      path3.elements += VLineTo(175)
      path3.elements += HLineTo(375)
      path3.elements += VLineTo(75)
      path3.elements += CubicCurveTo(200,175,450,175,275,75)
      path3.fillRule = FillRule.NonZero
      path3.fill = Color.Cyan

      content = List(path, path2, path3)

    }
  }
}

app.main(args)
