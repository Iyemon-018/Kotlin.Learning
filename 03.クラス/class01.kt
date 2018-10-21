//
// クラスについて
//
fun main(args: Array<String>) {
    val person = Person("Smith")
    println(person.FirstName)
    person.Type = 2
    println("person type is ${person.Type}")
    println("Default Name is ${Person.DEFAULT_NAME}")

    val child = Person("Underson")
    person.child = child
    println("child is ${child.FirstName} ${child.LastName}")
}

// クラスはクラス名の隣にコンストラクタの定義を記載する。
// 以下の場合はString 型のfirstName という引数ひとつをもつ
// コンストラクタを定義しているということ。
// ただし、コンストラクタは引数の定義のみなので、コードを含めることができない。
class Person(firstName: String) {
    //
    // companion object はクラスに一つだけ宣言できる。
    // static メソッドのようなもの。
    // Factory パターンとカニつかうっぽい？ここはいまいち用途が不明。
    // 定数(const) のようにstatic なメンバを初期化するのに使用する。
    // また、companion object を呼び出す場合は、クラスの型.create() を実行する。
    //
    companion object {
        const val DEFAULT_NAME:String = "June"
    }

    // 初期化コードは init ブロック内で実行する。
    init {
        // コンストラクタで指定した引数はクラス内であればどこでも呼び出せる。
        // init ブロック内やプロパティにも使用可能。
        println("Called Person init.firstName is ${firstName}")
    }

    // プロパティについて
    // https://dogwood008.github.io/kotlin-web-site-ja/docs/reference/properties.html
    // 
    // ・val : 読み取り専用（イミュータブル）であることを表す。
    // ・var : 可変（ミュータブル）であることを表す。
    //
    // val, var にかかわらず、プロパティは宣言時で初期化が必要になる。
    val FirstName = firstName
    var LastName: String = ""

    // getter と setter
    // Kotlin はC# と同様にプロパティに get / set を持つ。
    // これらは省略することが可能となっている。
    // 以下の場合は暗黙的に get/ set を持っていて、初期値に 1 が設定されているだけで、
    // プロパティを書き換えると書き換えた値を取得することができる。
    var Type: Int = 1

    // val（読み取り専用）の場合はget() のみ宣言することができる。
    val isEmpty:Boolean
    get() = false

    // set は引数が必ず必要になるため set(引数名) {} という構文になる。＝メソッドと同じ
    // 引数名は多くの場合、value だが変えても問題はない。
    var LastNameToUpper: String
    get() = LastName.toUpperCase()
    set(value) {LastName = value}

    //
    // バッキングフィールド
    // 
    // Kotlin はクラス内にフィールドを持つことができない。
    // ただし、プロパティのバッキングフィールドについては例外として field 識別子を使用した自動バッキングフィールドを提供する。
    var counter: Int = 0
        get() = field
        set(value) {
            // field はプロパティのアクセサのみで使用することができる。
            field = value
        }

    // どうしてもクラスにフィールドを持ちたい場合は、バッキングプロパティを使用する。
    // 単純にプロパティを private にすればクラス内からのみアクセス可能になるのでこれを使用する。
    private var _talbe:Map<String, Int>? = null

    //
    // 遅延初期化プロパティ
    //
    // 非null 型として宣言されたプロパティはコンストラクタ、もしくは宣言時に初期化する必要がある。
    // しかし、これら以外のタイミングで初期化が必要な場合（初期値が確定しない場合）、は遅延初期プロパティ(lateinit)のしくみを使用する。
    // ただし、遅延初期化プロパティはプリミティブ型以外の場合に使用することができる。
    // また、遅延初期化プロパティは var 可変が必須である。
    //
    lateinit var child:Person
}