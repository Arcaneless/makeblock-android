package com.bumptech.glide.manager;

import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.Set;

final class EmptyRequestManagerTreeNode
  implements RequestManagerTreeNode
{
  public Set<RequestManager> getDescendants()
  {
    return Collections.emptySet();
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\manager\EmptyRequestManagerTreeNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */