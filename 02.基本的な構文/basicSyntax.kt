
fun main(args : Array<String>) {
    val z = max(3, 4)
    println("3 と 4 なら ${z} のほうが大きい。")

    val z2 = max2(5, 0)
    println("6 と 0 なら ${z2} のほうが大きい。")

    val z3 = getNullable(false)
    val z4 = getNullable(false)
    // nullable 型はnull チェックが必要（これはC# とかと同じ）
    if (z3 != null && z4 != null) {
        // nullable 型の変数をnull チェックした時点で非 null 許容型へキャストされる。
        // C# では.Value が必要だったがKotlin は不要。
        // if (z3.HasValue && z4.HasValue) {
        //      var = z3.Value * z4.Value;
        // }
        println(z3 * z4)
    }

    // ちなみにString 型はC# と異なり、非 null 許容型となっている。
    // なので、null を含む場合は以下のようになる。
    val text : String? = "This is nullable String."

    // nullable 型変数の安全な呼び出しはC# と同じで "変数?.メンバ" となっている。
    println(text?.length)

    // !! operator
    // null 許容型変数を以下のように "変数!!.メンバ" と宣言すると、
    // 変数が null の場合、kotlin.KotlinNullPointerException をスローする。
    // 変数が null 以外の場合、メンバの値を返す。
    // これはC# になかった構文。
    /* 例外をスローするのでコメントアウトしておく。
    val textNull : String? = null
    val text2 = textNull!!.length
    */
    // C# だとこんな感じ？
    // string textNull = null;
    // var text2 = (textNull ?? throw new NullPointerException())?.Length;
}

fun max(x : Int, y : Int) : Int {
    // 条件式
    // ほかの言語と同じ感じ
    if (x > y) {
        return x
    } else {
        return y
    }
}

fun max2(x : Int, y : Int) : Int {
    // 条件式の書き方としてこんなこともできる。
    // ちょっと参考演算子っぽい。
    // if () のあとは、かっこがなければ１行すべてが分岐処理となるわけではないっぽい。
    // if () ～ else までが１つの分岐処理となっている。
    return if (x > y) x else y
}

// Null可能値
// データ型? でnullable 型となる。C# と同じ。
fun getNullable(isNull : Boolean) : Int? {
    return if (isNull) null else 109
}