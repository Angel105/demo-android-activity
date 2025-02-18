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
            /*val values1 = ContentValues().apply {
                // construct the first row of data
                put("name", "Robinson Crusoe")
                put("author", "Daniel Defoe")
                put("pages", 144)
                put("price", 9.65)
            }*/
            val values1 = cvOf("name" to "Robinson Crusoe", "author" to "Daniel Defoe", "pages" to 144, "price" to 9.65)
            // insert the first row of data
            db.insert("Book", null, values1)
            /*val values2 = ContentValues().apply {
                // construct the second row of data
                put("name", "De Drie Musketiers")
                put("author", "Alexandre Dumas")
                put("pages", 355)
                put("price", 4.9)
            }*/
            val values2 = cvOf("name" to "De Drie Musketiers", "author" to "Alexandre Dumas", "pages" to 355, "price" to 4.9)
            // insert the second row of data
            db.insert("Book", null, values2)
        }

        binding.updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 5.45)
            db.update("Book", values, "name = ?", arrayOf("Robinson Crusoe"))
        }

        binding.deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages < ?", arrayOf("300"))
        }

        binding.queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // query all data in Book table
            val cursor = db.query("Book", null, null, null, null, null, null)
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