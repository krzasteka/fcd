//package com.example.flashcarddispute;
//
//
//import java.util.Calendar;
//import java.util.Date;
//
//import android.app.Activity;
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.app.DialogFragment;
//import android.app.TimePickerDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//
//import android.widget.EditText;
//
//import android.widget.TextView;
//
//
//public class AddToDoActivity extends Activity {
//	
//	// 7 days in milliseconds - 7 * 24 * 60 * 60 * 1000
//	private static final int SEVEN_DAYS = 604800000;
//
//	private static final String TAG = "Lab-UserInterface";
//
//	private static String timeString;
//	private static String dateString;
//	private static TextView dateView;
//	private static TextView timeView;
//
//	
//	private Date mDate;
//	
//	private EditText mTitleText;
//
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.add_todo);
//
//		mTitleText = (EditText) findViewById(R.id.title);
//	
//		dateView = (TextView) findViewById(R.id.date);
//		timeView = (TextView) findViewById(R.id.time);
//
//		// Set the default date and time
//
//		setDefaultDateTime();
//
//		// OnClickListener for the Date button, calls showDatePickerDialog() to show
//		// the Date dialog
//
//		
//		// OnClickListener for the Cancel Button, 
//
//		final Button cancelButton = (Button) findViewById(R.id.cancelButton);
//		cancelButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				log("Entered cancelButton.OnClickListener.onClick()");
//
//				//TODO - Implement onClick().  
//		
//				setResult(RESULT_CANCELED,new Intent());
//				finish();
//
//
//			}
//		});
//
//		//OnClickListener for the Reset Button
//
//		final Button resetButton = (Button) findViewById(R.id.resetButton);
//		resetButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				log("Entered resetButton.OnClickListener.onClick()");
//
//				//TODO - Reset data fields to default values
//				
//
//				mTitleText.setText(""); 
//				
//			
//				setDefaultDateTime();
//			
//			
//			
//			}
//		});
//
//		// OnClickListener for the Submit Button
//		// Implement onClick().
//		
//		final Button submitButton = (Button) findViewById(R.id.submitButton);
//		submitButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				log("Entered submitButton.OnClickListener.onClick()");
//
//				// Gather ToDoItem data  
//				
//
//				//TODO -  Title
//				String titleString = mTitleText.getText().toString();
//
//				// Date
//				String fullDate = dateString + " " + timeString;
//
//				// Package ToDoItem data into an Intent
//				Intent data = new Intent();
//				ToDoItem.packageIntent(data, titleString, fullDate);
//
//				//TODO - return data Intent and finish
//				setResult(RESULT_OK,data);
//				finish();
//				
//
//				
//				
//			}
//		});
//	}
//
//	// Do not modify below here
//	
//	// Use this method to set the default date and time
//	
//	private void setDefaultDateTime() {
//
//		// Default is current time + 7 days
//		mDate = new Date();
//		mDate = new Date(mDate.getTime() + SEVEN_DAYS);
//
//		Calendar c = Calendar.getInstance();
//		c.setTime(mDate);
//
//		setDateString(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
//				c.get(Calendar.DAY_OF_MONTH));
//
//		dateView.setText(dateString);
//
//		setTimeString(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
//				c.get(Calendar.MILLISECOND));
//
//		timeView.setText(timeString);
//	}
//
//	private static void setDateString(int year, int monthOfYear, int dayOfMonth) {
//
//		// Increment monthOfYear for Calendar/Date -> Time Format setting
//		monthOfYear++;
//		String mon = "" + monthOfYear;
//		String day = "" + dayOfMonth;
//
//		if (monthOfYear < 10)
//			mon = "0" + monthOfYear;
//		if (dayOfMonth < 10)
//			day = "0" + dayOfMonth;
//
//		dateString = year + "-" + mon + "-" + day;
//	}
//
//	private static void setTimeString(int hourOfDay, int minute, int mili) {
//		String hour = "" + hourOfDay;
//		String min = "" + minute;
//
//		if (hourOfDay < 10)
//			hour = "0" + hourOfDay;
//		if (minute < 10)
//			min = "0" + minute;
//
//		timeString = hour + ":" + min + ":00";
//	}
//
//	
//
//
//	private void log(String msg) {
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		Log.i(TAG, msg);
//	}
//
//}
