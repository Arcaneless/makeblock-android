package com.bumptech.glide.util;

public class MultiClassKey
{
  private Class<?> first;
  private Class<?> second;
  private Class<?> third;
  
  public MultiClassKey() {}
  
  public MultiClassKey(Class<?> paramClass1, Class<?> paramClass2)
  {
    set(paramClass1, paramClass2);
  }
  
  public MultiClassKey(Class<?> paramClass1, Class<?> paramClass2, Class<?> paramClass3)
  {
    set(paramClass1, paramClass2, paramClass3);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (MultiClassKey)paramObject;
      if (!this.first.equals(((MultiClassKey)paramObject).first)) {
        return false;
      }
      if (!this.second.equals(((MultiClassKey)paramObject).second)) {
        return false;
      }
    } while (Util.bothNullOrEqual(this.third, ((MultiClassKey)paramObject).third));
    return false;
  }
  
  public int hashCode()
  {
    int j = this.first.hashCode();
    int k = this.second.hashCode();
    if (this.third != null) {}
    for (int i = this.third.hashCode();; i = 0) {
      return (j * 31 + k) * 31 + i;
    }
  }
  
  public void set(Class<?> paramClass1, Class<?> paramClass2)
  {
    set(paramClass1, paramClass2, null);
  }
  
  public void set(Class<?> paramClass1, Class<?> paramClass2, Class<?> paramClass3)
  {
    this.first = paramClass1;
    this.second = paramClass2;
    this.third = paramClass3;
  }
  
  public String toString()
  {
    return "MultiClassKey{first=" + this.first + ", second=" + this.second + '}';
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\util\MultiClassKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */