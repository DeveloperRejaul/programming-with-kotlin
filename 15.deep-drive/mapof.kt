fun main () {

    var map = mapOf(
        1 to "rejaul",
        2 to "jamal",
        3 to "kamal",
    );

    val map2 = mutableMapOf(
        1 to "rejaul",
        2 to "jamal",
        3 to "kamal",
    );

    // add new data
    // map2[4] = "ramjan" // way 1
    // map2.put(5, "Pervaz"); // way 2
    // println(map2)

    // val newMap =  map2 + (5 to "jashim"); // way 3 but its return new amap
    // println(newMap)

    // delete data
    map2.remove(3)
    println(map2);

    // loop
    // Iterating over keys
    for (key in map2.keys) {
        println("Key: $key, Value: ${map2[key]}")
    }

    // Iterating over values
    for (value in map2.values) {
        println("Value: $value")
    }

}