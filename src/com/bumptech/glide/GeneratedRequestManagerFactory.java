package com.bumptech.glide;

import cc.makeblock.makeblock.engine.GlideRequests;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerRetriever.RequestManagerFactory;
import com.bumptech.glide.manager.RequestManagerTreeNode;

final class GeneratedRequestManagerFactory
  implements RequestManagerRetriever.RequestManagerFactory
{
  public RequestManager build(Glide paramGlide, Lifecycle paramLifecycle, RequestManagerTreeNode paramRequestManagerTreeNode)
  {
    return new GlideRequests(paramGlide, paramLifecycle, paramRequestManagerTreeNode);
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\GeneratedRequestManagerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */