package cc.makeblock.makeblock.customview.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.util.Preconditions;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class GlideRoundedCorners
  extends BitmapTransformation
{
  private static final String ID = "cc.makeblock.makeblock.customview.util.GlideRoundedCorners";
  private static final byte[] ID_BYTES = "cc.makeblock.makeblock.customview.util.GlideRoundedCorners".getBytes(CHARSET);
  private final int roundingRadius;
  
  public GlideRoundedCorners(int paramInt)
  {
    if (paramInt > 0) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "roundingRadius must be greater than 0.");
      this.roundingRadius = paramInt;
      return;
    }
  }
  
  private static Bitmap getAlphaSafeBitmap(@NonNull BitmapPool paramBitmapPool, @NonNull Bitmap paramBitmap)
  {
    if (Bitmap.Config.ARGB_8888.equals(paramBitmap.getConfig())) {
      return paramBitmap;
    }
    paramBitmapPool = paramBitmapPool.get(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    new Canvas(paramBitmapPool).drawBitmap(paramBitmap, 0.0F, 0.0F, null);
    return paramBitmapPool;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof GlideRoundedCorners)) && (((GlideRoundedCorners)paramObject).roundingRadius == this.roundingRadius);
  }
  
  public int hashCode()
  {
    return "cc.makeblock.makeblock.customview.util.GlideRoundedCorners".hashCode() + this.roundingRadius;
  }
  
  protected Bitmap transform(@NonNull BitmapPool paramBitmapPool, @NonNull Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (paramInt1 > 0)
    {
      bool = true;
      Preconditions.checkArgument(bool, "width must be greater than 0.");
      if (paramInt2 <= 0) {
        break label312;
      }
      bool = true;
      label22:
      Preconditions.checkArgument(bool, "height must be greater than 0.");
      if (this.roundingRadius <= 0) {
        break label318;
      }
    }
    label312:
    label318:
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "roundingRadius must be greater than 0.");
      Bitmap localBitmap1 = getAlphaSafeBitmap(paramBitmapPool, paramBitmap);
      Bitmap localBitmap2 = paramBitmapPool.get(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
      localBitmap2.setHasAlpha(true);
      Paint localPaint = new Paint();
      localPaint.setStyle(Paint.Style.FILL);
      localPaint.setColor(-16777216);
      localPaint.setAntiAlias(true);
      paramInt1 = localBitmap1.getWidth();
      paramInt2 = localBitmap1.getHeight();
      Rect localRect = new Rect(0, 0, paramInt1, paramInt2);
      int i = localBitmap2.getWidth();
      int j = localBitmap2.getHeight();
      float f = Math.max(i * 1.0F / paramInt1, j * 1.0F / paramInt2);
      RectF localRectF1 = new RectF(0.0F, 0.0F, paramInt1 * f, paramInt2 * f);
      RectF localRectF2 = new RectF(0.0F, 0.0F, i, j);
      Canvas localCanvas = new Canvas(localBitmap2);
      localCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
      localCanvas.drawRoundRect(localRectF2, this.roundingRadius, this.roundingRadius, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(localBitmap1, localRect, localRectF1, localPaint);
      localCanvas.setBitmap(null);
      localPaint.setXfermode(null);
      if (!localBitmap1.equals(paramBitmap)) {
        paramBitmapPool.put(localBitmap1);
      }
      return localBitmap2;
      bool = false;
      break;
      bool = false;
      break label22;
    }
  }
  
  public void updateDiskCacheKey(MessageDigest paramMessageDigest)
  {
    paramMessageDigest.update(ID_BYTES);
    paramMessageDigest.update(ByteBuffer.allocate(4).putInt(this.roundingRadius).array());
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\util\GlideRoundedCorners.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */