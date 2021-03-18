package com.curious.customlistwithsqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public Context context;
    private static final String DATABASE_NAME = "member.db";
    private static  final String TABLE_NAME = "member_table";
    private static  final String NAME_COL = "name";
    private static  final String ADDRESS_COL = "address";
    private static  final String ID_COL = "id";

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE "+TABLE_NAME+" ( "+ ID_COL +" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME_COL+" TEXT, "+ADDRESS_COL+" TEXT)";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String SELECT_ALL_QUERY = "SELECT * FROM "+TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }

    public void addOne(Member member){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COL, member.getName());
        contentValues.put(ADDRESS_COL, member.getAddress());
        database.insert(TABLE_NAME, null, contentValues);
    }

    @SuppressLint("Recycle")
    public boolean deleteOne(Member member){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from " + TABLE_NAME + " where " + ID_COL + " = ?", new String[]{String.valueOf(member.getId())});
        if (cursor.getCount() > 0){
            long result = database.delete(TABLE_NAME, ID_COL+"= ?", new String[]{String.valueOf(member.getId())});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    public ArrayList<Member> getAllMember(){
        ArrayList<Member> memberArrayList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(SELECT_ALL_QUERY, null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(ID_COL));
                String name = cursor.getString(cursor.getColumnIndex(NAME_COL));
                String address = cursor.getString(cursor.getColumnIndex(ADDRESS_COL));
                memberArrayList.add(new Member(name, address, id));
            }while (cursor.moveToNext());
        }
        return memberArrayList;
    }
}
