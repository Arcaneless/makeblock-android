package cc.makeblock.makeblock.engine.protocol.codey;

import android.util.Pair;
import cc.makeblock.makeblock.engine.protocol.base.RespondParser;
import cc.makeblock.makeblock.engine.utils.CodeyByteUtil;
import java.nio.ByteBuffer;

public class CodeyRespondParser
  extends RespondParser
{
  private static final byte COMMUNICATION = 2;
  private static final int FRAME_INDEX = 4;
  private static final byte[] HEAD = { -13 };
  private static final int LEN1_INDEX = 2;
  private static final int LEN2_INDEX = 3;
  private static final int LEN_CHECK_INDEX = 1;
  private static final byte[] TAIL = { -12 };
  private static final byte TYPE_STRING = 12;
  private OnRespondReceiveListener onRespondReceiveListener;
  
  public CodeyRespondParser()
  {
    super(HEAD, TAIL);
  }
  
  private boolean check(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 6)) {}
    int i;
    do
    {
      do
      {
        return false;
      } while (CodeyByteUtil.calcFrameCheckSum(new byte[] { -13, paramArrayOfByte[2], paramArrayOfByte[3] }) != paramArrayOfByte[1]);
      i = paramArrayOfByte[2] + paramArrayOfByte[3] * 256;
    } while (CodeyByteUtil.calcFrameCheckSum(paramArrayOfByte, 4, i) != paramArrayOfByte[(i + 4)]);
    return true;
  }
  
  protected void packData(byte[] paramArrayOfByte)
  {
    if (check(paramArrayOfByte))
    {
      int i = paramArrayOfByte[2] + paramArrayOfByte[3] * 256 - 1;
      int j = paramArrayOfByte[4];
      paramArrayOfByte = ByteBuffer.allocate(i).put(paramArrayOfByte, 5, i).array();
      switch (j)
      {
      }
    }
    for (paramArrayOfByte = null;; paramArrayOfByte = new CommunicationVariableRespond((String)paramArrayOfByte.first, paramArrayOfByte.second))
    {
      if ((paramArrayOfByte != null) && (this.onRespondReceiveListener != null)) {
        this.onRespondReceiveListener.onRespondReceive(paramArrayOfByte);
      }
      return;
      paramArrayOfByte = CodeyByteUtil.byteArrayToString(paramArrayOfByte);
    }
  }
  
  public void setOnRespondReceiveListener(OnRespondReceiveListener paramOnRespondReceiveListener)
  {
    this.onRespondReceiveListener = paramOnRespondReceiveListener;
  }
  
  public static abstract interface OnRespondReceiveListener
  {
    public abstract void onRespondReceive(CodeyRespond paramCodeyRespond);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\codey\CodeyRespondParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */