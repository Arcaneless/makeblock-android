package cc.makeblock.makeblock.customview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class DeviceIconLayout
  extends RelativeLayout
{
  public static final int STATE_CONNECTED = 0;
  public static final int STATE_CONNECTING = 1;
  public static final int STATE_DISCONNECTED = -1;
  private static final String TAG = "DeviceIconLayout";
  AnimationDrawable animation;
  private ImageView img_connected;
  private ImageView img_connecting;
  private ImageView img_device_icon;
  private int mState;
  private TextView tv_deviceName;
  private TextView tv_distance;
  
  public DeviceIconLayout(Context paramContext)
  {
    super(paramContext);
    init(null, 0);
  }
  
  public DeviceIconLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet, 0);
  }
  
  public DeviceIconLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet, paramInt);
  }
  
  private void init(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427392, null);
    addView(paramAttributeSet, new RelativeLayout.LayoutParams(-1, -1));
    this.tv_distance = ((TextView)paramAttributeSet.findViewById(2131296738));
    this.tv_deviceName = ((TextView)paramAttributeSet.findViewById(2131296737));
    this.img_connecting = ((ImageView)paramAttributeSet.findViewById(2131296504));
    this.img_connected = ((ImageView)paramAttributeSet.findViewById(2131296503));
    this.img_device_icon = ((ImageView)paramAttributeSet.findViewById(2131296505));
    this.tv_distance.setVisibility(8);
    this.img_connecting.setVisibility(8);
    this.img_connected.setVisibility(8);
    this.img_connecting.setBackgroundResource(2131165593);
    this.animation = ((AnimationDrawable)this.img_connecting.getBackground());
  }
  
  public int getState()
  {
    return this.mState;
  }
  
  public void setDistance(float paramFloat)
  {
    if (paramFloat > 0.0F)
    {
      this.tv_distance.setVisibility(0);
      int i = (int)paramFloat;
      paramFloat = (int)((paramFloat - (int)paramFloat) * 10.0F) / 10.0F;
      String str = i + "";
      if (paramFloat != 0.0F) {
        str = i + paramFloat + "";
      }
      this.tv_distance.setText(str + "m");
      return;
    }
    this.tv_distance.setVisibility(8);
  }
  
  public void setName(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      this.tv_deviceName.setText("Unknown");
      return;
    }
    this.tv_deviceName.setText(paramString);
  }
  
  public void setState(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      this.mState = paramInt;
      return;
      this.img_connecting.setVisibility(8);
      this.img_connected.setVisibility(0);
      this.img_device_icon.setSelected(false);
      this.animation.stop();
      continue;
      this.img_connecting.setVisibility(0);
      this.img_connected.setVisibility(8);
      this.img_device_icon.setSelected(true);
      this.animation.start();
      continue;
      this.img_connecting.setVisibility(8);
      this.img_connected.setVisibility(8);
      this.animation.stop();
      this.img_device_icon.setSelected(false);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\DeviceIconLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */