//
// 型チェックと自動キャストの使用
//
fun main(args: Array<String>) {
    val length = getStringLength("Hello World!")
    println(length)
}

fun getStringLength(obj : Any) : Int? {
    if (obj is String) {
        // 上記のように is 型 で型チェックを実行した時点で比較対象の変数はキャストされる。
        // つまり、このスコープ内では obj はString 型になっている。
        return obj.length
    }

    return null

    // あるいはこの書き方でもOK.
    // if (obj !is String) return null;
    // return obj.length

    // 自動キャストの仕組み上、以下のように分岐条件式の左辺で型チェックすると
    // 右辺の条件式ではチェックした型にキャストされる。
    // よって以下の構文は正解。
    // if (obj is String && obj.length > 0) return obj.length;
    // return null;
}