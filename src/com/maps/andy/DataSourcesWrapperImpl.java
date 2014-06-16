package com.maps.andy;


import static com.maps.andy.Constants.MAX_LOCATION_AGE_MS;
import static com.maps.andy.Constants.MAX_NETWORK_AGE_MS;
import android.R;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.ContentObserver;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.maps.andy.Constants;

/**
 * Real implementation of the data sources, which talks to system services.
 *
 * @author Rodrigo Damazio
 */
class DataSourcesWrapperImpl implements DataSourcesWrapper {
  // System services
  private final SensorManager sensorManager;
  private final LocationManager locationManager;

  private final SharedPreferences sharedPreferences;
  private final Context context;

  DataSourcesWrapperImpl(Context context, SharedPreferences sharedPreferences) {
    this.context = context;
    this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
   
    this.sharedPreferences = sharedPreferences;
  }

  @Override
  public void registerOnSharedPreferenceChangeListener(
      OnSharedPreferenceChangeListener listener) {
    sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
  }

  @Override
  public void unregisterOnSharedPreferenceChangeListener(
      OnSharedPreferenceChangeListener listener) {
    sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
  }

  

  @Override
  public Sensor getSensor(int type) {
    return sensorManager.getDefaultSensor(type);
  }

  @Override
  public void registerSensorListener(SensorEventListener listener,
      Sensor sensor, int sensorDelay) {
    sensorManager.registerListener(listener, sensor, sensorDelay);
  }

  @Override
  public void unregisterSensorListener(SensorEventListener listener) {
    sensorManager.unregisterListener(listener);
  }

  @Override
  public boolean isLocationProviderEnabled(String provider) {
    return locationManager.isProviderEnabled(provider);
  }

  @Override
  public void requestLocationUpdates(LocationListener listener) {
    // Check if the provider exists.
    LocationProvider gpsProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
    if (gpsProvider == null) {
      listener.onProviderDisabled(LocationManager.GPS_PROVIDER);
      locationManager.removeUpdates(listener);
      return;
    }

    // Listen to GPS location.
    String providerName = gpsProvider.getName();
    Log.d(Constants.TAG, "TrackDataHub: Using location provider " + providerName);
    locationManager.requestLocationUpdates(providerName,
        0 /*minTime*/, 0 /*minDist*/, listener);

    // Give an initial update on provider state.
    if (locationManager.isProviderEnabled(providerName)) {
      listener.onProviderEnabled(providerName);
    } else {
      listener.onProviderDisabled(providerName);
    }

    // Listen to network location
    try {
      locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
          1000 * 60 * 5 /*minTime*/, 0 /*minDist*/, listener);
    } catch (RuntimeException e) {
      // If anything at all goes wrong with getting a cell location do not
      // abort. Cell location is not essential to this app.
      Log.w(Constants.TAG, "Could not register network location listener.", e);
    }
  }

  @Override
  public Location getLastKnownLocation() {
    // TODO: Let's look at more advanced algorithms to determine the best
    // current location.

    Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    final long now = System.currentTimeMillis();
    if (loc == null || loc.getTime() < now - MAX_LOCATION_AGE_MS) {
      // We don't have a recent GPS fix, just use cell towers if available
      loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

     // int toastResId = 0;
      if (loc == null || loc.getTime() < now - MAX_NETWORK_AGE_MS) {
        // We don't have a recent cell tower location, let the user know:
       // toastResId = 1;
      }

      // Let the user know we have only an approximate location:
     Toast.makeText(context, "we have only an approximate location", Toast.LENGTH_LONG).show();
    }
    return loc;
  }

  @Override
  public void removeLocationUpdates(LocationListener listener) {
    locationManager.removeUpdates(listener);
  }
}

