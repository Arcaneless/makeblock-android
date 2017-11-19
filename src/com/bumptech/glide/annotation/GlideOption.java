package com.bumptech.glide.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface GlideOption
{
  public static final int OVERRIDE_EXTEND = 1;
  public static final int OVERRIDE_NONE = 0;
  public static final int OVERRIDE_REPLACE = 2;
  
  boolean memoizeStaticMethod() default false;
  
  int override() default 0;
  
  boolean skipStaticMethod() default false;
  
  String staticMethodName() default "";
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\annotation\GlideOption.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */