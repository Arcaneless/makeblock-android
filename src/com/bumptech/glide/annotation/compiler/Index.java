package com.bumptech.glide.annotation.compiler;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.TYPE})
@interface Index
{
  String[] extensions() default {};
  
  String[] modules() default {};
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\annotation\compiler\Index.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */