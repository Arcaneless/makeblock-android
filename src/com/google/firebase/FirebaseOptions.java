package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.util.zzv;

public final class FirebaseOptions
{
  private final String zzalR;
  private final String zzbUL;
  private final String zzbUM;
  private final String zzbUN;
  private final String zzbUO;
  private final String zzbUP;
  
  private FirebaseOptions(@NonNull String paramString1, @NonNull String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable String paramString6)
  {
    if (!zzv.zzdD(paramString1)) {}
    for (boolean bool = true;; bool = false)
    {
      zzac.zza(bool, "ApplicationId must be set.");
      this.zzalR = paramString1;
      this.zzbUL = paramString2;
      this.zzbUM = paramString3;
      this.zzbUN = paramString4;
      this.zzbUO = paramString5;
      this.zzbUP = paramString6;
      return;
    }
  }
  
  public static FirebaseOptions fromResource(Context paramContext)
  {
    paramContext = new zzam(paramContext);
    String str = paramContext.getString("google_app_id");
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    return new FirebaseOptions(str, paramContext.getString("google_api_key"), paramContext.getString("firebase_database_url"), paramContext.getString("ga_trackingId"), paramContext.getString("gcm_defaultSenderId"), paramContext.getString("google_storage_bucket"));
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof FirebaseOptions)) {}
    do
    {
      return false;
      paramObject = (FirebaseOptions)paramObject;
    } while ((!zzaa.equal(this.zzalR, ((FirebaseOptions)paramObject).zzalR)) || (!zzaa.equal(this.zzbUL, ((FirebaseOptions)paramObject).zzbUL)) || (!zzaa.equal(this.zzbUM, ((FirebaseOptions)paramObject).zzbUM)) || (!zzaa.equal(this.zzbUN, ((FirebaseOptions)paramObject).zzbUN)) || (!zzaa.equal(this.zzbUO, ((FirebaseOptions)paramObject).zzbUO)) || (!zzaa.equal(this.zzbUP, ((FirebaseOptions)paramObject).zzbUP)));
    return true;
  }
  
  public String getApiKey()
  {
    return this.zzbUL;
  }
  
  public String getApplicationId()
  {
    return this.zzalR;
  }
  
  public String getDatabaseUrl()
  {
    return this.zzbUM;
  }
  
  public String getGcmSenderId()
  {
    return this.zzbUO;
  }
  
  public String getStorageBucket()
  {
    return this.zzbUP;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { this.zzalR, this.zzbUL, this.zzbUM, this.zzbUN, this.zzbUO, this.zzbUP });
  }
  
  public String toString()
  {
    return zzaa.zzv(this).zzg("applicationId", this.zzalR).zzg("apiKey", this.zzbUL).zzg("databaseUrl", this.zzbUM).zzg("gcmSenderId", this.zzbUO).zzg("storageBucket", this.zzbUP).toString();
  }
  
  public static final class Builder
  {
    private String zzalR;
    private String zzbUL;
    private String zzbUM;
    private String zzbUN;
    private String zzbUO;
    private String zzbUP;
    
    public Builder() {}
    
    public Builder(FirebaseOptions paramFirebaseOptions)
    {
      this.zzalR = FirebaseOptions.zza(paramFirebaseOptions);
      this.zzbUL = FirebaseOptions.zzb(paramFirebaseOptions);
      this.zzbUM = FirebaseOptions.zzc(paramFirebaseOptions);
      this.zzbUN = FirebaseOptions.zzd(paramFirebaseOptions);
      this.zzbUO = FirebaseOptions.zze(paramFirebaseOptions);
      this.zzbUP = FirebaseOptions.zzf(paramFirebaseOptions);
    }
    
    public FirebaseOptions build()
    {
      return new FirebaseOptions(this.zzalR, this.zzbUL, this.zzbUM, this.zzbUN, this.zzbUO, this.zzbUP, null);
    }
    
    public Builder setApiKey(@NonNull String paramString)
    {
      this.zzbUL = zzac.zzh(paramString, "ApiKey must be set.");
      return this;
    }
    
    public Builder setApplicationId(@NonNull String paramString)
    {
      this.zzalR = zzac.zzh(paramString, "ApplicationId must be set.");
      return this;
    }
    
    public Builder setDatabaseUrl(@Nullable String paramString)
    {
      this.zzbUM = paramString;
      return this;
    }
    
    public Builder setGcmSenderId(@Nullable String paramString)
    {
      this.zzbUO = paramString;
      return this;
    }
    
    public Builder setStorageBucket(@Nullable String paramString)
    {
      this.zzbUP = paramString;
      return this;
    }
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\google\firebase\FirebaseOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */