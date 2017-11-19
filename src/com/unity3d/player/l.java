package com.unity3d.player;

import java.lang.reflect.Method;
import java.util.HashMap;

final class l
{
  private HashMap a = new HashMap();
  private Class b = null;
  private Object c = null;
  
  public l(Class paramClass, Object paramObject)
  {
    this.b = paramClass;
    this.c = paramObject;
  }
  
  private void a(String paramString, a parama)
  {
    try
    {
      parama.b = this.b.getMethod(paramString, parama.a);
      return;
    }
    catch (Exception localException)
    {
      e.Log(6, "Exception while trying to get method " + paramString + ". " + localException.getLocalizedMessage());
      parama.b = null;
    }
  }
  
  public final Object a(String paramString, Object... paramVarArgs)
  {
    if (!this.a.containsKey(paramString))
    {
      e.Log(6, "No definition for method " + paramString + " can be found");
      return null;
    }
    a locala = (a)this.a.get(paramString);
    if (locala.b == null) {
      a(paramString, locala);
    }
    if (locala.b == null)
    {
      e.Log(6, "Unable to create method: " + paramString);
      return null;
    }
    try
    {
      if (paramVarArgs.length == 0)
      {
        paramVarArgs = locala.b.invoke(this.c, new Object[0]);
        paramString = paramVarArgs;
      }
      else
      {
        paramVarArgs = locala.b.invoke(this.c, paramVarArgs);
        paramString = paramVarArgs;
      }
    }
    catch (Exception paramVarArgs)
    {
      e.Log(6, "Error trying to call delegated method " + paramString + ". " + paramVarArgs.getLocalizedMessage());
      paramString = null;
    }
    return paramString;
  }
  
  public final void a(String paramString, Class[] paramArrayOfClass)
  {
    this.a.put(paramString, new a(paramArrayOfClass));
  }
  
  final class a
  {
    public Class[] a;
    public Method b;
    
    public a(Class[] paramArrayOfClass)
    {
      this.a = paramArrayOfClass;
      this.b = null;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\unity3d\player\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */