package com.example.iyemon018.kotlinlearningtech0302

import android.content.ContentResolver
import android.net.Uri
import android.provider.BaseColumns

/**
 * [WordOfTodayProvider] に外部からアクセスするための公開インターフェースです。
 * 外部からプロバイダーへアクセスするための情報をここに定義します。
 * Created by iyemon018 on 2018/11/14.
 */
interface WordOfTodayContract {

    companion object {
        /**
         * [WordOfTodayProvider] へのアクセス識別子
         */
        const val AUTHORITY = "com.iyemon.wordoftoday2"
        /**
         * テーブル名
         */
        const val TABLE_NAME = "WordsOfToday"
        /**
         * [WordOfTodayProvider] へアクセスするためのURI
         */
        val CONTENT_URI = Uri.parse("${ContentResolver.SCHEME_CONTENT}://${AUTHORITY}/${TABLE_NAME}")

        const val MIME_TYPE_DIR = "vnd.android.cursor.dir/${AUTHORITY}.${TABLE_NAME}"
        const val MIME_TYPE_ITEM = "vnd.android.cursor.item/${AUTHORITY}.${TABLE_NAME}"
    }

    /**
     * [WordOfTodayContract.TABLE_NAME] テーブルの持つ列情報インターフェースです。
     */
    interface WordsOfTodayColumns : BaseColumns {
        companion object {
            /**
             * 名前
             */
            const val NAME = "name"
            /**
             * 一言
             */
            const val WORDS = "words"
            /**
             * 日付
             */
            const val DATE = "date"
        }
    }
}