package com.example.iyemon018.kotlinlearningtech0302

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.net.Uri
import android.provider.BaseColumns._ID

/**
 * ウィザードから作成した独自ContentProvider クラスです。
 */
class WordOfTodayProvider : ContentProvider() {

    companion object {
        /**
         * 外部からアクセスしてきた際にURI を振り分けるためのヘルパー機能
         * ContentProvider の実装王道パターンとしてこれを使う。
         */
        private val _uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private const val ROW_DIR = 1
        private const val ROW_ITEM = 2
    }

    init {
        // 振り分けるルートをここで定義する。
        // 以下のURI にアクセスされると[ROW_DIR] の値を返す。
        // content://com.iyemon.wordoftoday2/WordsOfToday
        //
        // 以下のURI にアクセスされると[ROW_ITEM] の値を返す。
        // content://com.iyemon.wordoftoday2/WordsOfToday/#
        // # はワイルドカードとして任意の数値を表す。
        // 外にも"*" を使用することができ、こちらは任意の文字列を表す。
        _uriMatcher.addURI(WordOfTodayContract.AUTHORITY, WordOfTodayContract.TABLE_NAME, ROW_DIR)
        _uriMatcher.addURI(WordOfTodayContract.AUTHORITY, "${WordOfTodayContract.TABLE_NAME}/#", ROW_ITEM)
    }

    private lateinit var _dbHelper: WordOfTodayDbHelper

    /**
     * レコードを削除します。
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    /**
     * 引数で渡されたURI に対応するMIME タイプを取得します。
     */
    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    /**
     * レコードを追加します。
     */
    override fun insert(uri: Uri, values: ContentValues): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    /**
     * 初期化処理を実行します。
     */
    override fun onCreate(): Boolean {
        _dbHelper = WordOfTodayDbHelper(
            context,
            WordOfTodayDbHelper.DB_NAME,
            null,
            WordOfTodayDbHelper.DB_VERSION,
            DatabaseErrorHandler {
                // ここは上記で指定したDB が破損しているときなど
                // なんらかのエラーが発生した場合に実行されるハンドラです。
                val path = it.path
                context.deleteFile(path)
            })

        return true
    }

    /**
     * レコードを取得、検索します。
     */
    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var cursor: Cursor
        when (_uriMatcher.match(uri)) {
            ROW_DIR -> {
                //
                // synchronized は排他ロック
                // こちらは条件に一致したレコードを複数返すためのクエリを実行する。
                //
                synchronized(_dbHelper) {
                    val db = _dbHelper.writableDatabase
                    // 以下でクエリを実行している。
                    // 引数には外部から設定された条件などをそのまま設定する。
                    cursor = db.query(
                        WordOfTodayContract.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                    )
                }
            }
            ROW_ITEM -> {
                synchronized(_dbHelper) {
                    //
                    // こちらはID に一致したレコードを１件返すためのクエリを実行する。
                    //
                    val id = ContentUris.parseId(uri)
                    val db = _dbHelper.writableDatabase
                    cursor = db.query(WordOfTodayContract.TABLE_NAME, projection, _ID, arrayOf("$id"), null, null, null)
                }
            }
            else -> throw IllegalArgumentException("引数のURI が間違っています。")
        }

        return cursor
    }

    /**
     * レコードを更新します。
     */
    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}
