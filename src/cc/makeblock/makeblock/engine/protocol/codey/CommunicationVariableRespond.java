package cc.makeblock.makeblock.engine.protocol.codey;

public class CommunicationVariableRespond<T>
  extends CodeyRespond
{
  public final T value;
  public final String variableName;
  
  public CommunicationVariableRespond(String paramString, T paramT)
  {
    this.variableName = paramString;
    this.value = paramT;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\engine\protocol\codey\CommunicationVariableRespond.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */