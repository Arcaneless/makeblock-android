package cc.makeblock.makeblock.scene.panel;

import android.content.Context;
import cc.makeblock.makeblock.bean.EnumXibName;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.customview.panel.AirBlockBoardView;
import cc.makeblock.makeblock.customview.panel.AirBlockFlightJoyStickView;
import cc.makeblock.makeblock.customview.panel.AirBlockJoyStickView;
import cc.makeblock.makeblock.customview.panel.AirblockSwitchView;
import cc.makeblock.makeblock.customview.panel.ButtonView;
import cc.makeblock.makeblock.customview.panel.ChartLayout;
import cc.makeblock.makeblock.customview.panel.ColorPickerView;
import cc.makeblock.makeblock.customview.panel.DirectionView;
import cc.makeblock.makeblock.customview.panel.HorizontalJoyStickView;
import cc.makeblock.makeblock.customview.panel.JoyStickView;
import cc.makeblock.makeblock.customview.panel.LineFollowIndicator;
import cc.makeblock.makeblock.customview.panel.MusicKeyView;
import cc.makeblock.makeblock.customview.panel.PathMapView;
import cc.makeblock.makeblock.customview.panel.SliderDuplex;
import cc.makeblock.makeblock.customview.panel.SliderHorizontal;
import cc.makeblock.makeblock.customview.panel.SliderVertical;
import cc.makeblock.makeblock.customview.panel.SpeakerView;
import cc.makeblock.makeblock.customview.panel.StateView;
import cc.makeblock.makeblock.customview.panel.SwitchView;
import cc.makeblock.makeblock.customview.panel.ValueView;
import cc.makeblock.makeblock.customview.panel.ValueViewWithProgress;
import cc.makeblock.makeblock.customview.panel.VerticalJoyStickView;
import cc.makeblock.makeblock.engine.ControllerManager;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;

public class WidgetFactory
{
  private static final String TAG = "WidgetFactory";
  
  public static CellView createCellView(Context paramContext, WidgetData paramWidgetData, int paramInt)
  {
    Object localObject = null;
    String str = paramWidgetData.xibName;
    if (str.equals(EnumXibName.MBWMusicKey.toString()))
    {
      localObject = new MusicKeyView(paramContext);
      ((CellView)localObject).setCellWidth(18.0F);
      ((CellView)localObject).setCellHeight(6.0F);
    }
    if (str.equals(EnumXibName.MBWColorPicker.toString()))
    {
      localObject = new ColorPickerView(paramContext);
      ((CellView)localObject).setCellWidth(7.0F);
      ((CellView)localObject).setCellHeight(7.0F);
    }
    if (str.equals(EnumXibName.MBWJoystick.toString()))
    {
      localObject = new JoyStickView(paramContext);
      ((CellView)localObject).setCellWidth(9.0F);
      ((CellView)localObject).setCellHeight(9.0F);
    }
    if (str.equals(EnumXibName.MBWPathController.toString()))
    {
      localObject = new PathMapView(paramContext);
      ((CellView)localObject).setCellWidth(23.0F);
      ((CellView)localObject).setCellHeight(13.0F);
    }
    if (str.equals(EnumXibName.MBWDPad.toString()))
    {
      localObject = new DirectionView(paramContext);
      ((CellView)localObject).setCellWidth(9.0F);
      ((CellView)localObject).setCellHeight(9.0F);
    }
    if (str.equals(EnumXibName.MBWIconButton.toString()))
    {
      localObject = new ButtonView(paramContext);
      ((CellView)localObject).setCellWidth(7.0F);
      ((CellView)localObject).setCellHeight(3.0F);
    }
    if (str.equals(EnumXibName.MBWSwitch.toString()))
    {
      if (ControllerManager.getInstance().getChosenBoardName().equalsIgnoreCase(DeviceBoardName.crystal.name()))
      {
        localObject = new AirblockSwitchView(paramContext);
        ((CellView)localObject).setCellWidth(5.0F);
        ((CellView)localObject).setCellHeight(3.0F);
      }
    }
    else
    {
      if (str.equals(EnumXibName.MBWNumberDisplay.toString()))
      {
        localObject = new ValueView(paramContext);
        ((CellView)localObject).setCellWidth(5.0F);
        ((CellView)localObject).setCellHeight(3.0F);
      }
      if (str.equals(EnumXibName.MBWLineGraph.toString()))
      {
        localObject = new ChartLayout(paramContext);
        ((CellView)localObject).setCellWidth(10.0F);
        ((CellView)localObject).setCellHeight(7.0F);
      }
      if (str.equals(EnumXibName.MBWBarDisplay.toString()))
      {
        localObject = new ValueViewWithProgress(paramContext);
        ((CellView)localObject).setCellWidth(5.0F);
        ((CellView)localObject).setCellHeight(6.0F);
        if (paramInt != 0) {
          break label857;
        }
        ((ValueViewWithProgress)localObject).setProgress(0.0F);
      }
    }
    for (;;)
    {
      if (str.equals(EnumXibName.MBWIndicator.toString()))
      {
        localObject = new StateView(paramContext);
        ((CellView)localObject).setCellWidth(3.0F);
        ((CellView)localObject).setCellHeight(3.0F);
      }
      if (str.equals(EnumXibName.MBWSlider.toString()))
      {
        localObject = new SliderHorizontal(paramContext);
        ((CellView)localObject).setCellWidth(10.0F);
        ((CellView)localObject).setCellHeight(3.0F);
      }
      if (str.equals(EnumXibName.MBWVerticalSlider.toString()))
      {
        localObject = new SliderVertical(paramContext);
        ((CellView)localObject).setCellWidth(3.0F);
        ((CellView)localObject).setCellHeight(10.0F);
      }
      if (str.equals(EnumXibName.MBWSpeaker.toString()))
      {
        localObject = new SpeakerView(paramContext);
        ((CellView)localObject).setCellWidth(5.0F);
        ((CellView)localObject).setCellHeight(6.0F);
      }
      if (str.equals(EnumXibName.MBWTwoWaySlider.toString()))
      {
        localObject = new SliderDuplex(paramContext);
        ((CellView)localObject).setCellWidth(10.0F);
        ((CellView)localObject).setCellHeight(3.0F);
      }
      if (str.equals(EnumXibName.MBWCarJoystick.toString()))
      {
        localObject = new HorizontalJoyStickView(paramContext);
        ((CellView)localObject).setCellWidth(9.0F);
        ((CellView)localObject).setCellHeight(5.0F);
      }
      if (str.equals(EnumXibName.MBWFlightJoystick.toString()))
      {
        localObject = new AirBlockFlightJoyStickView(paramContext);
        ((CellView)localObject).setCellWidth(9.0F);
        ((CellView)localObject).setCellHeight(9.0F);
      }
      if (str.equals(EnumXibName.MBWFlightAttitudeJoystick.toString()))
      {
        localObject = new AirBlockJoyStickView(paramContext);
        ((CellView)localObject).setCellWidth(9.0F);
        ((CellView)localObject).setCellHeight(9.0F);
      }
      if (str.equals(EnumXibName.MBWFlightInstrument.toString()))
      {
        localObject = new AirBlockBoardView(paramContext);
        ((CellView)localObject).setCellWidth(10.0F);
        ((CellView)localObject).setCellHeight(6.0F);
      }
      if (str.equals(EnumXibName.MBWDuoIndicator.toString()))
      {
        localObject = new LineFollowIndicator(paramContext);
        ((CellView)localObject).setCellWidth(5.0F);
        ((CellView)localObject).setCellHeight(3.0F);
      }
      if (str.equals(EnumXibName.MBWVerticalJoystick.toString()))
      {
        localObject = new VerticalJoyStickView(paramContext);
        ((CellView)localObject).setCellHeight(9.0F);
        ((CellView)localObject).setCellWidth(5.0F);
      }
      if (localObject != null)
      {
        ((CellView)localObject).setMode(paramInt);
        ((CellView)localObject).setWidgetData(paramWidgetData);
        ((CellView)localObject).setId(paramWidgetData.widgetID + 100);
        ((CellView)localObject).setCellX(paramWidgetData.xPosition);
        ((CellView)localObject).setCellY(paramWidgetData.yPosition);
      }
      paramWidgetData = (WidgetData)localObject;
      if (localObject == null)
      {
        paramWidgetData = new ButtonView(paramContext);
        paramWidgetData.setCellWidth(10.0F);
        paramWidgetData.setCellHeight(3.0F);
      }
      return paramWidgetData;
      localObject = new SwitchView(paramContext);
      break;
      label857:
      ((ValueViewWithProgress)localObject).setProgress(0.0F);
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\scene\panel\WidgetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */