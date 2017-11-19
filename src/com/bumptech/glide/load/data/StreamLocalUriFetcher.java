package com.bumptech.glide.load.data;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class StreamLocalUriFetcher
  extends LocalUriFetcher<InputStream>
{
  private static final int ID_CONTACTS_CONTACT = 3;
  private static final int ID_CONTACTS_LOOKUP = 1;
  private static final int ID_CONTACTS_PHOTO = 4;
  private static final int ID_CONTACTS_THUMBNAIL = 2;
  private static final UriMatcher URI_MATCHER = new UriMatcher(-1);
  
  static
  {
    URI_MATCHER.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
    URI_MATCHER.addURI("com.android.contacts", "contacts/lookup/*", 1);
    URI_MATCHER.addURI("com.android.contacts", "contacts/#/photo", 2);
    URI_MATCHER.addURI("com.android.contacts", "contacts/#", 3);
    URI_MATCHER.addURI("com.android.contacts", "contacts/#/display_photo", 4);
  }
  
  public StreamLocalUriFetcher(ContentResolver paramContentResolver, Uri paramUri)
  {
    super(paramContentResolver, paramUri);
  }
  
  private InputStream loadResourceFromUri(Uri paramUri, ContentResolver paramContentResolver)
    throws FileNotFoundException
  {
    switch (URI_MATCHER.match(paramUri))
    {
    case 2: 
    default: 
      return paramContentResolver.openInputStream(paramUri);
    case 3: 
      return openContactPhotoInputStream(paramContentResolver, paramUri);
    }
    paramUri = ContactsContract.Contacts.lookupContact(paramContentResolver, paramUri);
    if (paramUri == null) {
      throw new FileNotFoundException("Contact cannot be found");
    }
    return openContactPhotoInputStream(paramContentResolver, paramUri);
  }
  
  @TargetApi(14)
  private InputStream openContactPhotoInputStream(ContentResolver paramContentResolver, Uri paramUri)
  {
    return ContactsContract.Contacts.openContactPhotoInputStream(paramContentResolver, paramUri, true);
  }
  
  protected void close(InputStream paramInputStream)
    throws IOException
  {
    paramInputStream.close();
  }
  
  public Class<InputStream> getDataClass()
  {
    return InputStream.class;
  }
  
  protected InputStream loadResource(Uri paramUri, ContentResolver paramContentResolver)
    throws FileNotFoundException
  {
    paramContentResolver = loadResourceFromUri(paramUri, paramContentResolver);
    if (paramContentResolver == null) {
      throw new FileNotFoundException("InputStream is null for " + paramUri);
    }
    return paramContentResolver;
  }
}


/* Location:              C:\Users\Marcus Cheung\Desktop\AndriodHacking\Makeblock_v3.0.8_apkpure.com-dex2jar.jar!\com\bumptech\glide\load\data\StreamLocalUriFetcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */