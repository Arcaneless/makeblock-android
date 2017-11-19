package cc.makeblock.makeblock.engine.firmware.airblock;

import android.os.Handler;
import android.util.Log;
import cc.makeblock.makeblock.engine.firmware.HexHelper;
import cc.makeblock.makeblock.engine.firmware.Programmer;
import cc.makeblock.makeblock.engine.firmware.Programmer.OnProgressChangeListener;
import java.util.UUID;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class AirblockProgrammer
  extends Programmer
{
  private AirblockUpdateCommand currentCommand;
  private HexHelper hexHelper = new AirblockHexHelper();
  private Handler mHandler = new Handler();
  
  public AirblockProgrammer(Programmer.OnProgressChangeListener paramOnProgressChangeListener)
  {
    super(paramOnProgressChangeListener);
  }
  
  public void onReadData(UUID paramUUID1, UUID paramUUID2, byte[] paramArrayOfByte) {}
  
  public void onReceive(final byte[] paramArrayOfByte)
  {
    Observable.just(null).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Action1()
    {
      public void call(Object paramAnonymousObject)
      {
        if (AirblockProgrammer.this.currentCommand != null)
        {
          paramAnonymousObject = "";
          int i = 0;
          while (i < paramArrayOfByte.length)
          {
            paramAnonymousObject = (String)paramAnonymousObject + String.format(" %02x", new Object[] { Integer.valueOf(paramArrayOfByte[i] & 0xFF) });
            i += 1;
          }
          AirblockProgrammer.this.currentCommand.receiveData(paramArrayOfByte);
        }
      }
    });
  }
  
  public void startProgram()
  {
    this.hexHelper.reset();
    updateProgramStatus(UpdateStatus.REQUEST_OFFSET_ANGLE, "开始升级");
  }
  
  public void updateProgramStatus(UpdateStatus paramUpdateStatus, String paramString)
  {
    Log.e("lyh", paramUpdateStatus.name());
    switch (paramUpdateStatus)
    {
    default: 
      this.currentCommand = null;
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
    case ???: 
      for (;;)
      {
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            if (AirblockProgrammer.this.currentCommand != null)
            {
              AirblockProgrammer.this.currentCommand.execute();
              return;
            }
            AirblockProgrammer.this.onProgressChangeListener.onFail();
          }
        });
        return;
        this.currentCommand = new RequestOffsetAngleCommand(this);
        continue;
        this.currentCommand = new SkipBleCheckCommand(this);
        continue;
        this.currentCommand = new EnterBootCommand(this);
        continue;
        this.currentCommand = new HandShake1Command(this);
        continue;
        this.currentCommand = new CheckLockCommand(this);
        continue;
        this.currentCommand = new UnlockCommand(this);
        continue;
        this.currentCommand = new HandShake3Command(this);
        continue;
        this.currentCommand = new EnterBoot2Command(this);
        continue;
        this.currentCommand = new HandShake2Command(this);
        continue;
        this.currentCommand = new Erase1Command(this);
        continue;
        this.currentCommand = new Erase2Command(this);
        continue;
        this.currentCommand = new WriteCommand(this);
        continue;
        float f = this.hexHelper.getPercent();
        this.onProgressChangeListener.onProgressChange(f);
        this.currentCommand = new SendAddressCommand(this, this.hexHelper);
        continue;
        this.currentCommand = new WriteCodeCommand(this, this.hexHelper);
        continue;
        this.currentCommand = new LockCommand(this);
        continue;
        this.currentCommand = new ResumeBleCheckCommand(this);
        continue;
        this.currentCommand = new ExitBootCommand(this);
        continue;
        this.currentCommand = new FailRequestAngleCommand(this);
        continue;
        this.currentCommand = new SetOffsetAngleCommand(this, this.mHandler);
        continue;
        this.currentCommand = null;
        this.onProgressChangeListener.onSuccess();
        return;
        Log.e("lyh", paramString);
        this.currentCommand = new ResumeBleCheckOnFailCommand(this);
      }
    }
    this.currentCommand = null;
    this.onProgressChangeListener.onFail();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\firmware\airblock\AirblockProgrammer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */