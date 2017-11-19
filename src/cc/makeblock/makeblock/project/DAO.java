package cc.makeblock.makeblock.project;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import java.util.ArrayList;

public class DAO
{
  private static final String TAG = "DAO";
  private DBHelper helper;
  
  private DAO(Context paramContext)
  {
    this.helper = new DBHelper(paramContext);
  }
  
  public static DAO getInstance(Context paramContext)
  {
    try
    {
      paramContext = new DAO(paramContext);
      return paramContext;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  @NonNull
  private ProjectBean initProjectBean(Cursor paramCursor)
  {
    ProjectBean localProjectBean = new ProjectBean();
    localProjectBean.isOfficial = paramCursor.getInt(paramCursor.getColumnIndex("isOfficial"));
    localProjectBean.id = paramCursor.getInt(paramCursor.getColumnIndex("id"));
    localProjectBean.screenshot = paramCursor.getString(paramCursor.getColumnIndex("screenshot"));
    localProjectBean.name = paramCursor.getString(paramCursor.getColumnIndex("name"));
    if (localProjectBean.name == null) {
      localProjectBean.name = "";
    }
    localProjectBean.codeSheet = paramCursor.getString(paramCursor.getColumnIndex("codeSheet"));
    localProjectBean.setWidgetsJson(paramCursor.getString(paramCursor.getColumnIndex("widgets")));
    localProjectBean.boardName = paramCursor.getString(paramCursor.getColumnIndex("boardName"));
    localProjectBean.robotForm = paramCursor.getString(paramCursor.getColumnIndex("robotForm"));
    return localProjectBean;
  }
  
  public Boolean delete(int paramInt)
  {
    try
    {
      this.helper.getReadableDatabase().execSQL("delete from project where id = " + paramInt);
      localBoolean1 = Boolean.valueOf(true);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Boolean localBoolean1;
        localException = localException;
        Boolean localBoolean2 = Boolean.valueOf(false);
      }
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
    return localBoolean1;
  }
  
  public boolean exist(int paramInt)
  {
    for (;;)
    {
      try
      {
        Cursor localCursor = this.helper.getReadableDatabase().rawQuery("select id from project where id = " + paramInt, null);
        boolean bool;
        if ((localCursor != null) && (localCursor.getCount() > 0))
        {
          bool = true;
          break label75;
          localCursor.close();
          if (bool) {}
          return bool;
        }
        else
        {
          bool = false;
        }
      }
      finally {}
      label75:
      if (localObject == null) {}
    }
  }
  
  public Boolean insert(ProjectBean paramProjectBean)
  {
    for (;;)
    {
      SQLiteDatabase localSQLiteDatabase;
      try
      {
        localSQLiteDatabase = this.helper.getReadableDatabase();
      }
      finally {}
      localSQLiteDatabase.execSQL("insert into project (screenshot,name,codeSheet,widgets,productName,buildName,robotForm,isOfficial,boardName) values (?,?,?,?,?,?,?,?,?)", new Object[] { paramProjectBean.screenshot, paramProjectBean.name, paramProjectBean.codeSheet, paramProjectBean.getWidgetsJson(), paramProjectBean.productName, paramProjectBean.buildName, paramProjectBean.robotForm, Integer.valueOf(paramProjectBean.isOfficial), paramProjectBean.boardName });
      return Boolean.valueOf(false);
    }
  }
  
  public ArrayList<ProjectBean> query(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase;
    ArrayList localArrayList;
    try
    {
      localSQLiteDatabase = this.helper.getReadableDatabase();
      localSQLiteDatabase.beginTransaction();
      paramString = localSQLiteDatabase.rawQuery("select * from project where boardName = '" + paramString + "' order by " + "id" + " desc", null);
      localArrayList = new ArrayList();
    }
    finally {}
    paramString.moveToFirst();
    int j = paramString.getCount();
    break label151;
    localArrayList.add(initProjectBean(paramString));
    paramString.moveToNext();
    break label165;
    label107:
    localSQLiteDatabase.endTransaction();
    break label172;
    label115:
    paramString.close();
    for (;;)
    {
      int i = localArrayList.size();
      if (i > 0) {}
      return localArrayList;
      if (paramString == null) {
        break label107;
      }
      break;
      label151:
      if (j == 0) {}
      i = 0;
      for (;;)
      {
        if (i >= j) {
          break label170;
        }
        break;
        label165:
        i += 1;
      }
      label170:
      break label107;
      label172:
      if (paramString != null) {
        break label115;
      }
    }
  }
  
  public ProjectBean selectById(int paramInt)
  {
    Object localObject3 = null;
    SQLiteDatabase localSQLiteDatabase;
    Cursor localCursor;
    try
    {
      localSQLiteDatabase = this.helper.getReadableDatabase();
      localSQLiteDatabase.beginTransaction();
      localCursor = localSQLiteDatabase.rawQuery("select * from project where id = " + paramInt, null);
    }
    finally {}
    localCursor.moveToFirst();
    paramInt = localCursor.getCount();
    break label116;
    label67:
    localCursor.moveToFirst();
    ProjectBean localProjectBean = initProjectBean(localCursor);
    label82:
    localSQLiteDatabase.endTransaction();
    for (;;)
    {
      localCursor.close();
      label116:
      do
      {
        return localProjectBean;
        Object localObject2 = localObject3;
        if (localCursor == null) {
          break label82;
        }
        break;
        localObject2 = localObject3;
        if (paramInt <= 0) {
          break label82;
        }
        break label67;
      } while (localCursor == null);
    }
  }
  
  public void update(ProjectBean paramProjectBean)
  {
    try
    {
      this.helper.getReadableDatabase().execSQL("update project set name = ?,codeSheet = ?,screenshot = ?,widgets = ? where id = " + paramProjectBean.id, new Object[] { paramProjectBean.name, paramProjectBean.codeSheet, paramProjectBean.screenshot, paramProjectBean.getWidgetsJson() });
      return;
    }
    finally
    {
      paramProjectBean = finally;
      throw paramProjectBean;
    }
  }
  
  public void updateName(String paramString, int paramInt)
  {
    try
    {
      paramString = DatabaseUtils.sqlEscapeString(paramString);
      this.helper.getReadableDatabase().execSQL("update project set name = " + paramString + " where " + "id" + " = " + paramInt);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\project\DAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */