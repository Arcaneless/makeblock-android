package android.arch.persistence.room;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface Relation
{
  Class entity() default Object.class;
  
  String entityColumn();
  
  String parentColumn();
  
  String[] projection() default {};
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\Relation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */