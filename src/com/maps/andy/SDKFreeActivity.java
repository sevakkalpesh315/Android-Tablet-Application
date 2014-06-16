package com.maps.andy;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.maps.andy.VDOParser.AdParseEventListener;

public class SDKFreeActivity extends Activity implements AdParseEventListener{
 
WebView webView;
VDOParser parser;
URL requestURL = null;
 
String AD_FETCH_URI = new String("http://serve.vdopia.com/adserver/html5/adFetch/?output=xhtml&container=androidWeb");
 
static final String SCHEME_TALK2ME_REPLAY = "replay:";
static final String SCHEME_TALK2ME_GOTOAPP = "gotoapp:";
 
private Activity activity;
     
/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.main1);
        
// Storing the activity context
activity = this;
// Get the handle to the webView
webView = (WebView)findViewById(R.id.webview);
        
if(webView != null)
{
webView.setWebViewClient(new WebViewClient()
{
public void onPageStarted (WebView view, String url, Bitmap favicon) 
{ }
public void onPageFinished(WebView view, String url) 
{ }
public boolean shouldOverrideUrlLoading(WebView view, String url) 
{
if(url.startsWith(WebView.SCHEME_TEL)) {
Intent dialer = Intent.createChooser(new Intent(Intent.ACTION_DIAL, Uri.parse(url)), "Choose Dialer");
activity.startActivity(dialer);
} else if (url.startsWith(WebView.SCHEME_MAILTO)) {
Intent mailer = Intent.createChooser(new Intent(Intent.ACTION_SENDTO, Uri.parse(url)), "Send Message");
activity.startActivity(mailer);
} else if (url.startsWith(WebView.SCHEME_GEO)) {
Intent geoviewer = Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse(url)), "Choose Viewer");
activity.startActivity(geoviewer);
} else if(url.startsWith("sms")) {
Intent smsIntent = new Intent(Intent.ACTION_VIEW);
smsIntent.putExtra("address", url.substring(url.indexOf(":") + 1));
smsIntent.setType("vnd.android-dir/mms-sms");
activity.startActivity(smsIntent);
}else {
String extension = MimeTypeMap.getFileExtensionFromUrl(url);
String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
if(!handleMimeType(url, mimeType)){
view.loadUrl(url);
}
}
return true;
}
});
 
webView.getSettings().setJavaScriptEnabled(true);
webView.getSettings().setBuiltInZoomControls(true);
      
webView.setBackgroundColor(0);
      
// Create the URL request
constructRequest();
         
// Initiate the parser Object
parser = new VDOParser();
parser.initParser(requestURL, this);
}
}
    
    
private void constructRequest() {
	// TODO Auto-generated method stub
	try
	{
	String strUrl = new String(AD_FETCH_URI);
	String adType = "vdobanner"; // Change this type according to the ad type required
	String key = "465964c4b6bad08c6e3a0f0fa7bba288"; // Your API key goes here
	String location = "locbot"; // Set locbot = 1, which means that the video banner will expand from bottom to top, setting it to 0 means the reverse.
	 
	String formatted = new String(String.format("%s&caller=vdosdk&adFormat=%s&ak=%s&version=1.0&fullscreen=1&showClose=0&vdo=1&locbot=1",strUrl,adType, key ));
	strUrl = new String(formatted);
	 
	// Create the URL
	try
	{
	requestURL = new URL(formatted);
	}
	catch ( MalformedURLException e) 
	{ 
	System.out.println("Bad URL: " + requestURL); 
	} 
	}

	catch(Exception e)
	{
	System.out.println("Exception caught "+ e);
	} 
}


// This is a callback method implemented to find out if ad was Found
public void adParseStatus(Boolean status, String adData)
{
if((status == true) && (adData != null))
{
webView.loadData(adData, "text/html", "UTF-8");
}
else
{
// Resume normal application or show ads by another provider
}
}
    
private boolean handleMimeType(String u, String mimeType){
String url = u;
if(mimeType != null && mimeType.startsWith("video/")) {
Intent videoIntent = new Intent(Intent.ACTION_VIEW);
videoIntent.setDataAndType(Uri.parse(url), mimeType);
activity.startActivity(videoIntent);
return true;
}
return false;
}
}