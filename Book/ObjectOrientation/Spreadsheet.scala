class Spreadsheet(rows:Int, columns:Int) {
  private val data = Array.fill(rows,columns)(0.0)

  def apply(c:Int, r:Int) : Double = data(r)(c)

  def update(c:Int, r:Int, v:Double) = data(r)(c)=v

  def apply(c:String, r:Int) : Double = data(r)(calcColumn(c))

  def update(c:String, r:Int, v:Double) = data(r)(calcColumn(c))=v

  def apply(cell:String) : Double = {
    val (c,r) = cell.partition(_.isLetter)
    data(r.toInt)(calcColumn(c))
  }

  def update(cell:String, v:Double) = {
    val (c,r) = cell.partition(_.isLetter)
    data(r.toInt)(calcColumn(c))=v
  }

  private def calcColumn(c:String) : Int = {
    c.toLowerCase.foldLeft(0)((v,c) => v*26+(c-'a'))
  }
}
