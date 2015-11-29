import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.chart._
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Pos

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Charts"
    scene = new Scene(1000, 800) {
      case class PlanetData(name:String, mass:Double, a:Double)
      val planets = List(PlanetData("Mercury", 0.0553, 0.387),
                         PlanetData("Venus", 0.815, 0.723),
                         PlanetData("Earth", 1.0, 1.0),
                         PlanetData("Mars", 0.107, 1.524),
                         PlanetData("Jupiter", 317.83, 5.203),
                         PlanetData("Saturn", 95.159, 9.537),
                         PlanetData("Uranus", 14.536, 19.191),
                         PlanetData("Neptune", 17.147, 30.069))

      val pieChart = PieChart(ObservableBuffer(planets.map(p => PieChart.Data(p.name,p.mass)):_*))

      val barSeries = XYChart.Series("Planetary Mass", ObservableBuffer(planets.map(p => XYChart.Data(p.name, p.mass:Number)):_*))
      val barChart = BarChart(CategoryAxis(), NumberAxis("Mass [Earth Mass]"), ObservableBuffer(barSeries))

      val scatterSeries = XYChart.Series("Mass vs. Orbital Distance", ObservableBuffer(planets.map(p => XYChart.Data[Number, Number](p.a, p.mass)):_*))
      val scatterChart = ScatterChart(NumberAxis("Semimajor Axis [AU]"), NumberAxis("Mass [Earth Mass]"), ObservableBuffer(scatterSeries))

      val tilePane = new TilePane
      tilePane.children = List(pieChart, barChart, scatterChart)
      tilePane.alignment = Pos.TopCenter
      
      root = tilePane
    }
  }
}

app.main(args)
