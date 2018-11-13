package com.example.iyemon018.kotlinlearningtech0301

import android.content.ContentUris
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cursor = getImage() ?: return

        if (cursor.moveToFirst()) {
            // １．各列のインデックスを取得する。
            val idColumnNum = cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID)
            val titleColumnNum = cursor.getColumnIndex(MediaStore.Images.ImageColumns.TITLE)
            val dateTakenColumnNum = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN)

            // ２．取得したインデックスをもとに該当データを取得する。
            // cursor.getXXX とすることで指定したカーソル インデックスのデータを取得するらしい。
            // このインデックスとは列インデックスのこと。
            val id = cursor.getLong(idColumnNum)
            val title = cursor.getString(titleColumnNum)
            val dateTaken = cursor.getLong(dateTakenColumnNum)
            val imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)

            // ３．データをView に設定する。
            val titleTextView = findViewById<TextView>(R.id.title_textView)
            val imageView = findViewById<ImageView>(R.id.imageView)
            val dateTakenTextView = findViewById<TextView>(R.id.date_taken_textView)

            titleTextView.setText(title)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = dateTaken
            val dateTimeText = DateFormat.format("yyyy/MM/dd(E) kk:mm:ss", calendar).toString()
            dateTakenTextView.setText("撮影日時:${dateTimeText}")
            imageView.setImageURI(imageUri)

            Toast.makeText(this, "Got Cursor.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Not found Cursor.", Toast.LENGTH_LONG).show()
        }

        cursor.close()
    }

    private fun getImage(): Cursor? {
        val contentResolver = getContentResolver()
        var queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        // 以下は取得する列名を定義している。
        val projection = arrayOf(
            MediaStore.Images.ImageColumns._ID
            , MediaStore.Images.ImageColumns.TITLE
            , MediaStore.Images.ImageColumns.DATE_TAKEN
        )
        val sortOrder = "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"

        //
        // queryUri の情報を引き継ぐ新たなURI を.buildUpon() で構築している。
        // appendQueryParameter() は第一引数に指定したキーと第二引数の値をクエリのパラメータとして構築する。
        // つまり以下の場合は"limit=1" のようになる。
        // 最後に.build() を呼び出すことでクエリを確定する。
        //
        queryUri = queryUri.buildUpon().appendQueryParameter("limit", "1").build()

        return contentResolver.query(queryUri, projection, null, null, sortOrder)
    }
}
