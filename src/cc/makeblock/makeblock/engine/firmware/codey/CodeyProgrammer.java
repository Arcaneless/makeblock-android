package cc.makeblock.makeblock.engine.firmware.codey;

import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.firmware.Programmer;
import cc.makeblock.makeblock.engine.firmware.Programmer.OnProgressChangeListener;
import cc.makeblock.makeblock.engine.protocol.codey.FileBodyInstruction;
import cc.makeblock.makeblock.engine.protocol.codey.FileHeaderInstruction;
import cc.makeblock.makeblock.engine.utils.CodeyByteUtil;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CodeyProgrammer
  extends Programmer
{
  private static final String DEFAULT_FILE_NAME = "/flash/main.py";
  private static final int LIMIT_LENGTH = 50;
  private static final int OUT_TIME = 5000;
  private static final int OUT_TIME_WHAT = 100;
  private static final byte STATUS_CODE_ERR = 1;
  private static final byte STATUS_CODE_FILE = -16;
  private static final byte STATUS_CODE_OK = 0;
  private int blockSize;
  private final String fileName;
  private float hasSendLength = 0.0F;
  private boolean isCancel;
  private Handler mHandler = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      if ((paramAnonymousMessage.what == 100) && (CodeyProgrammer.this.onProgressChangeListener != null)) {
        CodeyProgrammer.this.onProgressChangeListener.onFail();
      }
    }
  };
  private List<WrapByte> protocols;
  private int sendIndex;
  private int totalLength = 0;
  
  public CodeyProgrammer(String paramString)
  {
    this(paramString, null);
  }
  
  public CodeyProgrammer(String paramString, Programmer.OnProgressChangeListener paramOnProgressChangeListener)
  {
    super(paramOnProgressChangeListener);
    this.fileName = paramString;
  }
  
  private List<WrapByte> composer(String paramString, byte[] paramArrayOfByte, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfByte.length;
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = "/flash/main.py";
    }
    paramString = new FileHeaderInstruction(str, paramArrayOfByte).getBytes();
    localArrayList.add(new WrapByte(paramString));
    this.totalLength += paramString.length;
    int k = (int)Math.ceil(j * 1.0F / paramInt);
    int i = 0;
    if (i < k)
    {
      int m = i * paramInt;
      if (j - m < paramInt) {}
      for (paramString = CodeyByteUtil.sliceBytes(paramArrayOfByte, m, j);; paramString = CodeyByteUtil.sliceBytes(paramArrayOfByte, m, (i + 1) * paramInt))
      {
        paramString = new FileBodyInstruction(m, paramString).getBytes();
        localArrayList.add(new WrapByte(paramString));
        this.totalLength += paramString.length;
        i += 1;
        break;
      }
    }
    return localArrayList;
  }
  
  private byte[] getContentBytes(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      paramString = App.getContext().getAssets().open(paramString);
      localObject1 = localObject2;
      BufferedInputStream localBufferedInputStream = new BufferedInputStream(paramString);
      localObject1 = localObject2;
      int i = paramString.available();
      localObject1 = localObject2;
      paramString = new byte[i];
      localObject1 = paramString;
      localBufferedInputStream.read(paramString, 0, i);
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return (byte[])localObject1;
  }
  
  private void notifyProgress()
  {
    if (this.onProgressChangeListener != null) {
      this.onProgressChangeListener.onProgressChange(this.hasSendLength / this.totalLength);
    }
  }
  
  private void send(byte[] paramArrayOfByte)
  {
    if (this.isCancel) {
      return;
    }
    this.hasSendLength += paramArrayOfByte.length;
    BleManager.getInstance().writeToBluetooth(paramArrayOfByte);
    timeOut();
  }
  
  private void timeOut()
  {
    this.mHandler.sendEmptyMessageDelayed(100, 5000L);
  }
  
  public void onReadData(UUID paramUUID1, UUID paramUUID2, byte[] paramArrayOfByte) {}
  
  public void onReceive(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length < 10) {}
    do
    {
      do
      {
        return;
      } while ((paramArrayOfByte == null) || (paramArrayOfByte[7] != -16));
      switch (paramArrayOfByte[10])
      {
      default: 
        return;
      case 0: 
        this.mHandler.removeMessages(100);
        this.sendIndex += 1;
        if (this.sendIndex == this.blockSize) {
          if (this.onProgressChangeListener != null) {
            this.onProgressChangeListener.onSuccess();
          }
        }
        for (;;)
        {
          notifyProgress();
          return;
          send(((WrapByte)this.protocols.get(this.sendIndex)).bytes);
        }
      }
      this.mHandler.removeMessages(100);
    } while (this.onProgressChangeListener == null);
    this.onProgressChangeListener.onFail();
  }
  
  public void startProgram()
  {
    this.protocols = composer(null, getContentBytes(this.fileName), 50);
    this.blockSize = this.protocols.size();
    this.sendIndex = 0;
    send(((WrapByte)this.protocols.get(this.sendIndex)).bytes);
  }
  
  public void stop()
  {
    this.isCancel = true;
  }
  
  private class WrapByte
  {
    public final byte[] bytes;
    
    public WrapByte(byte[] paramArrayOfByte)
    {
      this.bytes = paramArrayOfByte;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\codey\CodeyProgrammer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */