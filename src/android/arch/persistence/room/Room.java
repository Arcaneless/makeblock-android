package android.arch.persistence.room;

import android.content.Context;
import android.support.annotation.NonNull;

public class Room
{
  private static final String CURSOR_CONV_SUFFIX = "_CursorConverter";
  static final String LOG_TAG = "ROOM";
  public static final String MASTER_TABLE_NAME = "room_master_table";
  
  public static <T extends RoomDatabase> RoomDatabase.Builder<T> databaseBuilder(@NonNull Context paramContext, @NonNull Class<T> paramClass, @NonNull String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() == 0)) {
      throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
    }
    return new RoomDatabase.Builder(paramContext, paramClass, paramString);
  }
  
  /* Error */
  @NonNull
  static <T, C> T getGeneratedImplementation(Class<C> paramClass, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 61	java/lang/Class:getPackage	()Ljava/lang/Package;
    //   4: invokevirtual 66	java/lang/Package:getName	()Ljava/lang/String;
    //   7: astore_3
    //   8: aload_0
    //   9: invokevirtual 69	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   12: astore_2
    //   13: aload_3
    //   14: invokevirtual 73	java/lang/String:isEmpty	()Z
    //   17: ifeq +48 -> 65
    //   20: new 75	java/lang/StringBuilder
    //   23: dup
    //   24: invokespecial 76	java/lang/StringBuilder:<init>	()V
    //   27: aload_2
    //   28: bipush 46
    //   30: bipush 95
    //   32: invokevirtual 80	java/lang/String:replace	(CC)Ljava/lang/String;
    //   35: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_1
    //   39: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: astore_2
    //   46: aload_3
    //   47: invokevirtual 73	java/lang/String:isEmpty	()Z
    //   50: ifeq +29 -> 79
    //   53: aload_2
    //   54: astore_1
    //   55: aload_1
    //   56: invokestatic 91	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   59: invokevirtual 95	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   62: astore_1
    //   63: aload_1
    //   64: areturn
    //   65: aload_2
    //   66: aload_3
    //   67: invokevirtual 32	java/lang/String:length	()I
    //   70: iconst_1
    //   71: iadd
    //   72: invokevirtual 99	java/lang/String:substring	(I)Ljava/lang/String;
    //   75: astore_2
    //   76: goto -56 -> 20
    //   79: new 75	java/lang/StringBuilder
    //   82: dup
    //   83: invokespecial 76	java/lang/StringBuilder:<init>	()V
    //   86: aload_3
    //   87: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: ldc 101
    //   92: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: aload_2
    //   96: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: astore_1
    //   103: goto -48 -> 55
    //   106: astore_1
    //   107: new 103	java/lang/RuntimeException
    //   110: dup
    //   111: new 75	java/lang/StringBuilder
    //   114: dup
    //   115: invokespecial 76	java/lang/StringBuilder:<init>	()V
    //   118: ldc 105
    //   120: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: aload_0
    //   124: invokevirtual 69	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   127: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: ldc 107
    //   132: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload_2
    //   136: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: ldc 109
    //   141: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: invokespecial 110	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   150: athrow
    //   151: astore_1
    //   152: new 103	java/lang/RuntimeException
    //   155: dup
    //   156: new 75	java/lang/StringBuilder
    //   159: dup
    //   160: invokespecial 76	java/lang/StringBuilder:<init>	()V
    //   163: ldc 112
    //   165: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: aload_0
    //   169: invokevirtual 69	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   172: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokespecial 110	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   181: athrow
    //   182: astore_1
    //   183: new 103	java/lang/RuntimeException
    //   186: dup
    //   187: new 75	java/lang/StringBuilder
    //   190: dup
    //   191: invokespecial 76	java/lang/StringBuilder:<init>	()V
    //   194: ldc 114
    //   196: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: aload_0
    //   200: invokevirtual 69	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   203: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   209: invokespecial 110	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   212: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	213	0	paramClass	Class<C>
    //   0	213	1	paramString	String
    //   12	124	2	str1	String
    //   7	80	3	str2	String
    // Exception table:
    //   from	to	target	type
    //   46	53	106	java/lang/ClassNotFoundException
    //   55	63	106	java/lang/ClassNotFoundException
    //   79	103	106	java/lang/ClassNotFoundException
    //   46	53	151	java/lang/IllegalAccessException
    //   55	63	151	java/lang/IllegalAccessException
    //   79	103	151	java/lang/IllegalAccessException
    //   46	53	182	java/lang/InstantiationException
    //   55	63	182	java/lang/InstantiationException
    //   79	103	182	java/lang/InstantiationException
  }
  
  public static <T extends RoomDatabase> RoomDatabase.Builder<T> inMemoryDatabaseBuilder(@NonNull Context paramContext, @NonNull Class<T> paramClass)
  {
    return new RoomDatabase.Builder(paramContext, paramClass, null);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\arch\persistence\room\Room.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */