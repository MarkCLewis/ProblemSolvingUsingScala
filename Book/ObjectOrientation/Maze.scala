import scalafx.scene.paint.Color

class Maze {
  private var cells:Array[Array[Int]] = null
  private var _player:PacMan = null
  private var _ghosts:List[Ghost] = Nil
  private var _fruit:Option[Fruit] = None
  private var count = 0
  private var nextFruitType = 0

  init()

  private def init():Unit = {
    cells = Array(Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
                  Array(0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0),
                  Array(0,2,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,2,0),
                  Array(0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0),
                  Array(0,1,0,0,1,0,1,0,1,0,1,0,1,0,1,0,0,1,0),
                  Array(0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0),
                  Array(0,0,0,0,1,0,0,0,3,0,3,0,0,0,1,0,0,0,0),
                  Array(0,0,0,0,1,0,3,3,3,3,3,3,3,0,1,0,0,0,0),
                  Array(0,0,0,0,1,0,3,0,0,-1,0,0,3,0,1,0,0,0,0),
                  Array(3,3,3,3,1,3,3,0,3,3,3,0,3,3,1,3,3,3,3),
                  Array(0,0,0,0,1,0,3,0,0,0,0,0,3,0,1,0,0,0,0),
                  Array(0,0,0,0,1,0,3,3,3,3,3,3,3,0,1,0,0,0,0),
                  Array(0,0,0,0,1,0,3,0,0,0,0,0,3,0,1,0,0,0,0),
                  Array(0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0),
                  Array(0,1,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,1,0),
                  Array(0,2,1,0,1,1,1,1,1,3,1,1,1,1,1,0,1,2,0),
                  Array(0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0),
                  Array(0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0),
                  Array(0,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,0),
                  Array(0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0),
                  Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0))
    _player = new PacMan(9, 15, -1)
    _ghosts = List(new Ghost(9, 7, 1, 0, Color.Red),
                   new Ghost(8, 9, 1, -50, Color.Cyan),
                   new Ghost(9, 9, 0, -25, Color.Pink),
                   new Ghost(10, 9, 3, -75, Color.Orange))
    _fruit = None
    count = 0
    nextFruitType = 0
  }

  def player = _player
  def ghosts = _ghosts
  def fruit = _fruit

  def rows = cells.length
  def columns = cells(0).length

  def cell(x:Int, y:Int):Int = {
    if (y < 0 || y >= cells.length || x < 0 || x >= cells(y).length) {
      if (y == 9) Maze.Open else Maze.Wall
    } else cells(y)(x)
  }

  def pelletCount:Int = cells.map(_.count(c => c == Maze.Pellet || c == Maze.PowerPellet)).sum

  def updateAll():Int = {
    // Update moving stuff
    player.update(this)
    if (player.dying) {
      if (player.animationCount > 10) init()
      0
    } else {
      ghosts.foreach(_.update(this))
      count += 1

      // Eat pellets
      val pelletScore = if (player.x>0 && player.x<cells(0).length-1) {
        val c = cells((player.y+0.5).toInt)((player.x+0.5).toInt)
        cells((player.y+0.5).toInt)((player.x+0.5).toInt) = Maze.Open
        if (c == Maze.PowerPellet) {
          ghosts.foreach(_.scare())
          100
        } else if (c == Maze.Pellet) 10 else 0
      } else 0

      // Check ghost intersects
      val (ghostScore, dying) = ghosts.map(g => {
        if ((g.x-player.x).abs < 0.8 && (g.y-player.y).abs < 0.8) {
          if (g.scared) {
            g.eaten()
            (200, false) 
          } else (0, true)
        } else (0, false)
      }).foldLeft(0, false)((acc, gt) => (acc._1+gt._1, acc._2 || gt._2))
      if (dying) {
        player.setDying
      }

      // Check fruit intersect
      val fruitScore = fruit.map(f => if ((f.x-player.x).abs < 0.8 && (f.y-player.y).abs < 0.8) Fruit.Scores(f.fruitType) else 0).getOrElse(0)
      if (fruitScore > 0) _fruit = None
  
      // Add fruit
      if (count%200 == 0) {
        if (fruit == None) {
          _fruit = Some(new Fruit(9, 11, nextFruitType))
          if (nextFruitType < Fruit.Orange) nextFruitType += 1
        } else {
          _fruit = None
        }
      }

      pelletScore+ghostScore+fruitScore
    }
  }
}

object Maze {
  val Door = -1
  val Wall = 0
  val Pellet = 1
  val PowerPellet = 2
  val Open = 3

  val Up = 0
  val Right = 1
  val Down = 2
  val Left = 3
  val XOffset = Array(0,1,0,-1)
  val YOffset = Array(-1,0,1,0)
}
