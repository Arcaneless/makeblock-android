package com.iflytek.cloud.ui;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;

public abstract interface RecognizerDialogListener
{
  public abstract void onError(SpeechError paramSpeechError);
  
  public abstract void onResult(RecognizerResult paramRecognizerResult, boolean paramBoolean);
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\ui\RecognizerDialogListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */