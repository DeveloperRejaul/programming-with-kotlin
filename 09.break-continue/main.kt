fun main() {

    // break example
    println("break example")
    var i = 0
    while (i < 10) {
      println(i)
      i++
      if (i == 4) {
        break
      }
    }  

    // countinue example
    println("countinue example")
    var j = 0
    while (j < 10) {
        if (j == 4) {
            j++
            continue
        }
        println(j)
        j++
    }
}