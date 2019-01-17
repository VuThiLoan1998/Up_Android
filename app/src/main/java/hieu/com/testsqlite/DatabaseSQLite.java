package hieu.com.testsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseSQLite extends SQLiteOpenHelper {
    //public DatabaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    //    super(context, name, factory, version);
    //}
    public static final int DATABASE_VERSION=1;
    private static String CREATE_lop = "CREATE TABLE LOPHOC (malop varchar(10) primary key , tenlop nvarchar(100), siso varchar(50))";
    //private static String CREATE_sinvien = "CREATE TABLE Sinhvien (masv varchar(10) primary key , tensv varchar(100), malop varchar(10))";
    private static String CREATE_sinhvien = "CREATE TABLE IF NOT EXISTS  SINHVIEN " +
            "(masv varchar(5) primary key,tensv varchar(100),gioitinh nvachar(10),ngaysinh varchar(50)," +
            "malop varchar(10) not null constraint malop references LopHoc(malop) on delete cascade)";
    public static  String DROP_Lophoc = "DROP TABLE LOPHOC";
    public static String DROP_Sinhvien="DROP TABLE SINHVIEN";



    public void themtable(String strtbl){
        String s="CREATE TABLE "+strtbl + "(malop varchar(10) primary key , tenlop varchar(100), siso int)";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(s);
    }
    public DatabaseSQLite(Context context, String DATABASE_NAME, SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void QueryData (String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }
    public Cursor getData(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();//doc va ghi
        return db.rawQuery(sql, null);
    }
    public ArrayList<LopHoc> getalllop(){
        SQLiteDatabase db = getReadableDatabase();
        String sql ="select * from LopHoc ";
        Cursor cursor  =db.rawQuery(sql, null);
       // Cursor cursor= db.query("LOPHOC",null,null,null,null,null,null);
        ArrayList<LopHoc> list = new ArrayList<LopHoc>();
        if(cursor != null)
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                LopHoc sp=new LopHoc(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                list.add(sp);
                cursor.moveToNext();
            }
        }
        return list;
    }
    void themCung(SQLiteDatabase db)
    {
        String sql="INSERT INTO LOPHOC VALUES ('NCUDPM10A','Nghiên cứu ứng dụng phần mềm 10A','96')";
        db.execSQL(sql);
        String sql1="INSERT INTO LOPHOC VALUES ('NCUDPM10B','Nghiên cứu ứng dụng phần mềm 10B','69')";
        db.execSQL(sql1);
        String sql2="INSERT INTO LOPHOC VALUES ('NCUDPM10C','Nghiên cứu ứng dụng phần mềm 10C','21')";
        db.execSQL(sql2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String s="CREATE TABLE IF NOT EXISTS  LOPHOC (malop varchar(10) primary key " +
                ", tenlop nvarchar(100), siso varchar(50))";
       /* String a="CREATE TABLE IF NOT EXISTS  SINHVIEN " +
                "(masv varchar(5) primary key,tensv varchar(100),gioitinh nvachar(10),ngaysinh varchar(50)," +
                "malop varchar(10) not null constraint malop references LopHoc(malop) on delete cascade)";*/
        db.execSQL(s);
       // db.execSQL(a);
        //db.execSQL(CREATE_lop);
        db.execSQL(CREATE_sinhvien);
        themCung(db);

    }
    public ArrayList<Sinhvien> getallSV(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("SINHVIEN",null,null,null,null,null,null);
        ArrayList<Sinhvien> list = new ArrayList<Sinhvien>();
        if(cursor != null)
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                Sinhvien sv=new Sinhvien(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                list.add(sv);
                cursor.moveToNext();
            }
        }
        return list;
    }
    public boolean Themsv(Sinhvien sv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("masv",sv.getMasv());
        values.put("tensv",sv.getTensv());
        values.put("gioitinh",sv.getGioitinh());
        values.put("ngaysinh",sv.getNgaysinh());
        values.put("malop",sv.getMalop());

        db.insert("SINHVIEN",null,values);
        db.close();
        return true;
    }
    public boolean Update(Sinhvien sv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("masv",sv.getMasv());
        values.put("tensv",sv.getTensv());
        values.put("gioitinh",sv.getGioitinh());
        values.put("ngaysinh",sv.getNgaysinh());
        values.put("malop",sv.getMalop());
        String whereClause="masv=?";
        String[]whereArgs={sv.getMasv()};
        long rec=db.update("SINHVIEN",values,whereClause,whereArgs);
        db.close();
        return rec>0;
    }
    public boolean Delete(String masvdel)   {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause="masv=?";
        String[]whereArgs={masvdel};
        long rec=db.delete("SINHVIEN",whereClause,whereArgs);
        db.close();
        return rec>0;
    }
    public boolean Themlop(LopHoc lop)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("malop",lop.getMalop());
        values.put("tenlop",lop.getTenlop());
        values.put("siso",lop.getSiso());

        db.insert("LOPHOC",null,values);
        db.close();
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String s="DROP TABLE IF EXISTS SINHVIEN";
        db.execSQL(s);
        //db.execSQL(DROP_Sinhvien);
        db.execSQL(DROP_Lophoc);
        onCreate(db);
    }
}
