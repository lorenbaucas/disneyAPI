package com.example.disney.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAccess extends SQLiteOpenHelper {
    public DBAccess(Context context){
        super(context, "usersDB", null, 1);
    }

    //Creo la tabla de los usuarios con sus datos
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT)");
    }

    //No es necesario actualizar la aplicación así que lo dejo así por si lo cambio o quiero borrar todos los datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    //Método para insertar los datos a nuestra base de datos
    public boolean insertData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //Añadimos el usuario y la contraseña a nuestra base de datos
        values.put("username", username);
        values.put("password", password);

        //Si se han añadido correctamente devolverá un true, sino será un false
        long result = db.insert("users", null, values);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    //Método para comprobar si ya existe el nombre de usuario introducido o por si lo ha escrito mal
    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        //Creamos un cursor que recorre todos los nombres de usuario de nuestra base de datos, si alguno
        //coincide con el introducido devolverá un true, y si no existía devolverá un false
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=?", new String[] {username});
        if(c.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    //Método para comprobar si la contraseña introducida es correcta
    public boolean checkPassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        //Creamos un cursor que recorre los usuarios con sus contraseñas y si coinciden con los introducidos
        //devolverá un true, en caso contrario devolverá un false
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[] {username, password});
        if(c.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}