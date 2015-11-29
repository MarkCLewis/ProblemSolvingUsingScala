import java.io._

val currentDir = new File(".")

def copyCode(f:File):Unit = {
	val destDir = new File(currentDir, f.getName)
	destDir.mkdirs()
	val codeDir = new File(f, "Code")
	println(codeDir)
	if(codeDir.exists) {
		for(s <- codeDir.listFiles; if s.getName.endsWith(".scala")) {
			val source = io.Source.fromFile(s)
			val pw = new PrintWriter(new File(destDir, s.getName))
			pw.print(source.mkString)
			pw.close
			source.close
		}
	}
}

val parent = new File(args(0))
for(f <- parent.listFiles; if f.isDirectory) {
	copyCode(f)
}
