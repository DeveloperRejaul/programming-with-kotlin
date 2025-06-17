fun main () {
   val list = listOf("rejaul", "Kamal", "ramjan", "karim"); // is not changeable list
   val mulist = mutableListOf("rejaul", "Kamal", "ramjan", "karim"); // is changeable list
    
    // add elements
    mulist.add("jamal"); // add element : similar of js push method
    mulist.add(0, "jashim")
    mulist.addAll(0, list)

    // delete elements
    mulist.removeAt(0)
    mulist.removeFirst()
    mulist.removeLast()
;

    // update elements
    mulist.set(0,"ramjan2")


    // remove duplicates elements
    val mySet = mulist.toSet() // return new array

    println(mySet)
}