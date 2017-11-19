package cc.makeblock.makeblock.viewmodel.laboratory;

import cc.makeblock.makeblock.engine.diy.DiyWidgetFactory;
import cc.makeblock.makeblock.engine.diy.Widget;
import cc.makeblock.makeblock.scene.laboratory.ChooseActionActivity;
import cc.makeblock.makeblock.scene.laboratory.WidgetDialog;
import cc.makeblock.makeblock.scene.laboratory.WidgetDialog.OnWidgetSelectListener;

public class ChooseActionViewModel
{
  public static final int WIDGET_TYPE_DETECTOR_DISTANCE = 15;
  public static final int WIDGET_TYPE_DETECTOR_LIGHT = 14;
  public static final int WIDGET_TYPE_DETECTOR_LINE_FOLLOW = 16;
  public static final int WIDGET_TYPE_LIGHT_ALARM = 6;
  public static final int WIDGET_TYPE_LIGHT_COLOR_PICKER = 4;
  public static final int WIDGET_TYPE_LIGHT_GRADIENT = 7;
  public static final int WIDGET_TYPE_LIGHT_SEVEN_COLORS = 5;
  public static final int WIDGET_TYPE_MOVE_JOYSTICK = 0;
  public static final int WIDGET_TYPE_MOVE_ROTATE = 2;
  public static final int WIDGET_TYPE_MOVE_SPRITE = 1;
  public static final int WIDGET_TYPE_MOVE_TURN = 3;
  public static final int WIDGET_TYPE_SOUND_ALERT = 11;
  public static final int WIDGET_TYPE_SOUND_BIRTHDAY = 9;
  public static final int WIDGET_TYPE_SOUND_JUNGLE_BELL = 13;
  public static final int WIDGET_TYPE_SOUND_STAR = 8;
  public static final int WIDGET_TYPE_SOUND_TIGER = 10;
  public static final int WIDGET_TYPE_SOUND_WHISTLE = 12;
  private ChooseActionActivity chooseActionActivity;
  private WidgetDialog dialog;
  private int[] location = { 0, 0 };
  
  public ChooseActionViewModel(ChooseActionActivity paramChooseActionActivity, int[] paramArrayOfInt)
  {
    this.chooseActionActivity = paramChooseActionActivity;
    if ((paramArrayOfInt != null) && (paramArrayOfInt.length == 2)) {
      this.location = paramArrayOfInt;
    }
  }
  
  private void createDialog(int paramInt)
  {
    if ((this.dialog != null) && (this.dialog.isShowing())) {
      this.dialog.dismiss();
    }
    this.dialog = new WidgetDialog(this.chooseActionActivity);
    this.dialog.setWidgetLayout(paramInt);
    this.dialog.setOnWidgetSelectListener(new WidgetDialog.OnWidgetSelectListener()
    {
      public void onWidgetSelect(int paramAnonymousInt)
      {
        ChooseActionViewModel.this.chooseActionActivity.finishWithResult(ChooseActionViewModel.this.fetchCorrespondingData(paramAnonymousInt));
      }
    });
    this.dialog.show();
  }
  
  private Widget fetchCorrespondingData(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return DiyWidgetFactory.createWidget("move_joystick", "move_joystick", this.location[0], this.location[1]);
    case 1: 
      return DiyWidgetFactory.createWidget("move_sprint", "move_sprint", this.location[0], this.location[1]);
    case 2: 
      return DiyWidgetFactory.createWidget("move_rotate", "move_rotate", this.location[0], this.location[1]);
    case 3: 
      return DiyWidgetFactory.createWidget("move_turn", "move_turn", this.location[0], this.location[1]);
    case 4: 
      return DiyWidgetFactory.createWidget("light_colorPicker", "light_colorPicker", this.location[0], this.location[1]);
    case 5: 
      return DiyWidgetFactory.createWidget("light_random", "light_random", this.location[0], this.location[1]);
    case 6: 
      return DiyWidgetFactory.createWidget("light_alarm", "light_alarm", this.location[0], this.location[1]);
    case 7: 
      return DiyWidgetFactory.createWidget("light_gradient", "light_gradient", this.location[0], this.location[1]);
    case 8: 
      return DiyWidgetFactory.createWidget("sound_star", "sound_star", this.location[0], this.location[1]);
    case 9: 
      return DiyWidgetFactory.createWidget("sound_birthday", "sound_birthday", this.location[0], this.location[1]);
    case 10: 
      return DiyWidgetFactory.createWidget("sound_tiger", "sound_tiger", this.location[0], this.location[1]);
    case 11: 
      return DiyWidgetFactory.createWidget("sound_alert", "sound_alert", this.location[0], this.location[1]);
    case 12: 
      return DiyWidgetFactory.createWidget("sound_whistle", "sound_whistle", this.location[0], this.location[1]);
    case 13: 
      return DiyWidgetFactory.createWidget("sound_christmas", "sound_christmas", this.location[0], this.location[1]);
    case 14: 
      return DiyWidgetFactory.createWidget("detector_light", "detector_light", this.location[0], this.location[1]);
    case 15: 
      return DiyWidgetFactory.createWidget("detector_distance", "detector_distance", this.location[0], this.location[1]);
    }
    return DiyWidgetFactory.createWidget("detector_line_follower", "detector_line_follower", this.location[0], this.location[1]);
  }
  
  public void leave()
  {
    this.chooseActionActivity.finishWithAnimation();
  }
  
  public void popupDetectorWidget()
  {
    createDialog(2131427440);
  }
  
  public void popupLightWidget()
  {
    createDialog(2131427441);
  }
  
  public void popupMoveWidget()
  {
    createDialog(2131427442);
  }
  
  public void popupSoundWidget()
  {
    createDialog(2131427443);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\laboratory\ChooseActionViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */