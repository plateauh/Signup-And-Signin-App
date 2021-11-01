package com.najed.signupandsigninapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "users.db", null, 1) {

    private var sqliteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table Users (name text, mobile text, location text, password text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun storeUser(user: User, password: String): Boolean {
        val contentValues = ContentValues()
        contentValues.put("name", user.name)
        contentValues.put("mobile", user.mobile)
        contentValues.put("location", user.location)
        contentValues.put("password", password)
        val status = sqliteDatabase.insert("Users", null, contentValues)
        return status != -1L
    }

    fun authenticateUser(mobile: String, password: String): Boolean {
        val cursor: Cursor = sqliteDatabase.query("Users", null, "mobile=? AND password=?", arrayOf(mobile, password),
            null, null, null)
        return cursor.count > 0
    }

    fun getUser(mobile: String): User {
        val user = User("", "", "")
        val cursor: Cursor = sqliteDatabase.query("Users", null, "mobile=?", arrayOf(mobile), null, null, null)
        cursor.moveToFirst()
        user.name = cursor.getString(cursor.getColumnIndex("name"))
        user.mobile = cursor.getString(cursor.getColumnIndex("mobile"))
        user.location = cursor.getString(cursor.getColumnIndex("location"))
        return user
    }
}