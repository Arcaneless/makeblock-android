package com.iflytek.cloud.thirdparty;

import android.os.Environment;
import java.io.File;

public class ah
{
  public static String a = "wtime";
  public static final String b = Environment.getExternalStorageDirectory().getPath() + "/msc/ist/audio/";
  public static final String[][] c;
  
  static
  {
    String[] arrayOfString1 = { "asr_sch", "sch" };
    String[] arrayOfString2 = { "scene", "scn" };
    String[] arrayOfString3 = { "asr_nomatch_error", "asr_nme" };
    String[] arrayOfString4 = { "asr_ptt", "ptt" };
    String[] arrayOfString5 = { "vad_bos", "vad_timeout" };
    String[] arrayOfString6 = { "eos", "vad_speech_tail", "eos" };
    String[] arrayOfString7 = { "asr_dwa", "dwa" };
    String[] arrayOfString8 = { "ivw_net_mode", "ivwnet_mode" };
    c = new String[][] { { "surl", "server_url" }, { "besturl_search", "search_best_url" }, { "bsts", "search_best_url" }, arrayOfString1, arrayOfString2, arrayOfString3, arrayOfString4, { "result_type", "rst" }, { "result_level", "rst_level" }, arrayOfString5, { "bos", "vad_timeout" }, { "vad_eos", "vad_speech_tail", "eos" }, arrayOfString6, { "asr_audio_path", "aap" }, { "tts_buffer_time", "tbt" }, { "tts_audio_path", "tap" }, { "subject", "sub" }, { "data_type", "dtt" }, { "asr_nbest", "nbest" }, { "asr_wbest", "wbest" }, arrayOfString7, { "voice_name", "vcn" }, { "background_sound", "bgs" }, { "text_encoding", "tte" }, arrayOfString8 };
  }
  
  public static enum a
  {
    private a() {}
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\iflytek\cloud\thirdparty\ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */