package com.maps.andy;


import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.ContentObserver;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;

/**
 * Interface for abstracting registration of external data source listeners.
 *
 * @author Rodrigo Damazio
 */
interface DataSourcesWrapper {
  // Preferences
  void registerOnSharedPreferenceChangeListener(
      OnSharedPreferenceChangeListener listener);
  void unregisterOnSharedPreferenceChangeListener(
      OnSharedPreferenceChangeListener listener);

  

  // Sensors
  Sensor getSensor(int type);
  void registerSensorListener(SensorEventListener listener,
      Sensor sensor, int sensorDelay);
  void unregisterSensorListener(SensorEventListener listener);

  // Location
  boolean isLocationProviderEnabled(String provider);
  void requestLocationUpdates(LocationListener listener);
  void removeLocationUpdates(LocationListener listener);
  Location getLastKnownLocation();
} 

