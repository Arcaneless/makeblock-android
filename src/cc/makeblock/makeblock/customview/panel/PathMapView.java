package cc.makeblock.makeblock.customview.panel;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cc.makeblock.makeblock.bean.WidgetData;
import cc.makeblock.makeblock.customview.cell.CellView;
import cc.makeblock.makeblock.engine.ControllerManager;

public class PathMapView
  extends CellView
  implements View.OnClickListener
{
  private PathMap pathMap;
  private View pathMapPauseView;
  private View pathMapPlayView;
  private View pathMapResetView;
  private TextView titleText;
  
  public PathMapView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public PathMapView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public PathMapView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    paramContext = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2131427554, null);
    this.titleText = ((TextView)paramContext.findViewById(2131296615));
    this.pathMapPauseView = paramContext.findViewById(2131296612);
    this.pathMapPlayView = paramContext.findViewById(2131296613);
    this.pathMapResetView = paramContext.findViewById(2131296614);
    this.pathMap = ((PathMap)paramContext.findViewById(2131296611));
    this.pathMapPauseView.setOnClickListener(this);
    this.pathMapPlayView.setOnClickListener(this);
    this.pathMapResetView.setOnClickListener(this);
    this.pathMap.setPathMapChangeListener(new PathMap.PathMapChangeListener()
    {
      public void onDrawPathFinish()
      {
        PathMapView.this.play();
      }
      
      public void onExecuteFinish()
      {
        PathMapView.this.uiReset();
      }
    });
    addView(paramContext);
  }
  
  private void pause()
  {
    this.pathMap.pause();
    post(new Runnable()
    {
      public void run()
      {
        PathMapView.this.pathMapPauseView.setVisibility(8);
        PathMapView.this.pathMapPlayView.setVisibility(0);
      }
    });
  }
  
  private void play()
  {
    if (!ControllerManager.getInstance().isConnectedOk()) {
      return;
    }
    if (this.pathMap.play())
    {
      post(new Runnable()
      {
        public void run()
        {
          PathMapView.this.pathMapPlayView.setVisibility(8);
          PathMapView.this.pathMapPauseView.setVisibility(0);
        }
      });
      return;
    }
    reset();
  }
  
  private void reset()
  {
    this.pathMap.reset();
    uiReset();
  }
  
  private void uiReset()
  {
    post(new Runnable()
    {
      public void run()
      {
        PathMapView.this.pathMapPlayView.setVisibility(0);
        PathMapView.this.pathMapPauseView.setVisibility(8);
      }
    });
  }
  
  public void notifyWidgetBeanChanged()
  {
    if (!TextUtils.isEmpty(this.widgetData.name)) {
      this.titleText.setText(this.widgetData.name);
    }
  }
  
  public void onClick(View paramView)
  {
    if (paramView == this.pathMapPauseView) {
      pause();
    }
    do
    {
      return;
      if (paramView == this.pathMapPlayView)
      {
        play();
        return;
      }
    } while (paramView != this.pathMapResetView);
    reset();
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    reset();
  }
  
  protected void sendState(Object paramObject) {}
  
  public void setControlListener() {}
  
  public void setMode(int paramInt)
  {
    super.setMode(paramInt);
    if (paramInt == 2) {
      reset();
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\cc\makeblock\makeblock\customview\panel\PathMapView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */