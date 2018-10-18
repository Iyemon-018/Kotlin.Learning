//
// for ループの使用
//
fun main(args: Array<String>) {
    val lengthFor = getLengthToFor(arrayOf("Hello ", ", ", "World", "!"))
    println("${lengthFor[0]}, ${lengthFor[1]}, ${lengthFor[2]}, ${lengthFor[3]}")

    val lengthFor2 = getLengthToFor2(arrayOf("Hello ", "Kotlin", "!"))
    println("${lengthFor2[0]}, ${lengthFor2[1]}, ${lengthFor2[2]}")

    var lengthWhile = getLengthToWhile(arrayOf("Loop", "is", "While"))
    println("${lengthWhile[0]}, ${lengthWhile[1]}, ${lengthWhile[2]}")
    
    var lengthDoWhile = getLengthToWhile(arrayOf("DoWhile", "Loop", "is", "Same", "to", "C#!"))
    println("${lengthDoWhile[0]}, ${lengthDoWhile[1]}, ${lengthDoWhile[2]}, ${lengthDoWhile[3]}, ${lengthDoWhile[4]}, ${lengthDoWhile[5]}")
}

fun getLengthToFor(textArray : Array<String>) : Array<Int> {
    // 配列の初期化は以下の構文で実行する。
    // Array<型>(要素数, 初期化処理(ラムダ式でOK))
    // また、配列の要素数は 配列.size で取得することができる。
    // 要素が決まっていれば、以下の方法でも配列を初期化可能。
    // val array = arrayOf(1, 2, 3)
    // nullable 型の配列は arrayOfNulls(...) となる。
    var result = Array<Int>(textArray.size, {0})
    var index = 0

    // for 分は for (要素 in 配列) の形式で記載する。
    // C# の foreach と同じ感じ。
    for (i in textArray) {
        // 配列へのアクセスはほかの高級言語と同じ。
        result[index] = i.length
        index += 1
    }
    return result
}

fun getLengthToFor2(textArray : Array<String>) : Array<Int> {
    var result = Array<Int>(textArray.size, {0})
    // 以下は、C# でいうところの for 文に近い。
    // 配列.indices とすることで、対象の配列のインデックスをイテレータとして提供する。
    // つまり、i にはインデックスが入る。
    // インデックスの参照有木ならこっちのほうが短くていい。
    // for (i in textArray.indices) {
    //     result[i] = textArray[i].length
    // }
    // return result

    // ほかの方法としてライブラリ関数の 配列.whithIndex() も使える。
    // これは、C# だと以下と同じような感じ。
    // foreach ((int i, string v) In textArray.Select((i, x) => (i, x)).ToArray()) {}
    // withIndex() はインデックスと値（タプル型）を返す関数。
    for ((i, v) in textArray.withIndex()) {
        result[i] = v.length
    }
    return result
}

fun getLengthToWhile(textArray : Array<String>) : Array<Int> {
    var result = Array<Int>(textArray.size, {0})
    // while はC# と同じ。
    // while (繰り返し条件<true の間繰り返す>)
    var i = 0
    while (i < textArray.size) {
        result[i] = textArray[i++].length
    }
    return result
}

fun getLengthToDoWhile(textArray : Array<String>) : Array<Int> {
    var result = Array<Int>(textArray.size, {0})
    var i = 0
    // do while もC# と同じ。
    do {
        result[i] = textArray[i++].length
    }
    while (i < textArray.size)
    return result
}