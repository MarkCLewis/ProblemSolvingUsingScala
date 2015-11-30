class PacMan(
    private var _x:Double, 
    private var _y:Double, 
    private var _dir:Int) {

  private var _animationCount = 0
  private var keyDir = -1
  private var _dying = false

  Game.newLife()

  def x = _x
  def y = _y
  def dir = _dir
  def animationCount = _animationCount
  def dying = _dying

  def setDying():Unit = {
    _dying = true
    _animationCount = 0
  }

  def update(maze:Maze):Unit = {
    if (dying) {
      _animationCount += 1
    } else {
      if ((x == x.toInt) && y == y.toInt) {
        val allowed = (0 to 3).filter(d => {
          maze.cell((x+Maze.XOffset(d)).toInt, (y+Maze.YOffset(d)).toInt) > 0
        })
        if (allowed.contains(keyDir)) _dir = keyDir
        if (!allowed.contains(dir)) _dir = -1
      } else if ((keyDir+2)%4 == dir) {
        _dir = keyDir
      }
      if (dir >= 0) {
        _x += Maze.XOffset(dir)/4.0
        _y += Maze.YOffset(dir)/4.0
        if (x < -1) _x = maze.columns
        else if (x >= maze.columns) _x = -1
        _animationCount += 1
      }
    }
  }

  def goLeft:Unit = keyDir = Maze.Left
  def goRight:Unit = keyDir = Maze.Right
  def goUp:Unit = keyDir = Maze.Up
  def goDown:Unit = keyDir = Maze.Down
}
