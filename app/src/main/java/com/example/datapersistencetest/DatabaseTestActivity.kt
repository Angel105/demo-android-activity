package com.example.datapersistencetest

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.databinding.ActivityDatabaseTestBinding

class DatabaseTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatabaseTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDatabaseTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        binding.createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }

        binding.addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // insert the first row of data
            db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)", arrayOf("Robinson Crusoe", "Daniel Defoe", "144", "9.65"))
            // insert the second row of data
            db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)", arrayOf("De Drie Musketiers", "Alexandre Dumas", "355", "4.9"))
        }

        binding.updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.execSQL("update Book set price = ? where name = ?", arrayOf("4.99", "De Drie Musketiers"))
        }

        binding.deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.execSQL("delete from Book where pages < ?", arrayOf("300"))
        }

        binding.queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // query all data in Book table
            // val cursor = db.query("Book", null, null, null, null, null, null)
            val cursor = db.rawQuery("select * from Book", null)
            if (cursor.moveToFirst()) {
                do {
                    // Get all the data from cursor object and print
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("DatabaseTestActivity", "Book name is $name, Author is $author, Pages is $pages, Price is $price")
                } while (cursor.moveToNext())
            }

            cursor.close()
        }

        binding.replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction() // start transaction
            try {
                db.delete("Book", null, null)

                val values = ContentValues().apply {
                    put("name", "La Dame aux Camélias")
                    put("author", "Alexandre Dumas")
                    put("pages", 725)
                    put("price", 6.5)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful() // successful transaction

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction() // end transaction
            }
        }


        supportActionBar?.hide()
    }
}