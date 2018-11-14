package com.example.iyemon018.kotlinlearningtech0302

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 * ウィザードから作成した独自ContentProvider クラスです。
 */
class WordOfTodayProvider : ContentProvider() {
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
        TODO("Implement this to initialize your content provider on startup.")
    }

    /**
     * レコードを取得、検索します。
     */
    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        TODO("Implement this to handle query requests from clients.")
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
