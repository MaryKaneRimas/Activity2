package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "record_database";
    //Database Table name
    private static final String TABLE_NAME = "RECORD";
    //Table columns
    public static final String ID = "id";
    public static final String STUDENTID = "studentid";
    public static final String FULLNAME = "fullname";
    public static final String ADDRESS = "address";
    private SQLiteDatabase sqLiteDatabase;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + STUDENTID + " TEXT NOT NULL,"+ FULLNAME +" TEXT NOT NULL," + ADDRESS +" TEXT NOT NULL);";
    //Constructor
    public DatabaseHelperClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add Record Data
    public void addRecord(RecordModelClass recordModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.STUDENTID, recordModelClass.getStudentid());
        contentValues.put(DatabaseHelperClass.FULLNAME, recordModelClass.getFullname());
        contentValues.put(DatabaseHelperClass.ADDRESS, recordModelClass.getAddress());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<RecordModelClass> getRecordList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<RecordModelClass> storeRecord = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String studentid = cursor.getString(1);
                String fullname = cursor.getString(2);
                String address = cursor.getString(3);
                storeRecord.add(new RecordModelClass(id,studentid,fullname,address));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeRecord;
    }

    public void updateRecord(RecordModelClass recordModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.STUDENTID, recordModelClass.getStudentid());
        contentValues.put(DatabaseHelperClass.FULLNAME, recordModelClass.getFullname());
        contentValues.put(DatabaseHelperClass.ADDRESS, recordModelClass.getAddress());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(recordModelClass.getId())});
    }

    public void deleteRecord(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}
