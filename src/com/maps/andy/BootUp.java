package com.maps.andy;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BootUp extends Activity{
	 private static final String TAG = "DialogActivity";
	    private static final int DLG_EXAMPLE1 = 0;
	    EditText input;
	    private static final int TEXT_ID = 0;
	    EditText text;
	ImageView adminBoot,tabbieBoot;
	TextView name;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.boot);
		adminBoot=(ImageView)findViewById(R.id.adminboot);
		tabbieBoot=(ImageView)findViewById(R.id.tabbieboot);
		//name=(TextView)findViewById(R.id.name);
		KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Activity.KEYGUARD_SERVICE);
		KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
		lock.disableKeyguard();
		
		PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = manager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Hiiiiiiiiii");
		wl.acquire();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		WindowManager.LayoutParams lp = getWindow().getAttributes();  
		lp.dimAmount=1.0f; 
		
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
			
			
		adminBoot.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				showDialog(DLG_EXAMPLE1);
				
			}
		});
		tabbieBoot.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent home=new Intent(BootUp.this,Police.class);
				startActivity(home);
			}
		});
		/* TimerTask task = new TimerTask() {

	            @Override
	            public void run() {
	                // TODO Auto-generated method stub
	                Intent intent = new Intent(BootUp.this, Police.class);
	            	
	 	  startActivity(intent);
	               
	               
	            }
	        };
	        Timer t = new Timer();
	        t.schedule(task, 10000);*/
	}
	
	/**
     * Called to create a dialog to be shown.
     */
    @Override
    protected Dialog onCreateDialog(int id) {
 
        switch (id) {
            case DLG_EXAMPLE1:
                return createExampleDialog();
            default:
                return null;
        }
    }
 
	/**
     * If a dialog has already been created,
     * this is called to reset the dialog
     * before showing it a 2nd time. Optional.
     */
    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
 
        switch (id) {
            case DLG_EXAMPLE1:
                // Clear the input box.
                 text = (EditText) dialog.findViewById(TEXT_ID);
                text.setText("");
                break;
        }
    }
	/**
     * Create and return an example alert dialog with an edit text box.
     */
    private Dialog createExampleDialog() {
 
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hello User");
        builder.setMessage("What is your name:");
 
         // Use an EditText view to get user input.
       input = new EditText(this);
         input.setId(TEXT_ID);
         builder.setView(input);
        
 
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
 
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                Log.d(TAG, "User name: " + value);
                if(value.equals("sevak")){
                	/*  Intent setIntent = new Intent(Intent.ACTION_MAIN); 
          	        setIntent.addCategory(Intent.CATEGORY_HOME); 
          	        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
          	        startActivity(setIntent); */
          	        finish();
                	
                	
                }else
                {
                	
                	//name.setText("Get out of the car...");
                }
                
                
                
                return;
            }
        });
 
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
 
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
 
        return builder.create();
    }
	

}
