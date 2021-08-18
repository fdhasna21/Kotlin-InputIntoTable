fun inputData(data:String):String{
    print(data)
    return readLine()!!
}

fun typeCheck(stringToShow:String, type:String):String{
    var check:String
    while(true){
        check = inputData(stringToShow)
        if(check.isEmpty()){continue}
        else if(type=="usia"){
            when(check.toIntOrNull()) {
                null -> continue
                else -> break
            }
        }
        else if(type=="yn"){
            if(check=="N" || check=="n" || check=="Y" || check=="y"){
                break
            }
            else{continue}
        }
        else{break}
    }
    return check
}

fun printRow(first:String, second:String, last:String, firstWidth:Int,secondWidth:Int, lastWidth:Int){
    fun spaceBar(spaceLength:Int){
        for(i in 1..spaceLength) print(" ")
    }
    fun separator()=print(" | ")
    fun eachLine(content:String, width:Int){
        separator()
        print(content)
        spaceBar(width)
    }
    eachLine(first,firstWidth)
    eachLine(second,secondWidth)
    eachLine(last,lastWidth)
    eachLine("\n",0)
}

fun printRowSeparator(tableWidth:Int){ //bisa diganti padEnd
    for(i in 1..tableWidth) print("-")
    println()
}

fun showData(data:List<Pair<String,Int>>){
    var maxNameLength=0
    val maxName = data.maxByOrNull{it.first.length}
    if(maxName!=null){maxNameLength = maxName.first.length}
    if(maxNameLength<4){maxNameLength=4}

    var maxAgeLength=0
    val maxAge= data.maxByOrNull{it.second.toString().length}
    if(maxAge!=null){maxAgeLength = maxAge.second.toString().length}
    if(maxAgeLength<4){maxAgeLength=4}

    val tableWidth=maxNameLength+maxAgeLength+14
    printRowSeparator(tableWidth)
    printRow("No","Nama","Usia",
        0,
        maxNameLength-4,
        maxAgeLength-4)
    printRowSeparator(tableWidth)
    data.forEach{
        printRow((data.indexOf(Pair(it.first,it.second))+1).toString(),
            it.first.capitalize(), it.second.toString(), 1,
            maxNameLength-it.first.length,
        maxAgeLength-it.second.toString().length)
    }
    printRowSeparator(tableWidth)
}

fun main() {
    var id=0
    var name = listOf<String>()
    var age = listOf<Int>()
    var person: List<Pair<String,Int>>
    while(true){
        id++
        println("========== Data ke-$id ==========")
        name = name + typeCheck("Masukkan nama : ", "nama")
        age = age + typeCheck("Masukkan usia : ", "usia").toInt()
        person = name.zip(age)
        val yn = typeCheck("(Y/N) ","yn")
        if(yn=="N" || yn=="n"){
                println("===============================\n")
                break}
        else if(yn=="Y" || yn=="y"){continue}
    }
    println("Hasil Akhir :")
    //println(person) //as list of pairs
    showData(person) //as table
}