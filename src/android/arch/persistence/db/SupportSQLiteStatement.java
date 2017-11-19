package android.arch.persistence.db;

public abstract interface SupportSQLiteStatement
  extends SupportSQLiteProgram
{
  public abstract void execute();
  
  public abstract long executeInsert();
  
  public abstract int executeUpdateDelete();
  
  public abstract long simpleQueryForLong();
  
  public abstract String simpleQueryForString();
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\db\SupportSQLiteStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */