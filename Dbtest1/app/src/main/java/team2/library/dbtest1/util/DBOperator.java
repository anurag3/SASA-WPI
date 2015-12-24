package team2.library.dbtest1.util;

/**
 * Created by Anurag on 9/27/2015.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import team2.library.dbtest1.constant.DBConstant;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Class to manipulate tables & data
 * Uses singleton pattern to create single instance
 */

public class DBOperator {


        public static String c;
        private static DBOperator instance = null;
        private SQLiteDatabase db;

        public DBOperator()
        {
            //path of database file
            String path = DBConstant.DATABASE_PATH + "/" + DBConstant.DATABASE_FILE;
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        }
        /*
         * Singleton Pattern
         * Why should we avoid multiple instances here?
         */
        public static DBOperator getInstance()
        {
            if (instance==null) instance = new DBOperator();
            return instance;
        }
        /**
         * Copy database file
         * From assets folder (in the project) to android folder (on device)
         */
        public static void copyDB(Context context) throws IOException,FileNotFoundException{
            String path = DBConstant.DATABASE_PATH + "/" + DBConstant.DATABASE_FILE;
            File file = new File(path);
            if (!file.exists()){
                DBOpenHelper dbhelper = new DBOpenHelper(context);
                dbhelper.getWritableDatabase();
                InputStream is = context.getAssets().open(DBConstant.DATABASE_FILE);
                OutputStream os = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer))>0){
                    os.write(buffer, 0, length);
                }
                is.close();
                os.flush();
                os.close();
            }
        }


    //check password
    public String searchPass(String uname){
        //db = this.getReadableDatabase();
        String query = "select st_id,st_email,st_pass from STUDENT";
        //System.out.println("QUERY "+query);
        //db.execSQL(query);
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b="not found";


        if(cursor.moveToFirst()){
            do {
                c = cursor.getString(0);
                a = cursor.getString(2);
                System.out.println("value of column a "+a);
                System.out.println("value of uname "+uname);
                if(a.equals(uname))
                {    System.out.println("I'm inside IF loop ");
                    b=cursor.getString(2);
                    System.out.println("value of column b "+b);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        System.out.println(c);
        return b;

    }
        /**
         * execute sql without returning data, such as alter
         * @param sql
         */
        public void execSQL(String sql) throws SQLException
        {
            db.execSQL(sql);
        }
        /**
         * execute sql such as update/delete/insert
         * @param sql
         * @param args
         * @throws SQLException
         */
        public void execSQL(String sql, Object[] args) throws SQLException
        {
            db.execSQL(sql, args);
        }
        /**
         * execute sql query
         * @param sql
         * @param selectionArgs
         * @return cursor
         * @throws SQLException
         */
        public Cursor execQuery(String sql,String[] selectionArgs) throws SQLException
        {
            return db.rawQuery(sql, selectionArgs);
        }
        /**
         * execute query without arguments
         * @param sql
         * @return
         * @throws SQLException
         */
        public Cursor execQuery(String sql) throws SQLException
        {
            return this.execQuery(sql, null);
        }
        /**
         * close database
         */
        public void closeDB()
        {
            if (db!=null) db.close();
        }
    }
