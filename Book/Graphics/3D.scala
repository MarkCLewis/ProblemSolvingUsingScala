import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.{Scene, ParallelCamera, PerspectiveCamera, AmbientLight, PointLight}
import scalafx.scene.image.Image
import scalafx.scene.paint.{Color, PhongMaterial}
import scalafx.scene.shape.{Box, Cylinder, MeshView, Sphere}
import scalafx.scene.control._
import scalafx.event.ActionEvent

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "3D"
    scene = new Scene(400, 400) {
      val marsImage = new Image("file:marssurface.jpg")
      val sphere = new Sphere(50)
      sphere.translateX = 200
      sphere.translateY = 200
      val sphereMaterial = new PhongMaterial(Color.Red)
      sphere.material = sphereMaterial

      val cylinder = new Cylinder(40, 100)
      cylinder.translateX = 100
      cylinder.translateY = 100
      cylinder.material = new PhongMaterial(Color.Green)

      val box = new Box(50, 40, 60)
      box.translateX = 300
      box.translateY = 300
      box.material = new PhongMaterial(Color.Blue)

      val ambient = new AmbientLight(Color.Gray)
      val point = new PointLight(Color.White)
      point.translateX = 400
      point.translateZ = -400

      val usePerspective = new CheckBox("Perspective View")
      usePerspective.layoutY = 270
      usePerspective.onAction = (e:ActionEvent) => {
        if(usePerspective.selected.value) {
          camera = new PerspectiveCamera(false)
        } else {
          camera = new ParallelCamera
        }
      }

      val useTexture = new CheckBox("Texture Sphere")
      useTexture.layoutY = 300
      useTexture.onAction = (e:ActionEvent) => {
        if(useTexture.selected.value) {
          sphereMaterial.diffuseMap = marsImage
          sphereMaterial.diffuseColor = Color.White
        } else {
          sphereMaterial.diffuseMap = null
          sphereMaterial.diffuseColor = Color.Red
        }
      }

      val ambientPicker = new ColorPicker(ambient.color.value)
      ambient.color <==> ambientPicker.value
      ambientPicker.layoutY = 330

      val pointPicker = new ColorPicker(point.color.value)
      point.color <==> pointPicker.value
      pointPicker.layoutY = 360

      content = List(sphere, cylinder, box, ambient, point, usePerspective, useTexture, ambientPicker, pointPicker)
    }
  }
}

app.main(args)
