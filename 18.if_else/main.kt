fun main() {
    
    // is else like js style 

    var num = 20;
    if(num>10){
        println("Im geter then 10");
    }

    var num2 = 11;
    
    if(num2 > 20){
        println("I'm gerter then 20")
    }else{
        println("I'm less then 20")
    }

    var num3 = 25;
    if(num3 > 30){
        println("I'm geter then 30");
    }else if(num3 > 20){
        println("I'm geter then 20");
    }else{
        println("I'm less then 20");
    }

    // sort condition 
    var num12 = 35;
    var res =  if(num12 > 30)  "I'm geter then 30" else "I'm less then then 30"
    println(res);
}