package com.maps.andy;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.VideoView;
import android.widget.ViewFlipper;

public class OfflineCon extends Activity {
	WebView http_load,httpBottom;
	ConnectivityManager conMgr1;
	public Boolean offlineContent1=true;
	 int minutes = 45;
	 Handler handler;
	 Runnable checkNet;
	 ProgressDialog pDialog;
public int s=3;
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	ImageView contentFirst;
	VideoView ndtv;
	ViewFlipper flip;

	@Override
	protected void onCreate(Bundle b) {
		// TODO Auto-generated method stub
		super.onCreate(b);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.offline);
		
		
		 
		http_load = (WebView) findViewById(R.id.imageView_http);
		httpBottom = (WebView) findViewById(R.id.imageView_http_bottom);
		contentFirst=(ImageView)findViewById(R.id.content1);
		flip=(ViewFlipper)findViewById(R.id.details);
		sideBanner();
		bottomBanner();
		checkConnection();
		new SubmitCommentTaskDevice().execute();
		contentFirst.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				flip.setInAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipnext));
				flip.setOutAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipoutnext));
				flip.setDisplayedChild(1);
				ndtv = (VideoView) findViewById(R.id.Video_ndtv);
				Uri path = Uri.parse("file://"+Environment.getExternalStorageDirectory()+"/Killer.Elite/Killer.Elite.2011.DVDRip.XviD.AC3-ViSiON.avi");
				ndtv.setMediaController(null);

				
				ndtv.setVideoURI(path);
				ndtv.start();
				
			}
		});
		
	}

	/*public boolean CheckInternet() 
	{
	    ConnectivityManager connec = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	    android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    android.net.NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

	    // Here if condition check for wifi and mobile network is available or not.
	    // If anyone of them is available or connected then it will return true, otherwise false;

	    if (wifi.isConnected()) {
	        return true;
	    } else if (mobile.isConnected()) {
	    	Intent i=new Intent(OfflineCon.this,AndyActivity.class);
			startActivity(i);
			
			finish();
	        return true;
	    }
	    return false;
	}*/
	
	public void checkConnection(){
		checkNet = new Runnable() {
		        @Override
		        public void run() {
		        if(!(minutes == 0))
		        	
		    // txtdate.setText("hii"+ minutes--);
		       // getMarkers();
		        	 conMgr1 = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

		    	// ARE WE CONNECTED TO THE NET

		    	if (conMgr1.getActiveNetworkInfo() != null

		    	

		    	&& conMgr1.getActiveNetworkInfo().isConnected()) {
		        	if(offlineContent1==true){
		        		offlineContent1=false;
		        		new AlertDialog.Builder(OfflineCon.this)
		        	    .setTitle(" Internet Connection is now available")
		        	    .setMessage("Do you want to enjoy the online content?")
		        	    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		        	        public void onClick(DialogInterface dialog, int which) { 
		        	        	//offlineContent1=false;
		        	        	GlobalVariable appState = ((GlobalVariable)getApplicationContext());
		        				  
		        				appState.setScreen(1);
		        				
		        				//ndtv.pause();
		        	        	 /*preferences = getSharedPreferences("MY_LANGUAGE",MODE_PRIVATE);
		        				 editor = preferences.edit();
		        				 editor.putBoolean("storedOffline",offlineContent1); // value to store
		        				 editor.commit();*/
		        	        	Intent i=new Intent(OfflineCon.this,AndyActivity.class);
				        		
			    				// b.putBoolean("OffContents", offlineContent1);
			    				
								startActivity(i);
								
							//finish();
							//	handler.removeCallbacks(checkNet);
								//finish();
		        	        }
		        	     })
		        	    .setNegativeButton("No", new DialogInterface.OnClickListener() {
		        	        public void onClick(DialogInterface dialog, int which) { 
		        	            // do nothing
		        	        	
		        	        }
		        	     }).show();
		        	     
		        
		        	
		        	}
						
		        
		    	}else {
		    		
		    	
		    			
		    				offlineContent1=true;
		    			
		    		
		    	
		    	}
		       
		           handler.postDelayed(this, 10000); //run the runnable in a 10sec again 
		       if(minutes==41){
		    	   minutes=45;
		       }
		
		    	}
		    };  
	}
	public void bottomBanner(){
		
		WebSettings webSettings2 = httpBottom.getSettings(); // Fetches the
		// WebSettings
		// import
		httpBottom.getSettings().setJavaScriptEnabled(true);
		httpBottom.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
		httpBottom.getSettings().setPluginsEnabled(true);
		httpBottom.getSettings().setAllowFileAccess(true);
		httpBottom.getSettings().setSupportZoom(false);
		httpBottom.getSettings().setAppCacheEnabled(false);
		httpBottom.getSettings().setAllowContentAccess(true);
		httpBottom.getSettings().setCacheMode(
				httpBottom.getSettings().LOAD_NO_CACHE);
	//float scale = 155 * http_load.getScale();
	//http_load.setInitialScale( (int) scale );

		httpBottom.setVerticalScrollBarEnabled(false);
		httpBottom.setHorizontalScrollBarEnabled(false);

	webSettings2.setPluginState(WebSettings.PluginState.ON);
	String urlSamsung="file://"+Environment.getExternalStorageDirectory()+"/Banner/728x90_new.html";
	//http_load.loadUrl(
	//"http://gotabbie.com/tabbiedev/adspace/1/showAds.php");
	httpBottom.loadUrl(urlSamsung);
	//http_load.setWebViewClient(new MyWebClient());
	httpBottom.setWebChromeClient(new WebChromeClient() {
	public void onProgressChanged(WebView view, int progress) {

		
		
		
	}
	});
	}

	
	
	public void sideBanner(){
		
		WebSettings webSettings2 = http_load.getSettings(); // Fetches the
		// WebSettings
		// import
http_load.getSettings().setJavaScriptEnabled(true);
http_load.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
http_load.getSettings().setPluginsEnabled(true);
http_load.getSettings().setAllowFileAccess(true);
http_load.getSettings().setSupportZoom(false);
http_load.getSettings().setAppCacheEnabled(false);
http_load.getSettings().setAllowContentAccess(true);
http_load.getSettings().setCacheMode(
http_load.getSettings().LOAD_NO_CACHE);
// float scale = 155 * http_load.getScale();
// http_load.setInitialScale( (int) scale );

http_load.setVerticalScrollBarEnabled(false);
http_load.setHorizontalScrollBarEnabled(false);

webSettings2.setPluginState(WebSettings.PluginState.ON);
String urlSamsung="file://"+Environment.getExternalStorageDirectory()+"/ADS/MSNMessenger_300x250_panel.swf";
// http_load.loadUrl(
// "http://gotabbie.com/tabbiedev/adspace/1/showAds.php");
http_load.loadUrl(urlSamsung);
//http_load.setWebViewClient(new MyWebClient());
http_load.setWebChromeClient(new WebChromeClient() {
	public void onProgressChanged(WebView view, int progress) {
	
		
		
		
	}
});


http_load.setWebViewClient(new WebViewClient() {
	@Override
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		// Handle the error
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) { 
		http_load.setVerticalScrollBarEnabled(false);
		http_load.setHorizontalScrollBarEnabled(false);
		
		// http_load.setInitialScale(120);
		//ndtv = (VideoView) findViewById(R.id.Video_ndtv);
		Uri path = null;
		
		for(int i=0;i<=100;i++){
			
		
		}
		
		if(url.endsWith("madhubala")){
			path = Uri.parse("android.resource://" + getPackageName()
					+ "/" + R.raw.madhubala);
			//KAARI++;
			
		}
		if(url.endsWith("kairee")){
			path = Uri.parse("android.resource://" + getPackageName()
					+ "/" + R.raw.kirii);
			//KAARI++;
			
		}
		
		else if(url.endsWith("nbt")){
			path = Uri.parse("android.resource://" + getPackageName()
					+ "/" + R.raw.nbt);
			//NATUM++;
			
		}
		else if(url.endsWith("parichay")){
			path = Uri.parse("android.resource://" + getPackageName()
					+ "/" + R.raw.parichay);
			//PARICHAY++;
			
		}
		else if(url.endsWith("chhal2")){
			path = Uri.parse("android.resource://" + getPackageName()
					+ "/" + R.raw.chall2);
			
			//CHHAL++;
		}
		
	/*	ndtv.setMediaController(null);

		ndtv.setVideoURI(path);
		//ndtv.getDuration();
		ndtv.start();
	
	//	play.setText(ndtv.getDuration());
		//l6.setVisibility(View.INVISIBLE);
		vf.setDisplayedChild(7);
	//	vf.setDisplayedChild(11);
		//youtube_loader.loadUrl(url);
*/		return true;
	}
});
		
		
	}
	private class SubmitCommentTaskDevice extends AsyncTask<String, Void, Void> {
		

		protected Void doInBackground(String... params) {
			
			return null;
		}

		protected void onPreExecute() {
			// dialog = ProgressDialog.show(AndyActivity.this,
			// "Submitting Comment",
			// "Please wait for the comment to be submitted.", true);
			
		}

		protected void onPostExecute(Void Result) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
			//checkAvailability();
			handler = new Handler();
			  handler.post(checkNet);
			
		}
	}

}
