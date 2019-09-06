package com.example.testcursoradapter

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessControlContext

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "Users"
val COL_NAME = "name"
val COL_AGE = "age"
val COL_ID = "id"

class DataBaseHandler (var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_AGE +" INTEGER)";

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {



    }

    fun insertData(user: User) {

        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, user.getName())
        cv.put(COL_AGE, user.getAge())
        var result = db.insert(TABLE_NAME, null, cv)
        if(result == -1.toLong()) {

            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

        }else {

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }



    }

    fun readData(): MutableList<User> {

        var list: MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()) {

            do {

                var user = User()
                user.setId(result.getString(result.getColumnIndex(COL_ID)).toInt())
                user.setName(result.getString(result.getColumnIndex(COL_NAME)))
                user.setAge(result.getString(result.getColumnIndex(COL_AGE)).toInt())
                list.add(user)

            }while (result.moveToNext())

        }

        return list

    }
    fun updateData() {

        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()) {

            do {

                var cv = ContentValues()
                cv.put(COL_AGE,(result.getInt(result.getColumnIndex(COL_AGE))+1))

                db.update(TABLE_NAME, cv, "$COL_ID=? AND $COL_NAME=?",
                    arrayOf(result.getString(result.getColumnIndex(COL_ID)), result.getString(result.getColumnIndex(
                        COL_NAME))))

            }while (result.moveToNext())

        }


    }

    fun deleteData() {

        val db = this.writableDatabase

        db.delete(TABLE_NAME, null, null)
        db.close()

    }


}