package rx.internal.util.unsafe;

public final class Pow2
{
  private Pow2()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static boolean isPowerOfTwo(int paramInt)
  {
    return (paramInt - 1 & paramInt) == 0;
  }
  
  public static int roundToPowerOfTwo(int paramInt)
  {
    return 1 << 32 - Integer.numberOfLeadingZeros(paramInt - 1);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\internal\util\unsafe\Pow2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */