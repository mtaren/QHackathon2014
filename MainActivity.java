package com.example.screenshot;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
  
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		//simulateEventDown(getWindow().getDecorView().findViewById(android.R.id.content), 150, 50);
		Log.d("main", "Start of main1");
		
		
		/*
		ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
		 ClipData clip = ClipData.newPlainText("label", "Text to copy");
		 clipboard.setPrimaryClip(clip);
		 
		 */
		//Bitmap bitmap = takeScreenshot();
		
		/* START HERE */
		
		Log.d("main", "reached end of main1");	
	
	}
	

	 

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void btnStart(View v){
		Log.d("main", "StartPressed");
		//simulateEventDown(getWindow().getDecorView().findViewById(android.R.id.content), 150, 50);
		return;
	}

	public void btnStop(View v){
		Log.d("main", "StopPressed");
		//simulateEventDown(getWindow().getDecorView().findViewById(android.R.id.content), 250, 150);
		return;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	

}
