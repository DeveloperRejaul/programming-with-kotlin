fun main() {
    println("Hello World");
    println("Rezaul Karim")


    // call function
    myFunction();

    // call multiple paramitar function 
    myFunc2("Rejaul", 20)


    // call sort function 
   val total =  sortFnc(20, 100);
   println(total);

   // call return function call 
   val totalNum = returnFnc(100, 2000);
   println(totalNum)


}

// create function 
fun myFunction() {
    println("I just got executed!")
}

// create function with resive multiple paramitars 
fun myFunc2  (name:String, age:Int){

    println("Hello " + name);
    println("Your old " + age);
}

// sort syntex function with return  
fun sortFnc(num1:Int, num2:Int) = num1 + num2  


// function return 
fun returnFnc ( param1:Int, param2:Int ):Int {
    return (param1 + param2)
}


