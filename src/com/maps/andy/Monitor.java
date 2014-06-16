package com.maps.andy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.maps.andy.AndyActivity.MapOverlay;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.AutoText;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Monitor extends MapActivity{
	
	public int DEVICE;
	
	ProgressBar waiting;
	
	Process proc;
	float lat1Wel;
	private AudioManager mAm;
	private boolean mIsMute;
	
	Timer mTimer;
	WebView engine, engine1, engine2, engine3, engine5, http_side, webView;
	float lng1Wel;
	// Holds the most up to date location.
	private Location currentLocationWel;
	WebSettings webSettings;
	
	SharedPreferences preferences;
	
	private boolean locationAvailableWel = true;
	  int  health;
	  TimerTask task;
	  Timer t;
	  int scale ;
      String healthBattery;
      String pluggedBattery;
      String statusBattery;
      String simserialno,phoneNumber;
     // String urlKomli="http://a.zestadz.com/apphandler/request/0.2.0/s2s/14131C047A504047475C44554153455C8E852631/14.195.0.3/Nokia6600/live/json/picture/1/imei/8762345898437598/A/XL/iueuytgw";
      SignalStrength signalStrengthListener;
      //  int  status= intent.getIntExtra(BatteryManager.EXTRA_STATUS,0);
        int  plugged;
       // boolean  present= intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
        int  status;
        int  temperature; 
        int  voltage;
	String internetCon;
	PendingIntent intent;
	Handler handler;
	 String strDate ;
		String time ;
		 String signal;
		 int deviceReturn;
	 int minutes = 45;
	 String a3;
TextView txtDate1;
int offlineContent=0;
TextView txtauto;
Button skip;
DigitalClock clock;
String newCustomer;
String[] ct_name;
int ct_id;
int taxiId;
String fd_id;
int rawlevel;
String fd_name;
String companyName,driverName,viiraDate,viiraTime;
int bookingId;
Boolean a=false;
String taxi;
TelephonyManager        Tel;
MyPhoneStateListener    MyListener;
GlobalVariable appState;
protected int Rssi=99;
private static final int TEXT_ID = 0;
ConnectivityManager conMgr;
int go=0;

//View v;
//protected static final String TAG = Police.class.getName();

Handler mHandler;

String formattedDate,destination;
private Timer timer;
private UncaughtExceptionHandler defaultUEH;

// handler listener
Button button1, button2;
int typeBar;                     // Determines type progress bar: 0 = spinner, 1 = horizontal
int delay = 40;                  // Milliseconds of delay in the update loop
int maxBarValue = 200;  
int previousId;
int currentId;
Context context;
ImageView device;
String IMEI;




/*//handler listener
private Thread.UncaughtExceptionHandler _unCaughtExceptionHandler = new Thread.UncaughtExceptionHandler() 
{
@Override
public void uncaughtException(Thread thread, Throwable ex) 
{

    // here I do logging of exception to a db

    PendingIntent myActivity = PendingIntent.getActivity(getBaseContext(), 192837, 
            new Intent(getBaseContext(), Police.class), PendingIntent.FLAG_ONE_SHOT);

    AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 
            1000, myActivity );
    System.exit(2);


    // re-throw critical exception further to the os (important)
    defaultUEH.uncaughtException(thread, ex);
}
};

public Police()
{
    defaultUEH = Thread.getDefaultUncaughtExceptionHandler();

    // setup handler for uncaught exception 
    Thread.setDefaultUncaughtExceptionHandler(_unCaughtExceptionHandler);
}*/
public String getCurrentTimeString(){
	
	
	Calendar calendar=Calendar.getInstance();
	int hour=calendar.get(Calendar.HOUR_OF_DAY);
	int minute=calendar.get(Calendar.MINUTE);
	int second=calendar.get(Calendar.SECOND);
	//return String.format("%02d:%02d:%02d", hour,minute,second);
	return String.format("%02d:%02d", hour,minute);
	

}



@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onResume();
	//a=false;
}
	/*@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	//a=false;
}*/

	@Override
protected void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
	
	timer=new Timer("Digital");
	//final AndyActivity ad=new AndyActivity();
	//ad.viiraTime=null;
	//turnGPSOn();
	
	
		
	
	Calendar calendar=Calendar.getInstance();
	final Runnable updateTask=new Runnable(){
		
		public void run(){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
			Calendar c = Calendar.getInstance();
	        formattedDate = df.format(c.getTime());
	        a3=getCurrentTimeString();
			txtDate1.setText("Date : "+formattedDate+ "    Time : "+getCurrentTimeString1());
			/*PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
			PowerManager.WakeLock wl = manager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Hiiiiiiiiii");
			wl.acquire();
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			WindowManager.LayoutParams lp = getWindow().getAttributes();  
			lp.dimAmount=1.0f;
			*/
			 
			//getTaxiId();
	        //getTripDetails();
	       
			//newCustomer=txtDate1.getText().toString();
			/*String a1="00:00:00";
		        if(a1.equals(txtDate1.getText()))
		        {
		        	Intent a=new Intent(Police.this,AndyActivity.class);
		        	startActivity(a);
		        	Log.i("No way", "Fire");
		        	
		        }*/
			
		}
		
	};
	int msec=999-calendar.get(Calendar.MILLISECOND);
	timer.scheduleAtFixedRate(new TimerTask(){
		@Override
		public void run(){
			runOnUiThread(updateTask);
		}
		
		
	}, msec, 1000);
	
}
	
	
	public void getDeviceNo(){
		
		  String result = null;
		   InputStream is = null;
		   StringBuilder sb=null;
		   StrictMode.ThreadPolicy policy = new StrictMode.
		    		ThreadPolicy.Builder().permitAll().build();
		    		StrictMode.setThreadPolicy(policy);
		    		 Tel       = ( TelephonyManager )getSystemService(Context.TELEPHONY_SERVICE);
		   	      Tel.listen(MyListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
		   	       
		   	 IMEI=  "357750040628173";
		   	 	 //IMEI = Tel.getDeviceId();
		    		Log.i("AsssId", "Urs deicve is "+IMEI);
		    		 ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		    		// double total = 44;
		    		   
					 nameValuePairs.add(new BasicNameValuePair("imei",IMEI));
					
		   try{
		       HttpClient httpclient = new DefaultHttpClient();
		       HttpPost httppost = new HttpPost("http://gotabbie.com/tabbiedev1/webservices/getDeviceId.php");
		    
		       httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		       HttpResponse response = httpclient.execute(httppost);
		       HttpEntity entity = response.getEntity();
		       is = entity.getContent();
		       }catch(Exception e){
		           Log.e("log_tag", "Error in http connection"+e.toString());
		      }
		   try{
		       BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		        sb = new StringBuilder();
		        sb.append(reader.readLine() + "\n");
		        String line="0";
		        while ((line = reader.readLine()) != null) {
		                       sb.append(line + "\n");
		                    //   line = reader.readLine();
		         }
		         is.close();
		         result=sb.toString();
		     	
		     //	 int myNum = Integer.parseInt(result);
		         //DEVICE=result;
				 
				//  Log.i("MyNumber", ""+myNum);
		       //  Log.i("Result", result);
		         }catch(Exception e){
		               Log.e("log_tag", "Error converting result "+e.toString());
		         }
		   Log.i("Device ID","deive FOUND 0");
		   JSONArray jArray;
		   try{
			   Log.i("device ID","TRY");
		         jArray = new JSONArray(result);
		         Log.i("device ID","devuce FOUND 1");
		         JSONObject json_data;
		       //  lat11=new Double[jArray.length()];
		     //   deviceReturn=new int[jArray.length()];
		         Log.i("device ID","device FOUND 2");
		        // ct_name=new String[jArray.length()];
		         int i=0;
		         Log.i("deive ID","devive FOUND 3");
		         for(i=0;i<jArray.length();i++){
		       	  
		        	 json_data = jArray.getJSONObject(i);
		               
		       	 
		             //   json_data = jArray.getJSONObject(i);
		        	 
		               deviceReturn=json_data.getInt("deviceId");
		          //     lat11[i]=json_data.getDouble("lat");
		                
		            //    lng11[i]=json_data.getDouble("lng");
		             //   ct_name[i]=json_data.getString("name"); 
		               appState = ((GlobalVariable) getApplicationContext());
						appState.setDevice(deviceReturn);
		               
		             // Log.i("Device ID","device "+deviceReturn);
		         }
		         }
		         catch(JSONException e1){
		        // Toast.makeText(getBaseContext(), "No taxi found" ,Toast.LENGTH_LONG).show();
		         } catch (ParseException e1) {
		    e1.printStackTrace();
		}
		   catch(NullPointerException e){
		  	 e.printStackTrace();
		   }
		
		  // txtdate.setText("id is"+ct_id);
		 //  String DeviceId=Integer.toString(DEVICE);
		   
		 Log.i("Device ID","device "+DEVICE);
		//taxiId=ct_id;
		 
		 
		
	}
	
	
	//http://gotabbie.com/tabbiedev1/webservices/getDeviceId.php
	public void getTaxiId(){
		
		  String result = null;
		   InputStream is = null;
		   StringBuilder sb=null;
		   StrictMode.ThreadPolicy policy = new StrictMode.
		    		ThreadPolicy.Builder().permitAll().build();
		    		StrictMode.setThreadPolicy(policy);
		    		
		    		 ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		    		// double total = 44;
		    		 appState = ((GlobalVariable) getApplicationContext());
						int DEVICE = appState.getDevice();
						
						
		    		// DEVICE=101;
					 nameValuePairs.add(new BasicNameValuePair("deviceId",""+DEVICE));
					
			          //  nameValuePairs.add(new BasicNameValuePair("lat",Latitude));
			            //nameValuePairs.add(new BasicNameValuePair("lng",Longitude));
		   try{
		       HttpClient httpclient = new DefaultHttpClient();
		       HttpPost httppost = new HttpPost("http://gotabbie.com/tabbiedev/webservices/getTaxiId.php");
		       httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		       HttpResponse response = httpclient.execute(httppost);
		       HttpEntity entity = response.getEntity();
		       is = entity.getContent();
		       }catch(Exception e){
		           Log.e("log_tag", "Error in http connection"+e.toString());
		      }
		   try{
		       BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		        sb = new StringBuilder();
		        sb.append(reader.readLine() + "\n");
		        String line="0";
		        while ((line = reader.readLine()) != null) {
		                       sb.append(line + "\n");
		                    //   line = reader.readLine();
		         }
		         is.close();
		         result=sb.toString();
		         
		         
		         Log.i("Result", result);
		         }catch(Exception e){
		               Log.e("log_tag", "Error converting result "+e.toString());
		         }
		   Log.i("TAXI ID","TAXI FOUND 0");
		   JSONArray jArray;
		   try{
			   Log.i("TAXI ID","TRY");
		         jArray = new JSONArray(result);
		         Log.i("TAXI ID","TAXI FOUND 1");
		         JSONObject json_data;
		       //  lat11=new Double[jArray.length()];
		        // lng11=new Double[jArray.length()];
		         Log.i("TAXI ID","TAXI FOUND 2");
		         ct_name=new String[jArray.length()];
		         int i=0;
		         Log.i("TAXI ID","TAXI FOUND 3");
		         for(i=0;i<jArray.length();i++){
		       	  
		       	 
		       	 
		                json_data = jArray.getJSONObject(i);
		                ct_id=json_data.getInt("taxiId");
		          //     lat11[i]=json_data.getDouble("lat");
		                
		            //    lng11[i]=json_data.getDouble("lng");
		             //   ct_name[i]=json_data.getString("name"); 
		               
		               Log.i("TAXI ID","TAXI FOUND 4");
		         }
		         }
		         catch(JSONException e1){
		        // Toast.makeText(getBaseContext(), "No taxi found" ,Toast.LENGTH_LONG).show();
		         } catch (ParseException e1) {
		    e1.printStackTrace();
		}
		   catch(NullPointerException e){
		  	 e.printStackTrace();
		   }
		
		  // txtdate.setText("id is"+ct_id);
		
		taxiId=ct_id;
		
		
	}
	
	public void getTripDetails(){
		
		//txtname1=(TextView)findViewById(R.id.txtname);
		//    engine = (WebView) findViewById(R.id.web_engine);
		    
		       // engine.setWebChromeClient(new WebChromeClient());
		       // engine.setWebViewClient(new WebViewClient());
		       // engine.getSettings().setAllowFileAccess(true);  

		    StrictMode.ThreadPolicy policy = new StrictMode.
	         		ThreadPolicy.Builder().permitAll().build();
	         		StrictMode.setThreadPolicy(policy);
	         		
		    String result = null;
		    InputStream input = null;
		    StringBuilder sbuilder = null;
		 //   outputStream = (EditText)findViewById(R.id.output);
		    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
    		// double total = 44;
		  //  taxi="3";
		     taxi=String.valueOf(taxiId);
			 //nameValuePairs.add(new BasicNameValuePair("Device_Id","173"));
	            nameValuePairs.add(new BasicNameValuePair("taxiId",taxi));
	            nameValuePairs.add(new BasicNameValuePair("deviceTime",a3));
	            nameValuePairs.add(new BasicNameValuePair("deviceDate",formattedDate));
	            

		    try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://gotabbie.com/viiradev/webservices/getTripDetails.php");
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost);
		        if (response.getStatusLine().getStatusCode() != 200) {
		            Log.d("MyApp", "Server encountered an error");
		        }
		        HttpEntity entity = response.getEntity();
		        input = entity.getContent();
		    }
		    catch(Exception e){
		        e.printStackTrace();
		    }
		    try{
		        BufferedReader reader = new BufferedReader(new InputStreamReader(input,"iso-8859-1"),8);
		        sbuilder = new StringBuilder();

		        String line = null;

		        while((line = reader.readLine()) != null){
		            sbuilder.append(line + "\n");
		        }
		        input.close();
		        result = sbuilder.toString();
		    }
		    catch(Exception e){
		        e.printStackTrace();
		    }
		    
		    try{
		        JSONArray jArray = new JSONArray(result);
		        JSONObject json_data = null;
		        for(int i=0;i<jArray.length();i++){
		            json_data = jArray.getJSONObject(i);
		            bookingId=json_data.getInt("bookingId");
		            fd_id = json_data.getString("customerName");
		            driverName=json_data.getString("driverName");
		            fd_name = json_data.getString("source");
		          
		            destination=json_data.getString("destination");
		            viiraDate=json_data.getString("date");
		            viiraTime=json_data.getString("time");
		            Log.i("MyApp", "Server encountered ");
		         
		            strDate = formattedDate;
		    		time = a3;
		    		String wstime = viiraTime;
		    		String deviceDateTime = strDate + time;
		    		String webserviceDateTime = strDate + wstime;
		    		String strFormat = "yyyy-MM-ddHH:mm";
		    		
		    		Date device = stringToDate(deviceDateTime, strFormat);
		    		Date api = stringToDate(webserviceDateTime, strFormat);
		    		if((api.getTime() - device.getTime()) > 600000){
		    			//Toast.makeText(getBaseContext(), "Date ka funda found", Toast.LENGTH_LONG).show();
		    			
		    			
		    			 Log.d("MyApp", "Server encountered an error11");
		    		}else{
		    			  //txtauto.setText(bookingId+   fd_id +" " + fd_name + "\n"+ destination+ driverName+ viiraDate+  viiraTime);
		    			
		    			//a=false;
		    			
		    			if(a==true){
		    				int c=0;
		    				c++;
		    				 Log.d("MyApp", "Server encountered an error22");
		    				// txtauto.setText("Goooo"+c);
		    				// a=false;
		    			}
		    			else{
		    				 Log.d("MyApp", "Server encountered an error33");
		    				 //txtauto.setText("Goooddddd");
		    				
		    				 Intent i4=new Intent(Monitor.this,AndyActivity.class);
		    				 Bundle b=new Bundle();
		    				 b.putInt("bookingId", bookingId);
		    				 b.putString("customerName", fd_id);
		    				 b.putString("driverName", driverName);
		    				 b.putString("source", fd_name);
		    				 b.putString("destination", destination);
		    				 b.putString("taxiId", taxi);
		    				 b.putString("strDate",strDate);
		    				 b.putString("time", time);
		    				
		    				 
		    				 i4.putExtras(b);
		    				 
		    				 
				    			startActivity(i4);
		    				 a=true;
		    				// task.cancel();
			    				//t.cancel();
			    			
		    				// go=1;
		    				// finish();
		    				 
		    				 
		    			}
		    			  
		    			
		    		}
		    		
		          
		        }
		        }
		    catch(JSONException e1){
		       // Toast.makeText(getBaseContext(), "No Customer found", Toast.LENGTH_LONG).show();
		    }
		    catch(ParseException e1){
		        e1.printStackTrace();
		    }
		     catch(NullPointerException ex) {
		    	 ex.printStackTrace();
		     }  
		    catch(Exception er){
		    	 er.printStackTrace();
		    }
		  
		
		
	}
	
	public static Date stringToDate(String strDate, String strFormat){
		Date objDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			try {
				objDate = sdf.parse(strDate);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		return objDate;
	}
	
	 public String getCurrentTimeString1(){
	    	
	    	
	    	Calendar calendar=Calendar.getInstance();
	    	int hour=calendar.get(Calendar.HOUR_OF_DAY);
	    	int minute=calendar.get(Calendar.MINUTE);
	    	int second=calendar.get(Calendar.SECOND);
	    	return String.format("%02d:%02d:%02d", hour,minute,second);

	    }
	 
	

	 
	 
	 Runnable r1 = new Runnable() {
	        @Override
	        public void run() {
	        	if(go==0){
	        if(!(minutes == 0))
	        	
	    // txtdate.setText("hii"+ minutes--);
	       // getMarkers();
	        	 conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

	    	// ARE WE CONNECTED TO THE NET

	    	if (conMgr.getActiveNetworkInfo() != null

	    	&& conMgr.getActiveNetworkInfo().isAvailable()

	    	&& conMgr.getActiveNetworkInfo().isConnected()) {
	    		getDeviceNo();
	        	getTaxiId();
	       getTripDetails();
	        //registerLocationListeners();
	        internetCon="Connected";
	         
	    	}else {
	    		
	    		internetCon="No Connection";
	    		go=0;
	    		
	    		 task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// Intent intent = new Intent(AudioRecordTest.this,
						// MainActivity.class);
						//Intent i=new Intent(Monitor.this,Monitor.class);
						//startActivity(i);
					//	t.cancel();
						
						// startActivity(intent);
					
					}
				};
				 t = new Timer();
				t.schedule(task, 10000);
				
	    	}
	    		//Toast.makeText(Monitor.this,"No network connection", Toast.LENGTH_SHORT);
	    		/*new AlertDialog.Builder(Monitor.this)
	    	    .setTitle("Delete entry")
	    	    .setMessage("Are you sure you want to delete this entry?")
	    	    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	        public void onClick(DialogInterface dialog, int which) { 
	    	            // continue with delete
	    	        }
	    	     })
	    	    .setNegativeButton("No", new DialogInterface.OnClickListener() {
	    	        public void onClick(DialogInterface dialog, int which) { 
	    	            // do nothing
	    	        }
	    	     })
	    	     .show();*/
	    	}
	       // trimCache(context);
	      //  String s=getCurrentTimeString();
	    	KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Activity.KEYGUARD_SERVICE);
			KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
			lock.disableKeyguard();
			batteryLevel();
			//  txtauto.setText(" GSM Cinr  "+ signal);
			
			PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
			PowerManager.WakeLock wl = manager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Hiiiiiiiiii");
			wl.acquire();
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			WindowManager.LayoutParams lp = getWindow().getAttributes();  
			lp.dimAmount=1.0f; 
			
	    //	new SubmitCommentTask().execute();
	           handler.postDelayed(this, 10000); //run the runnable in a 10sec again 
	       if(minutes==41){
	    	   minutes=45;
	       }
/*if(viiraTime.equals(s)){
	 
	 Intent i=new Intent(AndyActivity.this,Police.class);
	 startActivity(i);
	    	   
	       }*/
	    	}
	    };  
	    Runnable r11 = new Runnable() {
	        @Override
	        public void run() {
	        if(!(minutes == 0))
	        	
	    // txtdate.setText("hii"+ minutes--);
	       // getMarkers();
	        	 conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

	    	// ARE WE CONNECTED TO THE NET

	    	if (conMgr.getActiveNetworkInfo() != null

	    	&& conMgr.getActiveNetworkInfo().isAvailable()

	    	&& conMgr.getActiveNetworkInfo().isConnected()) {
	        	if(offlineContent>0){
	        		offlineContent=0;
	        		getDeviceNo();
	        		//getTaxiId();
	    	     //  getTripDetails();
	    	        
	    		       // trimCache(context);
	    		      //  String s=getCurrentTimeString();
	    		    	KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Activity.KEYGUARD_SERVICE);
	    				KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
	    				lock.disableKeyguard();
	    				
	    				PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
	    				PowerManager.WakeLock wl = manager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Hiiiiiiiiii");
	    				wl.acquire();
	    				getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    				WindowManager.LayoutParams lp = getWindow().getAttributes();  
	    				lp.dimAmount=1.0f; 
	    		    //	new SubmitCommentTask().execute();
	    		         
	    		
	        		
	        	}
	     
	        
	    	}else {
	    		
	    	
	    			if(offlineContent==0){
	    				offlineContent=1;
	    				//Intent i=new Intent(Monitor.this,OfflineCon.class);
						//startActivity(i);
						//finish();
	    		//Toast.makeText(AndyActivity.this,"No network connection", Toast.LENGTH_SHORT);
	    		
	    	}
	    	}
	      
	           handler.postDelayed(this, 10000); //run the runnable in a 10sec again 
	       if(minutes==41){
	    	   minutes=45;
	       }
	
	    	}
	    };  
	    
	    
	   /* Runnable r2 = new Runnable() {
	        @Override
	        public void run() {
	        if(!(minutes == 0))
	        	
	    // txtdate.setText("hii"+ minutes--);
	       // getMarkers();
	        	//getTaxiId();
	       // getTripDetails();
	       // trimCache(context);
	      //  String s=getCurrentTimeString();
	    	KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Activity.KEYGUARD_SERVICE);
			KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
			lock.disableKeyguard();
			batteryLevel();
			//  txtauto.setText(" GSM Cinr  "+ signal);
			
			PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
			PowerManager.WakeLock wl = manager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Hiiiiiiiiii");
			wl.acquire();
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			WindowManager.LayoutParams lp = getWindow().getAttributes();  
			lp.dimAmount=1.0f; 
			
	    //	new SubmitCommentTask().execute();
	           handler.postDelayed(this, 60000); //run the runnable in a 10sec again 
	       if(minutes==41){
	    	   minutes=45;
	       }
if(viiraTime.equals(s)){
	 
	 Intent i=new Intent(AndyActivity.this,Police.class);
	 startActivity(i);
	    	   
	       }
	        }
	    }; */
	    
	    
	   

	    /*private boolean checkInternetConnection1() {

	    	ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

	    	// ARE WE CONNECTED TO THE NET

	    	if (conMgr.getActiveNetworkInfo() != null

	    	&& conMgr.getActiveNetworkInfo().isAvailable()

	    	&& conMgr.getActiveNetworkInfo().isConnected()) {
	    		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		handler = new Handler();
		  handler.post(r1);
	    		internetCon="Connected";
	    	return true;
	    	

	    	} else {
	    		
	    		internetCon="No Connection";
	    		new AlertDialog.Builder(this)
	    	    .setTitle("Delete entry")
	    	    .setMessage("Are you sure you want to delete this entry?")
	    	    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	        public void onClick(DialogInterface dialog, int which) { 
	    	            // continue with delete
	    	        }
	    	     })
	    	    .setNegativeButton("No", new DialogInterface.OnClickListener() {
	    	        public void onClick(DialogInterface dialog, int which) { 
	    	            // do nothing
	    	        }
	    	     })
	    	     .show();
				// Must call show() prior to fetching text view
				
	    	return true;

	    	}

	    	} */
	   /* public void readFile(){
			try {
			    File myDir = new File(getFilesDir().getAbsolutePath());
			    File extStore = Environment.getExternalStorageDirectory();
			    String s = "";

			    FileWriter fw = new FileWriter(myDir + "/Test.txt");
			    fw.write("Hello World");
			    fw.close();
 
			    BufferedReader br = new BufferedReader(new FileReader( extStore + "/device.txt"));
			    s = br.readLine();
			    txtauto.setText(s);
			    // Set TextView text here using tv.setText(s);

			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			}
		}
	    */
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.police);
		Intent serviceIntent = new Intent(this, Geolocalisation.class);
		//serviceIntent.setClass(this, Geolocalisation.class);
		   startService(serviceIntent);
		 //  bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
         //  System.out.println("Turn ON");
	//	 Boolean boolLog = preferences.getBoolean("geo", false);
		txtDate1=(TextView)findViewById(R.id.txtDate);
		txtauto=(TextView)findViewById(R.id.txtAuto);
		device=(ImageView)findViewById(R.id.image_device);
		 MyListener   = new MyPhoneStateListener();
	        Tel       = ( TelephonyManager )getSystemService(Context.TELEPHONY_SERVICE);
	      Tel.listen(MyListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
	     
	     
	 	 IMEI = Tel.getDeviceId();
	 	//registerLocationListeners();
	 	 Log.i("MyApp", "Server encountered an error"+IMEI);
	      String phonenumber1 = Tel.getLine1Number();
	    //  readFile();
	      ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
	    //  Tel.listen(MyListener ,PhoneStateListener.LISTEN_SERVICE_STATE);
		new SubmitCommentTask1().execute();
		 
		 
		//
		skip=(Button)findViewById(R.id.skip);
		
			simserialno = Tel.getSimSerialNumber();
			 phoneNumber = Tel.getDeviceId();
			 
		
		new SubmitCommentTaskDevice().execute();
	    Process proc;
		try {
			proc = Runtime.getRuntime().exec(new String[]{"su","-c","service call activity 79 s16 com.android.systemui"});
			proc.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//clock=(DigitalClock)findViewById(R.id.digitalClock1);
		//String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
		
		 //Calendar c = Calendar.getInstance();
        //String formattedDate = df.format(c.getTime());
        //v=(View)findViewById(R.layout.police);
      //  v.setSystemUiVisibility(View.INVISIBLE);
        
      
		// textView is the TextView view that should display it
	//txtDate1.setText("Date : "+ formattedDate + "      Time : ");
       // txtDate1.setText(  formattedDate + "      Time : ");
		GlobalVariable appState = ((GlobalVariable)getApplicationContext());
		
		appState.setScreen(0);
	 skip.setOnClickListener(new View.OnClickListener() {
		
		@Override 
		public void onClick(View v) {
			// TODO Auto-generated method stub
			GlobalVariable appState = ((GlobalVariable)getApplicationContext());
			//locationManagerWel.removeUpdates(listenerFineWel);
			// locationManagerWel.removeUpdates(listenerCoarseWel);
			appState.setScreen(0);
		    			Intent i=new Intent(Monitor.this,AndyActivity.class);
		    			startActivity(i);
		    			finish();
		    			
		    		//	task.cancel();
	    			//	t.cancel();
		 	  //startActivity(intent);
		  
		}
	});
			
	device.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			AlertDialog.Builder builder = new AlertDialog.Builder(Monitor.this);
			builder.setTitle("Tabbie Status");
			builder.setMessage(
					"Serial Number :"+simserialno+"\n"+
					"Phone              :"+phoneNumber+"\n"+
					"Network           :"+internetCon+"\n"+
					"Health              :"+healthBattery+"\n"+
					"Level                :"+rawlevel+"%"+"\n"+
					"Plugged           :"+pluggedBattery+"\n"+
					"Status              :"+statusBattery+"\n"+
					"Temperature   :"+temperature+"\n");
			builder.setPositiveButton("OK", null);
			AlertDialog dialog = builder.show();

			// Must call show() prior to fetching text view
			TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
			//messageView.setGravity(Gravity.CENTER);
		}
	});
	}
	private static ContextWrapper getInstance() {
		// TODO Auto-generated method stub
		return null;
	}


	public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static boolean deleteDir(File dir) {
        if (dir!=null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }
    private class MyPhoneStateListener extends PhoneStateListener
    {
      /* Get the Signal strength from the provider, each tiome there is an update */
      @Override
      public void onSignalStrengthsChanged(SignalStrength signalStrength)
      {
    	  // get the signal strength (a value between 0 and 31)
    	     //int strengthAmplitude = signalStrength.getGsmSignalStrength();

    	    //do something with it (in this case we update a text view)
    	  //  txtauto.setText(String.valueOf(strengthAmplitude));
    	   
         super.onSignalStrengthsChanged(signalStrength);
      // txtauto.setText("Signal:"+String.valueOf(signalStrength.getCdmaDbm()));
     //   txtauto.setText("Signal:"+String.valueOf(signalStrength.getEvdoDbm()));

       
      }
    
    	  public void onCallForwardingIndicatorChanged(boolean cfi) {}
    	  public void onCallStateChanged(int state, String incomingNumber) {}
    	  public void onCellLocationChanged(CellLocation location) {}
    	  public void onDataActivity(int direction) {}
    	  public void onDataConnectionStateChanged(int state) {
    		 
    	  }
    	  public void onMessageWaitingIndicatorChanged(boolean mwi) {}
    	  public void onServiceStateChanged(ServiceState serviceState) {}
    	  public void onSignalStrengthChanged(int asu) {}
    	  };

    /* End of private Class */

/* GetGsmSignalStrength */
    
    
    
   
    
    private void batteryLevel() {
        BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                health= intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);
                rawlevel = intent.getIntExtra("level", -1);
               
                
              //  int  status= intent.getIntExtra(BatteryManager.EXTRA_STATUS,0);
                 plugged= intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
               // boolean  present= intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
                  status= intent.getIntExtra(BatteryManager.EXTRA_STATUS,0);
                 temperature= intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0);
               
                 
                 if(health==1)
                 {
                	 healthBattery="BATTERY_HEALTH_UNKNOWN";
                 }
                 else if(health==2){
                	 healthBattery="BATTERY_HEALTH_GOOD";
                 }
                 else if(health==3){
                	 healthBattery=" BATTERY_HEALTH_OVERHEAT";
                 }
                 else if(health==4){
                	 healthBattery="BATTERY_HEALTH_DEAD";
                 }
                 else if(health==5){
                	 healthBattery=" BATTERY_HEALTH_OVER_VOLTAGE";
                 }
                 else if(health==6){
                	 healthBattery="BATTERY_HEALTH_UNSPECIFIED_FAILURE";
                 }
                 
                 if( plugged==0)
                 {
                	 pluggedBattery="NO_CHARGING";
                 }
                 if( plugged==1)
                 {
                	 pluggedBattery="BATTERY_PLUGGED_AC";
                 }
                 if( plugged==2)
                 {
                	 pluggedBattery="BATTERY_PLUGGED_USB";
                 }
                 if( plugged==3)
                 {
                	 pluggedBattery="BATTERY_STATUS_DISCHARGING";
                 }
                 
                 if( status==1)
                 {
                	 statusBattery="BATTERY_STATUS_UNKNOWN";
                 }
                 if( status==2)
                 {
                	 statusBattery="BATTERY_STATUS_CHARGING";
                 }
                 if( status==3)
                 {
                	 statusBattery="BATTERY_STATUS_DISCHARGING";
                 }
                 if( status==4)
                 {
                	 statusBattery="BATTERY_STATUS_NOT_CHARGING";
                 }
                 
                 
                 
                 
                 
                 
                 
                 
                int level = -1;
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                
        //        p("----------current battery level ::"+level);
              //  txtauto.setText("Battery Level Remaining: " + level + "%" + plugged + temperature+ health);
            }
        };
        IntentFilter batteryLevelFilter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryLevelFilter);
    }
    
    
    
    
    
    
    
    
    /*private void turnGPSOn(){
		try{
	    String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	    if(!provider.contains("gps")){ //if gps is disabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        sendBroadcast(poke);
	    }
	}catch(Exception ex){
		ex.printStackTrace();
		}
    }*/
    private class SubmitCommentTask1 extends AsyncTask<String, Void, Void> {
		ProgressDialog dialog;

		protected Void doInBackground(String... params) {
			// Your long running code here
			// httpLiveUrl22 =
			// "http://94.75.250.49/liveorigin/smil:ndtvenglish_iphone.smil/playlist.m3u8";
			// setContentView(R.layout.media_player);

			return null;
		}

		protected void onPreExecute() {
			// dialog = ProgressDialog.show(AndyActivity.this,
			// "Submitting Comment",
			// "Please wait for the comment to be submitted.", true);
		}

		protected void onPostExecute(Void Result) {
			/*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			handler = new Handler();
			  handler.post(r1);*/
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
			.permitAll().build();
	StrictMode.setThreadPolicy(policy);
	handler = new Handler();
	  handler.post(r1);
	//checkInternetConnection1();
	//checkAvailability();

			
		}
	}
    
    
    
    
    
    private class SubmitCommentTaskDevice extends AsyncTask<String, Void, Void> {
		ProgressDialog dialog;

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
			//handler = new Handler();
			//  handler.post(r2);
			
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	 }


