package com.maps.andy;

import static com.maps.andy.Constants.MAX_LOCATION_AGE_MS;
import static com.maps.andy.Constants.MAX_NETWORK_AGE_MS;

import android.widget.MediaController;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.io.InputStream;

import com.maps.andy.MyLocation.LocationResult;
import com.maps.andy.Police;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import com.maps.andy.Police;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.LocalActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ParseException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.format.Time;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;
import com.maps.andy.AnimationFactory.FlipDirection;

public class AndyGPS extends MapActivity implements 
		OnCompletionListener, OnPreparedListener {
int DEVICE=101;
	int MAP = 0;
	int ENTERTAINMENT = 0;
	int NEWS = 0;
	int SPORTS = 0;
	int SETTINGS = 0;

	int FUNASIA = 0;
	int GOODTIMES = 0;
	int YOUTUBE = 0;

	int SAHARA = 0;
	int INDIATV = 0;
	int NEWX = 0;
	int PROFIT = 0;
	int HINDU = 0;
	int ENGLISH = 0;

	int CRICKET = 0;
	int TENNIS = 0;
	int IPL = 0;
	int FOOTBALL = 0;

	int BANNERMEDIUM = 0;
	int BANNERSMALL = 0;
	int BANNERLARGE = 0;

	int TERMS = 0;
	int LOGOUT = 0;
	int REFERAL = 0;
	int PRIVACY = 0;

	int DISCLOSURE = 0;
	int ADVERTISE = 0;
	int ABOUTUS = 0;

	int CLICKHERE = 0;
	int STARTCLICK=0;

	int BHEEM = 0;
	int DOREMON = 0;
	int POPEYE = 0;
	int SIMPSONS = 0;
	int TOM = 0;

	int CHAPLIN = 0;
	int MIND = 0;
	int OFFICE = 0;
	int NUKKAD = 0;
	int TWO = 0;

	int DANCE = 0;
	int KUCHBHI = 0;
	int IDOL = 0;
	int ROADIES = 0;
	int TALENT = 0;

	int CHHAL = 0;
	int KAARI = 0;
	int NATUM = 0;
	int PARICHAY = 0;
	int UTTARAN = 0;

	private MyLocationOverlay me = null;
	String add = "";
	int isfirst[];
	String viira;
	static RelativeLayout main;
	private GeoPoint lastMapCenter;
	private boolean isTouchEnded;
	private boolean isFirstComputeScroll;
	ImageView referal, about, advertise, privacy, conditions;
	TextView txt_referal, txtAbout, txtAdvertise, txtPrivacy, txtTerms,
			txtDriver;
	Timer timer;
	Button play, mute, back1, front;
	ProgressBar progThread;
	ProgressDialog progDialog;
	Button button1, button2;
	int typeBar; // Determines type progress bar: 0 = spinner, 1 = horizontal
	int delay = 40; // Milliseconds of delay in the update loop
	int maxBarValue = 200; // Maximum value of horizontal progress bar
	TextView ref;
	Bitmap bmp1;
	int taxiId;
	Button sate, taffic, myloc;
	GeoPoint p, k, maxSizeAreaGP;
	int c = 0;
	GeoPoint  pw;
	String a3;
	LocationManager lm;
	LinearLayout map, bottom;
	String[] cust_name, pickup_point;
	String destination;
	MapController mc;
	GeoPoint point;
	MapView mapView;
	List<Overlay> mapOverlays;
	LocationListener listener;
	LinearLayout noteBaloon, l2, l3, l4, l5, l6;
	Drawable drawable;
	Drawable drawable1;
	Drawable drawable2, drawable3;
	MyItemizedOverlay itemizedOverlay;
	MyItemizedOverlay itemizedOverlay1;

	private ProgressDialog dialog;
	MyItemizedOverlay itemizedOverlay2, itemizedOverlay3, itemizedOverlay123,
			itemizedOverlay22, itemizedOverlay555;
	String bestProvider;
	ImageView image_chan, image_sports, image_stocks, image_shop, image_map,
			cricket, football, ipl, tennis, btnLogout;
	private LinearLayout layout;
	private TextView title;
	private TextView snippet;
	private TextView video;
	private TextView call;
	String httpLiveUrl22;
	private TextView order;

	ImageView click;
	VideoView myVideoView;
	private boolean trackDrawingEnabled;
	private int lastHeading = 0;
	int location22 = 0;
	//private Location myLocation;
	private boolean showEndMarker = true;
	// TODO: Remove it completely after completing performance tests.
	private boolean alwaysVisible = true;
	RelativeLayout rl;
	DigitalClock clock;
	String a;
	Button satellite, terrain, traffic;
	private GeoPoint lastReferencePoint;
	private Rect lastViewRect;
	private boolean lastPathExists;
	private Location lastLocation;
	private TrackPathPainter trackPathPainter;
	AudioManager audioManager;
	double lat;
	String taxi;
	ProgressBar waiting;
	Location location;
	TextView txtdate;
	double lng;
	Process proc;
	float lat1;
	private AudioManager mAm;
	private boolean mIsMute;
	Timer mTimer;
	WebView engine, engine1, engine2, engine3, engine5, http_side, webView;
	float lng1;
	static ImageView idea;
	TextView txtname;
	ImageView terms;
	ViewFlipper vfads, vfterms, vf_settings;
	ImageView back, jeevansathi, acres, shiksha, tabbie;
	static ImageView dominoz;
	LocationManager locationManager;
	GeoPoint centerGp, marker_point;
	String addressString;
	String latLongString;
	Location loc;
	Canvas canvas;
	String AD_FETCH_URI = new String(
			"http://serve.vdopia.com/adserver/html5/adFetch/?output=xhtml&container=androidWeb");
	TextView txtname1, txtpickup, txtdes;
	static final String SCHEME_TALK2ME_REPLAY = "replay:";
	static final String SCHEME_TALK2ME_GOTOAPP = "gotoapp:";
	String fd_id;
	String fd_name;
	String companyName, driverName, viiraDate, viiraTime;
	int bookingId;
	public static final String MINI_VDO_BANNER = "320X75";
	VDOParser parser;
	URL requestURL = null;
	int ct_id;
	Double[] lat11;
	Double[] lng11;
	String[] ct_name;
	TimerTask task;
	Timer t;
	ListView lv;
	ImageView office;
	BalloonItemizedOverlay bb;
	 String deviceId;
	// static VideoView videoView;
	static ViewFlipper vf, vfadsup;
	static ViewFlipper vfmaps, settings;
	int n = 0;
	String formattedDate;
	TextView contact;
	// MapView mapView;
	GeoPoint point2;
	private boolean isFirstImage = true;
	Button btnImage, btnNdtv, btnMappy;
	VideoView video1, video_saam, video_fun, video_goodtimes, video_sahara,
			video_india, video_newx, video_hindi, video_english;
	Activity activity = this;
	Button ndtv_medium, ndtv_small;
	MapView mapview1;

	// Proximity Alerts
	// private String proximityIntentAction = new
	// String("com.maps.andy.wheresmycontacts.PROXIMITY_ALERT");
	

	private static final NumberFormat nf = new DecimalFormat("##.########");
	// private LocationManager locationManager;
	private LocationListener listenerCoarse;
	private LocationListener listenerFine;
	Context context;
	// Holds the most up to date location.
	private Location currentLocation;
	WebSettings webSettings;
	// Set to false when location services are
	// unavailable.
	int minutes = 45;
	Handler handler;
	private boolean locationAvailable = true;
	ImageView imView, bade, bheem, charlie, did, doremon, mind, entertainment,
			ktlkg, popaye, idol, parvarish, simpsons, nukkad, roadies, sas,
			tom, two, talent, uttaran;
	WebView http_load, youtube_loader, http_bottom;
	ImageView stop;
	String imageUrl = "http://10.0.2.2/images";
	Random r = new Random();
	VideoView viduoyo;
	private VideoView ndtv;
	int m = 0;

	ImageView channel_ndtv, youtube, comedy, saam, fun, goodtimes, sahara,
			india, newx, hindi, english;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	SeekBar volControl;
	double latitude;
	double longitude;

	LinearLayout parent;
	MyLocation myLocation = new MyLocation();
	/*
	 * protected OverlayItem createItem(int i){ LinearLayout layout; TextView
	 * title; TextView snippet; TextView video; TextView call; TextView order;
	 * LinearLayout layout1; BalloonOverlay bov = new BalloonOverlay(context,
	 * 50);
	 * 
	 * 
	 * layout1 = new LinearLayout(context); layout1.setVisibility(View.VISIBLE);
	 * 
	 * LayoutInflater inflater = (LayoutInflater) context
	 * .getSystemService(Context.LAYOUT_INFLATER_SERVICE); View v =
	 * inflater.inflate(R.layout.balloon_map_overlay, layout1); title =
	 * (TextView) v.findViewById(R.id.balloon_item_title); snippet = (TextView)
	 * v.findViewById(R.id.balloon_item_snippet); video = (TextView)
	 * v.findViewById(R.id.balloon_item_video); call = (TextView)
	 * v.findViewById(R.id.balloon_item_call); order = (TextView)
	 * v.findViewById(R.id.balloon_item_order);
	 * 
	 * 
	 * 
	 * FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
	 * LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); params.gravity =
	 * Gravity.NO_GRAVITY;
	 * 
	 * mapView.addView(layout1, params); return null;
	 * 
	 * 
	 * }
	 */
	Police an = new Police();
	String a1 = an.getCurrentTimeString();

	public void getTripDetails() {

		txtname1 = (TextView) findViewById(R.id.txtname);
		// engine = (WebView) findViewById(R.id.web_engine);

		// engine.setWebChromeClient(new WebChromeClient());
		// engine.setWebViewClient(new WebViewClient());
		// engine.getSettings().setAllowFileAccess(true);
		Police an = new Police();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		String result = null;
		InputStream input = null;
		StringBuilder sbuilder = null;
		// outputStream = (EditText)findViewById(R.id.output);
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
				3);
		// double total = 44;

		taxi = String.valueOf(taxiId);
		// nameValuePairs.add(new BasicNameValuePair("Device_Id","173"));
		nameValuePairs.add(new BasicNameValuePair("taxiId", taxi));
		nameValuePairs.add(new BasicNameValuePair("deviceTime", a3));
		nameValuePairs.add(new BasicNameValuePair("deviceDate", formattedDate));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://gotabbie.com/viiradev/webservices/getTripDetails.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() != 200) {
				Log.d("MyApp", "Server encountered an error");
			}
			HttpEntity entity = response.getEntity();
			input = entity.getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input, "iso-8859-1"), 8);
			sbuilder = new StringBuilder();

			String line = null;

			while ((line = reader.readLine()) != null) {
				sbuilder.append(line + "\n");
			}
			input.close();
			result = sbuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			JSONArray jArray = new JSONArray(result);
			JSONObject json_data = null;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				bookingId = json_data.getInt("bookingId");
				fd_id = json_data.getString("customerName");
				driverName = json_data.getString("driverName");
				fd_name = json_data.getString("source");

				destination = json_data.getString("destination");
				viiraDate = json_data.getString("date");
				viiraTime = json_data.getString("time");

				String strDate = formattedDate;
				String time = a3;
				String wstime = viiraTime;
				String deviceDateTime = strDate + time;
				String webserviceDateTime = strDate + wstime;
				String strFormat = "yyyy-MM-ddHH:mm";

				Date device = stringToDate(deviceDateTime, strFormat);
				Date api = stringToDate(webserviceDateTime, strFormat);
				if ((api.getTime() - device.getTime()) > 600000) {
					Toast.makeText(getBaseContext(), "Date ka funda found",
							Toast.LENGTH_LONG).show();
				} else {
					txtname1.append(fd_id + " " + fd_name + "\n" + destination);
					txtname1.setText(fd_id);
					txtpickup.setText(fd_name);
					txtdes.setText(destination);
					txtDriver.setText(driverName);
				}
				/*
				 * java.text.DateFormat df = new
				 * java.text.SimpleDateFormat("hh:mm:ss"); java.util.Date date1;
				 * try { date1 = df.parse(viiraTime); java.util.Date date2 =
				 * df.parse(a3); long diff = date2.getTime() - date1.getTime();
				 * if(diff>600000){ txtname1.append(fd_id +" " + fd_name + "\n"+
				 * destination); txtname1.setText(fd_id);
				 * txtpickup.setText(fd_name); txtdes.setText(destination);
				 * txtDriver.setText(driverName); } else{
				 * Toast.makeText(getBaseContext(), "Date ka funda found",
				 * Toast.LENGTH_LONG).show(); } } catch
				 * (java.text.ParseException e) { // TODO Auto-generated catch
				 * block e.printStackTrace(); }
				 */

				/*
				 * txtname1.append(fd_id +" " + fd_name + "\n"+ destination);
				 * txtname1.setText(fd_id); txtpickup.setText(fd_name);
				 * txtdes.setText(destination); txtDriver.setText(driverName);
				 * // viira=viiraTime;
				 */
			}
		} catch (JSONException e1) {
			Toast.makeText(getBaseContext(), "No Customer found",
					Toast.LENGTH_LONG).show();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		/*
		 * mTimer.scheduleAtFixedRate(new TimerTask() {
		 * 
		 * @Override public void run() { LocationManager lm = (LocationManager)
		 * getSystemService(Context.LOCATION_SERVICE);
		 * lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,
		 * AndyActivity.this); Location l = null; l =
		 * lm.getLastKnownLocation(LocationManager.GPS_PROVIDER); } }, 0, 1000);
		 */

	}

	@Override
	public void onStart() {
		super.onStart();

		timer = new Timer("Digital");
		// final AndyActivity ad=new AndyActivity();
		// ad.viiraTime=null;

		Calendar calendar = Calendar.getInstance();
		final Runnable updateTask = new Runnable() {

			public void run() {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
				Calendar c = Calendar.getInstance();
				formattedDate = df.format(c.getTime());
				a3 = getCurrentTimeString();
				Log.i("No way", formattedDate);
				Log.i("Nmmmmmmmmmm", a3);
				// txtDate1.setText("Date : "+formattedDate+
				// "    Time : "+getCurrentTimeString());
				// newCustomer=txtDate1.getText().toString();
				/*
				 * String a1="00:00:00"; if(a1.equals(txtDate1.getText())) {
				 * Intent a=new Intent(Police.this,AndyActivity.class);
				 * startActivity(a); Log.i("No way", "Fire");
				 * 
				 * }
				 */

			}

		};
		int msec = 999 - calendar.get(Calendar.MILLISECOND);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(updateTask);
			}

		}, msec, 1000);
		
		
	}

	public boolean isLocationProviderEnabled(String provider) {
		return locationManager.isProviderEnabled(provider);
	}

	// Method to create a progress bar dialog of either spinner or horizontal
	// type
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0: // Spinner
			progDialog = new ProgressDialog(this);
			progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progDialog.setMessage("Please wait...tabbie is loading ");

			return progDialog;
		case 1: // Horizontal
			progDialog = new ProgressDialog(this);
			progDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progDialog.setMax(maxBarValue);
			progDialog.setMessage("Dollars in checking account:");

			return progDialog;
		case 2: // Spinner
			progDialog = new ProgressDialog(this);
			progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progDialog.setMessage("Thank You....Have a great day");

			return progDialog;

		case 3: // Spinner
			progDialog = new ProgressDialog(this);
			progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progDialog.setMessage("Please wait...tabbie acquiring location ");

			return progDialog;
		default:
			return null;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	public static Date stringToDate(String strDate, String strFormat) {
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

	public void getMarkers() {

		mapOverlays = mapView.getOverlays();

		String Latitude = Double.toString(latitude);
		String Longitude = Double.toString(longitude);

		String result = null;
		InputStream is = null;
		StringBuilder sb = null;
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
				2);
		// double total = 44;

		// nameValuePairs.add(new BasicNameValuePair("Device_Id","173"));
		nameValuePairs.add(new BasicNameValuePair("lat", Latitude));
		nameValuePairs.add(new BasicNameValuePair("lng", Longitude));
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://gotabbie.com/flash1733/mark.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection" + e.toString());
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line = "0";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
				// line = reader.readLine();
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}
		JSONArray jArray;
		try {

			jArray = new JSONArray(result);
			JSONObject json_data;
			lat11 = new Double[jArray.length()];
			lng11 = new Double[jArray.length()];
			ct_name = new String[jArray.length()];
			int i = 0;
			for (i = 0; i < jArray.length(); i++) {

				json_data = jArray.getJSONObject(i);
				ct_id = json_data.getInt("id");
				lat11[i] = json_data.getDouble("lat");

				lng11[i] = json_data.getDouble("lng");
				ct_name[i] = json_data.getString("name");
				marker_point = new GeoPoint((int) (lat11[i] * 1E6),
						(int) (lng11[i] * 1E6));

				drawable = getResources().getDrawable(R.drawable.bags);
				itemizedOverlay123 = new MyItemizedOverlay(drawable, mapView);

				OverlayItem overlayItem = new OverlayItem(marker_point, "Point"
						+ i, "hii");
				itemizedOverlay123.addOverlay(overlayItem);
				mapView.getOverlays().add(itemizedOverlay123);
				mapView.invalidate();

			}
		} catch (JSONException e1) {
			Toast.makeText(getBaseContext(), "No City Found", Toast.LENGTH_LONG)
					.show();
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		try {
			lv = (ListView) findViewById(R.id.list);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					AndyGPS.this, android.R.layout.simple_list_item_1,
					ct_name);

			lv.setAdapter(adapter);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		// ;

	}

	public void getTaxiId() {

		String Latitude = Double.toString(latitude);
		String Longitude = Double.toString(longitude);

		String result = null;
		InputStream is = null;
		StringBuilder sb = null;
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
				1);
		// double total = 44;
		 String DeviceId=Integer.toString(DEVICE); 
		nameValuePairs.add(new BasicNameValuePair("deviceId",DeviceId));
		// nameValuePairs.add(new BasicNameValuePair("lat",Latitude));
		// nameValuePairs.add(new BasicNameValuePair("lng",Longitude));
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://gotabbie.com/tabbiedev/webservices/getTaxiId.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection" + e.toString());
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line = "0";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
				// line = reader.readLine();
			}
			is.close();
			result = sb.toString();

			Log.i("Result", result);
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}
		Log.i("TAXI ID", "TAXI FOUND 0");
		JSONArray jArray;
		try {
			Log.i("TAXI ID", "TRY");
			jArray = new JSONArray(result);
			Log.i("TAXI ID", "TAXI FOUND 1");
			JSONObject json_data;
			// lat11=new Double[jArray.length()];
			// lng11=new Double[jArray.length()];
			Log.i("TAXI ID", "TAXI FOUND 2");
			ct_name = new String[jArray.length()];
			int i = 0;
			Log.i("TAXI ID", "TAXI FOUND 3");
			for (i = 0; i < jArray.length(); i++) {

				json_data = jArray.getJSONObject(i);
				ct_id = json_data.getInt("taxiId");
				// lat11[i]=json_data.getDouble("lat");

				// lng11[i]=json_data.getDouble("lng");
				// ct_name[i]=json_data.getString("name");

				Log.i("TAXI ID", "TAXI FOUND 4");
			}
		} catch (JSONException e1) {
			Toast.makeText(getBaseContext(), "No taxi found", Toast.LENGTH_LONG)
					.show();
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		// txtdate.setText("id is"+ct_id);

		taxiId = ct_id;

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// final WebView engine = (WebView)findViewById(R.id.web_engine);
		// Calling .clearView does not stop the flash player must load new data
		// webview.loadData("", "text/html", "utf-8");

		// engine = (WebView) findViewById(R.id.web_engine);
		youtube_loader.stopLoading();
		youtube_loader.destroy();
		// engine.stopLoading();
		// engine.destroy();
	}

	
	

	private class MyWebClient extends WebViewClient {

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// http_load.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
			//url = "http://gotabbie.com/media2/300x250_2/index.php";
			//view.loadUrl(url);

			return true;
		}

	}

	private class MyWebClient1 extends WebViewClient {

		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			//url = "http://gotabbie.com/mediafx/728x90_1/index.html";
			//view.loadUrl(url);

			return true;
		}

	}

	private class MyWebClient2 extends WebViewClient {

		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			//url = "http://gotabbie.com/media3/index.html";
			//view.loadUrl(url);

			return true;
		}

	}

	Runnable r1 = new Runnable() {
		@Override
		public void run() {
			if (!(minutes == 0))

				// txtdate.setText("hii"+ minutes--);
				// getMarkers();
				getTaxiId();
			getTripDetails();
			findCurrentLocation();
			// String s=getCurrentTimeString();

			// new SubmitCommentTask().execute();
			handler.postDelayed(this, 1000); // run the runnable in a 10sec
												// again
			if (minutes == 41) {
				minutes = 45;
			}
			/*
			 * if(viiraTime.equals(s)){
			 * 
			 * Intent i=new Intent(AndyActivity.this,Police.class);
			 * startActivity(i);
			 * 
			 * }
			 */
		}
	};

	public String getCurrentTimeString() {

		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		// int second=calendar.get(Calendar.SECOND);
		return String.format("%02d:%02d", hour, minute);

	}

	Police police = new Police();

	private String readCall() {

		InputStream inputStream = getResources().openRawResource(R.raw.call);
		// InputStream inputStream =
		// getResources().openRawResource(R.raw.internals);
		System.out.println(inputStream);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try {
			i = inputStream.read();
			while (i != -1) {
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return byteArrayOutputStream.toString();
	}

	private String readReferal() {

		InputStream inputStream = getResources().openRawResource(R.raw.referal);
		// InputStream inputStream =
		// getResources().openRawResource(R.raw.internals);
		System.out.println(inputStream);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try {
			i = inputStream.read();
			while (i != -1) {
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return byteArrayOutputStream.toString();
	}

	private String readAdvertise() {

		InputStream inputStream = getResources().openRawResource(
				R.raw.advertise);
		// InputStream inputStream =
		// getResources().openRawResource(R.raw.internals);
		System.out.println(inputStream);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try {
			i = inputStream.read();
			while (i != -1) {
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return byteArrayOutputStream.toString();
	}

	private String readPrivacy() {

		InputStream inputStream = getResources().openRawResource(R.raw.privacy);
		// InputStream inputStream =
		// getResources().openRawResource(R.raw.internals);
		System.out.println(inputStream);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try {
			i = inputStream.read();
			while (i != -1) {
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return byteArrayOutputStream.toString();
	}

	private String readAbout() {

		InputStream inputStream = getResources().openRawResource(R.raw.aboutus);
		// InputStream inputStream =
		// getResources().openRawResource(R.raw.internals);
		System.out.println(inputStream);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try {
			i = inputStream.read();
			while (i != -1) {
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return byteArrayOutputStream.toString();
	}

	private String readTxt() {

		InputStream inputStream = getResources().openRawResource(R.raw.test);
		// InputStream inputStream =
		// getResources().openRawResource(R.raw.internals);
		System.out.println(inputStream);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try {
			i = inputStream.read();
			while (i != -1) {
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return byteArrayOutputStream.toString();
	}
	private void findCurrentLocation() {
        myLocation.getLocation(this, locationResult);
    }
	
	public LocationResult locationResult = new LocationResult() {

        @Override
        public void gotLocation(Location location) {
            // TODO Auto-generated method stub
            if (location != null) {
                String strloc  = location.getLatitude() + ","
                        + location.getLongitude();
                
              pw = new GeoPoint((int) (location.getLatitude() * 1E6),
    					(int) (location.getLongitude() * 1E6));
              MapController mc = mapView.getController();
              mc.setZoom(18);
              mc.animateTo(pw);
    			
    			
                Toast.makeText(AndyGPS.this,"hi"+strloc, Toast.LENGTH_SHORT);
                List<Overlay> overlays = mapView.getOverlays();
                overlays.clear();
                overlays.add(new MyOverlay());

                mapView.invalidate();
                
                ArrayList<NameValuePair> nameValuePairs1 = new
  					  ArrayList<NameValuePair>(3); // double total = 44;
  					  String Latitude1 = Double.toString(latitude); 
  					  String Longitude1=Double.toString(longitude); 
  					  nameValuePairs1.add(new BasicNameValuePair("Device_Id",deviceId));
  					  nameValuePairs1.add(new BasicNameValuePair("Latitude",Latitude1));
  					  nameValuePairs1.add(new BasicNameValuePair("Longitude",Longitude1));
  					  try{
  					  HttpClient httpclient = new DefaultHttpClient(); 
  					  HttpPost httppost = new HttpPost("http://gotabbie.com/tabbieOld/insert.php");
  					  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1)); 
  					  HttpResponse response = httpclient.execute(httppost);
  					  HttpEntity entity = response.getEntity(); 
  					  InputStream is3 = entity.getContent();
  					  Log.i("postData",response.getStatusLine().toString()); //
  					  Toast.makeText(AndyGPS.this,"send", Toast.LENGTH_LONG); }
  					  
  					  catch(Exception e) { Log.e("log_tag", "Error in http connection "+e.toString()); 
  					  }
                
            }
        }
    };
    
    private class MyOverlay extends com.google.android.maps.Overlay {

        @Override
        public void draw(Canvas canvas, MapView mapView, boolean shadow) {                              // 1
            super.draw(canvas, mapView, shadow);

            if (!shadow) {                                                                              // 2

                Point point = new Point();
                mapView.getProjection().toPixels(pw, point);                                      // 3

                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.colors);   // 4
            
                int x = point.x - bmp.getWidth() / 2;                                                   // 5
            
                int y = point.y - bmp.getHeight();                                                      // 6
            
                canvas.drawBitmap(bmp, x, y, null);                                                     // 7
            }

        }

    }
	@Override
	public void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.main);
		// findCurrentLocation();

		 deviceId=Integer.toString(DEVICE); 
		//final Context context=getApplicationContext();
		//ActivityManager activemanager=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		//activemanager.killBackgroundProcesses(com.android.)
		mAm = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		mIsMute = false;
		vf = (ViewFlipper) findViewById(R.id.details);
		vfmaps = (ViewFlipper) findViewById(R.id.list_maps);
		vf_settings = (ViewFlipper) findViewById(R.id.set_along);
		terms = (ImageView) findViewById(R.id.imageView_Terms);

		clock = (DigitalClock) findViewById(R.id.digitalClock1);
		txtTerms = (TextView) findViewById(R.id.txtterms);
		txtTerms.setText(readTxt());
		a = clock.getText().toString();
		txtAdvertise = (TextView) findViewById(R.id.txtadvertise);
		txtAdvertise.setText(readAdvertise());

		txtPrivacy = (TextView) findViewById(R.id.txtprivacy);
		txtPrivacy.setText(readPrivacy());

		txtAbout = (TextView) findViewById(R.id.txtabout);
		txtAbout.setText(readAbout());

		txt_referal = (TextView) findViewById(R.id.txtref);
		txt_referal.setText(readReferal());

		contact = (TextView) findViewById(R.id.txtcon);
		contact.setText(readCall());
		// txtfireferal=(TextView)findViewById(R.id.)
		vfads = (ViewFlipper) findViewById(R.id.list_ads);
		vfadsup = (ViewFlipper) findViewById(R.id.list_adsup);
		vfterms = (ViewFlipper) findViewById(R.id.list_terms);
		parent = (LinearLayout) findViewById(R.id.mainlayout23);
		l2 = (LinearLayout) findViewById(R.id.layout_top);
		l3 = (LinearLayout) findViewById(R.id.firstscreen);
		l4 = (LinearLayout) findViewById(R.id.mainlayout_side);
		// l5=(LinearLayout)findViewById(R.id.mainlayout_BOTTOM1);
		l6 = (LinearLayout) findViewById(R.id.mainlayout_BOTTOM);
		l2.setEnabled(false);
		l4.setVisibility(View.INVISIBLE);
parent.setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
		// l5.setVisibility(View.INVISIBLE);
		// l6.setVisibility(View.INVISIBLE);
		for (int i = 0; i < l2.getChildCount(); i++) {
			View view = l2.getChildAt(i);
			view.setEnabled(false);

		}

		referal = (ImageView) findViewById(R.id.referal);
		about = (ImageView) findViewById(R.id.about);
		advertise = (ImageView) findViewById(R.id.advertise);
		privacy = (ImageView) findViewById(R.id.privacy);
		conditions = (ImageView) findViewById(R.id.conditions);
		btnLogout = (ImageView) findViewById(R.id.logout);

		btnLogout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
LOGOUT++;
				typeBar = 2;
				
				
						  String DeviceId=Integer.toString(DEVICE); 
						  String maps=Integer.toString(MAP); 
						  String enter=Integer.toString(ENTERTAINMENT); 
						  String news=Integer.toString(NEWS); 
						  String sports=Integer.toString(SPORTS);
						  String settings=Integer.toString(SETTINGS);
						  
						  String funasia=Integer.toString(FUNASIA); 
						  String good=Integer.toString(GOODTIMES); 
						  String tube=Integer.toString(YOUTUBE); 
						  
						  String bheem=Integer.toString(BHEEM); 
						  String doremon=Integer.toString(DOREMON); 
						  String popeye=Integer.toString(POPEYE); 
						  String simpsons=Integer.toString(SIMPSONS); 
						  String tom=Integer.toString(TOM); 
						  
						  String chaplin=Integer.toString(CHAPLIN); 
						  String language=Integer.toString(MIND); 
						  String office=Integer.toString(OFFICE); 
						  String nukkand=Integer.toString(NUKKAD); 
						  String two=Integer.toString(TWO); 
						  
						  String dance=Integer.toString(DANCE); 
						  String kuch=Integer.toString(KUCHBHI); 
						  String idol=Integer.toString(IDOL); 
						  String roadies=Integer.toString(ROADIES); 
						  String talent=Integer.toString(TALENT); 
						  
						  String chhal=Integer.toString(CHHAL); 
						  String kairi=Integer.toString(KAARI); 
						  String natum=Integer.toString(NATUM); 
						  String parichay=Integer.toString(PARICHAY); 
						  String uttaran=Integer.toString(UTTARAN); 
						  
						  String sahara=Integer.toString(SAHARA); 
						  String india=Integer.toString(INDIATV);
						  String newx=Integer.toString(NEWX); 
						  String profit=Integer.toString(PROFIT);
						  String hindu=Integer.toString(HINDU); 
						  String english=Integer.toString(ENGLISH);
						  
						  String cricket=Integer.toString(CRICKET); 
						  String foot=Integer.toString(FOOTBALL);
						  String ipl=Integer.toString(IPL); 
						  String tennis=Integer.toString(TENNIS);
						  
						  String ref=Integer.toString(REFERAL); 
						  String about=Integer.toString(ABOUTUS);
						  String advertise=Integer.toString(ADVERTISE); 
						  String privacy=Integer.toString(PRIVACY);
						  String terms=Integer.toString(TERMS);
						  String logout=Integer.toString(LOGOUT);
						  String clickhere=Integer.toString(CLICKHERE);
						  
						  
						  String bannermed=Integer.toString(BANNERMEDIUM); 
						  String bannerlarge=Integer.toString(BANNERLARGE);
						  String bannersmall=Integer.toString(BANNERSMALL); 
						
						  
						  
						  //String Longitude1=Double.toString(longitude);
						  ArrayList<NameValuePair> nameValuePairs1 = new ArrayList<NameValuePair>(52); // double total = 44;
						  
						  nameValuePairs1.add(new BasicNameValuePair("taxiId",taxi));
						  nameValuePairs1.add(new BasicNameValuePair("deviceId",DeviceId));
						  nameValuePairs1.add(new BasicNameValuePair("time",a3));
						  nameValuePairs1.add(new BasicNameValuePair("date",formattedDate));
						  
						  nameValuePairs1.add(new BasicNameValuePair("map",maps));
						  nameValuePairs1.add(new BasicNameValuePair("entertainment",enter));
						  nameValuePairs1.add(new BasicNameValuePair("news",news));
						  nameValuePairs1.add(new BasicNameValuePair("sports",sports));
						  nameValuePairs1.add(new BasicNameValuePair("settings",settings));
						  
						  nameValuePairs1.add(new BasicNameValuePair("funasia",funasia));
						  nameValuePairs1.add(new BasicNameValuePair("goodtimes",good));
						  nameValuePairs1.add(new BasicNameValuePair("youtube",tube));
						  
						   nameValuePairs1.add(new BasicNameValuePair("sahara",sahara));
						  nameValuePairs1.add(new BasicNameValuePair("indiatv",india));
						  nameValuePairs1.add(new BasicNameValuePair("newx",newx));
						  nameValuePairs1.add(new BasicNameValuePair("profit",profit));
						  nameValuePairs1.add(new BasicNameValuePair("hindu",hindu));
						  nameValuePairs1.add(new BasicNameValuePair("english",english));
						  
						   nameValuePairs1.add(new BasicNameValuePair("cricket",cricket));
						  nameValuePairs1.add(new BasicNameValuePair("football",foot));
						  nameValuePairs1.add(new BasicNameValuePair("ipl",ipl));
						  nameValuePairs1.add(new BasicNameValuePair("tennis",tennis));
						  
						    nameValuePairs1.add(new BasicNameValuePair("bannerMedium",bannermed));
						  nameValuePairs1.add(new BasicNameValuePair("bannerSmall",bannersmall));
						  nameValuePairs1.add(new BasicNameValuePair("bannerLarge",bannerlarge));
						  
						    
						  nameValuePairs1.add(new BasicNameValuePair("referal",ref));
						  nameValuePairs1.add(new BasicNameValuePair("aboutUs",about));
						  nameValuePairs1.add(new BasicNameValuePair("advertise",advertise));
						  nameValuePairs1.add(new BasicNameValuePair("privacy",privacy));
						  
						  nameValuePairs1.add(new BasicNameValuePair("disclosure",terms));
						  
						  nameValuePairs1.add(new BasicNameValuePair("logout",logout));
						  nameValuePairs1.add(new BasicNameValuePair("clickHere",clickhere));
						 
						 nameValuePairs1.add(new BasicNameValuePair("map",maps));
						  nameValuePairs1.add(new BasicNameValuePair("entertainment",enter));
						  nameValuePairs1.add(new BasicNameValuePair("news",news));
						  nameValuePairs1.add(new BasicNameValuePair("sports",sports));
						  nameValuePairs1.add(new BasicNameValuePair("settings",settings));
						  
						  nameValuePairs1.add(new BasicNameValuePair("funasia",funasia));
						  nameValuePairs1.add(new BasicNameValuePair("goodtimes",good));
						  nameValuePairs1.add(new BasicNameValuePair("youtube",tube));
						  
						   nameValuePairs1.add(new BasicNameValuePair("sahara",sahara));
						  nameValuePairs1.add(new BasicNameValuePair("indiatv",india));
						  nameValuePairs1.add(new BasicNameValuePair("newx",newx));
						  nameValuePairs1.add(new BasicNameValuePair("profit",profit));
						  nameValuePairs1.add(new BasicNameValuePair("hindu",hindu));
						  nameValuePairs1.add(new BasicNameValuePair("english",english));
						  
						  
						  
						  nameValuePairs1.add(new BasicNameValuePair("bheem",bheem));
						  nameValuePairs1.add(new BasicNameValuePair("doremon",doremon));
						  nameValuePairs1.add(new BasicNameValuePair("popaye",popeye));
						  nameValuePairs1.add(new BasicNameValuePair("simpsons",simpsons));
						  nameValuePairs1.add(new BasicNameValuePair("tom",tom));
						  
						  nameValuePairs1.add(new BasicNameValuePair("chaplin",chaplin));
						  nameValuePairs1.add(new BasicNameValuePair("mind",language));
						  nameValuePairs1.add(new BasicNameValuePair("office",office));
						  nameValuePairs1.add(new BasicNameValuePair("nukkad",nukkand));
						  nameValuePairs1.add(new BasicNameValuePair("two",two));
						  
						  nameValuePairs1.add(new BasicNameValuePair("dance",dance));
						  nameValuePairs1.add(new BasicNameValuePair("kuch",kuch));
						  nameValuePairs1.add(new BasicNameValuePair("idol",idol));
						  nameValuePairs1.add(new BasicNameValuePair("roadies",roadies));
						  nameValuePairs1.add(new BasicNameValuePair("talent",talent));
						  
						  nameValuePairs1.add(new BasicNameValuePair("chhal",chhal));
						  nameValuePairs1.add(new BasicNameValuePair("kairi",kairi));
						  nameValuePairs1.add(new BasicNameValuePair("natum",natum));
						  nameValuePairs1.add(new BasicNameValuePair("parichay",parichay));
						  nameValuePairs1.add(new BasicNameValuePair("uttaran",uttaran));
						  
						
						  
						  
						   nameValuePairs1.add(new BasicNameValuePair("cricket",cricket));
						  nameValuePairs1.add(new BasicNameValuePair("football",foot));
						  nameValuePairs1.add(new BasicNameValuePair("ipl",ipl));
						  nameValuePairs1.add(new BasicNameValuePair("tennis",tennis));
						  
						    nameValuePairs1.add(new BasicNameValuePair("bannerMedium",bannermed));
						  nameValuePairs1.add(new BasicNameValuePair("bannerSmall",bannersmall));
						  nameValuePairs1.add(new BasicNameValuePair("bannerLarge",bannerlarge));
						  
						    
						  nameValuePairs1.add(new BasicNameValuePair("referal",ref));
						  nameValuePairs1.add(new BasicNameValuePair("aboutUs",about));
						  nameValuePairs1.add(new BasicNameValuePair("advertise",advertise));
						  nameValuePairs1.add(new BasicNameValuePair("privacy",privacy));
						  
						  nameValuePairs1.add(new BasicNameValuePair("disclosure",terms));
						  
						  nameValuePairs1.add(new BasicNameValuePair("back",logout));
						  nameValuePairs1.add(new BasicNameValuePair("clickHere",clickhere));
			
						
						  
						  try
						  {
						  HttpClient httpclient = new DefaultHttpClient(); 
						  HttpPost httppost = new HttpPost("http://gotabbie.com/tabbiedev/webservices/logEndTripDetails.php");
						  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1)); 
						  HttpResponse response = httpclient.execute(httppost);
						  HttpEntity entity = response.getEntity(); 
						  InputStream is3 = entity.getContent();
						  Log.i("postData",response.getStatusLine().toString()); //
						  Toast.makeText(AndyGPS.this,"send", Toast.LENGTH_LONG); }
						  
						  catch(Exception e) { Log.e("log_tag", "Error in http connection "+e.toString()); 
						  }
						
							
				// play.setVisibility(View.VISIBLE);
				showDialog(typeBar);
				Intent home = new Intent(AndyGPS.this,
						Police.class);
				startActivity(home);
				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// Intent intent = new Intent(AudioRecordTest.this,
						// MainActivity.class);
						Intent home = new Intent(AndyGPS.this,
								Police.class);
						startActivity(home);
						ndtv.pause();
						youtube_loader.loadUrl("about:blank");
						http_bottom.loadUrl("about:blank");
						http_load.loadUrl("about:blank");
						http_side.loadUrl("about:blank");
						http_load.clearCache(true);

						// Police sd=new Police();
						// sd.a=false;
						progDialog.dismiss();
						
						// startActivity(intent);

					}
				};
				//t = new Timer();
				//t.schedule(task, 5000);

			}
			
		});

		new SubmitCommentTask().execute();

		terms.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TERMS++;
				l2 = (LinearLayout) findViewById(R.id.layout_top);
				// l3=(LinearLayout)findViewById(R.id.firstscreen);
				// l4=(LinearLayout)findViewById(R.id.mainlayout_side);
				// l5=(LinearLayout)findViewById(R.id.mainlayout_BOTTOM1);
				// l6=(LinearLayout)findViewById(R.id.mainlayout_BOTTOM);

				// l2.setVisibility(View.VISIBLE);
				// l4.setVisibility(View.VISIBLE);
				// l5.setVisibility(View.VISIBLE);
				// l6.setVisibility(View.VISIBLE);
				// back1.setVisibility(View.VISIBLE);
				// l2.setEnabled(true);
				// mute.setVisibility(View.VISIBLE);
				// front.setVisibility(View.VISIBLE);
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(14);

				vf_settings.setDisplayedChild(3);

			}
		});

		referal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				REFERAL++;
				vf_settings.setInAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipnext));
				vf_settings.setOutAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipoutnext));
				vf_settings.setDisplayedChild(0);

			}
		});
		about.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ABOUTUS++;
				// TODO Auto-generated method stub
				vf_settings.setInAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipnext));
				vf_settings.setOutAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipoutnext));
				vf_settings.setDisplayedChild(1);

			}
		});
		advertise.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ADVERTISE++;
				// TODO Auto-generated method stub
				vf_settings.setInAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipnext));
				vf_settings.setOutAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipoutnext));
				vf_settings.setDisplayedChild(2);

			}
		});
		privacy.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				PRIVACY++;
				// TODO Auto-generated method stub
				vf_settings.setInAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipnext));
				vf_settings.setOutAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipoutnext));
				vf_settings.setDisplayedChild(3);

			}
		});
		conditions.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DISCLOSURE++;
				// TODO Auto-generated method stub
				vf_settings.setInAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipnext));
				vf_settings.setOutAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipoutnext));
				vf_settings.setDisplayedChild(4);

			}
		});

		http_load = (WebView) findViewById(R.id.imageView_http);
		http_bottom = (WebView) findViewById(R.id.imageView_http_bottom);
		http_side = (WebView) findViewById(R.id.imageView_http_bottom_side);

		ndtv = (VideoView) findViewById(R.id.Video_ndtv);
		myVideoView = (VideoView) findViewById(R.id.videoView1);
		// myVideoView.setOnCompletionListener(this);
		ndtv.setOnCompletionListener(this);
		ndtv.setOnPreparedListener(this);
		Context context;
		// View v = (View)findViewById(R.id.mainlayout23);
		// v.setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
		// VDO.initialize("AX123", this);
		// dialog=ProgressDialog.show(this,"","Loding.PleaseWait",true);

		// setContentView(R.layout.media_player);
		ndtv = (VideoView) findViewById(R.id.Video_ndtv);
		/*
		 * ndtv.setVideoURI(Uri.parse(httpLiveUrl)); ndtv.setMediaController(new
		 * MediaController(this));
		 * 
		 * ndtv.requestFocus(); ndtv.start();
		 */

		play = (Button) findViewById(R.id.btn_play);
		mute = (Button) findViewById(R.id.btn_mute);
		//back1 = (Button) findViewById(R.id.imageView_back);
		// front=(Button)findViewById(R.id.imageView_next);

		play.setVisibility(View.INVISIBLE);
		mute.setVisibility(View.INVISIBLE);
		//back1.setVisibility(View.INVISIBLE);
		// front.setVisibility(View.INVISIBLE);
		txtdate = (TextView) findViewById(R.id.txtdate);
		txtDriver = (TextView) findViewById(R.id.txtdriver);
		handler = new Handler();

		// schedule alarm here and post runnable as soon as scheduled
		handler.post(r1);

		// View v = null;
		// v.setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
		/*
		 * front.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipnext));
		 * vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipoutnext)); youtube_loader.loadUrl("about:blank");
		 * ndtv.stopPlayback();
		 * 
		 * m++; if(m==1){ vf.setDisplayedChild(2);
		 * 
		 * } if(m==2){ vf.setDisplayedChild(8);
		 * 
		 * } if(m==3){ vf.setDisplayedChild(10);
		 * 
		 * } if(m==4){ vf.setDisplayedChild(9);
		 * 
		 * } if(m==5){ vf.setDisplayedChild(12); } if(m==6){
		 * vf.setDisplayedChild(14); m=0; }
		 * 
		 * 
		 * } });
		 */
		/*
		 * tabbie=(ImageView)findViewById(R.id.imageView_channels);
		 * tabbie.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { // TODO Auto-generated
		 * method stub Intent startMain = new Intent(Intent.ACTION_MAIN);
		 * startMain.addCategory(Intent.CATEGORY_LAUNCHER);
		 * startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 * startActivity(startMain); finish(); Intent setIntent = new
		 * Intent(Intent.ACTION_MAIN);
		 * setIntent.addCategory(Intent.CATEGORY_HOME);
		 * setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 * startActivity(setIntent); finish();
		 * youtube_loader.loadUrl("about:blank"); ndtv.pause(); } });
		 */
		mute.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (mIsMute) {
					mAm.setStreamMute(AudioManager.STREAM_MUSIC, false);
					mIsMute = false;
					int currentProgress = 100;
					volControl.setProgress(currentProgress);
					mute.setText("MUTE");

				} else {
					mAm.setStreamMute(AudioManager.STREAM_MUSIC, true);
					mIsMute = true;
					int currentProgress = 0;
					volControl.setProgress(currentProgress);
					mute.setText("UNMUTE");
				}

			}
		});

		/*back1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.showPrevious();
				youtube_loader.loadUrl("about:blank");
				ndtv.stopPlayback();

				
				 * n++; if(n==1){ vf.setDisplayedChild(14);
				 * 
				 * } if(n==2){ vf.setDisplayedChild(12);
				 * 
				 * } if(n==3){ vf.setDisplayedChild(9);
				 * 
				 * } if(n==4){ vf.setDisplayedChild(10);
				 * 
				 * } if(n==5){ vf.setDisplayedChild(8); } if(n==6){
				 * vf.setDisplayedChild(2); n=0; }
				 

				// finish();

			}
		});*/

		// preAppObject =
		// VDO.requestPreApp(AndyActivity.this.getApplicationContext());
		// preAppObject.loadAndDisplayAd(null,3.0);
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int maxVolume = audioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		volControl = (SeekBar) findViewById(R.id.volbar);
		txtname1 = (TextView) findViewById(R.id.txtname);
		txtpickup = (TextView) findViewById(R.id.txtsource);
		txtdes = (TextView) findViewById(R.id.txtdestination);
		volControl.setMax(maxVolume);
		volControl.setProgress(curVolume);

		bheem = (ImageView) findViewById(R.id.imageView_bheem);
		charlie = (ImageView) findViewById(R.id.imageView_charlie);
		did = (ImageView) findViewById(R.id.imageView_did);
		bade = (ImageView) findViewById(R.id.imageView_bade);

		doremon = (ImageView) findViewById(R.id.imageView_doremon);
		mind = (ImageView) findViewById(R.id.imageView_mind);
		entertainment = (ImageView) findViewById(R.id.imageView_entertainment);
		ktlkg = (ImageView) findViewById(R.id.imageView_ktlkg);

		popaye = (ImageView) findViewById(R.id.imageView_popeye);
		office = (ImageView) findViewById(R.id.imageView_office);
		idol = (ImageView) findViewById(R.id.imageView_idol);
		parvarish = (ImageView) findViewById(R.id.imageView_parvarish);

		simpsons = (ImageView) findViewById(R.id.imageView_simpsons);
		nukkad = (ImageView) findViewById(R.id.imageView_nukkad);
		roadies = (ImageView) findViewById(R.id.imageView_roadies);
		sas = (ImageView) findViewById(R.id.imageView_sas);

		comedy = (ImageView) findViewById(R.id.imageView_tom);
		two = (ImageView) findViewById(R.id.imageView_two);
		talent = (ImageView) findViewById(R.id.imageView_talent);
		uttaran = (ImageView) findViewById(R.id.imageView_uttaran);

		cricket = (ImageView) findViewById(R.id.imageView_opt_cricket);
		football = (ImageView) findViewById(R.id.imageView_opt2_football);
		ipl = (ImageView) findViewById(R.id.imageView_opt3_ipl);
		tennis = (ImageView) findViewById(R.id.imageView_tennis);

		/*
		 * stop.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub ndtv.pause();
		 * 
		 * youtube_loader.stopLoading();
		 * 
		 * 
		 * } });
		 */

		// ********************************************************YOUTUBE
		// CARTOON******************************************************************
		play.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (ndtv.isPlaying()) {
					ndtv.pause();
					play.setText("Play");
				} else {
					ndtv.start();
					play.setText("Pause");
				}

				// ndtv.pause();

			}
		});

		bheem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BHEEM++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=chota+bheem+new+episodes+in+hindi+2012&oq=chota+bheem+new+&aq=3&aqi=p-p2g8&aql=&gs_l=youtube-psuggest.1.3.35i39l2j0l8.3063l3063l0l5234l1l1l0l0l0l0l276l276l2-1l1l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		doremon.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DOREMON++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=doraemon+official+cartoon+videos&oq=doraemon+official+cartoon+videos&aq=f&aqi=&aql=&gs_l=youtube-psuggest.3...4746l11504l0l11641l21l21l1l0l0l0l219l2223l0j15j2l17l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		popaye.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				POPEYE++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=popaye+official+cartoon+videos&oq=popaye+official+cartoon+videos&aq=f&aqi=&aql=&gs_l=youtube-psuggest.3...45223l46711l0l47394l6l6l0l0l0l0l149l598l0j5l5l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});
		simpsons.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SIMPSONS++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=the+simpsons+full+episode+videos&oq=the+simpsons+full+episode+videos&aq=f&aqi=&aql=&gs_l=youtube-psuggest.3...16817l18640l0l19804l7l7l0l0l0l0l223l1096l0j5j2l7l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		comedy.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TOM++;
				play.setVisibility(View.INVISIBLE);

				// downloadFile(imageUrl+"salman.jpg");
				// Log.i("im url",imageUrl+"png"+i+".png");
				// AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vfmaps,
				// FlipDirection.RIGHT_LEFT);
				vfmaps.setInAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipnext));
				vfmaps.setOutAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipoutnext));
				vfmaps.setDisplayedChild(0);
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(11);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=+tom+and+jerry+episodes&oq=+tom+and+jerry+episodes&aq=f&aqi=g10&aql=&gs_sm=12&gs_upl=12816312l12816312l0l12817911l1l1l0l0l0l0l1136l1136l7-1l1l0");

			}
		});

		// //***********************************************************YOUTUBE
		// COMEDY***********************************************************

		charlie.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CHAPLIN++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=charlie+chaplin+full+videos&oq=CHARLIE+&aq=1p&aqi=p-p2g8&aql=&gs_l=youtube-psuggest.1.1.35i39l2j0l8.140321l142013l0l144350l8l8l0l0l0l0l184l1072l0j7l7l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		mind.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MIND++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=mind+your+language+full+videos&oq=mind+your+language+full+videos&aq=f&aqi=&aql=&gs_l=youtube-psuggest.3...49126l55016l0l55231l18l18l0l0l0l0l229l2431l0j10j5l15l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		office.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				OFFICE++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=office+office+full+episodes&oq=office+&aq=1&aqi=g10&aql=&gs_l=youtube.1.1.0l10.936l2893l0l5203l7l7l0l2l2l0l240l474l2-2l2l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		nukkad.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				NUKKAD++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=nukkad+full+episodes&nfpr=0");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		two.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				TWO++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=two+and+half+men+full+episodes&oq=two+and+half+men+full+episodes&aq=f&aqi=g3&aql=&gs_l=youtube-psuggest.3..0l3.19676l26015l0l26394l16l16l0l0l0l0l148l1848l0j15l15l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		// /
		// *****************************************************************YOUTUBE
		// REALITY****************************************///

		did.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DANCE++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=dance+india+dance+full+videos&oq=dance+india+dance+full+videos&aq=f&aqi=&aql=&gs_l=youtube-psuggest.3...8061l15196l0l16087l12l12l0l4l4l0l172l1043l0j7l7l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		entertainment.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				KUCHBHI++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=entertainment+ke+liye+kuch+bhi+karega+full+videos&oq=entertainment+ke+liye+kuch+bhi+karega+full+videos&aq=f&aqi=p-p1&aql=&gs_l=youtube-psuggest.3..35i39.9874l13359l0l13572l13l13l0l0l0l0l292l1460l0j7j2l9l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		idol.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				IDOL++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=indian+idol+full+videos&oq=indian+idol+full+videos&aq=f&aqi=&aql=&gs_l=youtube-psuggest.3...29905l32931l0l33362l11l11l0l0l0l0l303l1518l0j7j1j1l9l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		roadies.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ROADIES++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=roadies+full+videos&oq=roadies+full+videos&aq=f&aqi=&aql=&gs_l=youtube-psuggest.3...55887l57303l0l57778l7l7l0l0l0l0l263l1005l0j4j2l6l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		talent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				TALENT++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=indias+got+talent+full+videos&oq=indias+got+talent+full+videos&aq=f&aqi=&aql=&gs_l=youtube-psuggest-reduced.3...101765l108204l0l110749l17l17l0l0l0l0l281l2402l0j10j4l14l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		// /********************************************************************YOUTUBE
		// SOAPS******************************>>>>>>>>>>>>>>>>>>>

		bade.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CHHAL++;
				play.setVisibility(View.INVISIBLE);
				// youtube_loader.loadUrl("http://www.youtube.com/user/colorstv/videos?query=Chhal");
				youtube_loader
						.loadUrl("http://www.youtube.com/user/colorstv/videos");

				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		ktlkg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				KAARI++;
				// TODO Auto-generated method stub
				play.setVisibility(View.INVISIBLE);
				// youtube_loader.loadUrl("http://www.youtube.com/user/colorstv/videos?query=Kairi");
				youtube_loader
						.loadUrl("http://www.youtube.com/user/colorstv/videos");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		parvarish.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				NATUM++;
				play.setVisibility(View.INVISIBLE);
				// youtube_loader.loadUrl("http://www.youtube.com/user/colorstv/videos?query=Na+Bole+Tum+Na+Maine+Kuch+Kaha+");
				youtube_loader
						.loadUrl("http://www.youtube.com/user/colorstv/videos");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		sas.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				PARICHAY++;
				play.setVisibility(View.INVISIBLE);
				// youtube_loader.loadUrl("http://www.youtube.com/user/colorstv/videos?query=Parichay");
				youtube_loader
						.loadUrl("http://www.youtube.com/user/colorstv/videos");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		uttaran.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				UTTARAN++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/user/colorstv/videos");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		// /***************************************************************YOUTUBE
		// SPORTS******************************************************///

		cricket.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CRICKET++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=+indian+cricket+videos+2012");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});
		football.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FOOTBALL++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=soccer+highlights+2012&oq=soccer+highlights+2012&aq=f&aqi=g4g-m6&aql=&gs_l=youtube-psuggest.3..0l4j0i5l6.25039l25039l0l25360l1l1l0l0l0l0l136l136l0j1l1l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});
		ipl.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				IPL++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=+IPL+highlights&oq=+IPL+highlights&aq=f&aqi=g9g-m1&aql=&gs_l=youtube-psuggest.3..0l9j0i5.318273l320216l0l320536l26l9l0l0l0l2l229l622l0j3j1l6l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});
		tennis.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				TENNIS++;
				play.setVisibility(View.INVISIBLE);
				youtube_loader
						.loadUrl("http://www.youtube.com/results?search_query=+Tennis+highlights+2012&oq=+Tennis+highlights+2012&aq=f&aqi=g5g-m2&aql=&gs_l=youtube-psuggest.3..0l5j0i5l2.23154l23154l0l23507l1l1l0l0l0l0l196l196l0j1l1l0.");
				// http_side.loadUrl("http://gotabbie.com/media3/index.html");
				vf.setDisplayedChild(11);
			}
		});

		String currentDateTimeString = DateFormat.getDateInstance().format(
				new Date());

		// textView is the TextView view that should display it
		// txtdate.setText(currentDateTimeString);

		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();

		txtdate.setText(currentDateTimeString + "   ");

		//click = (ImageView) findViewById(R.id.imageView_welcome);

		/*
		 * WebSettings webSettings_ad = click.getSettings(); // Fetches the
		 * WebSettings import click.getSettings().setJavaScriptEnabled(true);
		 * //click. getSettings().setJavaScriptCanOpenWindowsAutomatically
		 * (false); click. getSettings().setPluginsEnabled (true);
		 * click.getSettings().setAllowFileAccess(true);
		 * click.getSettings().setSupportZoom(false);
		 * click.getSettings().setAppCacheEnabled(false);
		 * click.getSettings().setAllowContentAccess(true);
		 * click.getSettings().setCacheMode(click.getSettings().LOAD_NO_CACHE);
		 * click.setVerticalScrollBarEnabled(false);
		 * click.setHorizontalScrollBarEnabled(false);
		 * click.getSettings().setJavaScriptEnabled(true);
		 * webSettings_ad.setPluginState(WebSettings.PluginState.ON);
		 * 
		 * // String ad = "www.google.com"; // click.loadUrl(ad);
		 * 
		 * String html =
		 * "<object width=\"550\" height=\"400\"> <param name=\"movie\" value=\"file:///android_asset/banner1.swf\"> <embed src=\"file:///android_asset/banner1.swf\" width=\"550\" height=\"400\"> </embed> </object>"
		 * ; String mimeType = "text/html"; String encoding = "utf-8";
		 * click.loadDataWithBaseURL("null", html, mimeType, encoding, "");
		 * 
		 * // click.loadUrl( "file:///android_asset/banner1.html");
		 * click.setWebViewClient(new MyWebClient());
		 * click.setWebChromeClient(new WebChromeClient() { public void
		 * onProgressChanged(WebView view, int progress) {
		 * activity.setTitle("Loading..."); activity.setProgress(progress *
		 * 100);
		 * 
		 * if(progress == 100) //
		 * http_load.getSettings().setDefaultZoom(ZoomDensity.FAR);
		 * 
		 * http_load.setVerticalScrollBarEnabled(false);
		 * http_load.setHorizontalScrollBarEnabled(false); //
		 * http_load.setInitialScale(120);
		 * 
		 * //http_load.getSettings().setBuiltInZoomControls(true); float scale =
		 * 155 * http_load.getScale(); http_load.setInitialScale( (int) scale );
		 * 
		 * 
		 * //activity.setTitle(R.string.app_name); } });
		 * 
		 * click.setWebViewClient(new WebViewClient() {
		 * 
		 * @Override public void onReceivedError(WebView view, int errorCode,
		 * String description, String failingUrl) { // Handle the error }
		 * 
		 * @Override public boolean shouldOverrideUrlLoading(WebView view,
		 * String url) { // http_load.setInitialScale(120);
		 * 
		 * 
		 * //http_load.getSettings().setLoadWithOverviewMode(true);
		 * //http_load.getSettings().setUseWideViewPort(true);
		 * 
		 * //http_load.getSettings().setBuiltInZoomControls(true); // float
		 * scale = 180 * http_load.getScale(); // http_load.setInitialScale(
		 * (int) scale );
		 * 
		 * // http_load.getSettings().setUseWideViewPort(true);
		 * click.setVerticalScrollBarEnabled(false);
		 * click.setHorizontalScrollBarEnabled(false); //
		 * http_load.setInitialScale(120);
		 * 
		 * 
		 * 
		 * view.loadUrl(url); return true; }
		 * 
		 * }); click.setOnTouchListener(new View.OnTouchListener() {
		 * 
		 * @Override public boolean onTouch(View arg0, MotionEvent arg1) { //
		 * TODO Auto-generated method stub
		 * 
		 * return true; } }); click.setOnClickListener(new
		 * View.OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { // TODO Auto-generated
		 * method stub
		 * 
		 * 
		 * 
		 * } });
		 */
		/*click.setOnTouchListener(new View.OnTouchListener() {

			
			@Override
			public boolean onTouch(View v, MotionEvent arg1) {
				// TODO Auto-generated method stub
STARTCLICK++;
				ndtv = (VideoView) findViewById(R.id.Video_ndtv);
				Uri path = Uri.parse("android.resource://" + getPackageName()
						+ "/" + R.raw.newkairee);
				ndtv.setMediaController(null);

				
				ndtv.setVideoURI(path);
				ndtv.start();
				l6.setVisibility(View.INVISIBLE);
				vf.setDisplayedChild(7);
				vfads.setDisplayedChild(1);
				vfadsup.setDisplayedChild(1);
				vfmaps.setDisplayedChild(1);
				//vfterms.setDisplayedChild(1);
				mapView.getController().setZoom(17);
				mapView.setBuiltInZoomControls(false);

				MAP = 0;
				ENTERTAINMENT = 0;
				NEWS = 0;
				SPORTS = 0;
				SETTINGS = 0;

				FUNASIA = 0;
				GOODTIMES = 0;
				YOUTUBE = 0;

				SAHARA = 0;
				INDIATV = 0;
				NEWX = 0;
				PROFIT = 0;
				HINDU = 0;
				ENGLISH = 0;

				CRICKET = 0;
				TENNIS = 0;
				IPL = 0;
				FOOTBALL = 0;

				BANNERMEDIUM = 0;
				BANNERSMALL = 0;
				BANNERLARGE = 0;

				TERMS = 0;
				LOGOUT = 0;
				REFERAL = 0;
				PRIVACY = 0;

				DISCLOSURE = 0;
				ADVERTISE = 0;
				ABOUTUS = 0;
                
				STARTCLICK=0;
				CLICKHERE = 0;

				BHEEM = 0;
				DOREMON = 0;
				POPEYE = 0;
				SIMPSONS = 0;
				TOM = 0;

				CHAPLIN = 0;
				MIND = 0;
				OFFICE = 0;
				NUKKAD = 0;
				TWO = 0;

				DANCE = 0;
				KUCHBHI = 0;
				IDOL = 0;
				ROADIES = 0;
				TALENT = 0;

				CHHAL = 0;
				KAARI = 0;
				NATUM = 0;
				PARICHAY = 0;
				UTTARAN = 0;

				return true;
			}
		});
*/
		/*
		 * click.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View arg0) { // TODO Auto-generated
		 * method stub // String SrcPath = "/sdcard/Videos/kair.flv";
		 *//** Called when the activity is first created. */
		/*
		 * 
		 * // setContentView(R.layout.main); ndtv =
		 * (VideoView)findViewById(R.id.Video_ndtv); ndtv.setVideoPath(SrcPath);
		 * ndtv.setMediaController(null); ndtv.requestFocus(); ndtv.start();
		 * ndtv = (VideoView)findViewById(R.id.Video_ndtv); Uri path =
		 * Uri.parse("android.resource://"+getPackageName()+"/"+
		 * R.raw.newkairee); ndtv.setMediaController(null);
		 * 
		 * ndtv.setVideoURI(path); ndtv.start();
		 * l6.setVisibility(View.INVISIBLE); vf.setDisplayedChild(7);
		 * vfads.setDisplayedChild(1); vfadsup.setDisplayedChild(1);
		 * vfmaps.setDisplayedChild(1); vfterms.setDisplayedChild(1);
		 * mapView.getController().setZoom(17);
		 * mapView.setBuiltInZoomControls(false); // VDO.initialize("AX123",
		 * AndyActivity.this); // inAppObject =
		 * VDO.requestInApp(AndyActivity.this.getApplicationContext()); //
		 * inAppObject =
		 * VDO.requestPreApp(AndyActivity.this.getApplicationContext());
		 * //inAppObject.loadAndDisplayAd(null,3.0);
		 * 
		 * 
		 * String bannerSize = new String(MINI_VDO_BANNER); //int location22 =
		 * 0; // Placing the banner that expands from top to bottom banObject =
		 * new VDOBannerView(AndyActivity.this.getApplicationContext(),
		 * bannerSize, location22); if (banObject == null) {
		 * Toast.makeText(AndyActivity.this,
		 * "No banner of the requested size found", Toast.LENGTH_SHORT); //
		 * System.out.println("No banner of the requested size found"); return;
		 * } Handler refresh = new Handler(); refresh.postDelayed(new Runnable()
		 * { public void run() {RelativeLayout.LayoutParams p8 =
		 * (RelativeLayout.LayoutParams)banObject.getLayoutParams();
		 * 
		 * RelativeLayout myLayout; myLayout =
		 * (RelativeLayout)findViewById(R.id.ads);
		 * myLayout.addView(banObject,p8); } }, 3000);
		 * 
		 * 
		 * String fileName=
		 * "http://demo.digi-corp.com/S2LWebservice/Resources/SampleVideo.mp4";
		 * //String fileName = "android.resource://" + getPackageName() + "/" +
		 * R.raw.idea; viduoyo=(VideoView)findViewById(R.id.Video_ndtv);
		 * viduoyo.setVideoURI(Uri.parse(fileName));
		 * viduoyo.setMediaController(new MediaController(AndyActivity.this));
		 * viduoyo.requestFocus(); viduoyo.start();
		 * 
		 * } });
		 */
		// VDO.initialize("7829cef30e99567ad494a10fd379f94e", this);
		// Storing the activity context

		volControl
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					@Override
					public void onStopTrackingTouch(SeekBar arg0) {
					}

					@Override
					public void onStartTrackingTouch(SeekBar arg0) {
					}

					@Override
					public void onProgressChanged(SeekBar arg0, int arg1,
							boolean arg2) {
						audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
								arg1, 0);
					}
				});

		// l5=(LinearLayout)findViewById(R.id.mainlayout_engine5);

		// txtname=(TextView)findViewById(R.id.txtwelcome);

		/*
		 * StrictMode.ThreadPolicy policy = new StrictMode.
		 * ThreadPolicy.Builder().permitAll().build();
		 * StrictMode.setThreadPolicy(policy);
		 * 
		 * ArrayList<NameValuePair> nameValuePairs = new
		 * ArrayList<NameValuePair>(3); // double total = 44; String Latitude =
		 * Double.toString(lat); String Longitude= Double.toString(lng);
		 * nameValuePairs.add(new BasicNameValuePair("Device_Id","151"));
		 * nameValuePairs.add(new BasicNameValuePair("Latitude","555.22"));
		 * nameValuePairs.add(new BasicNameValuePair("Longitude","9989.45"));
		 * try{ HttpClient httpclient = new DefaultHttpClient(); HttpPost
		 * httppost = new HttpPost("http://gotabbie.com/tabbie/insert.php");
		 * httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		 * HttpResponse response = httpclient.execute(httppost); HttpEntity
		 * entity = response.getEntity(); InputStream is = entity.getContent();
		 * Log.i("postData", response.getStatusLine().toString()); //
		 * Toast.makeText(this,"send", Toast.LENGTH_LONG); }
		 * 
		 * catch(Exception e) { Log.e("log_tag",
		 * "Error in http connection "+e.toString()); }
		 */

		// setContentView(R.layout.media_player);

		// viduoyo=(VideoView)findViewById(R.id.videoView1);
		// ndtv.setKeepScreenOn(true);
		/*
		 * String fileName=
		 * "http://cdn.m.yupptv.tv/liveorigin/smil:saamtv_iphone.smil/playlist.m3u8"
		 * ; //String fileName = "android.resource://" + getPackageName() + "/"
		 * + R.raw.idea; viduoyo=(VideoView)findViewById(R.id.Video_ndtv);
		 * viduoyo.setVideoURI(Uri.parse(fileName));
		 * viduoyo.setMediaController(new MediaController(this));
		 * viduoyo.requestFocus(); viduoyo.start();
		 */

		// String httpLiveUrl =
		// "http://cdn.m.yupptv.tv/liveorigin/smil:ndtvprofit.smil/playlist.m3u8";
		// setContentView(R.layout.media_player);

		channel_ndtv = (ImageView) findViewById(R.id.imageView_profit);
		youtube = (ImageView) findViewById(R.id.imageView_op8);
		// saam=(ImageView)findViewById(R.id.imageView_opt_saam);
		sahara = (ImageView) findViewById(R.id.imageView_opt_sahara);
		goodtimes = (ImageView) findViewById(R.id.imageView_opt3_good);
		fun = (ImageView) findViewById(R.id.imageView_opt2_fun);
		india = (ImageView) findViewById(R.id.imageView_india);
		newx = (ImageView) findViewById(R.id.imageView_news);
		hindi = (ImageView) findViewById(R.id.imageView_hindi);
		english = (ImageView) findViewById(R.id.imageView_english);

		// dominoz=(ImageView)findViewById(R.id.imageView1);
		// idea=(ImageView)findViewById(R.id.imageView11);
		// Button select=(Button)findViewById(R.id.googlemaps_select_location);
		// ImageView vii=(ImageView)findViewById(R.id.imageView2);
		main = (RelativeLayout) findViewById(R.id.mainlayout11);
		// engine3 = (WebView) findViewById(R.id.web_engine3);
		youtube_loader = (WebView) findViewById(R.id.web_engine_youtube);
		youtube_loader.getSettings().setJavaScriptEnabled(true);
		youtube_loader.getSettings().setJavaScriptCanOpenWindowsAutomatically(
				false);
		youtube_loader.getSettings().setPluginsEnabled(true);
		WebSettings webSettings4 = youtube_loader.getSettings(); // Fetches the
																	// WebSettings
																	// import

		youtube_loader.getSettings().setLoadWithOverviewMode(true);
		youtube_loader.getSettings().setUseWideViewPort(true);

		// engine.getSettings().setDefaultZoom(engine.ZoomDensity.FAR);
		// engine.getSettings().setPluginState(webSettings.PluginState.ON);
		webSettings4.setPluginState(WebSettings.PluginState.ON);

		youtube_loader.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				activity.setTitle("Loading...");
				activity.setProgress(progress * 100);

				if (progress == 100)
					activity.setTitle(R.string.app_name);
			}

			@Override
			public void onCloseWindow(WebView window) {
				// TODO Auto-generated method stub
				super.onCloseWindow(window);

				youtube_loader.stopLoading();
				youtube_loader.destroy();
			}
		});

		youtube_loader.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// Handle the error
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});

		// youtube_loader.loadUrl("http://www.youtube.com/results?search_query=+tom+and+jerry+episodes&oq=+tom+and+jerry+episodes&aq=f&aqi=g10&aql=&gs_sm=12&gs_upl=12816312l12816312l0l12817911l1l1l0l0l0l0l1136l1136l7-1l1l0");

		/*
		 * engine3.getSettings().setJavaScriptEnabled(true); engine3.
		 * getSettings().setJavaScriptCanOpenWindowsAutomatically (false);
		 * engine3. getSettings().setPluginsEnabled (true);
		 * 
		 * // engine5 = (WebView) findViewById(R.id.web_engine5);
		 * engine5.getSettings().setJavaScriptEnabled(true); engine5.
		 * getSettings().setJavaScriptCanOpenWindowsAutomatically (false);
		 * engine5. getSettings().setPluginsEnabled (true);
		 */
		mapView = (MapView) findViewById(R.id.mapview);
		// mapview1 = (MapView) findViewById(R.id.mapview1);
		mapView.setBuiltInZoomControls(false);
		// mapView.setBuiltInZoomControls(true);
		// this.locationManager = (LocationManager)
		// context.getSystemService(Context.LOCATION_SERVICE);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		myVideoView = (VideoView) findViewById(R.id.videoView1);

		// myVideoView.setOnCompletionListener(this);
		// videoView.setVisibility(View.GONE);
		// sate=(Button)findViewById(R.id.Button01);
		// myloc=(Button)findViewById(R.id.Button02);

		me = new MyLocationOverlay(this, mapView);
		me.enableCompass();
		// mapView.setStreetView(true);
		mapView.getOverlays().add(me);
		vf = (ViewFlipper) findViewById(R.id.details);
		vfmaps = (ViewFlipper) findViewById(R.id.list_maps);

		http_load = (WebView) findViewById(R.id.imageView_http);
		/*
		 * try { StrictMode.setThreadPolicy(policy);
		 * 
		 * // URL urlImage = new
		 * URL("http://gotabbie.com/tabbiedev/adspace/1/showAds.php"); //////
		 * HttpURLConnection connection = (HttpURLConnection) urlImage //
		 * .openConnection(); //InputStream inputStream =
		 * connection.getInputStream(); //Bitmap bitmap =
		 * BitmapFactory.decodeStream(inputStream); // ((WebView)
		 * http_load).setImageBitmap(bitmap); StrictMode.ThreadPolicy policy =
		 * new StrictMode. ThreadPolicy.Builder().permitAll().build(); } catch
		 * (MalformedURLException e) { e.printStackTrace(); } catch (IOException
		 * e) { e.printStackTrace(); }
		 */// ///////////////////////////////
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

		// http_load.loadUrl(
		// "http://gotabbie.com/tabbiedev/adspace/1/showAds.php");
		http_load
				.loadUrl("http://gotabbie.com/tabbiedev/adspace/1/showAds.php?deviceId="+DEVICE);
		http_load.setWebViewClient(new MyWebClient());
		http_load.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				/*
				 * activity.setTitle("Loading...");
				 * activity.setProgress(progress * 100);
				 * 
				 * if(progress == 100) //
				 * http_load.getSettings().setDefaultZoom(ZoomDensity.FAR);
				 * 
				 * http_load.setVerticalScrollBarEnabled(false);
				 * http_load.setHorizontalScrollBarEnabled(false);
				 */
				// http_load.setInitialScale(120);

				// http_load.getSettings().setBuiltInZoomControls(true);
				/*
				 * float scale = 155 * http_load.getScale();
				 * http_load.setInitialScale( (int) scale );
				 */

				// activity.setTitle(R.string.app_name);
			}
		});

		http_load.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// Handle the error
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) { // http_load.setInitialScale(120);

				
				
				
				// http_load.getSettings().setLoadWithOverviewMode(true);
				// http_load.getSettings().setUseWideViewPort(true);

				// http_load.getSettings().setBuiltInZoomControls(true);
				// float scale = 180 * http_load.getScale();
				// http_load.setInitialScale( (int) scale );
              
				// http_load.getSettings().setUseWideViewPort(true);
			//Toast.makeText(AndyGPS.this,"Url is"+url, Toast.LENGTH_LONG);
				http_load.setVerticalScrollBarEnabled(false);
				http_load.setHorizontalScrollBarEnabled(false);
				// http_load.setInitialScale(120);
				ndtv = (VideoView) findViewById(R.id.Video_ndtv);
				Uri path = null;
				if(url.endsWith("kairee")){
					path = Uri.parse("android.resource://" + getPackageName()
							+ "/" + R.raw.kirii);
					
				}
				
				else if(url.endsWith("nbt")){
					path = Uri.parse("android.resource://" + getPackageName()
							+ "/" + R.raw.nbt);
					
				}
				else if(url.endsWith("parichay")){
					path = Uri.parse("android.resource://" + getPackageName()
							+ "/" + R.raw.parichay);
					
				}
				else if(url.endsWith("chhal2")){
					path = Uri.parse("android.resource://" + getPackageName()
							+ "/" + R.raw.chall2);
					
				}
				ndtv.setMediaController(null);
	
				ndtv.setVideoURI(path);
				ndtv.start();
			
				//l6.setVisibility(View.INVISIBLE);
				vf.setDisplayedChild(7);
			//	vf.setDisplayedChild(11);
				//youtube_loader.loadUrl(url);
				
				return true;
			}
		});
		/*http_load.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				BANNERMEDIUM++;
				//youtube_loader.loadUrl("http://www.hdfcbank.com");
				youtube_loader.loadUrl("about:blank");
				//http_load
					//	.loadUrl("http://gotabbie.com/tabbiedev/adspace/1/showAds.php");
				//vf.setDisplayedChild(11);
				return true;
			}
		});
		/*
		http_load.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BANNERMEDIUM++;
			//	youtube_loader.loadUrl("http://www.hdfcbank.com");

			//	http_load
				//		.loadUrl("http://gotabbie.com/tabbiedev/adspace/1/showAds.php");
				vf.setDisplayedChild(11);

			}
		});*/

		// //////////////// /*bottom
		// banner*/////////////////////////////////////////////////////////////////////////////////
		http_bottom = (WebView) findViewById(R.id.imageView_http_bottom);
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);

			URL urlImage = new URL(
					"http://gotabbie.com/tabbiedev/adspace/2/showAds.php?deviceId="+DEVICE);
			HttpURLConnection connection = (HttpURLConnection) urlImage
					.openConnection();
			InputStream inputStream = connection.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			// ((WebView) http_load).setImageBitmap(bitmap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // ///////////////////////////////

		/*http_load.getSettings().setJavaScriptEnabled(true);
		http_load.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
		http_load.getSettings().setPluginsEnabled(true);
		http_load.getSettings().setAllowFileAccess(true);
		http_load.getSettings().setSupportZoom(false);
		http_load.getSettings().setAppCacheEnabled(false);
		http_load.getSettings().setAllowContentAccess(true);
		http_load.getSettings().setCacheMode(
				http_load.getSettings().LOAD_NO_CACHE);*/
		// float scale = 155 * http_load.getScale();
		// http_load.setInitialScale( (int) scale );

		http_load.setVerticalScrollBarEnabled(false);
		http_load.setHorizontalScrollBarEnabled(false);

		webSettings2.setPluginState(WebSettings.PluginState.ON);

		WebSettings webSettings3 = http_bottom.getSettings(); // Fetches the
																// WebSettings
																// import
		http_bottom.getSettings().setJavaScriptEnabled(true);
		http_bottom.getSettings().setJavaScriptCanOpenWindowsAutomatically(
				false);
		http_bottom.getSettings().setPluginsEnabled(true);
		http_bottom.getSettings().setSupportZoom(false);
		http_bottom.getSettings().setAppCacheEnabled(false);
		http_bottom.getSettings().setAllowContentAccess(true);
		http_bottom.getSettings().setCacheMode(
				http_bottom.getSettings().LOAD_NO_CACHE);

		// engine.getSettings().setPluginState(webSettings.PluginState.ON);
		webSettings3.setPluginState(WebSettings.PluginState.ON);
		// engine. getSettings().setSupportMultipleWindows (false);
		http_bottom
				.loadUrl("http://gotabbie.com/tabbiedev/adspace/2/showAds.php?deviceId="+DEVICE);
		http_bottom.setWebViewClient(new MyWebClient1());
		http_bottom.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {

				// activity.setTitle(R.string.app_name);
			}
		});

		http_bottom.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// Handle the error
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				http_bottom.setVerticalScrollBarEnabled(false);
				http_bottom.setHorizontalScrollBarEnabled(false);
				// http_load.setInitialScale(120);

				view.loadUrl(url);
				return true;
			}
		});

		http_bottom.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
			//	youtube_loader.loadUrl("http://www.ponds.com");
				BANNERLARGE++;
				//http_bottom
					//	.loadUrl("http://gotabbie.com/tabbiedev/adspace/2/showAds.php");
			//	vf.setDisplayedChild(11);
				return true;
			}
		});
		http_bottom.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			//	youtube_loader.loadUrl("http://www.ponds.com");
				BANNERLARGE++;
				//http_bottom
					//	.loadUrl("http://gotabbie.com/tabbiedev/adspace/2/showAds.php");
			//	vf.setDisplayedChild(11);

			}
		});

		// //////////////////////////////////////////
		// /*Side*///////////////////////////////////////

		http_side = (WebView) findViewById(R.id.imageView_http_bottom_side);
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
			URL urlImage = new URL(
					"http://gotabbie.com/tabbiedev/adspace/3/showAds.php?deviceId="+DEVICE);
			HttpURLConnection connection = (HttpURLConnection) urlImage
					.openConnection();
			InputStream inputStream = connection.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			// ((WebView) http_load).setImageBitmap(bitmap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ///////////////////////////////

		WebSettings webSettings6 = http_bottom.getSettings(); // Fetches the
																// WebSettings
																// import
		http_side.getSettings().setJavaScriptEnabled(true);
		http_side.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
		http_side.getSettings().setPluginsEnabled(true);
		http_side.getSettings().setSupportZoom(false);
		http_side.getSettings().setAppCacheEnabled(false);
		http_side.getSettings().setAllowContentAccess(true);
		http_side.getSettings().setCacheMode(
				http_load.getSettings().LOAD_NO_CACHE);

		// http_load.getSettings().setBuiltInZoomControls(true);
		// http_side.setInitialScale(30);

		// http_bottom.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		// http_bottom.setScrollbarFadingEnabled(false);
		http_side.getSettings().setPluginsEnabled(true);
		// engine.getSettings().setDefaultZoom(engine.ZoomDensity.FAR);
		// engine.getSettings().setPluginState(webSettings.PluginState.ON);

		webSettings6.setPluginState(WebSettings.PluginState.ON);
		// engine. getSettings().setSupportMultipleWindows (false);
		http_side
				.loadUrl("http://gotabbie.com/tabbiedev/adspace/3/showAds.php?deviceId="+DEVICE);
		http_side.setWebViewClient(new MyWebClient2());
		http_side.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {

				// activity.setTitle(R.string.app_name);
			}
		});

		http_side.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// Handle the error
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// http_load.setInitialScale(120);

				view.loadUrl(url);
				return true;
			}
		});

		http_side.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				BANNERSMALL++;
				//http_side
					//	.loadUrl("http://gotabbie.com/tabbiedev/adspace/3/showAds.php");
				vf.setDisplayedChild(11);
				return true;
			}
		});
		http_side.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BANNERSMALL++;
			//	http_side
				//		.loadUrl("http://gotabbie.com/tabbiedev/adspace/3/showAds.php");
				vf.setDisplayedChild(11);

			}
		});

		comedy = (ImageView) findViewById(R.id.imageView_tom);
		image_chan = (ImageView) findViewById(R.id.imageView_channels1);
		image_sports = (ImageView) findViewById(R.id.imageView_sports);
		image_stocks = (ImageView) findViewById(R.id.imageView_stock);
		image_shop = (ImageView) findViewById(R.id.imageView_shop);
		image_map = (ImageView) findViewById(R.id.imageView_map);
		// back=(ImageView)findViewById(R.id.imageView_back);
		// jeevansathi=(ImageView)findViewById(R.id.imageView_jeevansathi1);
		// acres=(ImageView)findViewById(R.id.imageView_acres1);
		// shiksha=(ImageView)findViewById(R.id.imageView_shiksha1);

		image_stocks.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent arg1) {
				// TODO Auto-generated method stub
				ENTERTAINMENT++;
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(8);
				ndtv.pause();

				youtube_loader.loadUrl("about:blank");
				return true;
			}
		});

		/*image_stocks.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				

				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(8);
				ndtv.pause();

				youtube_loader.loadUrl("about:blank");
				// TODO Auto-generated method stub
				// vf.setDisplayedChild(1);

			}
		});*/
		
		/*
		 * back.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub vf.showPrevious(); youtube_loader.loadUrl( "about:blank");
		 * //downloadFile(imageUrl+"salman.jpg");
		 * //Log.i("im url",imageUrl+"png"+i+".png");
		 * 
		 * 
		 * } });
		 */

		/*
		 * jeevansathi.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub
		 * 
		 * 
		 * //vf.setDisplayedChild(5);
		 * //engine5.loadUrl("http://www.jeevansathi.com");
		 * 
		 * } });
		 */
		youtube.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// downloadFile(imageUrl+"salman.jpg");
				// Log.i("im url",imageUrl+"png"+i+".png");
				YOUTUBE++;
				play.setVisibility(View.INVISIBLE);
				vfmaps.setInAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipnext));
				vfmaps.setOutAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipoutnext));
				// AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vfmaps,
				// FlipDirection.RIGHT_LEFT);
				vfmaps.setDisplayedChild(1);
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				// AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf,
				// FlipDirection.LEFT_RIGHT);
				vf.setDisplayedChild(10);

			}
		});

		// BalloonOverlay noteBaloon;
		/*
		 * LinearLayout noteBaloon; // itemizedOverlay.onTap(point2, mapView);]
		 * LayoutInflater layoutInflater = (LayoutInflater)
		 * this.getSystemService(Context.LAYOUT_INFLATER_SERVICE); noteBaloon =
		 * (LinearLayout) layoutInflater.inflate(R.layout.balloon_map_overlay,
		 * null); RelativeLayout.LayoutParams layoutParams = new
		 * RelativeLayout.LayoutParams(200,100);
		 * layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
		 * layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		 * noteBaloon.setLayoutParams(layoutParams);
		 * mapView.addView(noteBaloon);ndtv
		 * noteBaloon.setVisibility(View.VISIBLE);
		 */

		// engine = (WebView) findViewById(R.id.web_engine);
		// engine2 = (WebView) findViewById(R.id.web_engine2);

		// engine.setWebChromeClient(new WebChromeClient());
		// engine.setWebViewClient(new WebViewClient());
		// engine.getSettings().setAllowFileAccess(true);

		// MediaController mediaController = new MediaController(this);

		// engine.loadUrl("http://profit.ndtv.com/video/livetv");
		/*
		 * WebSettings webSettings = engine.getSettings(); // Fetches the
		 * WebSettings import engine.getSettings().setJavaScriptEnabled(true);
		 * engine. getSettings().setJavaScriptCanOpenWindowsAutomatically
		 * (false); engine. getSettings().setPluginsEnabled (true);
		 * engine.scrollBy(130, 800); engine.scrollTo(130, 800);
		 * engine.getSettings().setLoadWithOverviewMode(true);
		 * engine.getSettings().setUseWideViewPort(true);
		 * engine.getSettings().setBuiltInZoomControls(true); float scale = 200
		 * * engine.getScale(); engine.setInitialScale( (int) scale );
		 * 
		 * // engine.getSettings().setDefaultZoom(engine.ZoomDensity.FAR); //
		 * engine.getSettings().setPluginState(webSettings.PluginState.ON);
		 * webSettings.setPluginState(WebSettings.PluginState.ON); // engine.
		 * getSettings().setSupportMultipleWindows (false);
		 * engine.loadUrl("http://profit.ndtv.com/video/livetv");
		 * 
		 * ////////////
		 * 
		 * WebSettings webSettings1 = engine2.getSettings(); // Fetches the
		 * WebSettings import engine2.getSettings().setJavaScriptEnabled(true);
		 * engine2. getSettings().setJavaScriptCanOpenWindowsAutomatically
		 * (false); engine2. getSettings().setPluginsEnabled (true); //
		 * engine2.scrollBy(130, 800); // engine2.scrollTo(130, 800);
		 * engine2.getSettings().setLoadWithOverviewMode(true);
		 * engine2.getSettings().setUseWideViewPort(true);
		 * engine2.getSettings().setBuiltInZoomControls(true); // float scale1 =
		 * 200 * engine2.getScale(); // engine2.setInitialScale( (int) scale1);
		 * 
		 * // engine.getSettings().setDefaultZoom(engine.ZoomDensity.FAR); //
		 * engine.getSettings().setPluginState(webSettings.PluginState.ON);
		 * webSettings1.setPluginState(WebSettings.PluginState.ON); // engine.
		 * getSettings().setSupportMultipleWindows (false); engine2.loadUrl(
		 * "http://cdn.m.yupptv.tv/liveorigin/smil:ndtvprofit.smil/playlist.m3u8"
		 * );
		 */

		channel_ndtv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
PROFIT++;
				// // engine2.scrollBy(30, 370);
				// engine2.scrollTo(30, 370);
				/*
				 * Intent intent1 = new Intent(Intent.ACTION_VIEW,
				 * Uri.parse("http://profit.ndtv.com/video/livetv"));
				 * 
				 * startActivity(intent1);
				 */
				// float scale = 100 * engine2.getScale();
				// engine2.setInitialScale( (int) scale );
				// AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vfmaps,
				// FlipDirection.RIGHT_LEFT);
				// vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
				// R.anim.flipnext));

				// vfmaps.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
				// R.anim.flipnext));
				// vfmaps.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
				// R.anim.flipoutnext));
				// vfmaps.setDisplayedChild(1);
				play.setVisibility(View.VISIBLE);
				typeBar = 0;
				showDialog(typeBar);

				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// Intent intent = new Intent(AudioRecordTest.this,
						// MainActivity.class);
						vf.setInAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipnext));
						vf.setOutAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipoutnext));
						progDialog.dismiss();
						// startActivity(intent);

					}
				};
				t = new Timer();
				t.schedule(task, 10000);
				// AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf,
				// FlipDirection.LEFT_RIGHT);
				// vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
				// R.anim.flipoutnext));
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(7);

				String httpLiveUrl = "http://cdn.m.yupptv.tv/liveorigin/smil:ndtvprofit.smil/playlist.m3u8";
				// setContentView(R.layout.media_player);
				ndtv = (VideoView) findViewById(R.id.Video_ndtv);
				MediaController mediaController = new MediaController(
						AndyGPS.this);
				mediaController.setAnchorView(video);
				ndtv.setMediaController(mediaController);
				ndtv.setKeepScreenOn(true);
				ndtv.setVideoPath(httpLiveUrl);
				ndtv.start();
				ndtv.requestFocus();

				/*
				 * String httpLiveUrl1 =
				 * "http://cdn.m.yupptv.tv/liveorigin/smil:ndtvhindu_iphone.smil/playlist.m3u8"
				 * ; //setContentView(R.layout.media_player);
				 * ndtv=(VideoView)findViewById(R.id.Video_ndtv);
				 * ndtv.setVideoURI(Uri.parse(httpLiveUrl1));
				 * //ndtv.setMediaController(new
				 * MediaController(AndyActivity.this));
				 * ndtv.setMediaController(null); ndtv.requestFocus();
				 * ndtv.start();
				 */
				// typeBar = 0;
				// showDialog(typeBar);
				// getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				// engine2.loadUrl("http://cdn.m.yupptv.tv/liveorigin/smil:ndtvhindi_iphone.smil/playlist.m3u8");
				// mapView.postInvalidate();

			}
		});

		/*
		 * saam.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(final View v) { // TODO Auto-generated
		 * method stub typeBar = 0; showDialog(typeBar);
		 * play.setVisibility(View.VISIBLE); play.setEnabled(true); task = new
		 * TimerTask() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub //
		 * Intent intent = new Intent(AudioRecordTest.this, MainActivity.class);
		 * vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipnext));
		 * vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipoutnext)); progDialog.dismiss(); //startActivity(intent);
		 * 
		 * 
		 * } }; t = new Timer(); t.schedule(task, 10000);
		 * 
		 * vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipnext));
		 * vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipoutnext)); vf.setDisplayedChild(7); //ndtv.start(); //
		 * video_saam=(VideoView)findViewById(R.id.videoView_saam); //
		 * ndtv.setKeepScreenOn(true); String httpLiveUrl=
		 * "http://cdn.m.yupptv.tv/liveorigin/smil:saamtv_iphone.smil/playlist.m3u8"
		 * ; //String fileName = "android.resource://" + getPackageName() + "/"
		 * + R.raw.idea; ndtv=(VideoView)findViewById(R.id.Video_ndtv);
		 * MediaController mediaController = new
		 * MediaController(AndyActivity.this);
		 * mediaController.setAnchorView(video);
		 * ndtv.setMediaController(mediaController); ndtv.setKeepScreenOn(true);
		 * ndtv.setVideoPath(httpLiveUrl); ndtv.start(); ndtv.requestFocus();
		 * 
		 * 
		 * String httpLiveUrl =
		 * "http://cdn.m.yupptv.tv/liveorigin/smil:saamtv_iphone.smil/playlist.m3u8"
		 * ; //setContentView(R.layout.media_player);
		 * ndtv=(VideoView)findViewById(R.id.Video_ndtv);
		 * ndtv.setVideoURI(Uri.parse(httpLiveUrl)); //
		 * ndtv.setMediaController(null); ndtv.setMediaController(new
		 * MediaController(AndyActivity.this)); ndtv.requestFocus();
		 * ndtv.start();
		 * 
		 * } });
		 */

		india.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				typeBar = 0;
				INDIATV++;
				play.setVisibility(View.VISIBLE);
				showDialog(typeBar);

				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// Intent intent = new Intent(AudioRecordTest.this,
						// MainActivity.class);
						vf.setInAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipnext));
						vf.setOutAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipoutnext));
						progDialog.dismiss();
						// startActivity(intent);

					}
				};
				t = new Timer();
				t.schedule(task, 10000);
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(7);

				String httpLiveUrl = "http://cdn.m.yupptv.tv/liveorigin/smil:indiatv.smil/playlist.m3u8";
				// setContentView(R.layout.media_player);
				ndtv = (VideoView) findViewById(R.id.Video_ndtv);
				MediaController mediaController = new MediaController(
						AndyGPS.this);
				mediaController.setAnchorView(video);
				ndtv.setMediaController(mediaController);
				ndtv.setKeepScreenOn(true);
				ndtv.setVideoPath(httpLiveUrl);
				ndtv.start();
				ndtv.requestFocus();
			}
		});

		newx.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				NEWX++;
				typeBar = 0;
				showDialog(typeBar);
				play.setVisibility(View.VISIBLE);

				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// Intent intent = new Intent(AudioRecordTest.this,
						// MainActivity.class);
						vf.setInAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipnext));
						vf.setOutAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipoutnext));
						progDialog.dismiss();
						// startActivity(intent);

					}
				};
				t = new Timer();
				t.schedule(task, 10000);
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(7);

				String httpLiveUrl = "http://cdn.m.yupptv.tv/liveorigin/smil:newsx.smil/playlist.m3u8";
				// setContentView(R.layout.media_player);
				ndtv = (VideoView) findViewById(R.id.Video_ndtv);
				MediaController mediaController = new MediaController(
						AndyGPS.this);
				mediaController.setAnchorView(video);
				ndtv.setMediaController(mediaController);
				ndtv.setKeepScreenOn(true);
				ndtv.setVideoPath(httpLiveUrl);
				ndtv.start();
				ndtv.requestFocus();
			}
		});

		hindi.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				play.setVisibility(View.VISIBLE);
				typeBar = 0;
				showDialog(typeBar);
HINDU++;
				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// Intent intent = new Intent(AudioRecordTest.this,
						// MainActivity.class);
						vf.setInAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipnext));
						vf.setOutAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipoutnext));

						progDialog.dismiss();
						// startActivity(intent);

					}
				};
				t = new Timer();
				t.schedule(task, 10000);
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(7);

				String httpLiveUrl = "http://cdn.m.yupptv.tv/liveorigin/smil:ndtvhindu_iphone.smil/playlist.m3u8";
				ndtv = (VideoView) findViewById(R.id.Video_ndtv);
				MediaController mediaController = new MediaController(
						AndyGPS.this);
				mediaController.setAnchorView(video);
				ndtv.setMediaController(mediaController);
				ndtv.setKeepScreenOn(true);
				ndtv.setVideoPath(httpLiveUrl);
				ndtv.start();
				ndtv.requestFocus();
				/*
				 * ndtv=(VideoView)findViewById(R.id.Video_ndtv);
				 * ndtv.setVideoURI(Uri.parse(httpLiveUrl));
				 * ndtv.setMediaController(new
				 * MediaController(AndyActivity.this)); ndtv.requestFocus();
				 * ndtv.start();
				 */
			}
		});

		english.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
ENGLISH++;
				play.setVisibility(View.VISIBLE);
				httpLiveUrl22 = "http://94.75.250.49/liveorigin/smil:ndtvenglish_iphone.smil/playlist.m3u8";
				typeBar = 0;
				showDialog(typeBar);

				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// Intent intent = new Intent(AudioRecordTest.this,
						// MainActivity.class);
						vf.setInAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipnext));
						vf.setOutAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipoutnext));
						progDialog.dismiss();
						// startActivity(intent);

					}
				};
				t = new Timer();
				t.schedule(task, 10000);
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(7);

				// new SubmitCommentTask().execute();
				MediaController mediaController = new MediaController(
						AndyGPS.this);
				mediaController.setAnchorView(video);
				ndtv.setMediaController(mediaController);
				ndtv.setKeepScreenOn(true);
				ndtv.setVideoPath(httpLiveUrl22);
				ndtv.start();
				ndtv.requestFocus();

				/*
				 * String httpLiveUrl =
				 * "http://94.75.250.49/liveorigin/smil:ndtvenglish_iphone.smil/playlist.m3u8"
				 * ; //setContentView(R.layout.media_player);
				 * ndtv=(VideoView)findViewById(R.id.Video_ndtv);
				 * MediaController mediaController = new
				 * MediaController(AndyActivity.this);
				 * mediaController.setAnchorView(video);
				 * ndtv.setMediaController(mediaController);
				 * ndtv.setKeepScreenOn(true); ndtv.setVideoPath(httpLiveUrl);
				 * ndtv.start(); ndtv.requestFocus();
				 */
			}
		});

		goodtimes.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				play.setVisibility(View.VISIBLE);
				GOODTIMES++;
				// TODO Auto-generated method stub
				typeBar = 0;
				showDialog(typeBar);

				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// Intent intent = new Intent(AudioRecordTest.this,
						// MainActivity.class);
						vf.setInAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipnext));
						vf.setOutAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipoutnext));
						progDialog.dismiss();
						// startActivity(intent);

					}
				};
				t = new Timer();
				t.schedule(task, 10000);
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(7);

				String httpLiveUrl = "http://cdn.m.yupptv.tv/liveorigin/smil:ndtvgoodtimes_iphone.smil/playlist.m3u8";
				// setContentView(R.layout.media_player);
				ndtv = (VideoView) findViewById(R.id.Video_ndtv);
				MediaController mediaController = new MediaController(
						AndyGPS.this);
				mediaController.setAnchorView(video);
				ndtv.setMediaController(mediaController);
				ndtv.setKeepScreenOn(true);
				ndtv.setVideoPath(httpLiveUrl);
				ndtv.start();
				ndtv.requestFocus();
			}
		});

		fun.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				play.setVisibility(View.VISIBLE);
				FUNASIA++;
				typeBar = 0;
				showDialog(typeBar);

				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// Intent intent = new Intent(AudioRecordTest.this,
						// MainActivity.class);
						vf.setInAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipnext));
						vf.setOutAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipoutnext));
						progDialog.dismiss();
						// vf.setSystemUiVisibility(View.STATUS_BAR_HIDDEN);
						// startActivity(intent);

					}
				};
				t = new Timer();
				t.schedule(task, 10000);
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(7);

				String httpLiveUrl = "http://cdn.m.yupptv.tv/liveorigin/smil:funasia.smil/playlist.m3u8";
				// setContentView(R.layout.media_player);
				ndtv = (VideoView) findViewById(R.id.Video_ndtv);
				MediaController mediaController = new MediaController(
						AndyGPS.this);
				mediaController.setAnchorView(video);
				ndtv.setMediaController(mediaController);
				ndtv.setKeepScreenOn(true);
				ndtv.setVideoPath(httpLiveUrl);
				ndtv.start();
				ndtv.requestFocus();
			}
		});
		sahara.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				play.setVisibility(View.VISIBLE);
				SAHARA++;
				// TODO Auto-generated method stub
				typeBar = 0;
				showDialog(typeBar);

				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// Intent intent = new Intent(AudioRecordTest.this,
						// MainActivity.class);
						vf.setInAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipnext));
						vf.setOutAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipoutnext));
						progDialog.dismiss();
						// startActivity(intent);

					}
				};
				t = new Timer();
				t.schedule(task, 10000);
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(7);

				String httpLiveUrl = "http://cdn.m.yupptv.tv/liveorigin/smil:saharamumbai.smil/playlist.m3u8";
				ndtv = (VideoView) findViewById(R.id.Video_ndtv);
				MediaController mediaController = new MediaController(
						AndyGPS.this);
				mediaController.setAnchorView(video);
				ndtv.setMediaController(mediaController);
				ndtv.setKeepScreenOn(true);
				ndtv.setVideoPath(httpLiveUrl);
				ndtv.start();
				ndtv.requestFocus();
			}
		});

		image_chan.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(final View v, MotionEvent arg1) {
				// TODO Auto-generated method stub
				ndtv.pause();
				youtube_loader.loadUrl("about:blank");
				MAP++;
				typeBar = 3;
				showDialog(typeBar);
				task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// Intent intent = new Intent(AudioRecordTest.this,
						// MainActivity.class);
						vf.setInAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipnext));
						vf.setOutAnimation(AnimationUtils.loadAnimation(
								v.getContext(), R.anim.flipoutnext));
						progDialog.dismiss();
						// startActivity(intent);

					}
				};
				t = new Timer();
				t.schedule(task, 10000);
				vf.setInAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(
						v.getContext(), R.anim.flipoutnext));
				/*
				 * typeBar = 3; showDialog(typeBar);
				 * 
				 * task = new TimerTask() {
				 * 
				 * @Override public void run() { // TODO Auto-generated method
				 * stub // Intent intent = new Intent(AudioRecordTest.this,
				 * MainActivity.class);
				 * vf.setInAnimation(AnimationUtils.loadAnimation
				 * (v.getContext(), R.anim.flipnext));
				 * vf.setOutAnimation(AnimationUtils
				 * .loadAnimation(v.getContext(), R.anim.flipoutnext));
				 * 
				 * progDialog.dismiss(); //startActivity(intent);
				 * 
				 * 
				 * } }; t = new Timer(); t.schedule(task, 5000);
				 */
				// // engine2.scrollBy(30, 370);
				// engine2.scrollTo(30, 370);
				/*
				 * Intent intent1 = new Intent(Intent.ACTION_VIEW,
				 * Uri.parse("http://profit.ndtv.com/video/livetv"));
				 * 
				 * startActivity(intent1);
				 */
				// float scale = 100 * engine2.getScale();
				// engine2.setInitialScale( (int) scale );
				// AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf,
				// FlipDirection.RIGHT_LEFT);
				// vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
				// R.anim.slide_in_left));
				// ndtv.stopPlayback();

				// vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
				// R.anim.flipnext));
				// vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
				// R.anim.flipoutnext));
				vf.setDisplayedChild(2);
				mapView.getController().setZoom(17);
				mapView.setBuiltInZoomControls(false);
				return true;
			}
		});

		/*
		 * image_chan.setOnClickListener(new View.OnClickListener() {
		 * 
		 * private Object holder;
		 * 
		 * @Override public void onClick(final View v) { // TODO Auto-generated
		 * method stub
		 * 
		 * 
		 * 
		 * ndtv.pause(); youtube_loader.loadUrl("about:blank"); typeBar = 3;
		 * showDialog(typeBar); task = new TimerTask() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub //
		 * Intent intent = new Intent(AudioRecordTest.this, MainActivity.class);
		 * vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipnext));
		 * vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipoutnext)); progDialog.dismiss(); //startActivity(intent);
		 * 
		 * 
		 * } }; t = new Timer(); t.schedule(task, 10000); typeBar = 3;
		 * showDialog(typeBar);
		 * 
		 * task = new TimerTask() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub //
		 * Intent intent = new Intent(AudioRecordTest.this, MainActivity.class);
		 * vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipnext));
		 * vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipoutnext));
		 * 
		 * progDialog.dismiss(); //startActivity(intent);
		 * 
		 * 
		 * } }; t = new Timer(); t.schedule(task, 5000); ////
		 * engine2.scrollBy(30, 370); // engine2.scrollTo(30, 370); Intent
		 * intent1 = new Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); // float scale = 100 * engine2.getScale(); //
		 * engine2.setInitialScale( (int) scale );
		 * //AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf,
		 * FlipDirection.RIGHT_LEFT);
		 * //vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.slide_in_left)); //ndtv.stopPlayback();
		 * 
		 * // vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipnext));
		 * //vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipoutnext)); vf.setDisplayedChild(2);
		 * mapView.getController().setZoom(17);
		 * mapView.setBuiltInZoomControls(false); // Intent i=new
		 * Intent(AndyActivity.this,MapDemo.class); //startActivity(i);
		 * 
		 * LinearLayout
		 * bottom=(LinearLayout)findViewById(R.id.mainlayout_BOTTOM); Intent
		 * intent = new Intent(AndyActivity.this, MapDemo.class);
		 * 
		 * Window window= getLocalActivityManager().startActivity("MapDemo",
		 * intent); window.setContentView(R.layout.mappy); //
		 * setContentView(window.getDecorView()); View wd =
		 * window.getDecorView(); bottom.addView(wd); LocalActivityManager
		 * localActivityManager = getLocalActivityManager(); LinearLayout
		 * bottom=(LinearLayout)findViewById(R.id.mainlayout_BOTTOM); Intent i =
		 * new Intent(AndyActivity.this, MapDemo.class); Window w=null;
		 * 
		 * w = localActivityManager.startActivity("tag", i); View currentView;
		 * currentView=w.getDecorView();
		 * currentView.setVisibility(View.VISIBLE);
		 * currentView.setFocusableInTouchMode(true); ((ViewGroup)
		 * currentView).setDescendantFocusability
		 * (ViewGroup.FOCUS_AFTER_DESCENDANTS); bottom.addView(currentView);
		 * 
		 * Intent intent1 = new Intent(AndyActivity.this, MapDemo.class); Window
		 * window1 = null; requestWindowFeature(window1.FEATURE_NO_TITLE);
		 * window1= getLocalActivityManager().startActivity("map", intent1);
		 * 
		 * //setContentView(window1.getDecorView()); View wd =
		 * window1.getDecorView();
		 * 
		 * if(wd != null) { bottom.addView(wd); }
		 * 
		 * 
		 * //LocalActivityManager mgr = getLocalActivityManager();
		 * 
		 * Intent intent = new Intent("com.maps.andy.MAPDEMO");
		 * 
		 * Window w = mgr.startActivity("com.maps.andy.MAPDEMO", intent); View
		 * wd = w != null ? w.getDecorView() : null;
		 * 
		 * if(wd != null) { bottom1.addView(wd); }
		 * 
		 * 
		 * //LocalActivityManager mgr1 = getLocalActivityManager();
		 * 
		 * //bottom1.addView((mgr1.startActivity( "com.maps.andy.AndyActivity",
		 * new Intent(AndyActivity.this, MapDemo.class))).getDecorView());
		 * //bottom=(LinearLayout)findViewById(R.id.mainlayout_BOTTOM);
		 * 
		 * // LocalActivityManager mgr = getLocalActivityManager(); //SERVER
		 * FOLDER IMAGE FELCH // bottom.addView((mgr.startActivity("ABS", new
		 * Intent(this, MapDemo.class))).getDecorView()); // Intent intent = new
		 * Intent(AndyActivity.this, MapDemo.class);
		 * 
		 * // Window w = mgr.startActivity("MapDemo", intent); // View wd = w !=
		 * null ? w.getDecorView() : null;
		 * 
		 * //if(wd != null) { // bottom.addView(wd); // }
		 * 
		 * //map.removeView());
		 * 
		 * //mapView =holder.getMapView();
		 * 
		 * //youtube_loader.stopLoading(); //youtube_loader.destroy();
		 * 
		 * 
		 * String httpLiveUrl =
		 * "http://cdn.m.yupptv.tv/liveorigin/smil:ndtvhindi_iphone.smil/playlist.m3u8"
		 * ; //setContentView(R.layout.media_player);
		 * ndtv=(VideoView)findViewById(R.id.Video_ndtv);
		 * ndtv.setVideoURI(Uri.parse(httpLiveUrl)); ndtv.setMediaController(new
		 * MediaController(AndyActivity.this)); ndtv.requestFocus();
		 * ndtv.start();
		 * //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
		 * ); //engine2.loadUrl(
		 * "http://cdn.m.yupptv.tv/liveorigin/smil:ndtvhindi_iphone.smil/playlist.m3u8"
		 * ); //mapView.postInvalidate();
		 * 
		 * } }); // webSettings.setPluginsEnabled(true); // Allows plugins to
		 * run which are normally disabled in webView
		 * //engine.getSettings().setBuiltInZoomControls(true);
		 * 
		 * engine. setWebViewClient (new WebViewClient () {
		 * 
		 * @ Override public boolean shouldOverrideUrlLoading(WebView view,
		 * String url) { if (url.startsWith("http://profit")){
		 * 
		 * startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
		 * engine.scrollBy(130, 800); engine.scrollTo(130, 800); Intent intent1
		 * = new Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 200 * engine.getScale();
		 * engine.setInitialScale( (int) scale ); return true; } else { return
		 * false; } } }); engine.setWebChromeClient(new WebChromeClient() {
		 * public void onProgressChanged(WebView view, int progress) {
		 * progress=1; activity.setTitle("Loading...");
		 * activity.setProgress(progress * 100);
		 * 
		 * if(progress == 100) activity.setTitle(R.string.app_name);
		 * engine.scrollBy(130, 800); engine.scrollTo(130, 800); Intent intent1
		 * = new Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 200 * engine.getScale();
		 * engine.setInitialScale( (int) scale ); }
		 * 
		 * 
		 * 
		 * @Override public boolean onCreateWindow(WebView engine, boolean
		 * dialog, boolean userGesture, Message resultMsg) { // TODO
		 * Auto-generated method stub engine.scrollBy(130, 800);
		 * engine.scrollTo(130, 800); Intent intent1 = new
		 * Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 200 * engine.getScale();
		 * engine.setInitialScale( (int) scale ); return
		 * super.onCreateWindow(engine, dialog, userGesture, resultMsg); } });
		 * 
		 * engine.setWebViewClient(new WebViewClient() {
		 * 
		 * @Override public void onReceivedError(WebView view, int errorCode,
		 * String description, String failingUrl) { // Handle the error }
		 * 
		 * @Override public void onPageFinished(WebView engine, String url) { //
		 * TODO Auto-generated method stub engine.scrollBy(130, 800);
		 * engine.scrollTo(130, 800); Intent intent1 = new
		 * Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 200 * engine.getScale();
		 * engine.setInitialScale( (int) scale ); super.onPageFinished(engine,
		 * url); }
		 * 
		 * @Override public void onPageStarted(WebView engine, String url,
		 * Bitmap favicon) { engine.scrollBy(130, 800); engine.scrollTo(130,
		 * 800); Intent intent1 = new Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 200 * engine.getScale();
		 * engine.setInitialScale( (int) scale ); // TODO Auto-generated method
		 * stub
		 * 
		 * super.onPageStarted(engine, url, favicon); }
		 * 
		 * @Override public boolean shouldOverrideUrlLoading(WebView view,
		 * String url) { view.loadUrl("http://profit.ndtv.com/video/livetv");
		 * activity.setTitle(R.string.app_name); engine.scrollBy(130, 800);
		 * engine.scrollTo(130, 800); Intent intent1 = new
		 * Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 200 * engine.getScale();
		 * engine.setInitialScale( (int) scale ); return true; } });
		 * 
		 * engine2.setWebChromeClient(new WebChromeClient() { public void
		 * onProgressChanged(WebView engine2, int progress) { progress=1;
		 * //activity.setTitle("Loading..."); activity.setProgress(progress *
		 * 100);
		 * 
		 * if(progress == 100) // activity.setTitle(R.string.app_name);
		 * engine2.scrollBy(50, 370); engine2.scrollTo(50, 370); Intent intent1
		 * = new Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 100 * engine2.getScale();
		 * engine2.setInitialScale( (int) scale ); }
		 * 
		 * @Override public boolean onCreateWindow(WebView engine2, boolean
		 * dialog, boolean userGesture, Message resultMsg) { // TODO
		 * Auto-generated method stub engine2.scrollBy(50, 370);
		 * engine2.scrollTo(50, 370); Intent intent1 = new
		 * Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 100 * engine2.getScale();
		 * engine2.setInitialScale( (int) scale ); return
		 * super.onCreateWindow(engine2, dialog, userGesture, resultMsg); } });
		 * 
		 * engine2.setWebViewClient(new WebViewClient() {
		 * 
		 * @Override public void onReceivedError(WebView view, int errorCode,
		 * String description, String failingUrl) { // Handle the error }
		 * 
		 * @Override public void onPageStarted(WebView engine2, String url,
		 * Bitmap favicon) { // TODO Auto-generated method stub
		 * engine2.scrollBy(50, 370); engine2.scrollTo(50, 370); Intent intent1
		 * = new Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 100 * engine2.getScale();
		 * engine2.setInitialScale( (int) scale ); super.onPageStarted(engine2,
		 * url, favicon); }
		 * 
		 * @Override public void onPageFinished(WebView engine2, String url) {
		 * // TODO Auto-generated method stub engine2.scrollBy(50, 370);
		 * engine2.scrollTo(50, 370); Intent intent1 = new
		 * Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 100 * engine2.getScale();
		 * engine2.setInitialScale( (int) scale ); super.onPageFinished(engine2,
		 * url); }
		 * 
		 * @Override public boolean shouldOverrideUrlLoading(WebView view,
		 * String url) { view.loadUrl("http://profit.ndtv.com/video/livetv");
		 * activity.setTitle(R.string.app_name); engine.scrollBy(130, 800);
		 * engine.scrollTo(130, 800); Intent intent1 = new
		 * Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 200 * engine.getScale();
		 * engine.setInitialScale( (int) scale ); return true; } });
		 * 
		 * engine.scrollBy(300, 800);
		 * 
		 * engine.scrollTo(300, 800);
		 */
		image_shop.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent arg1) {
				// TODO Auto-generated method stub
				SPORTS++;
				youtube_loader.loadUrl("about:blank");
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(12);
				ndtv.pause();
				return true;
			}
		});

		/*
		 * image_shop.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * youtube_loader.loadUrl("about:blank");
		 * vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipnext));
		 * vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
		 * R.anim.flipoutnext)); vf.setDisplayedChild(12); ndtv.pause();
		 * 
		 * // TODO Auto-generated method stub
		 * AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf,
		 * FlipDirection.RIGHT_LEFT); vf.setDisplayedChild(3);
		 * engine.scrollBy(150, 800); engine.scrollTo(150, 800); Intent intent1
		 * = new Intent(Intent.ACTION_VIEW,
		 * Uri.parse("http://profit.ndtv.com/video/livetv"));
		 * 
		 * startActivity(intent1); float scale = 200 * engine.getScale();
		 * engine.setInitialScale( (int) scale ); engine.stopLoading();
		 * engine.scrollBy(300, 800); engine.scrollTo(300, 800); //
		 * engine.destroy(); //engine.stopLoading(); mapView.postInvalidate(); }
		 * });
		 */

		image_sports.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent arg1) {
				// TODO Auto-generated method stub
NEWS++;
				youtube_loader.loadUrl("about:blank");
				ndtv.pause();

				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(9);
				mapView.postInvalidate();
				return true;
			}
		});
		/*image_sports.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				youtube_loader.loadUrl("about:blank");
				ndtv.pause();

				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(9);
				// TODO Auto-generated method stub

				// getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

				mapView.postInvalidate();
			}
		});*/

		image_map.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent arg1) {
				// TODO Auto-generated method stub
				ndtv.pause();
SETTINGS++;
				youtube_loader.loadUrl("about:blank");
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(14);
				vf_settings.setDisplayedChild(0);
				return true;
			}
		});
		image_map.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf,
				// FlipDirection.RIGHT_LEFT);
				// vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
				// R.anim.slide_in_left));
SETTINGS++;
				ndtv.pause();

				youtube_loader.loadUrl("about:blank");
				vf.setInAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipnext));
				vf.setOutAnimation(AnimationUtils.loadAnimation(v.getContext(),
						R.anim.flipoutnext));
				vf.setDisplayedChild(14);
				vf_settings.setDisplayedChild(0);
				// vf.stopFlipping();

				// engine.scrollBy(130, 800);
				// engine.scrollTo(130, 800);
				// float scale = 200 * engine.getScale();
				// engine.setInitialScale( (int) scale );
				// engine.stopLoading();
				// engine.destroy();
				// engine.destroy();
				// engine.stopLoading();
				// startActivity(new Intent(AndyActivity.this,
				// AndyActivity.class));

			}
		});
		/*
		 * MapView.LayoutParams params = new
		 * MapView.LayoutParams(LayoutParams.WRAP_CONTENT,
		 * LayoutParams.WRAP_CONTENT, point2, 0, 0,
		 * MapView.LayoutParams.BOTTOM_CENTER); params.mode =
		 * MapView.LayoutParams.MODE_MAP; mapView.addView(noteBaloon, params);
		 * noteBaloon.setVisibility(View.VISIBLE);
		 */

		// new SubmitCommentTask().execute();
		// registerLocationListeners();
		// com.maps.andy.BalloonItemizedOverlay.balloonView.setVisibility(View.VISIBLE);
		// bb.onTap(1);
		// createLocationListeners();
		// main.setVisibility(View.VISIBLE);
		/*
		 * String LINK =
		 * "http://www.youtube.com/watch?v=lEbxLDuecHU&playnext=1&list=PL040F3034C69B1674"
		 * ; VideoView videoView = (VideoView) findViewById(R.id.videoView1);
		 * MediaController media = new MediaController(this);
		 * media.setAnchorView(videoView); // media.setMediaPlayer(videoView);
		 * Uri video = Uri.parse(LINK); videoView.setMediaController(media);
		 * videoView.setVideoURI(video); videoView.start();
		 */

		// lm = (LocationManager)
		// getSystemService(Context.LOCATION_SERVICE);

		// MyLocationListener locationListener = new MyLocationListener();

		/*
		 * Criteria criteria = new Criteria();
		 * criteria.setPowerRequirement(Criteria.POWER_LOW);
		 * criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		 * criteria.setAltitudeRequired(fa lse);
		 * criteria.setBearingRequired(false); criteria.setCostAllowed(true);
		 * criteria.setSpeedRequired(false); // Get the best provider from the
		 * criteria specified, and false to say it can turn the provider on if
		 * it isn't already bestProvider = lm.getBestProvider(criteria, false);
		 */
		/*
		 * lm.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 0, 0,
		 * locationListener);
		 */
		// Geocoder geocoder = new Geocoder(context);

		double src_lat = 18.980849 * 1E6; // the testing source
		double src_long = 72.826871 * 1E6;
		double dest_lat = 18.994178 * 1E6; // the testing destination
		double dest_long = 72.819595 * 1E6;
		GeoPoint srcGeoPoint = new GeoPoint((int) (src_lat), (int) (src_long));
		GeoPoint destGeoPoint = new GeoPoint((int) (dest_lat),
				(int) (dest_long));

		// DrawPath(srcGeoPoint, destGeoPoint, Color.GREEN, mapView);

		// mapView.getController().animateTo(srcGeoPoint);
		// mapView.getController().setZoom(14);

		// csie-tw.blogspot.com/2009/06/android-driving-direction-route-path.html#ixzz1laMVFRmq
		// Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		/*
		 * mc = mapView.getController(); mapView.setBuiltInZoomControls(true);
		 * // populateCoordinatesFromLastKnownLocation(); mc.setZoom(16);
		 */
		// mc.animateTo(centerGp);
		// mc.animateTo(marker_point);

		/*
		 * MediaController mediaController = new MediaController(this);
		 * mediaController.setAnchorView(videoView); // Set video link (mp4
		 * format ) Uri video = Uri.parse(
		 * "http://www.youtube.com/watch?v=lEbxLDuecHU&playnext=1&list=PL040F3034C69B1674"
		 * ); videoView.setMediaController(mediaController);
		 * videoView.setVideoURI(video); videoView.start();
		 */
		/*
		 * Uri mPath; mPath = Uri.parse(
		 * "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp"
		 * ); videoView.setVideoURI(mPath); videoView.setMediaController(new
		 * MediaController(this)); videoView.requestFocus(); videoView.start();
		 * videoView.setOnCompletionListener(this);
		 */
		// (()mapView.getParent()).removeView(mapView);
		/*
		 * idea.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Timer timer = new Timer(); TimerTask timerTask = new TimerTask()
		 * {
		 * 
		 * @Override public void run() { // Log.i(TAG,"i am in timertask");
		 * Timer timer = new Timer(); // TimerTask timerTask = new TimerTask()
		 * Intent i=new Intent(getBaseContext(),AndyActivity.class); //
		 * AnimationFactory.flipTransition(vf, FlipDirection.RIGHT_LEFT);
		 * startActivity(i);
		 * 
		 * timer.scheduleAtFixedRate(timerTask,2000,2000); }
		 * 
		 * };
		 * 
		 * 
		 * //mapView.invalidateDrawable(drawable); // mapView.invalidate();
		 * final int welcomeScreenDisplay = 800; Thread welcomeThread = new
		 * Thread() {
		 * 
		 * int wait = 0;
		 * 
		 * @Override public void run() { try { super.run();
		 *//**
		 * use while to get the splash time. Use sleep() to increase the wait
		 * variable for every 100L.
		 */
		/*
		 * while (wait < welcomeScreenDisplay) { sleep(200); wait += 200; } }
		 * catch (Exception e) { System.out.println("EXc=" + e); } finally {
		 *//**
		 * Called after splash times up. Do some action after splash times
		 * up. Here we moved to another main activity class
		 */
		/*
		 * startActivity(new Intent(AndyActivity.this, AndyActivity.class));
		 * finish(); } } }; welcomeThread.start();
		 * 
		 * 
		 * 
		 * } });
		 */

		// Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		// mc = mapView.getController();
		mapView.setBuiltInZoomControls(false);
		// requestLocationUpdates(listener);
		
		mapView.invalidate();

	}

	private LocalActivityManager getLocalActivityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	private void DrawPath(GeoPoint src, GeoPoint dest, int color,
			MapView mapView) {
		// TODO Auto-generated method stub

		// connect to map web service
		StringBuilder urlString = new StringBuilder();
		urlString.append("http://maps.google.com/maps?f=d&hl=en");
		urlString.append("&saddr=");// from
		urlString.append(Double.toString((double) src.getLatitudeE6() / 1.0E6));
		urlString.append(",");
		urlString
				.append(Double.toString((double) src.getLongitudeE6() / 1.0E6));
		urlString.append("&daddr=");// to
		urlString
				.append(Double.toString((double) dest.getLatitudeE6() / 1.0E6));
		urlString.append(",");
		urlString
				.append(Double.toString((double) dest.getLongitudeE6() / 1.0E6));
		urlString.append("&ie=UTF8&0&om=0&output=kml");
		Log.d("xxx", "URL=" + urlString.toString());
		// get the kml (XML) doc. And parse it to get the coordinates(direction
		// route).
		Document doc = null;
		HttpURLConnection urlConnection = null;
		URL url = null;
		try {
			url = new URL(urlString.toString());
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.connect();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(urlConnection.getInputStream());

			if (doc.getElementsByTagName("GeometryCollection").getLength() > 0) {
				// String path =
				// doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getNodeName();
				String path = doc.getElementsByTagName("GeometryCollection")
						.item(0).getFirstChild().getFirstChild()
						.getFirstChild().getNodeValue();
				Log.d("xxx", "path=" + path);
				String[] pairs = path.split(" ");
				String[] lngLat = pairs[0].split(","); // lngLat[0]=longitude
														// lngLat[1]=latitude
														// lngLat[2]=height
				// src
				GeoPoint startGP = new GeoPoint(
						(int) (Double.parseDouble(lngLat[1]) * 1E6),
						(int) (Double.parseDouble(lngLat[0]) * 1E6));
				mapView.getOverlays().add(new MyOverLay(startGP, startGP, 1));
				GeoPoint gp1;
				GeoPoint gp2 = startGP;
				for (int i = 1; i < pairs.length; i++) // the last one would be
														// crash
				{
					lngLat = pairs[i].split(",");
					gp1 = gp2;
					// watch out! For GeoPoint, first:latitude, second:longitude
					gp2 = new GeoPoint(
							(int) (Double.parseDouble(lngLat[1]) * 1E6),
							(int) (Double.parseDouble(lngLat[0]) * 1E6));
					mapView.getOverlays()
							.add(new MyOverLay(gp1, gp2, 2, color));
					mapView.invalidate();
					Log.d("xxx", "pair:" + pairs[i]);
				}
				mapView.getOverlays().add(new MyOverLay(dest, dest, 3)); // use
																			// the
																			// default
																			// color
				mapView.invalidate();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	}

/*	private void registerLocationListeners() {
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Initialize criteria for location providers
		Criteria fine = new Criteria();
		fine.setAccuracy(Criteria.ACCURACY_FINE);
		Criteria coarse = new Criteria();
		coarse.setAccuracy(Criteria.ACCURACY_COARSE);

		// Get at least something from the device,
		// could be very inaccurate though
		currentLocation = locationManager.getLastKnownLocation(locationManager
				.getBestProvider(fine, true));

		if (listenerFine == null || listenerCoarse == null)
			createLocationListeners();

		// Will keep updating about every 500 ms until
		// accuracy is about 1000 meters to get quick fix.
		locationManager.requestLocationUpdates(
				locationManager.getBestProvider(coarse, true), 0, 0,
				listenerCoarse);
		// Will keep updating about every 500 ms until
		// accuracy is about 50 meters to get accurate fix.
		locationManager.requestLocationUpdates(
				locationManager.getBestProvider(fine, true), 60000, 0,
				listenerFine);
	}

	*//**
	 * Creates LocationListeners
	 *//*
	private void createLocationListeners() {
		listenerCoarse = new LocationListener() {
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				switch (status) {
				case LocationProvider.OUT_OF_SERVICE:
				case LocationProvider.TEMPORARILY_UNAVAILABLE:
					locationAvailable = false;
					break;
				case LocationProvider.AVAILABLE:
					locationAvailable = true;
				}
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}

			public void onLocationChanged(Location location) {
				currentLocation = location;
				if (location.getAccuracy() > 1000 && location.hasAccuracy())
					locationManager.removeUpdates(this);
				if (location != null) {
					p = new GeoPoint((int) (location.getLatitude() * 1E6),
							(int) (location.getLongitude() * 1E6));
					mc = mapView.getController();

					// mapView.invalidate();
					lat = location.getLatitude();
					lng = location.getLongitude();
					lat1 = (float) location.getLatitude();
					lng1 = (float) location.getLongitude();
					latLongString = "Lat:" + lat + "\nLong:" + lng;
					latitude = location.getLatitude();
					longitude = location.getLongitude();

					mc.animateTo(p);

					
					 * mapOverlays = mapView.getOverlays();
					 * 
					 * 
					 * // engine.setWebChromeClient(new WebChromeClient()); //
					 * engine.setWebViewClient(new WebViewClient()); //
					 * engine.getSettings().setAllowFileAccess(true);
					 * 
					 * String Latitude = Double.toString(latitude); String
					 * Longitude= Double.toString(longitude);
					 * 
					 * 
					 * 
					 * //MediaController mediaController = new
					 * MediaController(this);
					 * 
					 * // engine.loadUrl("http://profit.ndtv.com/video/livetv");
					 * 
					 * 
					 * // lm = (LocationManager) //
					 * getSystemService(Context.LOCATION_SERVICE);
					 * 
					 * String result = null; InputStream is = null;
					 * StringBuilder sb=null; StrictMode.ThreadPolicy policy =
					 * new StrictMode.
					 * ThreadPolicy.Builder().permitAll().build();
					 * StrictMode.setThreadPolicy(policy);
					 * 
					 * ArrayList<NameValuePair> nameValuePairs = new
					 * ArrayList<NameValuePair>(2); // double total = 44;
					 * 
					 * //nameValuePairs.add(new
					 * BasicNameValuePair("Device_Id","173"));
					 * nameValuePairs.add(new
					 * BasicNameValuePair("lat",Latitude));
					 * nameValuePairs.add(new
					 * BasicNameValuePair("lng",Longitude)); try{ HttpClient
					 * httpclient = new DefaultHttpClient(); HttpPost httppost =
					 * new HttpPost("http://gotabbie.com/flash1733/mark.php");
					 * httppost.setEntity(new
					 * UrlEncodedFormEntity(nameValuePairs)); HttpResponse
					 * response = httpclient.execute(httppost); HttpEntity
					 * entity = response.getEntity(); is = entity.getContent();
					 * }catch(Exception e){ Log.e("log_tag",
					 * "Error in http connection"+e.toString()); } try{
					 * BufferedReader reader = new BufferedReader(new
					 * InputStreamReader(is,"iso-8859-1"),8); sb = new
					 * StringBuilder(); sb.append(reader.readLine() + "\n");
					 * String line="0"; while ((line = reader.readLine()) !=
					 * null) { sb.append(line + "\n"); // line =
					 * reader.readLine(); } is.close(); result=sb.toString();
					 * }catch(Exception e){ Log.e("log_tag",
					 * "Error converting result "+e.toString()); } JSONArray
					 * jArray; try{
					 * 
					 * jArray = new JSONArray(result); JSONObject json_data;
					 * lat11=new Double[jArray.length()]; lng11=new
					 * Double[jArray.length()]; ct_name=new
					 * String[jArray.length()]; int i=0;
					 * for(i=0;i<jArray.length();i++){
					 * 
					 * 
					 * 
					 * json_data = jArray.getJSONObject(i);
					 * ct_id=json_data.getInt("id");
					 * lat11[i]=json_data.getDouble("lat");
					 * 
					 * lng11[i]=json_data.getDouble("lng");
					 * ct_name[i]=json_data.getString("name"); marker_point =
					 * new GeoPoint((int)(lat11[i]*1E6),(int)(lng11[i]*1E6));
					 * 
					 * drawable = getResources().getDrawable(R.drawable.bags);
					 * itemizedOverlay123 = new MyItemizedOverlay(drawable,
					 * mapView);
					 * 
					 * OverlayItem overlayItem = new OverlayItem(marker_point,
					 * "Point"+i, "hii");
					 * itemizedOverlay123.addOverlay(overlayItem);
					 * mapView.getOverlays().add(itemizedOverlay123);
					 * mapView.invalidate();
					 * 
					 * } } catch(JSONException e1){
					 * Toast.makeText(getBaseContext(), "No City Found"
					 * ,Toast.LENGTH_LONG).show(); } catch (ParseException e1) {
					 * e1.printStackTrace(); } catch(NullPointerException e){
					 * e.printStackTrace(); } try{
					 * lv=(ListView)findViewById(R.id.list);
					 * ArrayAdapter<String> adapter = new
					 * ArrayAdapter<String>(AndyActivity.this,
					 * android.R.layout.simple_list_item_1,ct_name);
					 * 
					 * lv.setAdapter(adapter); } catch(NullPointerException e) {
					 * e.printStackTrace(); } //;
					 

					
					 * StrictMode.ThreadPolicy policy = new StrictMode.
					 * ThreadPolicy.Builder().permitAll().build();
					 * StrictMode.setThreadPolicy(policy);
					 * 
					 * ArrayList<NameValuePair> nameValuePairs = new
					 * ArrayList<NameValuePair>(2); // double total = 44; String
					 * lat = Double.toString(latitude); String lng=
					 * Double.toString(longitude); // nameValuePairs.add(new
					 * BasicNameValuePair("Device_Id","173"));
					 * nameValuePairs.add(new BasicNameValuePair("lat",lat));
					 * nameValuePairs.add(new BasicNameValuePair("lng",lng));
					 * try{ HttpClient httpclient = new DefaultHttpClient();
					 * HttpPost httppost = new
					 * HttpPost("http://gotabbie.com/tabbie/mark.php");
					 * httppost.setEntity(new
					 * UrlEncodedFormEntity(nameValuePairs)); HttpResponse
					 * response = httpclient.execute(httppost); HttpEntity
					 * entity = response.getEntity(); InputStream is =
					 * entity.getContent(); Log.i("postData and marker",
					 * response.getStatusLine().toString()); //
					 * Toast.makeText(this,"send", Toast.LENGTH_LONG); }
					 * 
					 * catch(Exception e) { Log.e("log_tag",
					 * "Error in http connection "+e.toString()); } try{
					 * BufferedReader reader = new BufferedReader(new
					 * InputStreamReader(is,"iso-8859-1"),8); sb = new
					 * StringBuilder(); sb.append(reader.readLine() + "\n");
					 * String line="0"; while ((line = reader.readLine()) !=
					 * null) { sb.append(line + "\n"); // line =
					 * reader.readLine(); } is.close(); result=sb.toString();
					 * }catch(Exception e){ Log.e("log_tag",
					 * "Error converting result "+e.toString()); } JSONArray
					 * jArray; try{
					 * 
					 * jArray = new JSONArray(result); JSONObject json_data;
					 * lat11=new Double[jArray.length()]; lng11=new
					 * Double[jArray.length()]; ct_name=new
					 * String[jArray.length()]; int i=0;
					 * for(i=0;i<jArray.length();i++){
					 * 
					 * 
					 * 
					 * json_data = jArray.getJSONObject(i);
					 * ct_id=json_data.getInt("id");
					 * lat11[i]=json_data.getDouble("lat");
					 * 
					 * lng11[i]=json_data.getDouble("lng");
					 * ct_name[i]=json_data.getString("name"); marker_point =
					 * new GeoPoint((int)(lat11[i]*1E6),(int)(lng11[i]*1E6));
					 * 
					 * drawable = getResources().getDrawable(R.drawable.
					 * balloon_overlay_unfocused); itemizedOverlay123 = new
					 * MyItemizedOverlay(drawable, mapView);
					 * 
					 * OverlayItem overlayItem = new OverlayItem(marker_point,
					 * "Point"+i, "hii");
					 * itemizedOverlay123.addOverlay(overlayItem);
					 * mapView.getOverlays().add(itemizedOverlay123);
					 * mapView.invalidate();
					 * 
					 * } } catch(JSONException e1){
					 * Toast.makeText(getBaseContext(), "No City Found"
					 * ,Toast.LENGTH_LONG).show(); } catch (ParseException e1) {
					 * e1.printStackTrace(); }
					 * lv=(ListView)findViewById(R.id.list);
					 * ArrayAdapter<String> adapter = new
					 * ArrayAdapter<String>(AndyActivity.this,
					 * android.R.layout.simple_list_item_1,ct_name);
					 * 
					 * lv.setAdapter(adapter);
					 

					Geocoder gc = new Geocoder(AndyGPS.this,
							Locale.getDefault());
					try {
						List<Address> addresses = gc.getFromLocation(latitude,
								longitude, 1);
						StringBuilder sb1 = new StringBuilder();
						if (addresses.size() > 0) {
							Address address = addresses.get(0);
							for (int i = 0; i < address
									.getMaxAddressLineIndex(); i++)
								sb1.append(address.getAddressLine(i)).append(
										"\n");
							sb1.append(address.getLocality()).append("\n");
							// sb.append(address.getPostalCode()).append("\n");
							sb1.append(address.getCountryName());
							mapView.invalidate();
						}
						addressString = sb1.toString();
					} catch (IOException e) {
					}
				} else {
					latLongString = "No location found...Please wait";

				}
				
				 * Toast.makeText(getBaseContext(), "Location changed: Lat: " +
				 * addressString,
				 * 
				 * Toast.LENGTH_SHORT).show();
				 

				// Add a location marker
				MapOverlay mapOverlay = new MapOverlay();
				List<Overlay> listofOverlays = mapView.getOverlays();
				// listofOverlays.clear();
				listofOverlays.add(mapOverlay);
				// //geocoding
				mapView.getOverlays().add(me);
				mc.animateTo(p);

				// mapView.invalidate();
				// invalidate() method forces the MapView to be redrawn
				// mapView.invalidate();
				mapView.invalidate();
			}
		};
		listenerFine = new LocationListener() {
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				switch (status) {
				case LocationProvider.OUT_OF_SERVICE:
				case LocationProvider.TEMPORARILY_UNAVAILABLE:
					locationAvailable = false;
					break;
				case LocationProvider.AVAILABLE:
					locationAvailable = true;
				}
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}

			public void onLocationChanged(Location location) {
				currentLocation = location;
				if (location.getAccuracy() > 1000 && location.hasAccuracy())
					locationManager.removeUpdates(this);
				if (location != null) {
					p = new GeoPoint((int) (location.getLatitude() * 1E6),
							(int) (location.getLongitude() * 1E6));
					mc = mapView.getController();
					mc.animateTo(p);
					// mapView.invalidate();
					lat = location.getLatitude();
					lng = location.getLongitude();
					lat1 = (float) location.getLatitude();
					lng1 = (float) location.getLongitude();
					latLongString = "Lat:" + lat + "\nLong:" + lng;
					latitude = location.getLatitude();
					longitude = location.getLongitude();
					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
					StrictMode.setThreadPolicy(policy);
					
					 * String Latitude = Double.toString(latitude); String
					 * Longitude= Double.toString(longitude);
					 * 
					 * 
					 * 
					 * 
					 * String result = null; InputStream is = null;
					 * StringBuilder sb=null;
					 * 
					 * 
					 * ArrayList<NameValuePair> nameValuePairs = new
					 * ArrayList<NameValuePair>(2); // double total = 44;
					 * 
					 * //nameValuePairs.add(new
					 * BasicNameValuePair("Device_Id","173"));
					 * nameValuePairs.add(new
					 * BasicNameValuePair("lat",Latitude));
					 * nameValuePairs.add(new
					 * BasicNameValuePair("lng",Longitude)); try{ HttpClient
					 * httpclient = new DefaultHttpClient(); HttpPost httppost =
					 * new HttpPost("http://gotabbie.com/flash1733/mark.php");
					 * httppost.setEntity(new
					 * UrlEncodedFormEntity(nameValuePairs)); HttpResponse
					 * response = httpclient.execute(httppost); HttpEntity
					 * entity = response.getEntity(); is = entity.getContent();
					 * }catch(Exception e){ Log.e("log_tag",
					 * "Error in http connection"+e.toString()); } try{
					 * BufferedReader reader = new BufferedReader(new
					 * InputStreamReader(is,"iso-8859-1"),8); sb = new
					 * StringBuilder(); sb.append(reader.readLine() + "\n");
					 * String line="0"; while ((line = reader.readLine()) !=
					 * null) { sb.append(line + "\n"); // line =
					 * reader.readLine(); } is.close(); result=sb.toString();
					 * }catch(Exception e){ Log.e("log_tag",
					 * "Error converting result "+e.toString()); } JSONArray
					 * jArray; try{
					 * 
					 * jArray = new JSONArray(result); JSONObject json_data;
					 * lat11=new Double[jArray.length()]; lng11=new
					 * Double[jArray.length()]; ct_name=new
					 * String[jArray.length()]; int i=0;
					 * for(i=0;i<jArray.length();i++){
					 * 
					 * 
					 * 
					 * json_data = jArray.getJSONObject(i);
					 * ct_id=json_data.getInt("id");
					 * lat11[i]=json_data.getDouble("lat");
					 * 
					 * lng11[i]=json_data.getDouble("lng");
					 * ct_name[i]=json_data.getString("name"); marker_point =
					 * new GeoPoint((int)(lat11[i]*1E6),(int)(lng11[i]*1E6));
					 * 
					 * drawable = getResources().getDrawable(R.drawable.bags);
					 * itemizedOverlay123 = new MyItemizedOverlay(drawable,
					 * mapView);
					 * 
					 * OverlayItem overlayItem = new OverlayItem(marker_point,
					 * "Point"+i, "hii");
					 * itemizedOverlay123.addOverlay(overlayItem);
					 * mapView.getOverlays().add(itemizedOverlay123);
					 * mapView.invalidate();
					 * 
					 * } } catch(JSONException e1){
					 * Toast.makeText(getBaseContext(), "No City Found"
					 * ,Toast.LENGTH_LONG).show(); } catch (ParseException e1) {
					 * e1.printStackTrace(); } try{
					 * lv=(ListView)findViewById(R.id.list);
					 * ArrayAdapter<String> adapter = new
					 * ArrayAdapter<String>(AndyActivity.this,
					 * android.R.layout.simple_list_item_1,ct_name);
					 * 
					 * lv.setAdapter(adapter); }catch(NullPointerException e) {
					 * e.printStackTrace(); }
					 

					
					  ArrayList<NameValuePair> nameValuePairs1 = new
					  ArrayList<NameValuePair>(3); // double total = 44;
					  String Latitude1 = Double.toString(latitude); 
					  String Longitude1=Double.toString(longitude); 
					  nameValuePairs1.add(new BasicNameValuePair("Device_Id","DEVICE"));
					  nameValuePairs1.add(new BasicNameValuePair("Latitude",Latitude1));
					  nameValuePairs1.add(new BasicNameValuePair("Longitude",Longitude1));
					  try{
					  HttpClient httpclient = new DefaultHttpClient(); 
					  HttpPost httppost = new HttpPost("http://gotabbie.com/tabbieOld/insert.php");
					  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs1)); 
					  HttpResponse response = httpclient.execute(httppost);
					  HttpEntity entity = response.getEntity(); 
					  InputStream is3 = entity.getContent();
					  Log.i("postData",response.getStatusLine().toString()); //
					  Toast.makeText(AndyGPS.this,"send", Toast.LENGTH_LONG); }
					  
					  catch(Exception e) { Log.e("log_tag", "Error in http connection "+e.toString()); 
					  }
					 

					Geocoder gc = new Geocoder(AndyGPS.this,
							Locale.getDefault());
					try {
						List<Address> addresses = gc.getFromLocation(latitude,
								longitude, 1);
						StringBuilder sb3 = new StringBuilder();
						if (addresses.size() > 0) {
							Address address = addresses.get(0);
							for (int i = 0; i < address
									.getMaxAddressLineIndex(); i++)
								sb3.append(address.getAddressLine(i)).append(
										"\n");
							sb3.append(address.getLocality()).append("\n");
							// sb.append(address.getPostalCode()).append("\n");
							sb3.append(address.getCountryName());
							mapView.invalidate();

						}
						addressString = sb3.toString();
					} catch (IOException e) {
					}
				} else {
					latLongString = "No location found...Please wait";

				}
				
				 * Toast.makeText(getBaseContext(), "Location changed: Lat: " +
				 * addressString,
				 * 
				 * Toast.LENGTH_SHORT).show();
				 

				mc.animateTo(p);
				// mc.setZoom(14);

				// Add a location marker
				MapOverlay mapOverlay = new MapOverlay();
				List<Overlay> listofOverlays = mapView.getOverlays();
				// listofOverlays.clear();
				listofOverlays.add(mapOverlay);
				// //geocoding
				mapView.getOverlays().add(me);
				mc.animateTo(p); // mc.animateTo(centerGp);

				// mapView.invalidate();
				// invalidate() method forces the MapView to be redrawn
				// mapView.invalidate();
				mapView.invalidate();
			}

		};
	}*/

	public void Trainrotate(int a, int b, float x, float y) {
		x = main.getWidth() / 2.0f;
		y = main.getHeight() / 2.0f;
		RotateAnimation RAnimation = new RotateAnimation(a, b, x, y);
		RAnimation.setInterpolator(new AccelerateInterpolator());
		RAnimation.setDuration(2000);
		RAnimation.setFillAfter(true);
		vf.startAnimation(RAnimation);
	}

	void applyRotation(float start, float end) {
		// Find the center of image
		final float centerX = main.getWidth() / 2.0f;
		final float centerY = main.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
				centerX, centerY);
		rotation.setDuration(800);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(isFirstImage, main,
				idea));

		if (isFirstImage) {// main.setAnimation(AnimationUtils.loadAnimation(AndyActivity.this,
							// R.anim.hyper));
							// overridePendingTransition(rotation);

			main.startAnimation(rotation);
		} else {
			idea.startAnimation(rotation);
		}

	}

	/*
	 * private void overridePendingTransition(Flip3dAnimation rotation) { //
	 * TODO Auto-generated method stub
	 * 
	 * }
	 */
	/*
	 * private class MyLocationListener implements LocationListener {String
	 * addressString; String latLongString; public void
	 * onLocationChanged(Location loc) { if (loc != null) { p = new GeoPoint(
	 * (int) (loc.getLatitude() * 1E6), (int) (loc.getLongitude() * 1E6));
	 * 
	 * //mapView.invalidate(); lat = loc.getLatitude(); lng =
	 * loc.getLongitude(); lat1=(float) loc.getLatitude(); lng1=(float)
	 * loc.getLongitude(); latLongString = "Lat:" + lat + "\nLong:" + lng;
	 * double latitude = loc.getLatitude(); double longitude =
	 * loc.getLongitude(); Geocoder gc = new Geocoder(AndyActivity.this,
	 * Locale.getDefault()); try { List<Address> addresses =
	 * gc.getFromLocation(latitude, longitude, 1); StringBuilder sb = new
	 * StringBuilder(); if (addresses.size() > 0) { Address address =
	 * addresses.get(0); for (int i = 0; i < address.getMaxAddressLineIndex();
	 * i++) sb.append(address.getAddressLine(i)).append("\n");
	 * sb.append(address.getLocality()).append("\n"); //
	 * sb.append(address.getPostalCode()).append("\n");
	 * sb.append(address.getCountryName()); mapView.invalidate(); }
	 * addressString = sb.toString(); } catch (IOException e) {} } else {
	 * latLongString = "No location found...Please wait";
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } Toast.makeText(getBaseContext(), "Location changed: Lat: " +
	 * addressString,
	 * 
	 * Toast.LENGTH_SHORT).show();
	 * 
	 * 
	 * 
	 * // mc.animateTo(p); // mc.setZoom(14);
	 * 
	 * // Add a location marker MapOverlay mapOverlay = new MapOverlay();
	 * List<Overlay> listofOverlays = mapView.getOverlays(); //
	 * listofOverlays.clear(); listofOverlays.add(mapOverlay); ////geocoding
	 * mapView.getOverlays().add(me); //mc.animateTo(centerGp);
	 * 
	 * // mapView.invalidate(); // invalidate() method forces the MapView to be
	 * redrawn // mapView.invalidate(); mapView.invalidate(); } //geocoding
	 * 
	 * public void onProviderDisabled(String provider){
	 * Toast.makeText(getBaseContext(), "GPS is correctly unavailabe....",
	 * Toast.LENGTH_LONG); }
	 * 
	 * public void onProviderEnabled(String provider){
	 * Toast.makeText(getBaseContext(),
	 * "GPS is acquiring your location",Toast.LENGTH_LONG); }
	 * 
	 * public void onStatusChanged(String provider, int status, Bundle extras){
	 * Toast.makeText(getBaseContext(),
	 * "Capturing location..please wait",Toast.LENGTH_LONG);
	 * 
	 * } }
	 */
	/*
	 * @Override public boolean onTouchEvent (MotionEvent ev){ if(ev.getAction()
	 * == MotionEvent.ACTION_DOWN){ if(ndtv.isPlaying()){ ndtv.pause(); } else {
	 * ndtv.start(); } return true; } else { return false; } }
	 */
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	class MapOverlay extends com.google.android.maps.Overlay {
		private GeoPoint point;
		private Paint paint1, paint2;

		public boolean onTap(GeoPoint p, final MapView mapView) {
			// TODO Auto-generated method stub

			// mapOverlays = mapView.getOverlays();
			// drawable3 = getResources().getDrawable(R.drawable.bags);
			// itemizedOverlay3 = new MyItemizedOverlay(drawable3, mapView);
			//
			// /* k = p;
			// mc= mapView.getController();
			// mc.animateTo(p);
			// Double latitude;
			// Double longitude;
			// mapView.invalidate();
			// latitude=p.getLatitudeE6() / 1E6;
			// longitude=p.getLongitudeE6() /1E6 ;*/
			// ////
			//
			//
			// GeoPoint point3 = new GeoPoint(p.getLatitudeE6(),
			// p.getLongitudeE6());
			// OverlayItem overlayItem3 = new OverlayItem(point3,
			// "Is this your destination" , "Good");
			//
			//
			// itemizedOverlay3.addOverlay(overlayItem3);
			//
			// mapOverlays.add(itemizedOverlay3);
			// mapView.invalidate();
			//
			//
			//
			// Toast.makeText(getBaseContext(),
			// "Location changed: Lat: " + p.getLatitudeE6() +
			// " Lng: " + p.getLongitudeE6(),
			// Toast.LENGTH_SHORT).show();
			// new AlertDialog.Builder(AndyActivity.this)
			// .setTitle("Please select destination-point ")
			// .setMessage("Is this your drop-point ?")
			// .setNegativeButton("NO",
			// new DialogInterface.OnClickListener() {
			//
			// @Override
			// public void onClick(DialogInterface dialog,
			// int which) {
			// // TODO Auto-generated method stub
			// mapOverlays.remove(itemizedOverlay3);
			// dialog.dismiss();
			//
			// }
			// })
			// .setPositiveButton("YES",
			// new DialogInterface.OnClickListener() {
			//
			// @Override
			// public void onClick(DialogInterface dialog,
			// int which) {
			// // TODO Auto-generated method stub
			//
			// }
			// }).show();
			//
			//
			//
			return super.onTap(p, mapView);

		}

		public void add(MapOverlay mapOverlay) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {/* get center point */
			super.draw(canvas, mapView, shadow);

			centerGp = mapView.getMapCenter();
			Projection projection = mapView.getProjection();
			Point centerPoint = projection.toPixels(centerGp, null);
			Paint p2 = new Paint();
			// Bitmap bmp2 = BitmapFactory.decodeResource(getResources(),
			// R.drawable.marker);
			// canvas.drawBitmap(bmp2, centerPoint.x, centerPoint.y, p2);

			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(Color.BLUE);
			canvas.drawCircle((int) (18.980849 * 1E6), (int) (72.826871 * 1E6),
					25, paint);

			/*
			 * Point pt = new Point(); projection.toPixels(p, pt); Point pt1 =
			 * new Point(); projection.toPixels(maxSizeAreaGP, pt1); float
			 * circleRadius = (float)Math.sqrt(Math.pow(pt1.x-pt.x,
			 * 2)+Math.pow(pt1.y-pt.y, 2)); Paint circlePaint = new
			 * Paint(Paint.ANTI_ALIAS_FLAG); circlePaint.setColor(0x0000cd);
			 * circlePaint.setStyle(Style.FILL_AND_STROKE);
			 * canvas.drawCircle((float) pt.x, (float) pt.y, circleRadius,
			 * circlePaint); circlePaint.setColor(0xff6666ff);
			 * circlePaint.setStyle(Style.STROKE); canvas.drawCircle((float)
			 * pt.x, (float) pt.y, circleRadius, circlePaint); Bitmap
			 * markerBitmap = BitmapFactory.decodeResource(
			 * getApplicationContext().getResources(), R.drawable.marker);
			 * canvas.drawBitmap(markerBitmap, pt.x, pt.y -
			 * markerBitmap.getHeight(), null); super.draw(canvas, mapView,
			 * shadow);
			 */

			/* get circle */

			/* get location marker */

			Point screenPts = new Point();
			mapView.getProjection().toPixels(p, screenPts);

			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.balloon_overlay_focused);
			canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 48, null);
			// Double markerHeight = ((BitmapDrawable)
			// defaultMarker).getBitmap().getHeight();

			if (k != null) {
				// ---translate the GeoPoint to screen pixels---
				Point screenPts1 = new Point();
				mapView.getProjection().toPixels(k, screenPts1);

				// ---add the marker---
				bmp1 = BitmapFactory.decodeResource(getResources(),
						R.drawable.icon);
				canvas.drawBitmap(bmp1, screenPts1.x - 10, screenPts1.y - 34,
						null);

			}

			return true;

		}

	}

	/*public void onStatusChanged(String provider, int status, Bundle extras) {
		switch (status) {
		case LocationProvider.OUT_OF_SERVICE:
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			locationAvailable = false;
			break;
		case LocationProvider.AVAILABLE:
			locationAvailable = true;
		}
	}

	public void onProviderEnabled(String provider) {
	}

	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onLocationChanged(Location location) {
		currentLocation = location;
		if (location.getAccuracy() > 1000 && location.hasAccuracy())
			locationManager.removeUpdates(this);
		if (location != null) {
			p = new GeoPoint((int) (location.getLatitude() * 1E6),
					(int) (location.getLongitude() * 1E6));
			mc = mapView.getController();
			mc.animateTo(p);
			// mapView.invalidate();
			lat = location.getLatitude();
			lng = location.getLongitude();
			lat1 = (float) location.getLatitude();
			lng1 = (float) location.getLongitude();
			latLongString = "Lat:" + lat + "\nLong:" + lng;
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();

			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);

			
			 * ArrayList<NameValuePair> nameValuePairs = new
			 * ArrayList<NameValuePair>(3); // double total = 44; String
			 * Latitude = Double.toString(latitude); String Longitude=
			 * Double.toString(longitude); nameValuePairs.add(new
			 * BasicNameValuePair("Device_Id","173")); nameValuePairs.add(new
			 * BasicNameValuePair("Latitude",Latitude)); nameValuePairs.add(new
			 * BasicNameValuePair("Longitude",Longitude)); try{ HttpClient
			 * httpclient = new DefaultHttpClient(); HttpPost httppost = new
			 * HttpPost("http://gotabbie.com/tabbie/insert.php");
			 * httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			 * HttpResponse response = httpclient.execute(httppost); HttpEntity
			 * entity = response.getEntity(); InputStream is =
			 * entity.getContent(); Log.i("postData",
			 * response.getStatusLine().toString()); //
			 * Toast.makeText(this,"send", Toast.LENGTH_LONG); }
			 * 
			 * catch(Exception e) { Log.e("log_tag",
			 * "Error in http connection "+e.toString()); }
			 
			Geocoder gc = new Geocoder(AndyGPS.this, Locale.getDefault());
			try {
				List<Address> addresses = gc.getFromLocation(latitude,
						longitude, 1);
				StringBuilder sb = new StringBuilder();
				if (addresses.size() > 0) {
					Address address = addresses.get(0);
					for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
						sb.append(address.getAddressLine(i)).append("\n");
					sb.append(address.getLocality()).append("\n");
					// sb.append(address.getPostalCode()).append("\n");
					sb.append(address.getCountryName());
					mapView.invalidate();

				}
				addressString = sb.toString();
			} catch (IOException e) {
			}
		} else {
			latLongString = "No location found...Please wait";

		}
		
		 * Toast.makeText(getBaseContext(), "Location changed: Lat: " +
		 * addressString,
		 * 
		 * Toast.LENGTH_SHORT).show();
		 

		// mc.animateTo(p);
		// mc.setZoom(14);

		// Add a location marker
		MapOverlay mapOverlay = new MapOverlay();
		List<Overlay> listofOverlays = mapView.getOverlays();
		// listofOverlays.clear();
		listofOverlays.add(mapOverlay);
		// //geocoding
		mapView.getOverlays().add(me);
		mc.animateTo(p);

		// mapView.invalidate();
		// invalidate() method forces the MapView to be redrawn
		// mapView.invalidate();
		mapView.invalidate();
	}*/

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		AnimationFactory.flipTransition(com.maps.andy.AndyGPS.vfmaps,
				FlipDirection.RIGHT_LEFT);
		vfmaps.setDisplayedChild(1);

	}

	/*
	 * @Override public void onPrepared(MediaPlayer arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * // if(ndtv.canSeekForward()) ndtv.seekTo(ndtv.getDuration()/5);
	 * ndtv.start(); }
	 */

	/*
	 * @Override public void onItemClick(AdapterView<?> parent, View v, int
	 * position, long id) {
	 * 
	 * switch (position) { case 0:
	 * 
	 * AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf,
	 * FlipDirection.RIGHT_LEFT); vf.setDisplayedChild(1); engine.scrollBy(300,
	 * 800); engine.scrollTo(300, 800); mapView.postInvalidate(); break; case 1:
	 * 
	 * AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf,
	 * FlipDirection.RIGHT_LEFT); vf.setDisplayedChild(2); engine.scrollBy(300,
	 * 800); engine.scrollTo(300, 800); mapView.postInvalidate();
	 * 
	 * break; case 2:
	 * AnimationFactory.flipTransition(com.maps.andy.AndyActivity.vf,
	 * FlipDirection.RIGHT_LEFT); vf.setDisplayedChild(0); engine.scrollBy(300,
	 * 800); engine.scrollTo(300, 800); mapView.postInvalidate(); break;
	 * default:
	 * 
	 * break; }
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	private class SubmitCommentTask extends AsyncTask<String, Void, Void> {
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
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
			/*
			 * MediaController mediaController = new
			 * MediaController(AndyActivity.this);
			 * mediaController.setAnchorView(video);
			 * ndtv.setMediaController(mediaController);
			 * ndtv.setKeepScreenOn(true); ndtv.setVideoPath(httpLiveUrl22);
			 * ndtv.start(); ndtv.requestFocus(); dialog.dismiss();
			 */
			
		}
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub\
		for (int i = 0; i < l2.getChildCount(); i++) {
			View view = l2.getChildAt(i);
			view.setEnabled(true);

		}

		for (int i = 0; i < l3.getChildCount(); i++) {
			View view = l3.getChildAt(i);
			view.setEnabled(true);

		}
		for (int i = 0; i < l4.getChildCount(); i++) {
			View view = l4.getChildAt(i);
			view.setEnabled(true);

		}
		// play.setEnabled(true);
		l2 = (LinearLayout) findViewById(R.id.layout_top);
		// l3=(LinearLayout)findViewById(R.id.firstscreen);
		l4 = (LinearLayout) findViewById(R.id.mainlayout_side);
		l5 = (LinearLayout) findViewById(R.id.mainlayout_BOTTOM1);
		l6 = (LinearLayout) findViewById(R.id.mainlayout_BOTTOM);

		l2.setVisibility(View.VISIBLE);
		l4.setVisibility(View.VISIBLE);
		l5.setVisibility(View.VISIBLE);
		l6.setVisibility(View.VISIBLE);
		//back1.setVisibility(View.VISIBLE);
		l2.setEnabled(true);
		mute.setVisibility(View.VISIBLE);
		// front.setVisibility(View.VISIBLE);
		vf.setDisplayedChild(2);

	}
}
