package android.databinding;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.TYPE})
public @interface InverseBindingMethods
{
  InverseBindingMethod[] value();
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\InverseBindingMethods.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */