package com.bumptech.glide.manager;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public class RequestTracker
{
  private boolean isPaused;
  private final List<Request> pendingRequests = new ArrayList();
  private final Set<Request> requests = Collections.newSetFromMap(new WeakHashMap());
  
  void addRequest(Request paramRequest)
  {
    this.requests.add(paramRequest);
  }
  
  public boolean clearRemoveAndRecycle(Request paramRequest)
  {
    boolean bool2;
    if (paramRequest == null)
    {
      bool2 = false;
      return bool2;
    }
    boolean bool1 = this.requests.remove(paramRequest);
    if ((this.pendingRequests.remove(paramRequest)) || (bool1)) {}
    for (bool1 = true;; bool1 = false)
    {
      bool2 = bool1;
      if (!bool1) {
        break;
      }
      paramRequest.clear();
      paramRequest.recycle();
      return bool1;
    }
  }
  
  public void clearRequests()
  {
    Iterator localIterator = Util.getSnapshot(this.requests).iterator();
    while (localIterator.hasNext()) {
      clearRemoveAndRecycle((Request)localIterator.next());
    }
    this.pendingRequests.clear();
  }
  
  public boolean isPaused()
  {
    return this.isPaused;
  }
  
  public void pauseRequests()
  {
    this.isPaused = true;
    Iterator localIterator = Util.getSnapshot(this.requests).iterator();
    while (localIterator.hasNext())
    {
      Request localRequest = (Request)localIterator.next();
      if (localRequest.isRunning())
      {
        localRequest.pause();
        this.pendingRequests.add(localRequest);
      }
    }
  }
  
  public void restartRequests()
  {
    Iterator localIterator = Util.getSnapshot(this.requests).iterator();
    while (localIterator.hasNext())
    {
      Request localRequest = (Request)localIterator.next();
      if ((!localRequest.isComplete()) && (!localRequest.isCancelled()))
      {
        localRequest.pause();
        if (!this.isPaused) {
          localRequest.begin();
        } else {
          this.pendingRequests.add(localRequest);
        }
      }
    }
  }
  
  public void resumeRequests()
  {
    this.isPaused = false;
    Iterator localIterator = Util.getSnapshot(this.requests).iterator();
    while (localIterator.hasNext())
    {
      Request localRequest = (Request)localIterator.next();
      if ((!localRequest.isComplete()) && (!localRequest.isCancelled()) && (!localRequest.isRunning())) {
        localRequest.begin();
      }
    }
    this.pendingRequests.clear();
  }
  
  public void runRequest(Request paramRequest)
  {
    this.requests.add(paramRequest);
    if (!this.isPaused)
    {
      paramRequest.begin();
      return;
    }
    this.pendingRequests.add(paramRequest);
  }
  
  public String toString()
  {
    return super.toString() + "{numRequests=" + this.requests.size() + ", isPaused=" + this.isPaused + "}";
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\manager\RequestTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */