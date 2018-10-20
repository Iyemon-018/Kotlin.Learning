//
// コレクションとラムダ式
// http://dogwood008.github.io/kotlin-web-site-ja/docs/reference/lambdas.html
//
fun main(args: Array<String>) {
    // ラムダ式はC# のLINQ と同じように描くことができる。
    val names = listOf("Smith", "John", "Edword")
    // C# と同様にメソッドチェーンで書くことも可能。
    // 違うのは、「=> が ->」「(x ...) が {x ...}」の部分。
    names.map{x -> x.toUpperCase()}.forEach{x -> println(x)}

    // また、ラムダ式内の引数と -> は省略することもできる。
    // その際、ラムダ式内で使用する引数名は"it"となる。
    // これは"単一パラメータの暗黙の名前"といい、Kotlin の言語レベルの仕様である。
    // ついでに改行してもOK。
    names.map{it.toUpperCase()}
         .forEach{println(it)}

    action({println("executing action!!!!")})
    action2({println("executing ${it} action!!!!")})
    func({"executing fun."})
    func2({"executing fun${it}."})
}

// 引数に実行したいメソッドを渡すこともできる。
// 例えば以下の場合は、引数が戻り値のないメソッド渡しということになる
// C# で言えば、Action となる
fun action(body : () -> Unit) {
    println("Begin Action.")
    body()
    println("Finish Action.")
}

// C# で言うところの Action<String> を引数で指定する場合は以下のようになる。
fun action2(body : (String) -> Unit) {
    println("Begin Action2.")
    // 呼び出す場合も引数で渡すだけ。
    body("Call")
    println("Finish Action2.")
}

// C# で言うところの Func<String> はこう。
fun func(body : () -> String) {
    println("Begin Func.")
    val b = body()
    println(b)
    println("Finish Func.")
}

// C# で言うところの Func<int, String> はこう。
fun func2(body : (Int) -> String) {
    println("Begin Func2.")
    val b = body(2)
    println(b)
    println("Finish Func2.")
}