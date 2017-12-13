package lifeshare.example.dell.lifeshare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private  static  final String DATABASE_NAME = "blood bank";
    // TABLE 1
    private  static final String TABLE_1 = "blood";
    private static final String KEY_ID="id";
    private static  final String KEY_NAME = "name";
    private static final String KEY_DOB = "dob";
    private static final String KEY_EMAILID = "email";
    private static final String KEY_MOBILENO = "mobile";

    // TABLE 2
    private  static final String TABLE_2 = "donor";
    private static final String KEY_DNAME = "dname";
    private static final String KEY_DCITY = "dcity";
    private static final String KEY_DMOBILENO = "dmob";
    private static final String KEY_DBLOODGROUP = "dbg";
    private static final String KEY_DAREA = "area";

    // CREATE TABLES
    // TABLE 1
    private  static final String CREATE_TABLES_1 ="CREATE TABLE blood (name TEXT COLLATE NOCASE,dob TEXT, email TEXT, mobile TEXT PRIMARY KEY)";
     // TABLE 2
     private  static final String CREATE_TABLES_2 ="CREATE TABLE donor (dname TEXT ASC COLLATE NOCASE, dcity TEXT ASC COLLATE NOCASE,area TEXT ASC COLLATE NOCASE, dmob TEXT PRIMARY KEY,dbg TEXT)";


    public DB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLES_1);
        db.execSQL(CREATE_TABLES_2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS :"+TABLE_1);
        db.execSQL("DROP TABLE IF EXISTS :"+TABLE_2);
    }
    public long addDetails(String ename, String edob, String eemail, String emob)
    {
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
       // cv.put(KEY_ID,i);
        cv.put(KEY_NAME,ename);
        cv.put(KEY_DOB,edob);
        cv.put(KEY_EMAILID,eemail);
        cv.put(KEY_MOBILENO,emob);
        long a = sd.insert(TABLE_1,null,cv);
        //i++;
        return a;
    }
    public String getDetails(String euser,String epass)
    {
        SQLiteDatabase Db = this.getReadableDatabase();
        String i="SELECT * FROM blood WHERE name='"+euser+"'COLLATE NOCASE AND mobile='"+epass+"'";
        //Cursor cursor = Db.query(TABLE_NAME, new String[] {KEY_ID, KEY_NAME, KEY_DOB, KEY_PASSWORD,KEY_MOBILENO },KEY_PASSWORD + "=?"+" AND " + KEY_NAME +"=?", new String[]{ String.valueOf(epass),String.valueOf(euser) }, null, null, null);
        Cursor cursor = Db.rawQuery(i,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return  cursor.getString(3);

    }
    public String getDetailsID(String euser,String epass)
    {
        SQLiteDatabase Db = this.getReadableDatabase();
        Cursor cursor = Db.query(TABLE_1, new String[] {KEY_NAME, KEY_DOB, KEY_EMAILID,KEY_MOBILENO },KEY_MOBILENO + "=?"+" AND " + KEY_NAME +"=?", new String[]{ String.valueOf(epass),String.valueOf(euser) }, null, null, null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return  cursor.getString(3);

    }

    public String getPassword(String emob)
    {
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor cursor = sd.query(TABLE_1, new String[] { KEY_NAME, KEY_DOB, KEY_EMAILID,KEY_MOBILENO }, KEY_EMAILID + "=?", new String[]{ String.valueOf(emob) }, null, null, null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return  cursor.getString(2);

    }
//    public Cursor displayLastCustomer(String id)
//    {
//
//        SQLiteDatabase sd = this.getReadableDatabase();
//        //Cursor c = sd.query(TABLE_NAME, new String[] {KEY_ID, KEY_NAME, KEY_DOB, KEY_PASSWORD,KEY_MOBILENO },KEY_ID + "=?", new String[]{ String.valueOf(id) }, null, null, null);
//        String s="SELECT * FROM blood WHERE id='"+id+"'";
//        Cursor c=sd.rawQuery(s,null);
//        if (c != null)
//        {
//            c.moveToFirst();
//        }
//        return c;
//    }
//    public boolean updatedetails(String id,String name, String dob, String pass, String mob)
//    {
//        SQLiteDatabase sd=this.getWritableDatabase();
//        ContentValues args = new ContentValues();
//        args.put(KEY_ID,id);
//        args.put(KEY_NAME, name);
//        args.put(KEY_DOB, dob);
//        args.put(KEY_PASSWORD, pass);
//        args.put(KEY_MOBILENO, mob);
//        //sd.update(TABLE_NAME, args,KEY_ID +" = "+id, null);
//        sd.update(TABLE_NAME,args,KEY_ID+"="+id,null);
//        //int i =  sd.update(TABLE_NAME, args, null , null);
//
//
//        return true;
//    }
    public void delete(String eid)
    {

        SQLiteDatabase sd = this.getWritableDatabase();
        String s = "DELETE FROM blood WHERE id = '"+eid+"'";
        sd.execSQL(s);
    }

    public List<Admin> getAllDetails()
    {
        List<Admin> bloodList = new ArrayList<Admin>();
        // Select All Query

        SQLiteDatabase db = this.getReadableDatabase();
        String s = "SELECT * FROM blood";
        Cursor cursor = db.rawQuery(s,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                Admin bl = new Admin();
                bl.setName(cursor.getString(0));
                bl.setDob(cursor.getString(1));
                bl.setPass(cursor.getString(2));
                bl.setMob(cursor.getString(3));
                // Adding contact to list
                bloodList.add(bl);
            } while (cursor.moveToNext());
        }


        return bloodList;
    }
//
//    public void deleteUsers(String mob)
//    {
//
//        SQLiteDatabase sd = this.getWritableDatabase();
//        String s = "DELETE FROM blood WHERE mobile = '"+mob+"'";
//        sd.execSQL(s);
//    }
//    public  String donorCheck(String nmob)
//    {
//        SQLiteDatabase sd = this.getReadableDatabase();
//
//        Cursor c = sd.query(TABLE_NAME,new String[]{KEY_ID,KEY_NAME,KEY_DOB,KEY_PASSWORD,KEY_MOBILENO},KEY_MOBILENO + "=?",new String[]{ String.valueOf(nmob) },null,null,null);
//        if(c!=null)
//        {
//            c.moveToFirst();
//        }
//        return c.getString(4);
//    }
// TABLE 2

    public long addDonor(String name, String city,String area, String mob, String bg)
    {
        SQLiteDatabase sd = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_DNAME,name);
        cv.put(KEY_DCITY,city);
        cv.put(KEY_DAREA,area);
        cv.put(KEY_DMOBILENO,mob);
        cv.put(KEY_DBLOODGROUP,bg);
        long a = sd.insert(TABLE_2,null,cv);
        return  a;
    }
    public  String getDonor(String bg,String city,String earea)
    {
        SQLiteDatabase sd = this.getReadableDatabase();
        String i="SELECT * FROM donor WHERE dbg='"+bg+"' AND dcity='"+city+"'COLLATE NOCASE AND area = '"+earea+"'COLLATE NOCASE";
        //Cursor cursor = Db.query(TABLE_NAME, new String[] {KEY_ID, KEY_NAME, KEY_DOB, KEY_PASSWORD,KEY_MOBILENO },KEY_PASSWORD + "=?"+" AND " + KEY_NAME +"=?", new String[]{ String.valueOf(epass),String.valueOf(euser) }, null, null, null);
        Cursor c = sd.rawQuery(i,null);
        //Cursor c = sd.query(TABLE_NAME,new String[]{KEY_NAME,KEY_CITY,KEY_MOBILENO,KEY_BLOODGROUP},KEY_BLOODGROUP + "=?"+" AND " + KEY_CITY +"=?",new String[]{ String.valueOf(bg),String.valueOf(city) },null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
        }
        return c.getString(2);
    }

    public List<Blood> getAllContacts(String bgroup, String rcity, String rarea)
    {
        List<Blood> bloodList = new ArrayList<Blood>();
        // Select All Query

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_2,new String[]{KEY_DNAME,KEY_DCITY,KEY_DAREA,KEY_DMOBILENO,KEY_DBLOODGROUP},KEY_DBLOODGROUP + "=?"+" AND " + KEY_DCITY +"=?"+" AND " + KEY_DAREA +"=?",new String[]{ String.valueOf(bgroup),String.valueOf(rcity),String.valueOf(rarea) },null,null,null);
        ;

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                Blood bl = new Blood();
                bl.setName(cursor.getString(0));
                bl.setBgroup(cursor.getString(4));
                bl.setCity(cursor.getString(1));
                bl.setMob(cursor.getString(3));
                bl.setArea(cursor.getString(2));
                // Adding contact to list
                bloodList.add(bl);
            } while (cursor.moveToNext());
        }

        return bloodList;
    }
}
