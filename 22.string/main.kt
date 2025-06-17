fun main() {
    println("Hello world")

    var txt = "Hello World";

    // check text length
    println("The length of the txt string is: " + txt.length);

    // convart upercase 
    var upercaseText = txt.uppercase();
    // old way :   var upercaseText = txt.toUpperCase();
    println(upercaseText);

    // convart loarcase 
    var loarcaseText = txt.lowercase()
    // old way : var loarcaseText = txt.toLowerCase();
    println(loarcaseText);

    // find string of index
    var myText = "Please locate where 'locate' occurs!"
    var textIndex = myText.indexOf("locate")
    println(textIndex)
    

    // find text where in myText
    var wareTextIndex = myText.indexOf("where");
    println(wareTextIndex);
    

    // string select by index
    var str = "hello world"
    println(str[2])

    // string concate :way 01
    var str2 = "hello";
    var str3 = "world"
    var str4 = str2 + str3;
    println(str4) 

    // string concate :way 02
    var str5 = "My"
    var str6 = "name";
    var str7 = "Is Rejaul Karim";
    var str8 =  str5.plus(str6).plus(str7);

    println(str8);


    // tamplel string 
    var tem = " $str5 $str6 $str7 "
    println(tem);
};



