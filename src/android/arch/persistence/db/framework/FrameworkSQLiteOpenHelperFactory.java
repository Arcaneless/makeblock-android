package android.arch.persistence.db.framework;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Factory;

public class FrameworkSQLiteOpenHelperFactory
  implements SupportSQLiteOpenHelper.Factory
{
  public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration paramConfiguration)
  {
    return new FrameworkSQLiteOpenHelper(paramConfiguration.context, paramConfiguration.name, paramConfiguration.version, paramConfiguration.errorHandler, paramConfiguration.callback);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\db\framework\FrameworkSQLiteOpenHelperFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */