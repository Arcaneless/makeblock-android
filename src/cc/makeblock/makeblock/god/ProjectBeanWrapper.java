package cc.makeblock.makeblock.god;

import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.engine.device.DeviceBoardName;
import cc.makeblock.makeblock.engine.utils.TextUtil;
import cc.makeblock.makeblock.project.ProjectBean;
import cc.makeblock.makeblock.viewmodel.PanelViewModel;
import java.util.ArrayList;
import java.util.List;

public class ProjectBeanWrapper
{
  private String boardName;
  private String correctedBoardName;
  private boolean hasEnteredBlockly;
  private ProjectBean mCurrentProjectBean;
  private ProjectBean mOriginalProjectBean;
  private boolean projectIsNewlyCreated;
  
  public ProjectBeanWrapper(ProjectBean paramProjectBean, boolean paramBoolean)
  {
    this.mOriginalProjectBean = paramProjectBean;
    this.mCurrentProjectBean = paramProjectBean.deepCopy();
    this.projectIsNewlyCreated = paramBoolean;
    this.boardName = paramProjectBean.boardName;
    if (this.boardName.equalsIgnoreCase(DeviceBoardName.crystal.name()))
    {
      switch (Integer.parseInt(paramProjectBean.robotForm))
      {
      default: 
        return;
      case 4: 
        this.correctedBoardName = "AirBlockDrone";
        return;
      case 7: 
        this.correctedBoardName = "AirBlockShip";
        return;
      case 5: 
        this.correctedBoardName = "AirBlockCar";
        return;
      }
      this.correctedBoardName = "AirBlockCustom";
      return;
    }
    this.correctedBoardName = paramProjectBean.boardName;
  }
  
  public void addWidgetData(WidgetData paramWidgetData, PanelViewModel paramPanelViewModel)
  {
    this.mCurrentProjectBean.getWidgets().add(paramWidgetData);
    int j = 0;
    int i = 0;
    while (i < this.mCurrentProjectBean.getWidgets().size())
    {
      j = Math.max(((WidgetData)this.mCurrentProjectBean.getWidgets().get(i)).widgetID, j);
      i += 1;
    }
    paramWidgetData.widgetID = (j + 1);
    paramPanelViewModel.addWidgetToBlockly(paramWidgetData.getWidgetAddJson());
  }
  
  public boolean existWidgets()
  {
    return (this.mCurrentProjectBean.getWidgets() != null) && (this.mCurrentProjectBean.getWidgets().size() > 0);
  }
  
  public String getBoardName()
  {
    return this.boardName;
  }
  
  public String getCorrectedBoardName()
  {
    return this.correctedBoardName;
  }
  
  public String getCurrentFormText()
  {
    if (String.valueOf(3).equalsIgnoreCase(this.mCurrentProjectBean.robotForm)) {
      return TextUtil.getStringById(2131493024);
    }
    if (DeviceBoardName.mcore.name().equalsIgnoreCase(this.mCurrentProjectBean.boardName)) {
      return TextUtil.getStringById(2131493022);
    }
    if (DeviceBoardName.crystal.name().equalsIgnoreCase(this.mCurrentProjectBean.boardName)) {
      switch (Integer.parseInt(this.mCurrentProjectBean.robotForm))
      {
      }
    }
    try
    {
      int i = Integer.valueOf(this.mCurrentProjectBean.robotForm).intValue();
      switch (i)
      {
      }
    }
    catch (Exception localException)
    {
      String str;
      for (;;) {}
    }
    return TextUtil.getStringById(2131493022);
    return TextUtil.getStringById(2131492941);
    return TextUtil.getStringById(2131492971);
    return TextUtil.getStringById(2131492933);
    return TextUtil.getStringById(2131492936);
    return TextUtil.getStringById(2131493022);
    return TextUtil.getStringById(2131493023);
    return TextUtil.getStringById(2131493021);
    str = TextUtil.getStringById(2131493024);
    return str;
  }
  
  public String[] getFormSelectableTexts()
  {
    String str1 = TextUtil.getStringById(2131493022);
    String str2 = TextUtil.getStringById(2131493023);
    String str3 = TextUtil.getStringById(2131493021);
    String str4 = TextUtil.getStringById(2131493024);
    String[] arrayOfString = null;
    if (this.boardName.equalsIgnoreCase(DeviceBoardName.orion.name()))
    {
      arrayOfString = new String[2];
      arrayOfString[0] = str1;
      arrayOfString[1] = str2;
    }
    while ((!this.boardName.equalsIgnoreCase(DeviceBoardName.auriga.name())) && (!this.boardName.equalsIgnoreCase(DeviceBoardName.megaPi.name()))) {
      return arrayOfString;
    }
    if (isOfficial()) {
      return new String[] { str1, str2, str3 };
    }
    return new String[] { str1, str2, str3, str4 };
  }
  
  public int getId()
  {
    return this.mCurrentProjectBean.id;
  }
  
  public String getName()
  {
    return this.mCurrentProjectBean.name;
  }
  
  public String getNameKey()
  {
    return this.mCurrentProjectBean.nameKey;
  }
  
  public ProjectBean getOriginalProjectBean()
  {
    return this.mOriginalProjectBean;
  }
  
  public ProjectBean getProjectBean()
  {
    return this.mCurrentProjectBean;
  }
  
  public String getProjectBeanBoardName()
  {
    return this.mCurrentProjectBean.boardName;
  }
  
  public String getProjectName()
  {
    return this.mCurrentProjectBean.name;
  }
  
  public String getRobotForm()
  {
    return this.mCurrentProjectBean.robotForm;
  }
  
  public List<WidgetData> getWidgetDataList()
  {
    return this.mCurrentProjectBean.getWidgets();
  }
  
  public boolean hasEnteredBlockly()
  {
    return this.hasEnteredBlockly;
  }
  
  public boolean isAirBlock()
  {
    return this.mCurrentProjectBean.boardName.equalsIgnoreCase(DeviceBoardName.crystal.name());
  }
  
  public boolean isCar()
  {
    return String.valueOf(5).equals(this.mCurrentProjectBean.robotForm);
  }
  
  public boolean isFormChangeable()
  {
    if ((this.mCurrentProjectBean.boardName.equalsIgnoreCase(DeviceBoardName.mcore.name())) || (this.mCurrentProjectBean.boardName.equalsIgnoreCase(DeviceBoardName.crystal.name())) || (this.mCurrentProjectBean.name.equalsIgnoreCase(TextUtil.getStringById(2131493151)))) {}
    while ((this.mCurrentProjectBean.boardName.equalsIgnoreCase(DeviceBoardName.octopus.name())) || ((!this.projectIsNewlyCreated) && ((this.mCurrentProjectBean.isOfficial & 0x1) != 0) && (this.mCurrentProjectBean.robotForm.equals("3")))) {
      return false;
    }
    return true;
  }
  
  public boolean isFormSelectorVisible()
  {
    return !this.mCurrentProjectBean.boardName.equalsIgnoreCase(DeviceBoardName.octopus.name());
  }
  
  public boolean isOfficial()
  {
    return (this.mCurrentProjectBean.isOfficial & 0x1) == 1;
  }
  
  public boolean isOfficialProjectBeanModified()
  {
    return (isOfficial()) && ((!this.mCurrentProjectBean.toString().equals(this.mOriginalProjectBean.toString())) || (this.hasEnteredBlockly));
  }
  
  public boolean isRobotFormSelfBalance()
  {
    return String.valueOf(3).equalsIgnoreCase(this.mCurrentProjectBean.robotForm);
  }
  
  public void modifyRobotForm(String paramString, PanelViewModel paramPanelViewModel)
  {
    this.mCurrentProjectBean.robotForm = paramString;
    if ((this.mCurrentProjectBean.boardName.equalsIgnoreCase(DeviceBoardName.orion.name())) || (this.mCurrentProjectBean.boardName.equalsIgnoreCase(DeviceBoardName.auriga.name())) || (this.mCurrentProjectBean.boardName.equalsIgnoreCase(DeviceBoardName.megaPi.name()))) {
      paramPanelViewModel.setRobotForm(paramString);
    }
  }
  
  public boolean projectIsNewlyCreated()
  {
    return this.projectIsNewlyCreated;
  }
  
  public void removeWidgetData(WidgetData paramWidgetData)
  {
    this.mCurrentProjectBean.getWidgets().remove(paramWidgetData);
  }
  
  public void setHasEnteredBlockly(boolean paramBoolean)
  {
    this.hasEnteredBlockly = paramBoolean;
  }
  
  public void setId(int paramInt)
  {
    this.mCurrentProjectBean.id = paramInt;
  }
  
  public void setIsOfficialValue(int paramInt)
  {
    this.mCurrentProjectBean.isOfficial = paramInt;
  }
  
  public void setProjectName(String paramString)
  {
    this.mCurrentProjectBean.name = paramString;
  }
  
  public void setScreenShot(String paramString)
  {
    this.mCurrentProjectBean.screenshot = paramString;
  }
  
  public boolean shouldDeleteProject()
  {
    return (this.mCurrentProjectBean.getWidgets() == null) || (this.mCurrentProjectBean.getWidgets().size() == 0);
  }
  
  public boolean shouldSaveAsNewProject()
  {
    return ((this.projectIsNewlyCreated) && (this.mCurrentProjectBean.getWidgets().size() > 0)) || (((this.mCurrentProjectBean.isOfficial & 0x1) == 1) && ((!this.mCurrentProjectBean.toString().equals(this.mOriginalProjectBean.toString())) || (this.hasEnteredBlockly)) && (this.mCurrentProjectBean.getWidgets().size() > 0));
  }
  
  public boolean shouldSaveModification()
  {
    return (!this.projectIsNewlyCreated) && ((this.mCurrentProjectBean.isOfficial & 0x1) == 0) && ((!this.mCurrentProjectBean.toString().equals(this.mOriginalProjectBean.toString())) || (this.hasEnteredBlockly));
  }
  
  public boolean supportSmartServo()
  {
    return (this.mCurrentProjectBean.boardName.equalsIgnoreCase(DeviceBoardName.octopus.name())) || (this.mCurrentProjectBean.boardName.equalsIgnoreCase(DeviceBoardName.auriga.name()));
  }
  
  public boolean supportTune()
  {
    return (DeviceBoardName.crystal.name().equalsIgnoreCase(this.boardName)) && (Integer.valueOf(this.mCurrentProjectBean.robotForm).intValue() == 4);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\god\ProjectBeanWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */