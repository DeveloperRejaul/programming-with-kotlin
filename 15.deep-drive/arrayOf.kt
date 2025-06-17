

fun main () {

    // array of
    val array = arrayOf(1,2,3,4,5);
    val array2 = arrayOf("rejaul","kamal","ramjan","jamal","karim");

    // access array value
    // println(array[0]);

    /* change array value */
    //  array[0]=2
    //  println(array[0])

    /* ========================================================
                        iterate arrayOf with forEach
    ======================================================== */

    // array.forEach { println(it) } // lambda function with forEach more shortcut with it keyword
    // array.forEach { x:Int -> println(x) } // lambda function with forEach
    //  array.forEach(fun(x){ println(x) }) //way:1 with unanimous function
    // val fn = fun(x:Int){ println(x) };
    // array.forEach(fn) //way:2 with unanimous function

    // fun nameFn(x:Int){println(x)}
    // array.forEach(::nameFn) // with name function

    // index ways work with forEach
    // array2.forEachIndexed(fun(index, x) { println("$index:$x") })

    /* ========================================================
                   iterate arrayOf with map
    ======================================================== */

    // val newArray = array.map { it } // using lambda surtout function it keyword
    // println(newArray);
    // val newArray = array.map { x-> x*x } // using lambda surtout function without it keyword
    // println(newArray)


    /* ========================================================
               iterate arrayOf with filter
    ======================================================== */
    // val oddNum = array.filter { it%2 == 0 }
    // val oddNum = array.filter { x -> x%2 == 0 }
    // println(oddNum);

    /* ========================================================
               iterate arrayOf with find
    ======================================================== */
     // val oddNum = array.find { it%2 == 0 }
     // val oddNum = array.find { x -> x%2 == 0 }
     // println(oddNum);

    /* ========================================================
               iterate arrayOf with reduce
    ======================================================== */
    //   val newArray = array.reduce(fun(acc,currentValue):Int{return acc + currentValue })
    //   println(newArray)
    //  val newArray = array.fold(10,fun(acc,currentValue):Int{return acc + currentValue }) // defining initial acc value
    //  println(newArray)
}

