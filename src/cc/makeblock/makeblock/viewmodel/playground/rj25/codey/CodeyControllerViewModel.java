package cc.makeblock.makeblock.viewmodel.playground.rj25.codey;

import android.databinding.Bindable;
import cc.makeblock.makeblock.engine.device.Codey;

public class CodeyControllerViewModel
  extends CodeyOffLineUpdateViewModel
{
  private static String program_fileName = "script/codey.py";
  private static String program_version = "1_1";
  private boolean isAllSkillCooling;
  private float verticalJoystickPercent;
  
  public CodeyControllerViewModel(Codey paramCodey, CodeyViewModel.CodeyControllerView paramCodeyControllerView)
  {
    super(paramCodey, paramCodeyControllerView);
    resetCodey();
  }
  
  private void allSkillCooling()
  {
    this.isAllSkillCooling = true;
    notifyPropertyChanged(70);
  }
  
  public String getProgramPath()
  {
    return program_fileName;
  }
  
  public String getProgramVersion()
  {
    return program_version;
  }
  
  @Bindable
  public boolean getShouldCancelSkills()
  {
    return true;
  }
  
  @Bindable
  public float getVerticalJoystickPercent()
  {
    return this.verticalJoystickPercent;
  }
  
  public void happy()
  {
    ((Codey)this.device).executeCommand("4");
    allSkillCooling();
  }
  
  @Bindable
  public boolean isStartCooling()
  {
    return this.isAllSkillCooling;
  }
  
  public void moveJoystick(int paramInt, float paramFloat)
  {
    this.verticalJoystickPercent = paramFloat;
    notifyPropertyChanged(78);
    ((Codey)this.device).moveJoystick(paramInt, paramFloat);
  }
  
  public void resetCodey()
  {
    ((Codey)this.device).stop();
    notifyPropertyChanged(61);
  }
  
  public void sad()
  {
    ((Codey)this.device).executeCommand("5");
    allSkillCooling();
  }
  
  public void shake()
  {
    ((Codey)this.device).executeCommand("3");
    allSkillCooling();
  }
  
  public void shock()
  {
    ((Codey)this.device).executeCommand("6");
    allSkillCooling();
  }
  
  public void spin()
  {
    ((Codey)this.device).executeCommand("2");
    allSkillCooling();
  }
  
  public void sprint()
  {
    ((Codey)this.device).executeCommand("1");
    allSkillCooling();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\viewmodel\playground\rj25\codey\CodeyControllerViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */