
fun main () {
//    println(add(10,20))
//    println(add2(10,20))
//    println(add3())
//    val  b = B();
//    b.foo();
//    foo(baz = 1);
//    println(stringPlus.invoke("<-", "->"))
//    println(stringPlus("Hello, ", "world!"))
//    println(intPlus.invoke(1, 1))
//    println(intPlus(1, 2))
//    println(sum(10, 20))
    HOCF(10, 20, c = { a,b -> println(a);println(b) })

}


// normal function
fun add2 (a: Int, b: Int):Int {
    return  a+b
};


// arrow function
fun  add (a:Int,b:Int) = a+b;


// function default parameter
fun  add3 (a: Int = 10, b: Int = 10) = a+b

// default parameter inner of class function
open  class  A {
   open fun foo (i:Int = 10)  {}
}
class  B: A() {
    override fun foo(i: Int) {
       println(i)
    }
}


// function name argument
fun foo (bar:Int = 0, baz:Int) {
    println("bar: $bar")
    println("baz: $baz")
}

// Lambda  function
var  stringPlus: (String ,String ) -> String = String::plus;
var intPlus: (Int, Int) -> Int = Int::plus


// higher-order function lambda
var sum = {x:Int, y:Int -> x+y}

fun HOCF (a:Int =1, b:Int=2, c: (a:Int,b:Int) -> Unit)  {
  c(a,b)
}