package android.arch.persistence.room;

import java.lang.annotation.Annotation;

public @interface ForeignKey
{
  public static final int CASCADE = 5;
  public static final int NO_ACTION = 1;
  public static final int RESTRICT = 2;
  public static final int SET_DEFAULT = 4;
  public static final int SET_NULL = 3;
  
  String[] childColumns();
  
  boolean deferred() default false;
  
  Class entity();
  
  @Action
  int onDelete() default 1;
  
  @Action
  int onUpdate() default 1;
  
  String[] parentColumns();
  
  public static @interface Action {}
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\ForeignKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */