package android.arch.persistence.room;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface ColumnInfo
{
  public static final int BLOB = 5;
  public static final String INHERIT_FIELD_NAME = "[field-name]";
  public static final int INTEGER = 3;
  public static final int REAL = 4;
  public static final int TEXT = 2;
  public static final int UNDEFINED = 1;
  
  boolean index() default false;
  
  String name() default "[field-name]";
  
  @SQLiteTypeAffinity
  int typeAffinity() default 1;
  
  public static @interface SQLiteTypeAffinity {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\ColumnInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */