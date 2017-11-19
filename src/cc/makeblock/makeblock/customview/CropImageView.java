package cc.makeblock.makeblock.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView.ScaleType;
import cc.makeblock.makeblock.R.styleable;
import java.util.HashMap;
import java.util.Map;

public class CropImageView
  extends AppCompatImageView
{
  private CropType cropType = CropType.NONE;
  private ImageMaths imageMaths;
  
  public CropImageView(Context paramContext)
  {
    super(paramContext);
    initImageView();
  }
  
  public CropImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CropImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    parseAttributes(paramAttributeSet);
    initImageView();
  }
  
  @TargetApi(21)
  public CropImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1);
    parseAttributes(paramAttributeSet);
    initImageView();
  }
  
  private void computeImageMatrix()
  {
    int i = getWidth() - getPaddingLeft() - getPaddingRight();
    int j = getHeight() - getPaddingTop() - getPaddingBottom();
    Matrix localMatrix;
    int k;
    int m;
    float f2;
    float f3;
    float f1;
    if ((this.cropType != CropType.NONE) && (j > 0) && (i > 0))
    {
      localMatrix = this.imageMaths.getMatrix();
      k = getDrawable().getIntrinsicWidth();
      m = getDrawable().getIntrinsicHeight();
      f2 = j / m;
      f3 = i / k;
      if (f3 <= f2) {
        break label174;
      }
      f1 = f3;
      localMatrix.setScale(f1, f1);
      if (f3 <= f2) {
        break label179;
      }
    }
    label174:
    label179:
    for (boolean bool = true;; bool = false)
    {
      f2 = k;
      f2 = getXTranslation(this.cropType, i, f2 * f1, bool);
      f3 = m;
      localMatrix.postTranslate(f2, getYTranslation(this.cropType, j, f3 * f1, bool));
      setImageMatrix(localMatrix);
      return;
      f1 = f2;
      break;
    }
  }
  
  private float getXTranslation(CropType paramCropType, int paramInt, float paramFloat, boolean paramBoolean)
  {
    if (!paramBoolean) {}
    switch (paramCropType)
    {
    case ???: 
    case ???: 
    default: 
      return 0.0F;
    case ???: 
    case ???: 
    case ???: 
      return paramInt - paramFloat;
    }
    return (paramInt - paramFloat) / 2.0F;
  }
  
  private float getYTranslation(CropType paramCropType, int paramInt, float paramFloat, boolean paramBoolean)
  {
    if (paramBoolean) {}
    switch (paramCropType)
    {
    default: 
      return 0.0F;
    case ???: 
    case ???: 
    case ???: 
      return paramInt - paramFloat;
    }
    return (paramInt - paramFloat) / 2.0F;
  }
  
  private void initImageView()
  {
    this.imageMaths = new ImageMathsFactory(null).getImageMaths(this);
  }
  
  private void parseAttributes(AttributeSet paramAttributeSet)
  {
    paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.civ_CropImageView);
    int i = paramAttributeSet.getInt(0, CropType.NONE.getCrop());
    if (i >= 0)
    {
      setScaleType(ImageView.ScaleType.MATRIX);
      this.cropType = CropType.get(i);
    }
    paramAttributeSet.recycle();
  }
  
  public CropType getCropType()
  {
    return this.cropType;
  }
  
  public void setCropType(CropType paramCropType)
  {
    this.cropType = paramCropType;
  }
  
  protected boolean setFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool = super.setFrame(paramInt1, paramInt2, paramInt3, paramInt4);
    if (!isInEditMode()) {
      computeImageMatrix();
    }
    return bool;
  }
  
  private class API18Image
    extends CropImageView.CropImage
    implements CropImageView.ImageMaths
  {
    API18Image(CropImageView paramCropImageView)
    {
      super(paramCropImageView);
    }
    
    public Matrix getMatrix()
    {
      return this.imageView.getImageMatrix();
    }
  }
  
  abstract class CropImage
  {
    @NonNull
    final CropImageView imageView;
    
    CropImage(CropImageView paramCropImageView)
    {
      this.imageView = paramCropImageView;
    }
  }
  
  public static enum CropType
  {
    private static final Map<Integer, CropType> codeLookup;
    final int cropType;
    
    static
    {
      int i = 0;
      NONE = new CropType("NONE", 0, -1);
      LEFT_TOP = new CropType("LEFT_TOP", 1, 0);
      LEFT_CENTER = new CropType("LEFT_CENTER", 2, 1);
      LEFT_BOTTOM = new CropType("LEFT_BOTTOM", 3, 2);
      RIGHT_TOP = new CropType("RIGHT_TOP", 4, 3);
      RIGHT_CENTER = new CropType("RIGHT_CENTER", 5, 4);
      RIGHT_BOTTOM = new CropType("RIGHT_BOTTOM", 6, 5);
      CENTER_TOP = new CropType("CENTER_TOP", 7, 6);
      CENTER_BOTTOM = new CropType("CENTER_BOTTOM", 8, 7);
      $VALUES = new CropType[] { NONE, LEFT_TOP, LEFT_CENTER, LEFT_BOTTOM, RIGHT_TOP, RIGHT_CENTER, RIGHT_BOTTOM, CENTER_TOP, CENTER_BOTTOM };
      codeLookup = new HashMap();
      CropType[] arrayOfCropType = values();
      int j = arrayOfCropType.length;
      while (i < j)
      {
        CropType localCropType = arrayOfCropType[i];
        codeLookup.put(Integer.valueOf(localCropType.getCrop()), localCropType);
        i += 1;
      }
    }
    
    private CropType(int paramInt)
    {
      this.cropType = paramInt;
    }
    
    public static CropType get(int paramInt)
    {
      return (CropType)codeLookup.get(Integer.valueOf(paramInt));
    }
    
    public int getCrop()
    {
      return this.cropType;
    }
  }
  
  static abstract interface ImageMaths
  {
    public abstract Matrix getMatrix();
  }
  
  private class ImageMathsFactory
  {
    private ImageMathsFactory() {}
    
    CropImageView.ImageMaths getImageMaths(@NonNull CropImageView paramCropImageView)
    {
      int i;
      if (Build.VERSION.SDK_INT < 18)
      {
        i = 1;
        if (i == 0) {
          break label37;
        }
      }
      label37:
      for (paramCropImageView = new CropImageView.PreApi18CropImage(CropImageView.this, paramCropImageView);; paramCropImageView = new CropImageView.API18Image(CropImageView.this, paramCropImageView))
      {
        return (CropImageView.ImageMaths)paramCropImageView;
        i = 0;
        break;
      }
    }
  }
  
  private class PreApi18CropImage
    extends CropImageView.CropImage
    implements CropImageView.ImageMaths
  {
    private Matrix matrix;
    
    PreApi18CropImage(CropImageView paramCropImageView)
    {
      super(paramCropImageView);
      init(paramCropImageView);
    }
    
    private void init(CropImageView paramCropImageView)
    {
      if (paramCropImageView.getCropType() != CropImageView.CropType.NONE) {
        this.matrix = new Matrix();
      }
    }
    
    public Matrix getMatrix()
    {
      if (this.matrix == null) {
        return this.imageView.getImageMatrix();
      }
      return this.matrix;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\CropImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */