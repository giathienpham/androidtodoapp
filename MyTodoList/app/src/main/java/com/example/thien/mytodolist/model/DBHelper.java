package com.example.thien.mytodolist.model;

/**
 * Created by Thien on 2/5/2017.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ToDoDB.db";
    public static final String TODO_TABLE_NAME = "todo";
    public static final String TODO_COLUMN_ID = "_id";
    public static final String TODO_COLUMN_TITLE = "title";
    public static final String TODO_COLUMN_PRIORITY = "priority";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table todo(id integer primary key autoincrement, title text, priority text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("dropped");
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS todo");
        onCreate(db);
    }


    public boolean insertTodo (String title, String priority) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

//        contentValues.put("_id", Math.floor(Math.random()*28198920));
        if (title.equals("")){
            title = "untitled";
        }
        contentValues.put("title", title);
        contentValues.put("priority", priority);

        db.insert("todo", null, contentValues);
        return true;
    }

    public ToDoItem getTodo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =   db.rawQuery("SELECT * FROM todo WHERE _id = '"+id+"'", null);
        ToDoItem toDoItem = new ToDoItem(res.getInt(res.getColumnIndex("_id")), res.getString(res.getColumnIndex("title")), res.getString(res.getColumnIndex("priority")));
        System.out.println(toDoItem.getId());
        System.out.println(toDoItem.getTitle());
        System.out.println(toDoItem.getPriority());


        return toDoItem;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TODO_TABLE_NAME);
        return numRows;
    }

    public boolean updateTodo (int id, String title, String priority) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("priority", priority);
        System.out.println(title);
        System.out.println(priority);
        System.out.println(id);
        int row = db.update("todo", contentValues, "_id = ?", new String[]{Integer.toString(id)});

        System.out.println(row + "effectd");
        return true;
    }

    public Integer deleteTodo (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("todo",
                "_id = ? ",
                new String[]{Integer.toString(id)});
    }

    public Integer deleteAllTodo () {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("todo",
                null,
                null);
    }

    public ArrayList<ToDoItem> getAllTodos() {
        ArrayList<ToDoItem> array_list = new ArrayList<ToDoItem>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from todo", null );
        res.moveToFirst();
        ToDoItem toDoItem = null;
        while(res.isAfterLast() == false){
            toDoItem = new ToDoItem(res.getInt(res.getColumnIndex(TODO_COLUMN_ID)), res.getString(res.getColumnIndex(TODO_COLUMN_TITLE)),res.getString(res.getColumnIndex(TODO_COLUMN_PRIORITY)));
            array_list.add(toDoItem);
            res.moveToNext();
        }
        return array_list;
    }
}