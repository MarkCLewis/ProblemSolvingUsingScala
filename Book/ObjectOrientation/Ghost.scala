import scalafx.scene.paint.Color

class Ghost(
    private var _x:Double, 
    private var _y:Double, 
    private var _dir:Int, 
    private var _animationCount:Int,
    val color:Color) {

  private var _scared = false

  def x = _x
  def y = _y
  def dir = _dir
  def animationCount = _animationCount
  def scared = _scared

  def scare():Unit = {
    _scared = true
    _animationCount = 0
  }

  def eaten():Unit = {
    _x = 9
    _y = 9
    _scared = false
  }

  def update(maze:Maze):Unit = {
    if (animationCount >= 0) {
      if ((x == x.toInt) && y == y.toInt) {
        if (maze.cell(x.toInt, y.toInt-1) == Maze.Door) {
          _dir = 0
        } else {
          val allowed = (0 to 3).filter(d => {
            val cell = maze.cell((x+Maze.XOffset(d)).toInt, (y+Maze.YOffset(d)).toInt)
            cell > 0 && (d+2)%4 != dir
          })
          _dir = allowed(util.Random.nextInt(allowed.length))
        }
      }
      if (dir >= 0) {
        _x += Maze.XOffset(dir)/4.0
        _y += Maze.YOffset(dir)/4.0
        if (x < -1) _x = maze.columns
        else if (x >= maze.columns) _x = -1
      }
    }
    _animationCount += 1
    if (scared && animationCount > Ghost.ScaredTime) {
      _scared = false
    }
  }
}

object Ghost {
  val ScaredTime = 100
}
