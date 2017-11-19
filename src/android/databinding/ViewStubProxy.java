package android.databinding;

import android.view.View;
import android.view.ViewStub;
import android.view.ViewStub.OnInflateListener;

public class ViewStubProxy
{
  private ViewDataBinding mContainingBinding;
  private ViewStub.OnInflateListener mOnInflateListener;
  private ViewStub.OnInflateListener mProxyListener = new ViewStub.OnInflateListener()
  {
    public void onInflate(ViewStub paramAnonymousViewStub, View paramAnonymousView)
    {
      ViewStubProxy.access$002(ViewStubProxy.this, paramAnonymousView);
      ViewStubProxy.access$102(ViewStubProxy.this, DataBindingUtil.bind(ViewStubProxy.this.mContainingBinding.mBindingComponent, paramAnonymousView, paramAnonymousViewStub.getLayoutResource()));
      ViewStubProxy.access$302(ViewStubProxy.this, null);
      if (ViewStubProxy.this.mOnInflateListener != null)
      {
        ViewStubProxy.this.mOnInflateListener.onInflate(paramAnonymousViewStub, paramAnonymousView);
        ViewStubProxy.access$402(ViewStubProxy.this, null);
      }
      ViewStubProxy.this.mContainingBinding.invalidateAll();
      ViewStubProxy.this.mContainingBinding.forceExecuteBindings();
    }
  };
  private View mRoot;
  private ViewDataBinding mViewDataBinding;
  private ViewStub mViewStub;
  
  public ViewStubProxy(ViewStub paramViewStub)
  {
    this.mViewStub = paramViewStub;
    this.mViewStub.setOnInflateListener(this.mProxyListener);
  }
  
  public ViewDataBinding getBinding()
  {
    return this.mViewDataBinding;
  }
  
  public View getRoot()
  {
    return this.mRoot;
  }
  
  public ViewStub getViewStub()
  {
    return this.mViewStub;
  }
  
  public boolean isInflated()
  {
    return this.mRoot != null;
  }
  
  public void setContainingBinding(ViewDataBinding paramViewDataBinding)
  {
    this.mContainingBinding = paramViewDataBinding;
  }
  
  public void setOnInflateListener(ViewStub.OnInflateListener paramOnInflateListener)
  {
    if (this.mViewStub != null) {
      this.mOnInflateListener = paramOnInflateListener;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\android\databinding\ViewStubProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */