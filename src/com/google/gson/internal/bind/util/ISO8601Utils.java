package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils
{
  private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");
  private static final String UTC_ID = "UTC";
  
  private static boolean checkOffset(String paramString, int paramInt, char paramChar)
  {
    return (paramInt < paramString.length()) && (paramString.charAt(paramInt) == paramChar);
  }
  
  public static String format(Date paramDate)
  {
    return format(paramDate, false, TIMEZONE_UTC);
  }
  
  public static String format(Date paramDate, boolean paramBoolean)
  {
    return format(paramDate, paramBoolean, TIMEZONE_UTC);
  }
  
  public static String format(Date paramDate, boolean paramBoolean, TimeZone paramTimeZone)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(paramTimeZone, Locale.US);
    localGregorianCalendar.setTime(paramDate);
    int k = "yyyy-MM-ddThh:mm:ss".length();
    int i;
    int j;
    label51:
    char c;
    if (paramBoolean)
    {
      i = ".sss".length();
      if (paramTimeZone.getRawOffset() != 0) {
        break label320;
      }
      j = "Z".length();
      paramDate = new StringBuilder(k + i + j);
      padInt(paramDate, localGregorianCalendar.get(1), "yyyy".length());
      paramDate.append('-');
      padInt(paramDate, localGregorianCalendar.get(2) + 1, "MM".length());
      paramDate.append('-');
      padInt(paramDate, localGregorianCalendar.get(5), "dd".length());
      paramDate.append('T');
      padInt(paramDate, localGregorianCalendar.get(11), "hh".length());
      paramDate.append(':');
      padInt(paramDate, localGregorianCalendar.get(12), "mm".length());
      paramDate.append(':');
      padInt(paramDate, localGregorianCalendar.get(13), "ss".length());
      if (paramBoolean)
      {
        paramDate.append('.');
        padInt(paramDate, localGregorianCalendar.get(14), "sss".length());
      }
      i = paramTimeZone.getOffset(localGregorianCalendar.getTimeInMillis());
      if (i == 0) {
        break label336;
      }
      j = Math.abs(i / 60000 / 60);
      k = Math.abs(i / 60000 % 60);
      if (i >= 0) {
        break label330;
      }
      c = '-';
      label274:
      paramDate.append(c);
      padInt(paramDate, j, "hh".length());
      paramDate.append(':');
      padInt(paramDate, k, "mm".length());
    }
    for (;;)
    {
      return paramDate.toString();
      i = 0;
      break;
      label320:
      j = "+hh:mm".length();
      break label51;
      label330:
      c = '+';
      break label274;
      label336:
      paramDate.append('Z');
    }
  }
  
  private static int indexOfNonDigit(String paramString, int paramInt)
  {
    while (paramInt < paramString.length())
    {
      int i = paramString.charAt(paramInt);
      if ((i < 48) || (i > 57)) {
        return paramInt;
      }
      paramInt += 1;
    }
    return paramString.length();
  }
  
  private static void padInt(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    String str = Integer.toString(paramInt1);
    paramInt1 = paramInt2 - str.length();
    while (paramInt1 > 0)
    {
      paramStringBuilder.append('0');
      paramInt1 -= 1;
    }
    paramStringBuilder.append(str);
  }
  
  public static Date parse(String paramString, ParsePosition paramParsePosition)
    throws ParseException
  {
    try
    {
      i = paramParsePosition.getIndex();
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      int i6;
      int i7;
      int i8;
      GregorianCalendar localGregorianCalendar;
      if (paramString != null) {
        break label758;
      }
      paramString = null;
      String str1 = localIndexOutOfBoundsException.getMessage();
      if (str1 == null) {
        break label316;
      }
      Object localObject2 = str1;
      if (!str1.isEmpty()) {
        break label349;
      }
      localObject2 = "(" + localIndexOutOfBoundsException.getClass().getName() + ")";
      paramString = new ParseException("Failed to parse date [" + paramString + "]: " + (String)localObject2, paramParsePosition.getIndex());
      paramString.initCause(localIndexOutOfBoundsException);
      throw paramString;
      i *= 10;
      break label1008;
      i *= 100;
      break label1008;
      c = paramString.charAt(i);
      break label1029;
      Object localObject1 = TIMEZONE_UTC;
      break label1038;
      String str2;
      do
      {
        do
        {
          localObject1 = new GregorianCalendar((TimeZone)localObject1);
          ((Calendar)localObject1).setLenient(false);
          ((Calendar)localObject1).set(1, i6);
          ((Calendar)localObject1).set(2, i7 - 1);
          ((Calendar)localObject1).set(5, i8);
          ((Calendar)localObject1).set(11, k);
          ((Calendar)localObject1).set(12, n);
          ((Calendar)localObject1).set(13, i1);
          ((Calendar)localObject1).set(14, m);
          paramParsePosition.setIndex(i);
          return ((Calendar)localObject1).getTime();
          localObject1 = paramString.substring(i);
          if (((String)localObject1).length() >= 5) {}
          for (;;)
          {
            j = i + ((String)localObject1).length();
            if ((!"+0000".equals(localObject1)) && (!"+00:00".equals(localObject1))) {
              break label603;
            }
            localObject1 = TIMEZONE_UTC;
            i = j;
            break;
            localObject1 = (String)localObject1 + "00";
          }
          str1 = "GMT" + (String)localObject1;
          localObject2 = TimeZone.getTimeZone(str1);
          str2 = ((TimeZone)localObject2).getID();
          i = j;
          localObject1 = localObject2;
        } while (str2.equals(str1));
        i = j;
        localObject1 = localObject2;
      } while (str2.replace(":", "").equals(str1));
      throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str1 + " given, resolves to " + ((TimeZone)localObject2).getID());
      throw new IndexOutOfBoundsException("Invalid time zone indicator '" + c + "'");
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        int i;
        int i2;
        boolean bool;
        int k;
        int i3;
        int i4;
        int m;
        int n;
        int i1;
        char c;
        continue;
        paramString = '"' + paramString + "'";
        continue;
        do
        {
          k = i2;
          m = i4;
          n = i3;
          i1 = j;
          break label266;
          i = k;
          break label171;
          i = j;
          break label843;
          j = i + 4;
          break;
          i = j + 1;
          j = i + 2;
          break label34;
          i = j + 1;
          i2 = i + 2;
          break label57;
          k = 0;
          n = 0;
          j = 0;
          i4 = 0;
          break label69;
          m = i4;
          i = i2;
          i1 = j;
          if (!bool) {
            break label266;
          }
          i = i2 + 1;
          k = i + 2;
          break label122;
          i = k + 1;
          k = i + 2;
          break label148;
          i = k + 1;
          break label171;
        } while ((k == 90) || (k == 43) || (k == 45));
        int i5 = i + 2;
        continue;
        int j = i;
        if (i > 59)
        {
          j = i;
          if (i < 63)
          {
            j = 59;
            continue;
            m = i5 + 1;
            continue;
            switch (k - m)
            {
            }
            k = i2;
            m = i;
            n = i3;
            i = i1;
            i1 = j;
            continue;
            if (c == 'Z')
            {
              continue;
              i += 1;
            }
            else if (c != '+')
            {
              if (c != '-') {}
            }
          }
        }
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      label34:
      label57:
      label69:
      label122:
      label148:
      label171:
      label266:
      label316:
      label349:
      label603:
      label758:
      label825:
      label838:
      label843:
      label851:
      label866:
      label895:
      label900:
      label908:
      label916:
      label945:
      label970:
      label979:
      label1008:
      label1029:
      label1038:
      for (;;) {}
    }
    i6 = parseInt(paramString, i, j);
    i = j;
    if (checkOffset(paramString, j, '-'))
    {
      break label825;
      i7 = parseInt(paramString, i, j);
      if (checkOffset(paramString, j, '-'))
      {
        break label838;
        i8 = parseInt(paramString, i, i2);
        break label851;
        bool = checkOffset(paramString, i2, 'T');
        if ((bool) || (paramString.length() > i2)) {
          break label866;
        }
        localGregorianCalendar = new GregorianCalendar(i6, i7 - 1, i8);
        paramParsePosition.setIndex(i2);
        return localGregorianCalendar.getTime();
        i2 = parseInt(paramString, i, k);
        i = k;
        if (!checkOffset(paramString, k, ':')) {
          break label900;
        }
        break label895;
        i3 = parseInt(paramString, i, k);
        if (checkOffset(paramString, k, ':'))
        {
          break label908;
          if (paramString.length() > i)
          {
            k = paramString.charAt(i);
            break label916;
            i = parseInt(paramString, i, i5);
            break label945;
            k = i2;
            m = i4;
            n = i3;
            i = i5;
            i1 = j;
            if (checkOffset(paramString, i5, '.'))
            {
              break label970;
              i1 = indexOfNonDigit(paramString, m + 1);
              k = Math.min(i1, m + 3);
              i = parseInt(paramString, m, k);
              break label979;
            }
            if (paramString.length() <= i) {
              throw new IllegalArgumentException("No time zone indicator");
            }
          }
        }
      }
    }
  }
  
  private static int parseInt(String paramString, int paramInt1, int paramInt2)
    throws NumberFormatException
  {
    if ((paramInt1 < 0) || (paramInt2 > paramString.length()) || (paramInt1 > paramInt2)) {
      throw new NumberFormatException(paramString);
    }
    int j = 0;
    int i;
    if (paramInt1 < paramInt2)
    {
      i = paramInt1 + 1;
      j = Character.digit(paramString.charAt(paramInt1), 10);
      if (j < 0) {
        throw new NumberFormatException("Invalid number: " + paramString.substring(paramInt1, paramInt2));
      }
      j = -j;
    }
    for (;;)
    {
      if (i < paramInt2)
      {
        int k = Character.digit(paramString.charAt(i), 10);
        if (k < 0) {
          throw new NumberFormatException("Invalid number: " + paramString.substring(paramInt1, paramInt2));
        }
        j = j * 10 - k;
        i += 1;
      }
      else
      {
        return -j;
        i = paramInt1;
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\gson\internal\bind\util\ISO8601Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */