package cc.makeblock.makeblock.project;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class DBHelper
  extends SQLiteOpenHelper
{
  private static final String NAME = "project.db";
  public static final String TABLE_NAME = "project";
  private static final String TAG = "DBHelper";
  private static final int VERSION = 2;
  
  public DBHelper(Context paramContext)
  {
    super(paramContext, "project.db", null, 2);
  }
  
  private String getSqlOfCreateDb()
  {
    return "create table project(id integer PRIMARY KEY AUTOINCREMENT,screenshot text,name text,codeSheet text,widgets text,productName text,buildName text,robotForm text,isOfficial integer,boardName text)";
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL(getSqlOfCreateDb());
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    if (paramInt1 == 1)
    {
      paramSQLiteDatabase.beginTransaction();
      Object localObject1 = paramSQLiteDatabase.rawQuery("select * from project order by id", null);
      Object localObject2 = new ArrayList();
      Object localObject3;
      if (localObject1 != null)
      {
        ((Cursor)localObject1).moveToFirst();
        paramInt2 = ((Cursor)localObject1).getCount();
        paramInt1 = 0;
        while (paramInt1 < paramInt2)
        {
          localObject3 = new ProjectBean();
          ((ProjectBean)localObject3).id = ((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndex("id"));
          ((ProjectBean)localObject3).screenshot = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("screenshot"));
          ((ArrayList)localObject2).add(localObject3);
          ((Cursor)localObject1).moveToNext();
          paramInt1 += 1;
        }
      }
      if (localObject1 != null) {
        ((Cursor)localObject1).close();
      }
      paramSQLiteDatabase.setTransactionSuccessful();
      paramSQLiteDatabase.endTransaction();
      paramSQLiteDatabase.beginTransaction();
      localObject1 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ProjectBean)((Iterator)localObject1).next();
        if ((((ProjectBean)localObject2).screenshot == null) || (!((ProjectBean)localObject2).screenshot.contains("Random")))
        {
          paramInt1 = new Random().nextInt(4);
          localObject3 = DatabaseUtils.sqlEscapeString("settings/MakeblockAppResource/OfficialControlPanels/cover-default-Random - " + (paramInt1 + 1) + ".png");
          paramSQLiteDatabase.execSQL("update project set screenshot = " + (String)localObject3 + " where " + "id" + " = " + ((ProjectBean)localObject2).id);
        }
      }
      paramSQLiteDatabase.setTransactionSuccessful();
      paramSQLiteDatabase.endTransaction();
    }
  }
  
  public static final class DBFieldName
  {
    public static final String BOARD_NAME = "boardName";
    public static final String BUILD_NAME = "buildName";
    public static final String CODE_SHEET = "codeSheet";
    public static final String ID = "id";
    public static final String IS_OFFICIAL = "isOfficial";
    public static final String NAME = "name";
    public static final String PRODUCT_NAME = "productName";
    public static final String ROBOT_FORM = "robotForm";
    public static final String SCREENSHOT = "screenshot";
    public static final String WIDGETS = "widgets";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\project\DBHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */