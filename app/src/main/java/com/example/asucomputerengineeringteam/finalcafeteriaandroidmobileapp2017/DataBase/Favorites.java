package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.MenuItemDataModel;

import java.util.ArrayList;

/**
 * Created by Esraa Hosny on 6/23/2017.
 */

public class Favorites extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME ="Favorites.db";
    public static final String TABLE_NAME ="favoriteTable";
    private static final int DATABASE_VERSION =100;
    public static final String COL_1 ="ID";
    public static final String COL_2 ="PRICE";
    public static final String COL_3 ="NAME";
    public static final String COL_4= "DESCRIPTION";
    public static final String COL_5= "TYPE";
    public static final String COL_6 ="ALTERNATETEXT";
    public static final String COL_7= "IMAGEDATA";


    String Create_Table = "CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
       COL_2 + " DOUBLE ," + COL_3 + " TEXT ," + COL_4 + " TEXT ," + COL_5 + " TEXT ," + COL_6  + " TEXT ,"+ COL_7 +");";



    String Drop_Table= "DROP TABLE IF EXISTS " + TABLE_NAME;


    public Favorites(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Table);
        //Toast.makeText(context,"database created",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Drop_Table);
      //  Toast.makeText(context,"database upgraded",Toast.LENGTH_SHORT).show();
        onCreate(db);
    }
    public boolean insertData(String id ,String price , String name , String description , String type , String alternate_text , String image )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Integer.parseInt(id));
        contentValues.put(COL_2,Double.parseDouble(price));
        contentValues.put(COL_3 , name);
        contentValues.put(COL_4 , description);
        contentValues.put(COL_5 , type);
        contentValues.put(COL_6 ,alternate_text);
        contentValues.put(COL_7 ,image);
        int result = (int) db.insert(TABLE_NAME,null,contentValues);
        Toast.makeText(context,"menuitem id " + result + " is added" , Toast.LENGTH_SHORT).show();
        if(result == -1)
            return false;
        else
            return true;

    }
    public ArrayList<MenuItemDataModel> GetAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<MenuItemDataModel> favorite_menu_item = new ArrayList<>();
        Cursor menu_cursor = db.rawQuery("Select * from " + TABLE_NAME , null );
        while(menu_cursor.moveToNext())
        {
            MenuItemDataModel menuItemDataModel = new MenuItemDataModel();
            menuItemDataModel.setId(String.valueOf(menu_cursor.getInt(0)));
            menuItemDataModel.setPrice(menu_cursor.getString(1));
            menuItemDataModel.setName(menu_cursor.getString(2));
            menuItemDataModel.setDescription(menu_cursor.getString(3));
            menuItemDataModel.setType(menu_cursor.getString(4));
            menuItemDataModel.setAlternatetext(menu_cursor.getString(5));
            menuItemDataModel.setImageData(menu_cursor.getString(6));

            favorite_menu_item.add(menuItemDataModel);
        }
        return favorite_menu_item;
    }
    public void delete_menu_item(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int m= db.delete(TABLE_NAME,COL_1 +"="+ID,null);
        if (m==0){
            Toast.makeText(context, "favorites list doesn't contain this menuitem to be deleted", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
        db.close();
    }


    public boolean search_menu_item(String ID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_1};
        String []args = new String[] { ID };
        Cursor cursor = db.query(TABLE_NAME, columns, COL_1 + "=?",args, null, null, null, null);

        if (cursor.getCount()== 0)
            return false;
        else
            return true;
    }
}
