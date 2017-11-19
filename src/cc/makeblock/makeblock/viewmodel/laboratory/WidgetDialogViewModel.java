package cc.makeblock.makeblock.viewmodel.laboratory;

import android.databinding.BaseObservable;
import cc.makeblock.makeblock.scene.laboratory.WidgetDialog;

public class WidgetDialogViewModel
  extends BaseObservable
{
  public static final int WIDGET_CATEGORY_DETECTOR = 3;
  public static final int WIDGET_CATEGORY_LIGHT = 1;
  public static final int WIDGET_CATEGORY_MOVE = 0;
  public static final int WIDGET_CATEGORY_SOUND = 2;
  private int widgetCategory;
  private WidgetDialog widgetDialog;
  
  public WidgetDialogViewModel(WidgetDialog paramWidgetDialog)
  {
    this.widgetDialog = paramWidgetDialog;
  }
  
  public void detectorDistance()
  {
    this.widgetDialog.onSelectWidget(15);
  }
  
  public void detectorLight()
  {
    this.widgetDialog.onSelectWidget(14);
  }
  
  public void detectorLineFollow()
  {
    this.widgetDialog.onSelectWidget(16);
  }
  
  public int getWidgetCategory()
  {
    return this.widgetCategory;
  }
  
  public void lightAlarm()
  {
    this.widgetDialog.onSelectWidget(6);
  }
  
  public void lightColorPicker()
  {
    this.widgetDialog.onSelectWidget(4);
  }
  
  public void lightGradient()
  {
    this.widgetDialog.onSelectWidget(7);
  }
  
  public void lightSevenColors()
  {
    this.widgetDialog.onSelectWidget(5);
  }
  
  public void moveJoystick()
  {
    this.widgetDialog.onSelectWidget(0);
  }
  
  public void moveRotate()
  {
    this.widgetDialog.onSelectWidget(2);
  }
  
  public void moveSprite()
  {
    this.widgetDialog.onSelectWidget(1);
  }
  
  public void moveTurn()
  {
    this.widgetDialog.onSelectWidget(3);
  }
  
  public void setLayoutContentId(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 2131427442: 
      this.widgetCategory = 0;
      return;
    case 2131427441: 
      this.widgetCategory = 1;
      return;
    case 2131427443: 
      this.widgetCategory = 2;
      return;
    }
    this.widgetCategory = 3;
  }
  
  public void soundAlert()
  {
    this.widgetDialog.onSelectWidget(11);
  }
  
  public void soundBirthday()
  {
    this.widgetDialog.onSelectWidget(9);
  }
  
  public void soundJungleBell()
  {
    this.widgetDialog.onSelectWidget(13);
  }
  
  public void soundStar()
  {
    this.widgetDialog.onSelectWidget(8);
  }
  
  public void soundTiger()
  {
    this.widgetDialog.onSelectWidget(10);
  }
  
  public void soundWhistle()
  {
    this.widgetDialog.onSelectWidget(12);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\laboratory\WidgetDialogViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */