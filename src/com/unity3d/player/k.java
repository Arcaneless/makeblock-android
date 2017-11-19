package com.unity3d.player;

final class k
{
  private static boolean a = false;
  private boolean b;
  private boolean c;
  private boolean d;
  private boolean e;
  
  k()
  {
    if (!g.c) {}
    for (boolean bool = true;; bool = false)
    {
      this.b = bool;
      this.c = false;
      this.d = false;
      this.e = true;
      return;
    }
  }
  
  static void a()
  {
    a = true;
  }
  
  static void b()
  {
    a = false;
  }
  
  static boolean c()
  {
    return a;
  }
  
  final void a(boolean paramBoolean)
  {
    this.c = paramBoolean;
  }
  
  final void b(boolean paramBoolean)
  {
    this.e = paramBoolean;
  }
  
  final void c(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }
  
  final void d()
  {
    this.b = true;
  }
  
  final boolean e()
  {
    return this.e;
  }
  
  final boolean f()
  {
    return (a) && (this.c) && (this.b) && (!this.e) && (!this.d);
  }
  
  final boolean g()
  {
    return this.d;
  }
  
  public final String toString()
  {
    return super.toString();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\unity3d\player\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */