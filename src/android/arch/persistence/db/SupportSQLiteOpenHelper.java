package android.arch.persistence.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

public abstract interface SupportSQLiteOpenHelper
{
  public abstract void close();
  
  public abstract String getDatabaseName();
  
  public abstract SupportSQLiteDatabase getReadableDatabase();
  
  public abstract SupportSQLiteDatabase getWritableDatabase();
  
  @RequiresApi(api=16)
  public abstract void setWriteAheadLoggingEnabled(boolean paramBoolean);
  
  public static abstract class Callback
  {
    public void onConfigure(SupportSQLiteDatabase paramSupportSQLiteDatabase) {}
    
    public abstract void onCreate(SupportSQLiteDatabase paramSupportSQLiteDatabase);
    
    public void onDowngrade(SupportSQLiteDatabase paramSupportSQLiteDatabase, int paramInt1, int paramInt2)
    {
      throw new SQLiteException("Can't downgrade database from version " + paramInt1 + " to " + paramInt2);
    }
    
    public void onOpen(SupportSQLiteDatabase paramSupportSQLiteDatabase) {}
    
    public abstract void onUpgrade(SupportSQLiteDatabase paramSupportSQLiteDatabase, int paramInt1, int paramInt2);
  }
  
  public static class Configuration
  {
    @NonNull
    public final SupportSQLiteOpenHelper.Callback callback;
    @NonNull
    public final Context context;
    @Nullable
    public final DatabaseErrorHandler errorHandler;
    @Nullable
    public final String name;
    public final int version;
    
    Configuration(@NonNull Context paramContext, @Nullable String paramString, int paramInt, @Nullable DatabaseErrorHandler paramDatabaseErrorHandler, @NonNull SupportSQLiteOpenHelper.Callback paramCallback)
    {
      this.context = paramContext;
      this.name = paramString;
      this.version = paramInt;
      this.callback = paramCallback;
      this.errorHandler = paramDatabaseErrorHandler;
    }
    
    public static Builder builder(Context paramContext)
    {
      return new Builder(paramContext);
    }
    
    public static class Builder
    {
      SupportSQLiteOpenHelper.Callback mCallback;
      Context mContext;
      DatabaseErrorHandler mErrorHandler;
      String mName;
      int mVersion = 1;
      
      Builder(@NonNull Context paramContext)
      {
        this.mContext = paramContext;
      }
      
      public SupportSQLiteOpenHelper.Configuration build()
      {
        if (this.mCallback == null) {
          throw new IllegalArgumentException("Must set a callback to create the configuration.");
        }
        if (this.mContext == null) {
          throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
        }
        if (this.mErrorHandler == null) {
          this.mErrorHandler = new DefaultDatabaseErrorHandler();
        }
        return new SupportSQLiteOpenHelper.Configuration(this.mContext, this.mName, this.mVersion, this.mErrorHandler, this.mCallback);
      }
      
      public Builder callback(@NonNull SupportSQLiteOpenHelper.Callback paramCallback)
      {
        this.mCallback = paramCallback;
        return this;
      }
      
      public Builder errorHandler(@Nullable DatabaseErrorHandler paramDatabaseErrorHandler)
      {
        this.mErrorHandler = paramDatabaseErrorHandler;
        return this;
      }
      
      public Builder name(@Nullable String paramString)
      {
        this.mName = paramString;
        return this;
      }
      
      public Builder version(int paramInt)
      {
        this.mVersion = paramInt;
        return this;
      }
    }
  }
  
  public static abstract interface Factory
  {
    public abstract SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration paramConfiguration);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\db\SupportSQLiteOpenHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */