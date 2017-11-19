package cc.makeblock.makeblock.event;

public class SearchEvent
{
  public static final int BOND_BONDED = 10;
  public static final int BOND_BONDING = 9;
  public static final int BOND_CANCEL = 11;
  public static final int CONNECT_DISCONNECTED = 7;
  public static final int CONNECT_FAIL = 5;
  public static final int CONNECT_START = 3;
  public static final int CONNECT_SUCCEED = 6;
  public static final int DISCOVER_FAIL = -1;
  public static final int DISCOVER_FINISH = 1;
  public static final int DISCOVER_FOUND = 2;
  public static final int DISCOVER_START = 0;
  public static final int OPEN_BLUETOOTH = 8;
  public int eventType;
  
  public SearchEvent(int paramInt)
  {
    this.eventType = paramInt;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\event\SearchEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */