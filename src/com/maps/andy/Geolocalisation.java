package com.maps.andy;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.android.maps.GeoPoint;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;

public class Geolocalisation extends Service{
	private LocationListener listenerCoarse1;
	private LocationListener listenerFine1;
	private Location currentLocationWel;
private LocationManager locationManager;
GlobalVariable appState;
int DEVICE;
String DeviceId;
//private String locationProvider = LocationManager.NETWORK_PROVIDER;
private String locationProvider = LocationManager.NETWORK_PROVIDER;

@Override 
public void onCreate(){

    System.out.println("Service en cours !!");

    //Recuperation Location
    String locationContext = Context.LOCATION_SERVICE;
    locationManager = (LocationManager) getSystemService(locationContext);
    if (locationManager != null && locationProvider != null) {
    	registerLocationListeners();
     //   majCoordonnes();
      //  locationManager.requestLocationUpdates(locationProvider, 10000,0 , new MajListener());

    }

}

private void createLocationListeners() {
	listenerCoarse1 = new LocationListener() {
		public void onStatusChanged(String provider, int status,
				Bundle extras) {
			switch (status) {
			case LocationProvider.OUT_OF_SERVICE:
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
			
			//	locationAvailableWel = false;
				break;
			case LocationProvider.AVAILABLE:
				//locationAvailableWel = true;
			}
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}

		public void onLocationChanged(Location location) {
			//currentLocationWel = location;
			if (location.getAccuracy() > 1000 && location.hasAccuracy())
				locationManager.removeUpdates(this);
			if (location != null) {
			GeoPoint p = new GeoPoint((int) (location.getLatitude() * 1E6),
						(int) (location.getLongitude() * 1E6));
				//mc = mapView.getController();
				 double latitude = location.getLatitude();
			        double longitude = location.getLongitude();
				// mapView.invalidate();
			
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		// DEVICE=deviceReturn;
		
		appState = ((GlobalVariable) getApplicationContext());
		DEVICE= appState.getDevice();
		DeviceId=Integer.toString(DEVICE); 
		Log.i("postData in Service",DeviceId);
		  ArrayList<NameValuePair> nameValuePairs1 = new
		  ArrayList<NameValuePair>(3); // double total = 44;
		  String Latitude1 = Double.toString(latitude); 
		  String Longitude1=Double.toString(longitude); 
		  Log.i("Executedddddddd", "Wts sayyh"+latitude+longitude);
		
		  nameValuePairs1.add(new BasicNameValuePair("Device_Id",DeviceId));
		  nameValuePairs1.add(new BasicNameValuePair("Latitude",Latitude1));
		  nameValuePairs1.add(new BasicNameValuePair("Longitude",Longitude1));
		  try{
			  HttpClient httpclient = new DefaultHttpClient(); 
			  HttpPost httppost = new HttpPost("http://gotabbie.com/tabbieOld/insert.php");
			  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1)); 
			  HttpResponse response = httpclient.execute(httppost);
			  HttpEntity entity = response.getEntity(); 
			  InputStream is3 = entity.getContent();
			//  Log.i("postData",response.getStatusLine().toString()); //
			//  Toast.makeText(AndyActivity.this,"send", Toast.LENGTH_LONG); 
			  }
			  
			  catch(Exception e) { Log.e("log_tag", "Error in http connection "+e.toString()); 
			  }
			 
			}
			// Add a location marker
			
		}
	};
	listenerFine1 = new LocationListener() {
		
		public void onStatusChanged(String provider, int status,
				Bundle extras) {
			switch (status) {
			case LocationProvider.OUT_OF_SERVICE:
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				
				//locationAvailableWel = false;
				break;
			case LocationProvider.AVAILABLE:
				//locationAvailableWel = true;
			}
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}

		@SuppressWarnings("unused")
		public void onLocationChanged(Location location) {
			
		
			
			
			  
			
			if (location.getAccuracy() > 1000 && location.hasAccuracy())
				locationManager.removeUpdates(this);
			if (location != null) {
				GeoPoint p = new GeoPoint((int) (location.getLatitude() * 1E6),
						(int) (location.getLongitude() * 1E6));
				//mc = mapView.getController();
				
				// mapView.invalidate();
				 double latitude = location.getLatitude();
			        double longitude = location.getLongitude();
				
				
				 
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
				
				appState = ((GlobalVariable) getApplicationContext());
				DEVICE= appState.getDevice();
				DeviceId=Integer.toString(DEVICE); 
				Log.i("postData in Service",DeviceId);
				  ArrayList<NameValuePair> nameValuePairs1 = new
				  ArrayList<NameValuePair>(3); // double total = 44;
				  String Latitude1 = Double.toString(latitude); 
				  String Longitude1=Double.toString(longitude); 
				  Log.i("Executedddddddd", "Wts sayyh"+latitude+longitude);
				  
				  nameValuePairs1.add(new BasicNameValuePair("Device_Id",DeviceId));
				  nameValuePairs1.add(new BasicNameValuePair("Latitude",Latitude1));
				  nameValuePairs1.add(new BasicNameValuePair("Longitude",Longitude1));
				  try{
				  HttpClient httpclient = new DefaultHttpClient(); 
				  HttpPost httppost = new HttpPost("http://gotabbie.com/tabbieOld/insert.php");
				  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1)); 
				  HttpResponse response = httpclient.execute(httppost);
				  HttpEntity entity = response.getEntity(); 
				  InputStream is3 = entity.getContent();
				//  Log.i("postData",response.getStatusLine().toString()); //
				//  Toast.makeText(AndyActivity.this,"send", Toast.LENGTH_LONG); 
				  }
				  
				  catch(Exception e) { Log.e("log_tag", "Error in http connection "+e.toString()); 
				  }
				 
				 
			
			}
			// Add a location marker
			
		
		}

	};
}


private void registerLocationListeners() {
	try{
	locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

	// Initialize criteria for location providers
	Criteria fine = new Criteria();
	fine.setAccuracy(Criteria.ACCURACY_FINE);
	Criteria coarse = new Criteria();
	coarse.setAccuracy(Criteria.ACCURACY_COARSE);

	// Get at least something from the device,
	// could be very inaccurate though
	currentLocationWel = locationManager.getLastKnownLocation(locationManager
			.getBestProvider(fine, true));

	if (listenerFine1 == null || listenerCoarse1 == null)
		createLocationListeners();

	// Will keep updating about every 500 ms until
	// accuracy is about 1000 meters to get quick fix.
	locationManager.requestLocationUpdates(
			locationManager.getBestProvider(coarse, true), 5000, 5,
			listenerCoarse1);
	// Will keep updating about every 500 ms until
	// accuracy is about 50 meters to get accurate fix.
	locationManager.requestLocationUpdates(
			locationManager.getBestProvider(fine, true), 5000, 500,
			listenerFine1);
	
	Log.i("Executedddddddd", "Wts sayyh");
	}catch(Exception ex){
		ex.printStackTrace();
		
	}
}
@Override
public void onStart(Intent intent, int StartId){
    System.out.println("Service commence !!");
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    SharedPreferences.Editor editor = preferences.edit();
    editor.putBoolean("geo", true);
    editor.commit();    
}

@Override
public void onDestroy(){
    System.out.println("Service détruit !!");
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    SharedPreferences.Editor editor = preferences.edit();
    editor.putBoolean("geo", false);
    editor.commit();    

}

@Override
public IBinder onBind(Intent arg0){
    return null;
}


public void majCoordonnes() {
    StringBuilder stringBuilder = new StringBuilder("Fournisseur :");
    stringBuilder.append("\n");
    stringBuilder.append(locationProvider);
    stringBuilder.append(" : ");
    Location location = locationManager.getLastKnownLocation(locationProvider);

    if (location != null) {

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        String lat = String.valueOf(latitude);
        String lon = String.valueOf(longitude);

        stringBuilder.append(latitude);
        stringBuilder.append(", ");
        stringBuilder.append(longitude);
    	//if (location.getAccuracy() > 1000 && location.hasAccuracy())
			//locationManager.removeUpdates(this);
	
			GeoPoint p = new GeoPoint((int) (location.getLatitude() * 1E6),
					(int) (location.getLongitude() * 1E6));
			//mc = mapView.getController();
			
			// mapView.invalidate();
		
			
			
			 
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
			GlobalVariable appState = ((GlobalVariable) getApplicationContext());
			int state = appState.getDevice();
			String DeviceId=Integer.toString(state); 
			//String DeviceId=Integer.toString(DEVICE); 
			Log.i("postData",DeviceId);
			  ArrayList<NameValuePair> nameValuePairs1 = new
			  ArrayList<NameValuePair>(3); // double total = 44;
			  String Latitude1 = Double.toString(latitude); 
			  String Longitude1=Double.toString(longitude); 
			
			  
			  nameValuePairs1.add(new BasicNameValuePair("Device_Id",DeviceId));
			  nameValuePairs1.add(new BasicNameValuePair("Latitude",Latitude1));
			  nameValuePairs1.add(new BasicNameValuePair("Longitude",Longitude1));
			  try{
			  HttpClient httpclient = new DefaultHttpClient(); 
			  HttpPost httppost = new HttpPost("http://gotabbie.com/tabbieOld/insert.php");
			  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1)); 
			  HttpResponse response = httpclient.execute(httppost);
			  HttpEntity entity = response.getEntity(); 
			  InputStream is3 = entity.getContent();
			//  Log.i("postData",response.getStatusLine().toString()); //
			//  Toast.makeText(AndyActivity.this,"send", Toast.LENGTH_LONG); 
			  }
			  
			  catch(Exception e) { Log.e("log_tag", "Error in http connection "+e.toString()); 
			  }
			 
			 
		
		
        //Send location to server
     //   new sendLocation().execute(lat, lon);

        System.out.println("Localisation:  "+ lat +" "+lon );


    } else {
        stringBuilder.append("Non déterminée");
    }
    //Log.d("MaPositionMaj", stringBuilder.toString());
}


/**
 * Ecouteur utilisé pour les mises à jour des coordonnées
 */
class MajListener implements android.location.LocationListener {

    public void onLocationChanged(Location location) {
     //   majCoordonnes();
        System.out.println("Update geo!");
    }
    public void onProviderDisabled(String provider){
    }
    public void onProviderEnabled(String provider){
    }
    public void onStatusChanged(String provider, int status, Bundle extras){
    }
};
}