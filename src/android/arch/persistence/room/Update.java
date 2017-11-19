package android.arch.persistence.room;

import java.lang.annotation.Annotation;

public @interface Update
{
  int onConflict() default 3;
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\Update.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */