package android.arch.persistence.db;

public abstract interface SupportSQLiteQuery
{
  public abstract void bindTo(SupportSQLiteProgram paramSupportSQLiteProgram);
  
  public abstract String getSql();
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\db\SupportSQLiteQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */