package com.unity3d.player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import java.io.FileInputStream;
import java.io.IOException;

public final class m
  extends FrameLayout
  implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl
{
  private static boolean a = false;
  private final Context b;
  private final SurfaceView c;
  private final SurfaceHolder d;
  private final String e;
  private final int f;
  private final int g;
  private final boolean h;
  private final long i;
  private final long j;
  private final FrameLayout k;
  private final Display l;
  private int m;
  private int n;
  private int o;
  private int p;
  private MediaPlayer q;
  private MediaController r;
  private boolean s = false;
  private boolean t = false;
  private int u = 0;
  private boolean v = false;
  private boolean w = false;
  private a x;
  private b y;
  private volatile int z = 0;
  
  protected m(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong1, long paramLong2, a parama)
  {
    super(paramContext);
    this.x = parama;
    this.b = paramContext;
    this.k = this;
    this.c = new SurfaceView(paramContext);
    this.d = this.c.getHolder();
    this.d.addCallback(this);
    this.d.setType(3);
    this.k.setBackgroundColor(paramInt1);
    this.k.addView(this.c);
    this.l = ((WindowManager)this.b.getSystemService("window")).getDefaultDisplay();
    this.e = paramString;
    this.f = paramInt2;
    this.g = paramInt3;
    this.h = paramBoolean;
    this.i = paramLong1;
    this.j = paramLong2;
    if (a) {
      b("fileName: " + this.e);
    }
    if (a) {
      b("backgroundColor: " + paramInt1);
    }
    if (a) {
      b("controlMode: " + this.f);
    }
    if (a) {
      b("scalingMode: " + this.g);
    }
    if (a) {
      b("isURL: " + this.h);
    }
    if (a) {
      b("videoOffset: " + this.i);
    }
    if (a) {
      b("videoLength: " + this.j);
    }
    setFocusable(true);
    setFocusableInTouchMode(true);
  }
  
  private void a(int paramInt)
  {
    this.z = paramInt;
    if (this.x != null) {
      this.x.a(this.z);
    }
  }
  
  private static void b(String paramString)
  {
    Log.i("Video", "VideoPlayer: " + paramString);
  }
  
  private void c()
  {
    if (this.q != null)
    {
      this.q.setDisplay(this.d);
      if (!this.v)
      {
        if (a) {
          b("Resuming playback");
        }
        this.q.start();
      }
      return;
    }
    a(0);
    doCleanUp();
    for (;;)
    {
      try
      {
        this.q = new MediaPlayer();
        if (this.h)
        {
          this.q.setDataSource(this.b, Uri.parse(this.e));
          this.q.setDisplay(this.d);
          this.q.setScreenOnWhilePlaying(true);
          this.q.setOnBufferingUpdateListener(this);
          this.q.setOnCompletionListener(this);
          this.q.setOnPreparedListener(this);
          this.q.setOnVideoSizeChangedListener(this);
          this.q.setAudioStreamType(3);
          this.q.prepareAsync();
          this.y = new b(this);
          new Thread(this.y).start();
          return;
        }
      }
      catch (Exception localException)
      {
        if (a) {
          b("error: " + localException.getMessage() + localException);
        }
        a(2);
        return;
      }
      Object localObject;
      if (this.j != 0L)
      {
        localObject = new FileInputStream(this.e);
        this.q.setDataSource(((FileInputStream)localObject).getFD(), this.i, this.j);
        ((FileInputStream)localObject).close();
      }
      else
      {
        localObject = getResources().getAssets();
        try
        {
          localObject = ((AssetManager)localObject).openFd(this.e);
          this.q.setDataSource(((AssetFileDescriptor)localObject).getFileDescriptor(), ((AssetFileDescriptor)localObject).getStartOffset(), ((AssetFileDescriptor)localObject).getLength());
          ((AssetFileDescriptor)localObject).close();
        }
        catch (IOException localIOException)
        {
          FileInputStream localFileInputStream = new FileInputStream(this.e);
          this.q.setDataSource(localFileInputStream.getFD());
          localFileInputStream.close();
        }
      }
    }
  }
  
  private void d()
  {
    if (isPlaying()) {}
    do
    {
      return;
      a(1);
      if (a) {
        b("startVideoPlayback");
      }
      updateVideoLayout();
    } while (this.v);
    start();
  }
  
  public final void CancelOnPrepare()
  {
    a(2);
  }
  
  final boolean a()
  {
    return this.v;
  }
  
  public final boolean canPause()
  {
    return true;
  }
  
  public final boolean canSeekBackward()
  {
    return true;
  }
  
  public final boolean canSeekForward()
  {
    return true;
  }
  
  protected final void destroyPlayer()
  {
    if (a) {
      b("destroyPlayer");
    }
    if (!this.v) {
      pause();
    }
    doCleanUp();
  }
  
  protected final void doCleanUp()
  {
    if (this.y != null)
    {
      this.y.a();
      this.y = null;
    }
    if (this.q != null)
    {
      this.q.release();
      this.q = null;
    }
    this.o = 0;
    this.p = 0;
    this.t = false;
    this.s = false;
  }
  
  public final int getBufferPercentage()
  {
    if (this.h) {
      return this.u;
    }
    return 100;
  }
  
  public final int getCurrentPosition()
  {
    if (this.q == null) {
      return 0;
    }
    return this.q.getCurrentPosition();
  }
  
  public final int getDuration()
  {
    if (this.q == null) {
      return 0;
    }
    return this.q.getDuration();
  }
  
  public final boolean isPlaying()
  {
    int i1;
    if ((this.t) && (this.s))
    {
      i1 = 1;
      if (this.q != null) {
        break label36;
      }
      if (i1 != 0) {
        break label34;
      }
    }
    label34:
    label36:
    while ((this.q.isPlaying()) || (i1 == 0))
    {
      return true;
      i1 = 0;
      break;
      return false;
    }
    return false;
  }
  
  public final void onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt)
  {
    if (a) {
      b("onBufferingUpdate percent:" + paramInt);
    }
    this.u = paramInt;
  }
  
  public final void onCompletion(MediaPlayer paramMediaPlayer)
  {
    if (a) {
      b("onCompletion called");
    }
    destroyPlayer();
    a(3);
  }
  
  public final boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) || ((this.f == 2) && (paramInt != 0) && (!paramKeyEvent.isSystem())))
    {
      destroyPlayer();
      a(3);
      return true;
    }
    if (this.r != null) {
      return this.r.onKeyDown(paramInt, paramKeyEvent);
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public final void onPrepared(MediaPlayer paramMediaPlayer)
  {
    if (a) {
      b("onPrepared called");
    }
    if (this.y != null)
    {
      this.y.a();
      this.y = null;
    }
    if ((this.f == 0) || (this.f == 1))
    {
      this.r = new MediaController(this.b);
      this.r.setMediaPlayer(this);
      this.r.setAnchorView(this);
      this.r.setEnabled(true);
      this.r.show();
    }
    this.t = true;
    if ((this.t) && (this.s)) {
      d();
    }
  }
  
  public final boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i1 = paramMotionEvent.getAction();
    if ((this.f == 2) && ((i1 & 0xFF) == 0))
    {
      destroyPlayer();
      a(3);
      return true;
    }
    if (this.r != null) {
      return this.r.onTouchEvent(paramMotionEvent);
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public final void onVideoSizeChanged(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    if (a) {
      b("onVideoSizeChanged called " + paramInt1 + "x" + paramInt2);
    }
    if ((paramInt1 == 0) || (paramInt2 == 0)) {
      if (a) {
        b("invalid video width(" + paramInt1 + ") or height(" + paramInt2 + ")");
      }
    }
    do
    {
      return;
      this.s = true;
      this.o = paramInt1;
      this.p = paramInt2;
    } while ((!this.t) || (!this.s));
    d();
  }
  
  public final void pause()
  {
    if (this.q == null) {
      return;
    }
    if (this.w) {
      this.q.pause();
    }
    this.v = true;
  }
  
  public final void seekTo(int paramInt)
  {
    if (this.q == null) {
      return;
    }
    this.q.seekTo(paramInt);
  }
  
  public final void start()
  {
    if (a) {
      b("Start");
    }
    if (this.q == null) {
      return;
    }
    if (this.w) {
      this.q.start();
    }
    this.v = false;
  }
  
  public final void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    if (a) {
      b("surfaceChanged called " + paramInt1 + " " + paramInt2 + "x" + paramInt3);
    }
    if ((this.m != paramInt2) || (this.n != paramInt3))
    {
      this.m = paramInt2;
      this.n = paramInt3;
      if (this.w) {
        updateVideoLayout();
      }
    }
  }
  
  public final void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    if (a) {
      b("surfaceCreated called");
    }
    this.w = true;
    c();
  }
  
  public final void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    if (a) {
      b("surfaceDestroyed called");
    }
    this.w = false;
  }
  
  protected final void updateVideoLayout()
  {
    if (a) {
      b("updateVideoLayout");
    }
    if (this.q == null) {
      return;
    }
    Object localObject;
    if ((this.m == 0) || (this.n == 0))
    {
      localObject = (WindowManager)this.b.getSystemService("window");
      this.m = ((WindowManager)localObject).getDefaultDisplay().getWidth();
      this.n = ((WindowManager)localObject).getDefaultDisplay().getHeight();
    }
    int i2 = this.m;
    int i1 = this.n;
    float f1 = this.o / this.p;
    float f2 = this.m / this.n;
    if (this.g == 1) {
      if (f2 <= f1) {
        i1 = (int)(this.m / f1);
      }
    }
    for (;;)
    {
      if (a) {
        b("frameWidth = " + i2 + "; frameHeight = " + i1);
      }
      localObject = new FrameLayout.LayoutParams(i2, i1, 17);
      this.k.updateViewLayout(this.c, (ViewGroup.LayoutParams)localObject);
      return;
      i2 = (int)(this.n * f1);
      continue;
      if (this.g == 2)
      {
        if (f2 >= f1) {
          i1 = (int)(this.m / f1);
        } else {
          i2 = (int)(this.n * f1);
        }
      }
      else if (this.g == 0)
      {
        i2 = this.o;
        i1 = this.p;
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
  }
  
  public final class b
    implements Runnable
  {
    private m b;
    private boolean c;
    
    public b(m paramm)
    {
      this.b = paramm;
      this.c = false;
    }
    
    public final void a()
    {
      this.c = true;
    }
    
    public final void run()
    {
      try
      {
        Thread.sleep(5000L);
        if (!this.c)
        {
          if (m.b()) {
            m.a("Stopping the video player due to timeout.");
          }
          this.b.CancelOnPrepare();
        }
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\unity3d\player\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */