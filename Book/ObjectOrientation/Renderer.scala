import scalafx.scene.canvas._
import scalafx.scene.paint._
import scalafx.scene.shape.ArcType

object Renderer {
  def render(gc:GraphicsContext, maze:Maze):Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, Game.Width, Game.Height)
    val cellWidth = Game.Width/maze.columns
    val cellHeight = Game.Height/maze.rows
    for (i <- 0 until maze.rows; j <- 0 until maze.columns) {
      maze.cell(j, i) match {
        case Maze.Wall =>
          gc.fill = Color.Blue
          gc.fillRect(j*cellWidth, i*cellHeight, cellWidth, cellHeight)
        case Maze.Pellet =>
          gc.fill = Color.White
          gc.fillOval(j*cellWidth+3*cellWidth/8, i*cellHeight+3*cellHeight/8, cellWidth/4, cellHeight/4)
        case Maze.PowerPellet =>
          gc.fill = Color.White
          gc.fillOval(j*cellWidth+cellWidth/4, i*cellHeight+cellHeight/4, cellWidth/2, cellHeight/2)
        case Maze.Open =>
        case Maze.Door =>
          gc.fill = Color.LightGray
          gc.fillRect(j*cellWidth, i*cellHeight+cellHeight/4, cellWidth, cellHeight/2)
      }
    }
    maze.ghosts.foreach(g => drawGhost(gc, g, cellWidth, cellHeight))
    drawPacMan(gc, maze.player, cellWidth, cellHeight)
    maze.fruit.foreach(f => {
      gc.fill = Fruit.Colors(f.fruitType)
      gc.fillOval(f.x*cellWidth+cellWidth/4, f.y*cellHeight+cellHeight/4, cellWidth/2, cellHeight/2)
    })
    gc.fill = Color.White
    gc.fillText(Game.score.toString, 10, 20)
    for (i <- 0 until Game.lives) {
      gc.fill = Color.Yellow
      gc.fillArc(400+i*cellWidth, 0, cellWidth, cellHeight, 45, 270, ArcType.Round)
    }
  }

  private def drawPacMan(gc:GraphicsContext, pm:PacMan, cellWidth:Double, cellHeight:Double):Unit = {
    gc.fill = Color.Yellow
    if (pm.dying) {
      val centerAngle = 90
      val openAngle = pm.animationCount*18
      gc.fillArc(pm.x*cellWidth, pm.y*cellHeight, cellWidth, cellHeight, centerAngle+openAngle, 360-2*openAngle, ArcType.Round)
    } else {
      val openAngle = 45*math.sin(pm.animationCount/2.0)*math.sin(pm.animationCount/2.0)
      val centerAngle = pm.dir match {
        case Maze.Up => 90
        case Maze.Right => 0
        case Maze.Down => 270
        case Maze.Left => 180
        case _ => 0
      }
      gc.fillArc(pm.x*cellWidth, pm.y*cellHeight, cellWidth, cellHeight, centerAngle+openAngle, 360-2*openAngle, ArcType.Round)
    }
  }

  private def drawGhost(gc:GraphicsContext, ghost:Ghost, cellWidth:Double, cellHeight:Double):Unit = {
    gc.fill = if (ghost.scared) {
      if (ghost.animationCount < Ghost.ScaredTime-20 || ghost.animationCount%10>2) Color.DarkBlue else Color.AliceBlue
    } else ghost.color
    gc.fillArc(ghost.x*cellWidth, ghost.y*cellHeight, cellWidth, cellHeight, 0, 180, ArcType.Round)
    gc.fillRect(ghost.x*cellWidth, ghost.y*cellHeight+cellHeight/2, cellWidth, cellHeight/3)
  }
}
