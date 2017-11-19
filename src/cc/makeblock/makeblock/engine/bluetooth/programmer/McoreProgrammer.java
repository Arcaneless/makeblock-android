package cc.makeblock.makeblock.engine.bluetooth.programmer;

import android.util.Log;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.engine.Command.Builder;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.bluetooth.BleManager;
import cc.makeblock.makeblock.engine.firmware.Programmer.OnProgressChangeListener;
import cc.makeblock.makeblock.engine.utils.AssetsUtils;
import java.util.UUID;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class McoreProgrammer
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
  private static final int OrionISPChunkSize = 256;
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
  private static final int STK_ENTER_PROGMODE = 80;
  private static final int STK_FAILED = 17;
  private static final int STK_GET_PARAMETER = 65;
  private static final int STK_GET_SYNCH = 48;
  private static final int STK_INSYNC = 20;
  private static final int STK_LEAVE_PROGMODE = 81;
  private static final int STK_LOAD_ADDRESS = 85;
  private static final int STK_NOSYNC = 21;
  private static final int STK_OK = 16;
  private static final int STK_PROGRAM_FLASH = 70;
  private static final int STK_PROGRAM_PAGE = 100;
  private static final int STK_READ_SIGN = 117;
  private static final int STK_SW_MAJOR = 129;
  private static final int STK_SW_MINOR = 130;
  private static final int STK_UNKNOWN = 18;
  private static final int SYNC_CRC_EOP = 32;
  private static final byte TOKEN = 14;
  int codePageIndex = 1;
  boolean isFlashLargerThan64KB = true;
  byte requestIndex = 1;
  private final byte[] stkDeviceSignatureResponse = { 20, 63, 63, 63, 16 };
  private final byte[] stkEnterProgrammingModeCommand = { 80, 32 };
  private final byte[] stkGetDeviceSignatureCommand = { 117, 32 };
  private final byte[] stkLeaveProgrammingModeCommand = { 81, 32 };
  private final byte[] stkOKResponse = { 20, 16 };
  
  public McoreProgrammer()
  {
    this.firmwareData = AssetsUtils.getFirmwareFromAssetsFile(App.getContext(), "firmware/Blink.hex");
  }
  
  private void doResetIO() {}
  
  private void sendData(byte[] paramArrayOfByte)
  {
    ControllerManager.getInstance().sendCommand(new Command.Builder().appendCommand(paramArrayOfByte).build());
  }
  
  public void onReadData(UUID paramUUID1, UUID paramUUID2, byte[] paramArrayOfByte) {}
  
  protected void processProgramming()
  {
    if (this.ispStatus == null) {
      Log.e("lyh", "真假");
    }
    int i;
    int j;
    switch (this.ispStatus)
    {
    default: 
    case ???: 
    case ???: 
    case ???: 
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
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusGetDeviceSignature;
        sendData(new byte[] { 80, 32 });
        addExpectedResponses(new byte[] { 20, 16 });
        continue;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusSendAddress;
        addExpectedLengthOfResponses(5);
        sendData(new byte[] { 117, 32 });
        continue;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusGetMinorVersion;
        sendData(new byte[] { 3, -111 });
        addExpectedLengthOfResponses(3);
        continue;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusEnterProgrammingMode;
        sendData(new byte[] { 3, -110 });
        addExpectedLengthOfResponses(3);
        continue;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusGetDeviceSignature;
        addExpectedResponses(this.stkOKResponse);
        sendData(this.stkEnterProgrammingModeCommand);
        continue;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusFinished;
        addExpectedResponses(this.stkOKResponse);
        sendData(this.stkLeaveProgrammingModeCommand);
        continue;
        Observable.just(null).observeOn(Schedulers.io()).subscribe(new Action1()
        {
          public void call(Object paramAnonymousObject)
          {
            try
            {
              Thread.sleep(200L);
              McoreProgrammer.this.doResetIO();
              return;
            }
            catch (InterruptedException paramAnonymousObject)
            {
              for (;;)
              {
                ((InterruptedException)paramAnonymousObject).printStackTrace();
              }
            }
          }
        });
        success();
        continue;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusSendCode;
        k = this.codePageIndex * 256 / 4;
        i = (byte)(k & 0xFF);
        j = (byte)(k >> 8);
        addExpectedResponses(new byte[] { 20, 16 });
        sendData(new byte[] { 85, i, j, 32 });
      }
    }
    this.ispStatus = Programmer.ProgrammerStatus.ISPStatusSendAddress;
    int k = 128;
    this.progress = ((float)((this.codePageIndex + 1) * 128 * 1.0D / this.firmwareData.length));
    if (this.progress > 1.0D) {}
    for (float f = 1.0F;; f = this.progress)
    {
      this.progress = f;
      if (this.listener != null) {
        this.listener.onProgressChange(this.progress);
      }
      if ((this.codePageIndex + 1) * 128 > this.firmwareData.length)
      {
        k = this.firmwareData.length - this.codePageIndex * 128;
        this.ispStatus = Programmer.ProgrammerStatus.ISPStatusLeaveProgrammingMode;
      }
      i = (byte)(k & 0xFF);
      j = (byte)(k >> 8);
      byte[] arrayOfByte1 = new byte[4];
      arrayOfByte1[0] = 100;
      arrayOfByte1[1] = j;
      arrayOfByte1[2] = i;
      arrayOfByte1[3] = 70;
      byte[] arrayOfByte2 = new byte[1];
      arrayOfByte2[0] = 32;
      byte[] arrayOfByte3 = new byte[arrayOfByte1.length + arrayOfByte2.length + k];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, arrayOfByte1.length);
      System.arraycopy(this.firmwareData, this.codePageIndex * k, arrayOfByte3, arrayOfByte1.length, k);
      System.arraycopy(arrayOfByte2, 0, arrayOfByte3, arrayOfByte3.length - arrayOfByte2.length, arrayOfByte2.length);
      this.codePageIndex += 1;
      addExpectedResponses(new byte[] { 20, 16 });
      sendData(arrayOfByte3);
      break;
    }
  }
  
  public void receiveData(byte[] paramArrayOfByte)
  {
    compareData(paramArrayOfByte);
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
            McoreProgrammer.this.doResetIO();
          }
          catch (Exception paramAnonymousObject)
          {
            ((Exception)paramAnonymousObject).printStackTrace();
            return;
          }
          Thread.sleep(100);
          McoreProgrammer.this.addExpectedResponses(new byte[] { 20, 16 });
          ControllerManager.getInstance().sendCommand(new Command.Builder().appendCommand(new byte[] { 48, 32 }).build());
          return;
        }
      }
    });
    this.ispStatus = Programmer.ProgrammerStatus.ISPStatusGetHardwareVersion;
    return true;
  }
  
  protected void success()
  {
    super.success();
    BleManager.getInstance().stopProgram();
    if (this.listener != null)
    {
      this.listener.onSuccess();
      this.listener = null;
    }
    Log.d("lyh", "固件刷新成功");
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\bluetooth\programmer\McoreProgrammer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */