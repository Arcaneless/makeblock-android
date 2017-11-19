package rx.plugins;

class RxJavaSingleExecutionHookDefault
  extends RxJavaSingleExecutionHook
{
  private static final RxJavaSingleExecutionHookDefault INSTANCE = new RxJavaSingleExecutionHookDefault();
  
  public static RxJavaSingleExecutionHook getInstance()
  {
    return INSTANCE;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\plugins\RxJavaSingleExecutionHookDefault.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */