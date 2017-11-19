package cc.makeblock.makeblock.engine.utils;

public class FileNameToMIMEType
{
  private static String[][] MIME_MapTable;
  
  static
  {
    String[] arrayOfString1 = { ".apk", "application/vnd.Android.package-archive" };
    String[] arrayOfString2 = { ".avi", "video/x-msvideo" };
    String[] arrayOfString3 = { ".bmp", "image/bmp" };
    String[] arrayOfString4 = { ".c", "text/plain" };
    String[] arrayOfString5 = { ".conf", "text/plain" };
    String[] arrayOfString6 = { ".cpp", "text/plain" };
    String[] arrayOfString7 = { ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" };
    String[] arrayOfString8 = { ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" };
    String[] arrayOfString9 = { ".gif", "image/gif" };
    String[] arrayOfString10 = { ".gtar", "application/x-gtar" };
    String[] arrayOfString11 = { ".gz", "application/x-gzip" };
    String[] arrayOfString12 = { ".h", "text/plain" };
    String[] arrayOfString13 = { ".htm", "text/html" };
    String[] arrayOfString14 = { ".html", "text/html" };
    String[] arrayOfString15 = { ".jar", "application/java-archive" };
    String[] arrayOfString16 = { ".java", "text/plain" };
    String[] arrayOfString17 = { ".js", "application/x-javascript" };
    String[] arrayOfString18 = { ".log", "text/plain" };
    String[] arrayOfString19 = { ".m3u", "audio/x-mpegurl" };
    String[] arrayOfString20 = { ".m4a", "audio/mp4a-latm" };
    String[] arrayOfString21 = { ".mov", "video/quicktime" };
    String[] arrayOfString22 = { ".mp2", "audio/x-mpeg" };
    String[] arrayOfString23 = { ".mpc", "application/vnd.mpohun.certificate" };
    String[] arrayOfString24 = { ".mpe", "video/mpeg" };
    String[] arrayOfString25 = { ".mpeg", "video/mpeg" };
    String[] arrayOfString26 = { ".mpg4", "video/mp4" };
    String[] arrayOfString27 = { ".mpga", "audio/mpeg" };
    String[] arrayOfString28 = { ".msg", "application/vnd.ms-outlook" };
    String[] arrayOfString29 = { ".ogg", "audio/ogg" };
    String[] arrayOfString30 = { ".pdf", "application/pdf" };
    String[] arrayOfString31 = { ".png", "image/png" };
    String[] arrayOfString32 = { ".ppt", "application/vnd.ms-powerpoint" };
    String[] arrayOfString33 = { ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation" };
    String[] arrayOfString34 = { ".prop", "text/plain" };
    String[] arrayOfString35 = { ".rc", "text/plain" };
    String[] arrayOfString36 = { ".sh", "text/plain" };
    String[] arrayOfString37 = { ".tgz", "application/x-compressed" };
    String[] arrayOfString38 = { ".txt", "text/plain" };
    String[] arrayOfString39 = { ".wav", "audio/x-wav" };
    String[] arrayOfString40 = { ".wma", "audio/x-ms-wma" };
    String[] arrayOfString41 = { ".wmv", "audio/x-ms-wmv" };
    String[] arrayOfString42 = { ".zip", "application/x-zip-compressed" };
    MIME_MapTable = new String[][] { { ".3gp", "video/3gpp" }, arrayOfString1, { ".asf", "video/x-ms-asf" }, arrayOfString2, { ".bin", "application/octet-stream" }, arrayOfString3, arrayOfString4, { ".class", "application/octet-stream" }, arrayOfString5, arrayOfString6, { ".doc", "application/msword" }, arrayOfString7, { ".xls", "application/vnd.ms-excel" }, arrayOfString8, { ".exe", "application/octet-stream" }, arrayOfString9, arrayOfString10, arrayOfString11, arrayOfString12, arrayOfString13, arrayOfString14, arrayOfString15, arrayOfString16, { ".jpeg", "image/jpeg" }, { ".jpg", "image/jpeg" }, arrayOfString17, arrayOfString18, arrayOfString19, arrayOfString20, { ".m4b", "audio/mp4a-latm" }, { ".m4p", "audio/mp4a-latm" }, { ".m4u", "video/vnd.mpegurl" }, { ".m4v", "video/x-m4v" }, arrayOfString21, arrayOfString22, { ".mp3", "audio/x-mpeg" }, { ".mp4", "video/mp4" }, arrayOfString23, arrayOfString24, arrayOfString25, { ".mpg", "video/mpeg" }, arrayOfString26, arrayOfString27, arrayOfString28, arrayOfString29, arrayOfString30, arrayOfString31, { ".pps", "application/vnd.ms-powerpoint" }, arrayOfString32, arrayOfString33, arrayOfString34, arrayOfString35, { ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" }, arrayOfString36, { ".tar", "application/x-tar" }, arrayOfString37, arrayOfString38, arrayOfString39, arrayOfString40, arrayOfString41, { ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" }, { ".z", "application/x-compress" }, arrayOfString42, { "", "*/*" } };
  }
  
  public static String getMIMEType(String paramString)
  {
    String str1 = paramString;
    if (paramString == null) {
      str1 = "";
    }
    String str2 = "*/*";
    int i = str1.lastIndexOf(".");
    if (i < 0) {
      return "*/*";
    }
    str1 = str1.substring(i, str1.length()).toLowerCase();
    if (str1 == "") {
      return "*/*";
    }
    i = 0;
    for (;;)
    {
      paramString = str2;
      if (i < MIME_MapTable.length)
      {
        if (str1.equals(MIME_MapTable[i][0])) {
          paramString = MIME_MapTable[i][1];
        }
      }
      else {
        return paramString;
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\utils\FileNameToMIMEType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */