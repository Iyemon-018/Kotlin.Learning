// パッケージの構文

// package パッケージ名

// この辺はJava と同じ。
// パッケージ名とディレクトリが一致する必要はない。
// 参考 : http://dogwood008.github.io/kotlin-web-site-ja/docs/reference/packages.html
package iyemon018.com.learning

fun main(args : Array<String>) {
    var z = sum(2, 7)
    var z2 = sum(1, 4)
    // 文字列
    println("z=${z}\nz2=${z2}")

    // ローカル変数は読み取り専用と変更可能な２種類の変数が宣言可能
    // こっちは読み取り専用
    val a = 1
    // こっちは変更可能
    var b = 2
    // a = 3 とかはできない。以下は可能
    b = 3

    // 型の指定
    // ローカル変数は型を指定する方法と暗黙的な宣言の２種類が存在する。
    // 明示的なローカル変数はこんな感じ
    val x1: Int = 1
    // 暗黙的なローカル変数はこんな感じ（ちなみに Int 型になる）
    val x2 = 2

    // また、val の場合は型を指定して宣言→代入 という手順であれば宣言時に値を設定しなくてもいい。
    val v1:String
    v1 = "これはOK"
    println(v1)

    // 文字列テンプレート
    // 文字列に変数を埋め込むことができる。
    // C# の $"{t}" と同じような構文となっている。
    val t = "こんな感じ。"
    println("String template is ${t}")
}

// 関数を定義する。
// 例として Int 型引数２つを加算する関数を定義する。
// 戻り値がある場合は最後に " : 戻り値の型" という形式で型を指定する。
fun sum(x : Int, y : Int) : Int {
    return x + y
}

// 以下は上記と同じ結果になる。
// C# でいうところの int Sum2(int x, int y) => x + y; のような感じっぽい。
// こっちは戻り値の型を指定する必要がない。
fun sum2(x : Int, y : Int) = x + y