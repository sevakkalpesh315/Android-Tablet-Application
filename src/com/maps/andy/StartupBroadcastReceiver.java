package com.maps.andy;

import java.io.IOException;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.view.WindowManager;

public class StartupBroadcastReceiver extends BroadcastReceiver {

@Override
public void onReceive(Context context, Intent intent) {
	
	
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
		
	
Intent startupIntent = new Intent(context, BootUp.class); // substitute with your launcher class
startupIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
context.startActivity(startupIntent);
}

}