package android.databinding;

public class DynamicUtil
{
  public static byte safeUnbox(Byte paramByte)
  {
    if (paramByte == null) {
      return 0;
    }
    return paramByte.byteValue();
  }
  
  public static char safeUnbox(Character paramCharacter)
  {
    if (paramCharacter == null) {
      return '\000';
    }
    return paramCharacter.charValue();
  }
  
  public static double safeUnbox(Double paramDouble)
  {
    if (paramDouble == null) {
      return 0.0D;
    }
    return paramDouble.doubleValue();
  }
  
  public static float safeUnbox(Float paramFloat)
  {
    if (paramFloat == null) {
      return 0.0F;
    }
    return paramFloat.floatValue();
  }
  
  public static int safeUnbox(Integer paramInteger)
  {
    if (paramInteger == null) {
      return 0;
    }
    return paramInteger.intValue();
  }
  
  public static long safeUnbox(Long paramLong)
  {
    if (paramLong == null) {
      return 0L;
    }
    return paramLong.longValue();
  }
  
  public static short safeUnbox(Short paramShort)
  {
    if (paramShort == null) {
      return 0;
    }
    return paramShort.shortValue();
  }
  
  public static boolean safeUnbox(Boolean paramBoolean)
  {
    if (paramBoolean == null) {
      return false;
    }
    return paramBoolean.booleanValue();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\DynamicUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */