package com.maps.andy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class Idea extends Activity {
ImageView im;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.idea);
		im=(ImageView)findViewById(R.id.imageView11);
		
	}
	

}
