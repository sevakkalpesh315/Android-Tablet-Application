package com.maps.andy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PostBootUp extends Activity{
	ImageView image_mappy, image_news, image_enter, image_sport, image_settings,
	cricket, football, ipl, tennis, btnLogout,imageFlipcart;
	LinearLayout li;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.id.postBootUp);
		
		li=(LinearLayout)findViewById(R.id.layout_middle);
		image_mappy = (ImageView) findViewById(R.id.imageView_channels1);
		image_news = (ImageView) findViewById(R.id.imageView_sports);
		image_enter = (ImageView) findViewById(R.id.imageView_stock);
		image_sport = (ImageView) findViewById(R.id.imageView_shop);
		image_settings = (ImageView) findViewById(R.id.imageView_map);
		
		
	}
	

}
