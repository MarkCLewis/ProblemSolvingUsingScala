import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene._
import scalafx.scene.effect._
import scalafx.scene.paint._
import scalafx.scene.shape._
import scalafx.scene.text._

def makeText(x:Double, y:Double, s:String, effect:Effect, darkBack:Boolean):Node = {
  val text = new Text(x, y, s)
  text.font = Font("serif", FontWeight.Bold, 40)
  text.fill = if (darkBack) Color.White else Color.Black
  text.effect = effect
  if (darkBack) {
    val group = new Group
    val b = text.boundsInLocal.value
    val r = Rectangle(b.minX, b.minY, b.width, b.height)
    r.fill = Color.Black
    group.children = List(r, text)
    group
  } else {
    text
  }
}

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Effects"
    scene = new Scene(450,720) {
      val bloom = makeText(20, 40, "Bloom", new Bloom(0.1), true)
      val boxBlur = makeText(20, 100, "BoxBlur", new BoxBlur(7, 7, 2), false)
      val floatMap = new FloatMap(400, 40)
      for (i <- 0 until floatMap.width.value;
          offset = (0.1*math.sin(i/30.0)).toFloat;
          j <- 0 until floatMap.height.value) {
        floatMap.setSamples(i, j, 0.0f, offset)
      }
      val dMap = new DisplacementMap(floatMap)
      val displacementMap = makeText(20, 160, "DisplacementMap", dMap, false)
      val dropShadow = makeText(20, 220, "DropShadow", new DropShadow(5, 20, 20, Color.Black), false)
      val gaussianBlur = makeText(20, 300, "GaussianBlur", new GaussianBlur(7), false)
      val innerShadow = makeText(20, 360, "InnerShadow", new InnerShadow(3, 3, 3, Color.Green), false)
      val lightEffect = new Lighting(new Light.Distant(0, 45, Color.White))
      lightEffect.surfaceScale = 5.0
      val lighting = makeText(20, 420, "Lighting", lightEffect, false)
      val motionBlur = makeText(20, 480, "MotionBlur", new MotionBlur(15, 10), false)
      val perspective = makeText(20, 540, "Perspective", new PerspectiveTransform(20, 500, 400, 510, 400, 520, 20, 540), false) 
      val reflection = makeText(20, 600, "Reflection", new Reflection(5, 0.8, 1.0, 0.0), false)
      val shadow = makeText(20, 700, "Shadow", new Shadow(5, Color.Black), false)

      content = List(bloom, boxBlur, displacementMap, dropShadow, gaussianBlur, innerShadow, lighting, motionBlur, perspective, reflection, shadow)
    }
  }
}

app.main(args)
