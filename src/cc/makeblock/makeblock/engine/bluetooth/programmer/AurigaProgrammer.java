package cc.makeblock.makeblock.engine.bluetooth.programmer;

import android.util.Log;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.engine.Command.Builder;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.firmware.Programmer.OnProgressChangeListener;
import cc.makeblock.makeblock.engine.utils.AssetsUtils;
import java.util.UUID;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class AurigaProgrammer
  extends Programmer
{
  private static final byte ANSWER_CKSUM_ERROR = -80;
  private static final byte CMD_CHIP_ERASE_HVSP = 50;
  private static final byte CMD_CHIP_ERASE_ISP = 18;
  private static final byte CMD_CHIP_ERASE_PP = 34;
  private static final byte CMD_ENTER_PROGMODE_HVSP = 48;
  private static final byte CMD_ENTER_PROGMODE_ISP = 16;
  private static final byte CMD_ENTER_PROGMODE_PP = 32;
  private static final byte CMD_FIRMWARE_UPGRADE = 7;
  private static final byte CMD_GET_PARAMETER = 3;
  private static final byte CMD_LEAVE_PROGMODE_HVSP = 49;
  private static final byte CMD_LEAVE_PROGMODE_ISP = 17;
  private static final byte CMD_LEAVE_PROGMODE_PP = 33;
  private static final byte CMD_LOAD_ADDRESS = 6;
  private static final byte CMD_OSCCAL = 5;
  private static final byte CMD_PROGRAM_EEPROM_HVSP = 53;
  private static final byte CMD_PROGRAM_EEPROM_ISP = 21;
  private static final byte CMD_PROGRAM_EEPROM_PP = 37;
  private static final byte CMD_PROGRAM_FLASH_HVSP = 51;
  private static final byte CMD_PROGRAM_FLASH_ISP = 19;
  private static final byte CMD_PROGRAM_FLASH_PP = 35;
  private static final byte CMD_PROGRAM_FUSE_HVSP = 55;
  private static final byte CMD_PROGRAM_FUSE_ISP = 23;
  private static final byte CMD_PROGRAM_FUSE_PP = 39;
  private static final byte CMD_PROGRAM_LOCK_HVSP = 57;
  private static final byte CMD_PROGRAM_LOCK_ISP = 25;
  private static final byte CMD_PROGRAM_LOCK_PP = 41;
  private static final byte CMD_READ_EEPROM_HVSP = 54;
  private static final byte CMD_READ_EEPROM_ISP = 22;
  private static final byte CMD_READ_EEPROM_PP = 38;
  private static final byte CMD_READ_FLASH_HVSP = 52;
  private static final byte CMD_READ_FLASH_ISP = 20;
  private static final byte CMD_READ_FLASH_PP = 36;
  private static final byte CMD_READ_FUSE_HVSP = 56;
  private static final byte CMD_READ_FUSE_ISP = 24;
  private static final byte CMD_READ_FUSE_PP = 40;
  private static final byte CMD_READ_LOCK_HVSP = 58;
  private static final byte CMD_READ_LOCK_ISP = 26;
  private static final byte CMD_READ_LOCK_PP = 42;
  private static final byte CMD_READ_OSCCAL_HVSP = 60;
  private static final byte CMD_READ_OSCCAL_ISP = 28;
  private static final byte CMD_READ_OSCCAL_PP = 44;
  private static final byte CMD_READ_SIGNATURE_HVSP = 59;
  private static final byte CMD_READ_SIGNATURE_ISP = 27;
  private static final byte CMD_READ_SIGNATURE_PP = 43;
  private static final byte CMD_SET_CONTROL_STACK = 45;
  private static final byte CMD_SET_DEVICE_PARAMETERS = 4;
  private static final byte CMD_SET_PARAMETER = 2;
  private static final byte CMD_SIGN_ON = 1;
  private static final byte CMD_SPI_MULTI = 29;
  private static final byte MESSAGE_START = 11;
  private static final byte PARAM_BUILD_NUMBER_HIGH = -127;
  private static final byte PARAM_BUILD_NUMBER_LOW = -128;
  private static final byte PARAM_CONTROLLER_INIT = -97;
  private static final byte PARAM_DATA = -99;
  private static final byte PARAM_HW_VER = -112;
  private static final byte PARAM_OSC_CMATCH = -105;
  private static final byte PARAM_OSC_PSCALE = -106;
  private static final byte PARAM_RESET_POLARITY = -98;
  private static final byte PARAM_SCK_DURATION = -104;
  private static final byte PARAM_STATUS = -100;
  private static final byte PARAM_SW_MAJOR = -111;
  private static final byte PARAM_SW_MINOR = -110;
  private static final byte PARAM_TOPCARD_DETECT = -102;
  private static final byte PARAM_VADJUST = -107;
  private static final byte PARAM_VTARGET = -108;
  private static final byte STATUS_CKSUM_ERROR = -63;
  private static final byte STATUS_CMD_FAILED = -64;
  private static final byte STATUS_CMD_OK = 0;
  private static final byte STATUS_CMD_TOUT = -128;
  private static final byte STATUS_CMD_UNKNOWN = -55;
  private static final byte STATUS_RDY_BSY_TOUT = -127;
  private static final byte STATUS_SET_PARAM_MISSING = -126;
  private static final byte TOKEN = 14;
  int codePageIndex = 1;
  int codePageSize = 256;
  boolean isFlashLargerThan64KB = true;
  byte requestIndex = 1;
  
  public AurigaProgrammer()
  {
    this.firmwareData = AssetsUtils.getFirmwareFromAssetsFile(App.getContext(), "firmware/auriga.hex");
  }
  
  private void doResetIO() {}
  
  private byte[] makePacket(byte[] paramArrayOfByte)
  {
    int m = paramArrayOfByte.length;
    int i = (byte)(paramArrayOfByte.length & 0xFF);
    int j = (byte)(paramArrayOfByte.length >> 8);
    byte[] arrayOfByte = new byte[m + 6];
    arrayOfByte[0] = 27;
    int k = this.requestIndex;
    this.requestIndex = ((byte)(k + 1));
    arrayOfByte[1] = k;
    arrayOfByte[2] = j;
    arrayOfByte[3] = i;
    arrayOfByte[4] = 14;
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 5, paramArrayOfByte.length);
    i = arrayOfByte[0];
    m = 1;
    while (m < arrayOfByte.length - 1)
    {
      i = (byte)(arrayOfByte[m] ^ i);
      m += 1;
    }
    arrayOfByte[(arrayOfByte.length - 1)] = i;
    return arrayOfByte;
  }
  
  private void resetIO()
  {
    Observable.just(null).observeOn(Schedulers.io()).subscribe(new Action1()
    {
      public void call(Object paramAnonymousObject)
      {
        AurigaProgrammer.this.doResetIO();
      }
    });
  }
  
  private void sendBytesInPacket(byte[] paramArrayOfByte)
  {
    ControllerManager.getInstance().sendCommand(new Command.Builder().appendCommand(makePacket(paramArrayOfByte)).build());
  }
  
  private byte[] unpackPacket(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte.length < 5) || (paramArrayOfByte[0] != 27) || (paramArrayOfByte[4] != 14))
    {
      Log.d("lyh", "固件回复数据格式不对");
      return paramArrayOfByte;
    }
    byte[] arrayOfByte = new byte[paramArrayOfByte[2] << 8 | paramArrayOfByte[3]];
    System.arraycopy(paramArrayOfByte, 5, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
  
  public void onReadData(UUID paramUUID1, UUID paramUUID2, byte[] paramArrayOfByte) {}
  
  protected void processProgramming()
  {
    int j;
    int i;
    switch (this.ispStatus)
    {
    case ???: 
    case ???: 
    case ???: 
    default: 
    case ???: 
    case ???: 
    case ???: 
    case ???: 
    case ???: 
    case ???: 
    case ???: 
      for (;;)
      {
        resetTimeoutTimer();
        return;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusGetMajorVersion;
        sendBytesInPacket(new byte[] { 3, -112 });
        addExpectedLengthOfResponses(3);
        continue;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusGetMinorVersion;
        sendBytesInPacket(new byte[] { 3, -111 });
        addExpectedLengthOfResponses(3);
        continue;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusEnterProgrammingMode;
        sendBytesInPacket(new byte[] { 3, -110 });
        addExpectedLengthOfResponses(3);
        continue;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusSendAddress;
        addExpectedResponses(new byte[] { 16, 0 });
        sendBytesInPacket(new byte[] { 16, -56, 100, 25, 32, 0, 83, 3, -84, 83, 0, 0 });
        continue;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusFinished;
        addExpectedResponses(new byte[] { 17, 0 });
        sendBytesInPacket(new byte[] { 17, 1, 1 });
        continue;
        resetIO();
        success();
        continue;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusSendCode;
        i1 = this.codePageIndex * this.codePageSize / 2;
        int k = (byte)(i1 & 0xFF);
        int m = (byte)(i1 >> 8 & 0xFF);
        int n = (byte)(i1 >> 16 & 0xFF);
        j = (byte)(i1 >> 24 & 0xFF);
        i = j;
        if (this.isFlashLargerThan64KB) {
          i = (byte)(j | 0x80);
        }
        addExpectedResponses(new byte[] { 6, 0 });
        sendBytesInPacket(new byte[] { 6, i, n, m, k });
      }
    }
    this.ispStatus = Programmer.ProgrammerStatus.ISPStatusSendAddress;
    int i1 = this.codePageSize;
    this.progress = (this.codePageIndex * this.codePageSize * 1.0F / this.firmwareData.length);
    if (this.progress > 1.0D) {}
    for (float f = 1.0F;; f = this.progress)
    {
      this.progress = f;
      if (this.listener != null) {
        this.listener.onProgressChange(this.progress);
      }
      if ((this.codePageIndex + 1) * this.codePageSize > this.firmwareData.length)
      {
        i1 = this.firmwareData.length - this.codePageIndex * this.codePageSize;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusLeaveProgrammingMode;
      }
      i = (byte)(i1 & 0xFF);
      j = (byte)(i1 >> 8);
      byte[] arrayOfByte1 = new byte[10];
      arrayOfByte1[0] = 19;
      arrayOfByte1[1] = j;
      arrayOfByte1[2] = i;
      arrayOfByte1[3] = -63;
      arrayOfByte1[4] = 10;
      arrayOfByte1[5] = 64;
      arrayOfByte1[6] = 76;
      arrayOfByte1[7] = 32;
      arrayOfByte1[8] = 0;
      arrayOfByte1[9] = 0;
      byte[] arrayOfByte2 = new byte[i1 + 10];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
      System.arraycopy(this.firmwareData, this.codePageIndex * this.codePageSize, arrayOfByte2, arrayOfByte1.length, i1);
      this.codePageIndex += 1;
      addExpectedResponses(new byte[] { 19, 0 });
      sendBytesInPacket(arrayOfByte2);
      break;
    }
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    compareData(unpackPacket(paramArrayOfByte));
  }
  
  public boolean startProgram()
  {
    Observable.just(null).observeOn(Schedulers.io()).subscribe(new Action1()
    {
      public void call(Object paramAnonymousObject)
      {
        for (;;)
        {
          try
          {
            AurigaProgrammer.this.doResetIO();
          }
          catch (Exception paramAnonymousObject)
          {
            ((Exception)paramAnonymousObject).printStackTrace();
            return;
          }
          Thread.sleep(100);
          ControllerManager.getInstance().sendCommand(new Command.Builder().appendCommand(new byte[] { 1 }).build());
          return;
        }
      }
    });
    addExpectedLengthOfResponses(11);
    this.ispStatus = Programmer.ProgrammerStatus.ISPStatusGetHardwareVersion;
    return true;
  }
  
  protected void success()
  {
    super.success();
    if (this.listener != null)
    {
      this.listener.onSuccess();
      this.listener = null;
    }
    Log.d("lyh", "固件刷新成功");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\bluetooth\programmer\AurigaProgrammer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */