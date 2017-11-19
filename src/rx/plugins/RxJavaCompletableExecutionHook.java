package rx.plugins;

import rx.Completable;
import rx.Completable.CompletableOnSubscribe;
import rx.Completable.CompletableOperator;
import rx.annotations.Experimental;

@Experimental
public abstract class RxJavaCompletableExecutionHook
{
  public Completable.CompletableOnSubscribe onCreate(Completable.CompletableOnSubscribe paramCompletableOnSubscribe)
  {
    return paramCompletableOnSubscribe;
  }
  
  public Completable.CompletableOperator onLift(Completable.CompletableOperator paramCompletableOperator)
  {
    return paramCompletableOperator;
  }
  
  public Throwable onSubscribeError(Throwable paramThrowable)
  {
    return paramThrowable;
  }
  
  public Completable.CompletableOnSubscribe onSubscribeStart(Completable paramCompletable, Completable.CompletableOnSubscribe paramCompletableOnSubscribe)
  {
    return paramCompletableOnSubscribe;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\rx\plugins\RxJavaCompletableExecutionHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */