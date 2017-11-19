package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.DialerKeyListener;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TextKeyListener;
import android.text.method.TextKeyListener.Capitalize;
import android.util.Log;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.android.databinding.library.baseAdapters.R.id;

@BindingMethods({@android.databinding.BindingMethod(attribute="android:autoLink", method="setAutoLinkMask", type=TextView.class), @android.databinding.BindingMethod(attribute="android:drawablePadding", method="setCompoundDrawablePadding", type=TextView.class), @android.databinding.BindingMethod(attribute="android:editorExtras", method="setInputExtras", type=TextView.class), @android.databinding.BindingMethod(attribute="android:inputType", method="setRawInputType", type=TextView.class), @android.databinding.BindingMethod(attribute="android:scrollHorizontally", method="setHorizontallyScrolling", type=TextView.class), @android.databinding.BindingMethod(attribute="android:textAllCaps", method="setAllCaps", type=TextView.class), @android.databinding.BindingMethod(attribute="android:textColorHighlight", method="setHighlightColor", type=TextView.class), @android.databinding.BindingMethod(attribute="android:textColorHint", method="setHintTextColor", type=TextView.class), @android.databinding.BindingMethod(attribute="android:textColorLink", method="setLinkTextColor", type=TextView.class), @android.databinding.BindingMethod(attribute="android:onEditorAction", method="setOnEditorActionListener", type=TextView.class)})
public class TextViewBindingAdapter
{
  public static final int DECIMAL = 5;
  public static final int INTEGER = 1;
  public static final int SIGNED = 3;
  private static final String TAG = "TextViewBindingAdapters";
  
  @InverseBindingAdapter(attribute="android:text", event="android:textAttrChanged")
  public static String getTextString(TextView paramTextView)
  {
    return paramTextView.getText().toString();
  }
  
  private static boolean haveContentsChanged(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    int j;
    if (paramCharSequence1 == null)
    {
      i = 1;
      if (paramCharSequence2 != null) {
        break label24;
      }
      j = 1;
      label12:
      if (i == j) {
        break label29;
      }
    }
    label24:
    label29:
    do
    {
      return true;
      i = 0;
      break;
      j = 0;
      break label12;
      if (paramCharSequence1 == null) {
        return false;
      }
      j = paramCharSequence1.length();
    } while (j != paramCharSequence2.length());
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label83;
      }
      if (paramCharSequence1.charAt(i) != paramCharSequence2.charAt(i)) {
        break;
      }
      i += 1;
    }
    label83:
    return false;
  }
  
  @BindingAdapter({"android:autoText"})
  public static void setAutoText(TextView paramTextView, boolean paramBoolean)
  {
    KeyListener localKeyListener = paramTextView.getKeyListener();
    TextKeyListener.Capitalize localCapitalize = TextKeyListener.Capitalize.NONE;
    int i;
    if (localKeyListener != null)
    {
      i = localKeyListener.getInputType();
      if ((i & 0x1000) == 0) {
        break label50;
      }
      localCapitalize = TextKeyListener.Capitalize.CHARACTERS;
    }
    for (;;)
    {
      paramTextView.setKeyListener(TextKeyListener.getInstance(paramBoolean, localCapitalize));
      return;
      i = 0;
      break;
      label50:
      if ((i & 0x2000) != 0) {
        localCapitalize = TextKeyListener.Capitalize.WORDS;
      } else if ((i & 0x4000) != 0) {
        localCapitalize = TextKeyListener.Capitalize.SENTENCES;
      }
    }
  }
  
  @BindingAdapter({"android:bufferType"})
  public static void setBufferType(TextView paramTextView, TextView.BufferType paramBufferType)
  {
    paramTextView.setText(paramTextView.getText(), paramBufferType);
  }
  
  @BindingAdapter({"android:capitalize"})
  public static void setCapitalize(TextView paramTextView, TextKeyListener.Capitalize paramCapitalize)
  {
    if ((0x8000 & paramTextView.getKeyListener().getInputType()) != 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramTextView.setKeyListener(TextKeyListener.getInstance(bool, paramCapitalize));
      return;
    }
  }
  
  @BindingAdapter({"android:digits"})
  public static void setDigits(TextView paramTextView, CharSequence paramCharSequence)
  {
    if (paramCharSequence != null) {
      paramTextView.setKeyListener(DigitsKeyListener.getInstance(paramCharSequence.toString()));
    }
    while (!(paramTextView.getKeyListener() instanceof DigitsKeyListener)) {
      return;
    }
    paramTextView.setKeyListener(null);
  }
  
  @BindingAdapter({"android:drawableBottom"})
  public static void setDrawableBottom(TextView paramTextView, Drawable paramDrawable)
  {
    setIntrinsicBounds(paramDrawable);
    Drawable[] arrayOfDrawable = paramTextView.getCompoundDrawables();
    paramTextView.setCompoundDrawables(arrayOfDrawable[0], arrayOfDrawable[1], arrayOfDrawable[2], paramDrawable);
  }
  
  @BindingAdapter({"android:drawableEnd"})
  public static void setDrawableEnd(TextView paramTextView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT < 17)
    {
      setDrawableRight(paramTextView, paramDrawable);
      return;
    }
    setIntrinsicBounds(paramDrawable);
    Drawable[] arrayOfDrawable = paramTextView.getCompoundDrawablesRelative();
    paramTextView.setCompoundDrawablesRelative(arrayOfDrawable[0], arrayOfDrawable[1], paramDrawable, arrayOfDrawable[3]);
  }
  
  @BindingAdapter({"android:drawableLeft"})
  public static void setDrawableLeft(TextView paramTextView, Drawable paramDrawable)
  {
    setIntrinsicBounds(paramDrawable);
    Drawable[] arrayOfDrawable = paramTextView.getCompoundDrawables();
    paramTextView.setCompoundDrawables(paramDrawable, arrayOfDrawable[1], arrayOfDrawable[2], arrayOfDrawable[3]);
  }
  
  @BindingAdapter({"android:drawableRight"})
  public static void setDrawableRight(TextView paramTextView, Drawable paramDrawable)
  {
    setIntrinsicBounds(paramDrawable);
    Drawable[] arrayOfDrawable = paramTextView.getCompoundDrawables();
    paramTextView.setCompoundDrawables(arrayOfDrawable[0], arrayOfDrawable[1], paramDrawable, arrayOfDrawable[3]);
  }
  
  @BindingAdapter({"android:drawableStart"})
  public static void setDrawableStart(TextView paramTextView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT < 17)
    {
      setDrawableLeft(paramTextView, paramDrawable);
      return;
    }
    setIntrinsicBounds(paramDrawable);
    Drawable[] arrayOfDrawable = paramTextView.getCompoundDrawablesRelative();
    paramTextView.setCompoundDrawablesRelative(paramDrawable, arrayOfDrawable[1], arrayOfDrawable[2], arrayOfDrawable[3]);
  }
  
  @BindingAdapter({"android:drawableTop"})
  public static void setDrawableTop(TextView paramTextView, Drawable paramDrawable)
  {
    setIntrinsicBounds(paramDrawable);
    Drawable[] arrayOfDrawable = paramTextView.getCompoundDrawables();
    paramTextView.setCompoundDrawables(arrayOfDrawable[0], paramDrawable, arrayOfDrawable[2], arrayOfDrawable[3]);
  }
  
  @BindingAdapter({"android:imeActionId"})
  public static void setImeActionLabel(TextView paramTextView, int paramInt)
  {
    paramTextView.setImeActionLabel(paramTextView.getImeActionLabel(), paramInt);
  }
  
  @BindingAdapter({"android:imeActionLabel"})
  public static void setImeActionLabel(TextView paramTextView, CharSequence paramCharSequence)
  {
    paramTextView.setImeActionLabel(paramCharSequence, paramTextView.getImeActionId());
  }
  
  @BindingAdapter({"android:inputMethod"})
  public static void setInputMethod(TextView paramTextView, CharSequence paramCharSequence)
  {
    try
    {
      paramTextView.setKeyListener((KeyListener)Class.forName(paramCharSequence.toString()).newInstance());
      return;
    }
    catch (ClassNotFoundException paramTextView)
    {
      Log.e("TextViewBindingAdapters", "Could not create input method: " + paramCharSequence, paramTextView);
      return;
    }
    catch (InstantiationException paramTextView)
    {
      Log.e("TextViewBindingAdapters", "Could not create input method: " + paramCharSequence, paramTextView);
      return;
    }
    catch (IllegalAccessException paramTextView)
    {
      Log.e("TextViewBindingAdapters", "Could not create input method: " + paramCharSequence, paramTextView);
    }
  }
  
  private static void setIntrinsicBounds(Drawable paramDrawable)
  {
    if (paramDrawable != null) {
      paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
    }
  }
  
  @BindingAdapter({"android:lineSpacingExtra"})
  public static void setLineSpacingExtra(TextView paramTextView, float paramFloat)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramTextView.setLineSpacing(paramFloat, paramTextView.getLineSpacingMultiplier());
      return;
    }
    paramTextView.setLineSpacing(paramFloat, 1.0F);
  }
  
  @BindingAdapter({"android:lineSpacingMultiplier"})
  public static void setLineSpacingMultiplier(TextView paramTextView, float paramFloat)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramTextView.setLineSpacing(paramTextView.getLineSpacingExtra(), paramFloat);
      return;
    }
    paramTextView.setLineSpacing(0.0F, paramFloat);
  }
  
  @BindingAdapter({"android:maxLength"})
  public static void setMaxLength(TextView paramTextView, int paramInt)
  {
    InputFilter[] arrayOfInputFilter = paramTextView.getFilters();
    Object localObject;
    if (arrayOfInputFilter == null)
    {
      localObject = new InputFilter[1];
      localObject[0] = new InputFilter.LengthFilter(paramInt);
      paramTextView.setFilters((InputFilter[])localObject);
      return;
    }
    int i = 0;
    int j = 0;
    for (;;)
    {
      int k = i;
      int m;
      if (j < arrayOfInputFilter.length)
      {
        localObject = arrayOfInputFilter[j];
        if (!(localObject instanceof InputFilter.LengthFilter)) {
          break label170;
        }
        m = 1;
        i = 1;
        if (Build.VERSION.SDK_INT >= 21) {
          if (((InputFilter.LengthFilter)localObject).getMax() == paramInt) {
            break label165;
          }
        }
      }
      label165:
      for (i = 1;; i = 0)
      {
        k = m;
        if (i != 0)
        {
          arrayOfInputFilter[j] = new InputFilter.LengthFilter(paramInt);
          k = m;
        }
        localObject = arrayOfInputFilter;
        if (k != 0) {
          break;
        }
        localObject = new InputFilter[arrayOfInputFilter.length + 1];
        System.arraycopy(arrayOfInputFilter, 0, localObject, 0, arrayOfInputFilter.length);
        localObject[(localObject.length - 1)] = new InputFilter.LengthFilter(paramInt);
        break;
      }
      label170:
      j += 1;
    }
  }
  
  @BindingAdapter({"android:numeric"})
  public static void setNumeric(TextView paramTextView, int paramInt)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramInt & 0x3) != 0)
    {
      bool1 = true;
      if ((paramInt & 0x5) == 0) {
        break label31;
      }
    }
    for (;;)
    {
      paramTextView.setKeyListener(DigitsKeyListener.getInstance(bool1, bool2));
      return;
      bool1 = false;
      break;
      label31:
      bool2 = false;
    }
  }
  
  @BindingAdapter({"android:password"})
  public static void setPassword(TextView paramTextView, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramTextView.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
    while (!(paramTextView.getTransformationMethod() instanceof PasswordTransformationMethod)) {
      return;
    }
    paramTextView.setTransformationMethod(null);
  }
  
  @BindingAdapter({"android:phoneNumber"})
  public static void setPhoneNumber(TextView paramTextView, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramTextView.setKeyListener(DialerKeyListener.getInstance());
    }
    while (!(paramTextView.getKeyListener() instanceof DialerKeyListener)) {
      return;
    }
    paramTextView.setKeyListener(null);
  }
  
  @BindingAdapter({"android:shadowColor"})
  public static void setShadowColor(TextView paramTextView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      float f1 = paramTextView.getShadowDx();
      float f2 = paramTextView.getShadowDy();
      paramTextView.setShadowLayer(paramTextView.getShadowRadius(), f1, f2, paramInt);
    }
  }
  
  @BindingAdapter({"android:shadowDx"})
  public static void setShadowDx(TextView paramTextView, float paramFloat)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      int i = paramTextView.getShadowColor();
      float f = paramTextView.getShadowDy();
      paramTextView.setShadowLayer(paramTextView.getShadowRadius(), paramFloat, f, i);
    }
  }
  
  @BindingAdapter({"android:shadowDy"})
  public static void setShadowDy(TextView paramTextView, float paramFloat)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      int i = paramTextView.getShadowColor();
      float f = paramTextView.getShadowDx();
      paramTextView.setShadowLayer(paramTextView.getShadowRadius(), f, paramFloat, i);
    }
  }
  
  @BindingAdapter({"android:shadowRadius"})
  public static void setShadowRadius(TextView paramTextView, float paramFloat)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      int i = paramTextView.getShadowColor();
      paramTextView.setShadowLayer(paramFloat, paramTextView.getShadowDx(), paramTextView.getShadowDy(), i);
    }
  }
  
  @BindingAdapter({"android:text"})
  public static void setText(TextView paramTextView, CharSequence paramCharSequence)
  {
    CharSequence localCharSequence = paramTextView.getText();
    if ((paramCharSequence == localCharSequence) || ((paramCharSequence == null) && (localCharSequence.length() == 0))) {}
    do
    {
      return;
      if (!(paramCharSequence instanceof Spanned)) {
        break;
      }
    } while (paramCharSequence.equals(localCharSequence));
    while (haveContentsChanged(paramCharSequence, localCharSequence))
    {
      paramTextView.setText(paramCharSequence);
      return;
    }
  }
  
  @BindingAdapter({"android:textSize"})
  public static void setTextSize(TextView paramTextView, float paramFloat)
  {
    paramTextView.setTextSize(0, paramFloat);
  }
  
  @BindingAdapter(requireAll=false, value={"android:beforeTextChanged", "android:onTextChanged", "android:afterTextChanged", "android:textAttrChanged"})
  public static void setTextWatcher(TextView paramTextView, BeforeTextChanged paramBeforeTextChanged, final OnTextChanged paramOnTextChanged, final AfterTextChanged paramAfterTextChanged, final InverseBindingListener paramInverseBindingListener)
  {
    if ((paramBeforeTextChanged == null) && (paramAfterTextChanged == null) && (paramOnTextChanged == null) && (paramInverseBindingListener == null)) {}
    for (paramBeforeTextChanged = null;; paramBeforeTextChanged = new TextWatcher()
        {
          public void afterTextChanged(Editable paramAnonymousEditable)
          {
            if (paramAfterTextChanged != null) {
              paramAfterTextChanged.afterTextChanged(paramAnonymousEditable);
            }
          }
          
          public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
          {
            if (this.val$before != null) {
              this.val$before.beforeTextChanged(paramAnonymousCharSequence, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
            }
          }
          
          public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
          {
            if (paramOnTextChanged != null) {
              paramOnTextChanged.onTextChanged(paramAnonymousCharSequence, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
            }
            if (paramInverseBindingListener != null) {
              paramInverseBindingListener.onChange();
            }
          }
        })
    {
      paramOnTextChanged = (TextWatcher)ListenerUtil.trackListener(paramTextView, paramBeforeTextChanged, R.id.textWatcher);
      if (paramOnTextChanged != null) {
        paramTextView.removeTextChangedListener(paramOnTextChanged);
      }
      if (paramBeforeTextChanged != null) {
        paramTextView.addTextChangedListener(paramBeforeTextChanged);
      }
      return;
    }
  }
  
  public static abstract interface AfterTextChanged
  {
    public abstract void afterTextChanged(Editable paramEditable);
  }
  
  public static abstract interface BeforeTextChanged
  {
    public abstract void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3);
  }
  
  public static abstract interface OnTextChanged
  {
    public abstract void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\adapters\TextViewBindingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */