package cc.makeblock.makeblock.customview.drawable;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import cc.makeblock.makeblock.base.App;
import cc.makeblock.makeblock.bean.ChooseDeviceItem;
import java.util.List;

public class ChooseDeviceDrawable
  extends Drawable
{
  private SparseArray<Bitmap> mCacheBitmap = new SparseArray(3);
  private Bitmap mCurrentBitmap;
  private int mCurrentIndex = -1;
  private Paint mCurrentPaint = new Paint(1);
  private List<ChooseDeviceItem> mDrawables;
  private Bitmap mLeftBitmap;
  private Bitmap mNextBitmap;
  private Paint mNextPaint = new Paint(1);
  private Bitmap mRightBitmap;
  private int size;
  
  private void recyclerAndCacheBitmap(int[] paramArrayOfInt)
  {
    int j = this.mCacheBitmap.size();
    int i = 0;
    while (i < j)
    {
      int k = this.mCacheBitmap.keyAt(i);
      ((Bitmap)this.mCacheBitmap.get(k)).recycle();
      i += 1;
    }
    this.mCacheBitmap.clear();
    this.mCacheBitmap.put(paramArrayOfInt[0], this.mLeftBitmap);
    this.mCacheBitmap.put(paramArrayOfInt[1], this.mCurrentBitmap);
    this.mCacheBitmap.put(paramArrayOfInt[2], this.mRightBitmap);
  }
  
  private void setShowBitmap(int[] paramArrayOfInt)
  {
    this.mLeftBitmap = getBitmap(paramArrayOfInt[0]);
    this.mCurrentBitmap = getBitmap(paramArrayOfInt[1]);
    this.mRightBitmap = getBitmap(paramArrayOfInt[2]);
  }
  
  public void draw(Canvas paramCanvas)
  {
    if ((this.mCurrentBitmap != null) && (!this.mCurrentBitmap.isRecycled())) {
      paramCanvas.drawBitmap(this.mCurrentBitmap, null, getBounds(), this.mCurrentPaint);
    }
    if ((this.mNextBitmap != null) && (!this.mNextBitmap.isRecycled())) {
      paramCanvas.drawBitmap(this.mNextBitmap, null, getBounds(), this.mNextPaint);
    }
  }
  
  public Bitmap getBitmap(int paramInt)
  {
    Bitmap localBitmap = (Bitmap)this.mCacheBitmap.get(paramInt);
    if (localBitmap == null)
    {
      new BitmapFactory.Options().inPreferredConfig = Bitmap.Config.RGB_565;
      return BitmapFactory.decodeResource(App.getContext().getResources(), ((ChooseDeviceItem)this.mDrawables.get(paramInt)).deviceBg);
    }
    this.mCacheBitmap.remove(paramInt);
    return localBitmap;
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public void refresh(int paramInt, float paramFloat)
  {
    if (paramFloat == 0.0F) {
      return;
    }
    int i = (int)(255.0F * paramFloat);
    if (this.mCurrentIndex == paramInt)
    {
      this.mNextBitmap = this.mRightBitmap;
      this.mCurrentPaint.setAlpha(255 - i);
      this.mNextPaint.setAlpha(i);
    }
    for (;;)
    {
      invalidateSelf();
      return;
      this.mNextBitmap = this.mLeftBitmap;
      this.mCurrentPaint.setAlpha(i);
      this.mNextPaint.setAlpha(255 - i);
    }
  }
  
  public void setAlpha(int paramInt)
  {
    this.mCurrentPaint.setAlpha(paramInt);
    this.mNextPaint.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.mCurrentPaint.setColorFilter(paramColorFilter);
    this.mNextPaint.setColorFilter(paramColorFilter);
  }
  
  public void setCurrentPage(int paramInt)
  {
    if (this.mDrawables == null) {}
    while (this.mCurrentIndex == paramInt) {
      return;
    }
    this.mCurrentIndex = paramInt;
    int[] arrayOfInt = new int[3];
    if (paramInt == 0)
    {
      arrayOfInt[0] = (this.size - 1);
      arrayOfInt[1] = paramInt;
      arrayOfInt[2] = (paramInt + 1);
    }
    for (;;)
    {
      setShowBitmap(arrayOfInt);
      recyclerAndCacheBitmap(arrayOfInt);
      this.mCurrentPaint.setAlpha(255);
      this.mNextBitmap = null;
      return;
      if (paramInt == this.size - 1)
      {
        arrayOfInt[0] = (paramInt - 1);
        arrayOfInt[1] = paramInt;
        arrayOfInt[2] = 0;
      }
      else
      {
        arrayOfInt[0] = (paramInt - 1);
        arrayOfInt[1] = paramInt;
        arrayOfInt[2] = (paramInt + 1);
      }
    }
  }
  
  public void setDrawables(List<ChooseDeviceItem> paramList)
  {
    this.mDrawables = paramList;
    this.size = this.mDrawables.size();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\drawable\ChooseDeviceDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */