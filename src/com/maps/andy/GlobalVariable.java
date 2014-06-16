package com.maps.andy;

import android.app.Application;

public class GlobalVariable extends Application {

	  private int myScreen;
	  private int dev;
	  private int flag;
	  private int adsTime;
	  
	  private Integer[] adId;

	  public int getScreen(){
	    return myScreen;
	  }
	  public void setScreen(int s){
	    myScreen = s;
	  }
	  
	  public int getadsTime(){
		    return adsTime;
		  }
		  public void setadsTime(int ads){
			  adsTime = ads;
		  }
		  
	  
	  
	  public int getDevice(){
		    return dev;
		  }
		  public void setDevice(int d){
		    dev = d;
		  }
		  
		  public int getFlag(){
			    return flag;
			  }
			  public void setFlag(int f){
			    flag = f;
			  }
	  
	  public Integer[] getadId(){
		    return adId;
		  }
		  public void setadId(Integer[] ct_id){
		   adId = ct_id;
		  }
	}
