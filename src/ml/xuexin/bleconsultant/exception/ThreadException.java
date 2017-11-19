package ml.xuexin.bleconsultant.exception;

public class ThreadException
  extends RuntimeException
{
  public ThreadException()
  {
    super("Please call init on main thread");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\exception\ThreadException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */