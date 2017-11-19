package ml.xuexin.bleconsultant.port;

public abstract interface ConnectCallback
  extends OvertimeInterface
{
  public static final int STATE_CONNECTED = 2;
  public static final int STATE_CONNECTING = 1;
  public static final int STATE_CONNECT_OVERTIME = 5;
  public static final int STATE_DISCONNECTED = 0;
  public static final int STATE_DISCONNECTING = 3;
  public static final int STATE_SERVICES_DISCOVERED = 4;
  public static final int STATE_SERVICES_DISCOVERED_FAIL = 6;
  public static final int STATE_UNINIT = -1;
  
  public abstract void onStateChange(int paramInt);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\ml\xuexin\bleconsultant\port\ConnectCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */