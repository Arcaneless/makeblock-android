package com.iflytek.cloud;

import android.os.Bundle;

public abstract interface RequestListener
{
  public abstract void onBufferReceived(byte[] paramArrayOfByte);
  
  public abstract void onCompleted(SpeechError paramSpeechError);
  
  public abstract void onEvent(int paramInt, Bundle paramBundle);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\RequestListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */