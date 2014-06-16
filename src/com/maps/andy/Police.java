package com.maps.andy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.TextView;

public class Police extends Activity{
	
	int DEVICE=109;
	
	
	Handler handler;
	 int minutes = 45;
	 String a3;
TextView txtDate1;
TextView txtauto;
//Button skip;
DigitalClock clock;
String newCustomer;
String[] ct_name;
int ct_id;
int taxiId;
String fd_id;
String fd_name;
String companyName,driverName,viiraDate,viiraTime;
int bookingId;
Boolean a=false;
String taxi;
//View v;
//protected static final String TAG = Police.class.getName();

Handler mHandler;

String formattedDate,destination;
private Timer timer;

Button button1, button2;
int typeBar;                     // Determines type progress bar: 0 = spinner, 1 = horizontal
int delay = 40;                  // Milliseconds of delay in the update loop
int maxBarValue = 200;  
int previousId;
int currentId;
Context context;
public String getCurrentTimeString(){
	
	
	Calendar calendar=Calendar.getInstance();
	int hour=calendar.get(Calendar.HOUR_OF_DAY);
	int minute=calendar.get(Calendar.MINUTE);
	int second=calendar.get(Calendar.SECOND);
	//return String.format("%02d:%02d:%02d", hour,minute,second);
	return String.format("%02d:%02d", hour,minute);
	

}


	@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	//a=false;
}


	@Override
protected void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
	
	timer=new Timer("Digital");
	//final AndyActivity ad=new AndyActivity();
	//ad.viiraTime=null;
	turnGPSOn();
	Calendar calendar=Calendar.getInstance();
	final Runnable updateTask=new Runnable(){
		
		public void run(){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
			Calendar c = Calendar.getInstance();
	        formattedDate = df.format(c.getTime());
	        a3=getCurrentTimeString();
			txtDate1.setText("Date : "+formattedDate+ "    Time : "+getCurrentTimeString1());
			PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
			PowerManager.WakeLock wl = manager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Hiiiiiiiiii");
			wl.acquire();
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			WindowManager.LayoutParams lp = getWindow().getAttributes();  
			lp.dimAmount=1.0f;
			
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
	
	
	
	public void getTaxiId(){
		
		

		  String result = null;
		   InputStream is = null;
		   StringBuilder sb=null;
		   StrictMode.ThreadPolicy policy = new StrictMode.
		    		ThreadPolicy.Builder().permitAll().build();
		    		StrictMode.setThreadPolicy(policy);
		    		
		    		 ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		    		// double total = 44;
		    		 
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
Police an=new Police();
		    StrictMode.ThreadPolicy policy = new StrictMode.
	         		ThreadPolicy.Builder().permitAll().build();
	         		StrictMode.setThreadPolicy(policy);
	         		
		    String result = null;
		    InputStream input = null;
		    StringBuilder sbuilder = null;
		 //   outputStream = (EditText)findViewById(R.id.output);
		    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
    		// double total = 44;
		    
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
		           
		         
		            String strDate = formattedDate;
		    		String time = a3;
		    		String wstime = viiraTime;
		    		String deviceDateTime = strDate + time;
		    		String webserviceDateTime = strDate + wstime;
		    		String strFormat = "yyyy-MM-ddHH:mm";
		    		
		    		Date device = stringToDate(deviceDateTime, strFormat);
		    		Date api = stringToDate(webserviceDateTime, strFormat);
		    		if((api.getTime() - device.getTime()) > 600000){
		    			//Toast.makeText(getBaseContext(), "Date ka funda found", Toast.LENGTH_LONG).show();
		    			
		    			
		    			
		    		}else{
		    			  //txtauto.setText(bookingId+   fd_id +" " + fd_name + "\n"+ destination+ driverName+ viiraDate+  viiraTime);
		    			
		    			//a=false;
		    			
		    			if(a==true){
		    				int c=0;
		    				c++;
		    				// txtauto.setText("Goooo"+c);
		    				// a=false;
		    			}
		    			else{
		    				 //txtauto.setText("Goooddddd");
		    				 Intent i4=new Intent(Police.this,AndyActivity.class);
		    				 Bundle b=new Bundle();
		    				 b.putInt("bookingId", bookingId);
		    				 b.putString("customerName", fd_id);
		    				 b.putString("driverName", driverName);
		    				 b.putString("source", fd_name);
		    				 b.putString("destination", destination);
		    				 b.putString("taxiId", taxi);
		    				 i4.putExtras(b);
		    				 
		    				 
				    			startActivity(i4);
		    				 a=true;
		    				 
		    			}
		    			  
		    			  /* txtname1.append(fd_id +" " + fd_name + "\n"+ destination);
				            txtname1.setText(fd_id);
				            txtpickup.setText(fd_name);
				            txtdes.setText(destination);
				            txtDriver.setText(driverName);*/
		    		}
		    		
		           /* java.text.DateFormat df = new java.text.SimpleDateFormat("hh:mm:ss");
		            java.util.Date date1;
					try {
						date1 = df.parse(viiraTime);
						java.util.Date date2 = df.parse(a3);
						 long diff = date2.getTime() - date1.getTime();
						 if(diff>600000){
							   txtname1.append(fd_id +" " + fd_name + "\n"+ destination);
					            txtname1.setText(fd_id);
					            txtpickup.setText(fd_name);
					            txtdes.setText(destination);
					            txtDriver.setText(driverName);
						 }
						 else{
							 Toast.makeText(getBaseContext(), "Date ka funda found", Toast.LENGTH_LONG).show();
						 }
					} catch (java.text.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
		           
		            
		          /* txtname1.append(fd_id +" " + fd_name + "\n"+ destination);
		            txtname1.setText(fd_id);
		            txtpickup.setText(fd_name);
		            txtdes.setText(destination);
		            txtDriver.setText(driverName);
		           // viira=viiraTime;
*/			            
		        }
		        }
		    catch(JSONException e1){
		       // Toast.makeText(getBaseContext(), "No Customer found", Toast.LENGTH_LONG).show();
		    }
		    catch(ParseException e1){
		        e1.printStackTrace();
		    }
		        
		   /* mTimer.scheduleAtFixedRate(new TimerTask() {
		        @Override
		        public void run() {
		        	LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		        	lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, AndyActivity.this);
		        	Location l = null;
		        	l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		             }
		        }, 0, 1000);*/
		
		
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
	        if(!(minutes == 0))
	        	
	    // txtdate.setText("hii"+ minutes--);
	       // getMarkers();
	        	getTaxiId();
	        getTripDetails();
	        trimCache(context);
	      //  String s=getCurrentTimeString();
	     
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.police);
		
		
		KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Activity.KEYGUARD_SERVICE);
		KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
		lock.disableKeyguard();
		
		PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = manager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Hiiiiiiiiii");
		wl.acquire();
		txtDate1=(TextView)findViewById(R.id.txtDate);
		txtauto=(TextView)findViewById(R.id.txtAuto);
		//skip=(Button)findViewById(R.id.skip);
		//schedule alarm here and post runnable as soon as scheduled
		  handler = new Handler();
	    handler.post(r1);
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
		
	  /*skip.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		    			Intent i=new Intent(Police.this,AndyActivity.class);
		    			startActivity(i);
		 		    
		 	  //startActivity(intent);
		  
		}
	});*/
			
	
		
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
	
    private void turnGPSOn(){
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
    }
}
