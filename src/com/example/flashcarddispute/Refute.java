package com.example.flashcarddispute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Refute extends Activity {
	static final String TAG = "FCD";
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refute);

		/**** quick & dirty dev purposes allow http on main thread, move to AsyncTask later ****/
		StrictMode.ThreadPolicy policy = new StrictMode.
			    ThreadPolicy.Builder().permitAll().build();
			    StrictMode.setThreadPolicy(policy);
		/***************************************************************************************/
	Button addRefuteButton = (Button) findViewById(R.id.refute_add);
	Intent i = getIntent();
	final Question question = (Question) i.getSerializableExtra("question");
	
    String refutes = getRefutes(question.getId());
    try {
      JSONArray jsonArray = new JSONArray(refutes);
      Log.i(TAG,"JSONArray: " + String.valueOf(jsonArray != null));
      ArrayList<String> listdata = new ArrayList<String>();
      Log.i(TAG,"JSONArray: " + String.valueOf(jsonArray != null));
      if (jsonArray != null) { 
         for (int j=0;j< jsonArray.length();j++){ 
        	 listdata.add(jsonArray.get(j).toString());
        	 Log.i(TAG, jsonArray.get(j).toString());
         } 
      }

      ListView refuteList = (ListView) findViewById(R.id.refuteList);
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(Refute.this,
              android.R.layout.simple_list_item_1 , listdata  );
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      refuteList.setAdapter(adapter);
      
      Log.i(ParseException.class.getName(),
          "Number of entries " + jsonArray.length());
      for (int k = 0; k < jsonArray.length(); k++) {
        JSONObject jsonObject = jsonArray.getJSONObject(k);
        Log.i(ParseException.class.getName(), jsonObject.getString("text"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
	
	addRefuteButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			TextView refuteText = (TextView) findViewById(R.id.refute_text);
			addRefuteComment(question.getId(), refuteText.getText().toString());
		}
	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.refute, menu);
		return true;
	}
	
	public void addRefuteComment(long refId, String refText){
		
			String refIdString = String.valueOf(refId);
			String refTextEncoded = "ERROR";
			try {
				refTextEncoded = URLEncoder.encode(refText, "utf-8");
				Log.i(TAG, refTextEncoded);
			} catch (UnsupportedEncodingException e1) {
				Log.i(TAG, "addRefuteComment: URLEncoder UnsupportedEncodingException ");
				e1.printStackTrace();
			}
		    
		    HttpClient client = new DefaultHttpClient();
		    String url = ("http://54.83.12.144/refute_add.php?refute_id=" + refIdString + "&refute_text=" + refTextEncoded);
		    HttpGet httpGet = new HttpGet(url);
		    try {
		    	Log.i(TAG, "TRYING HTTP");
		        HttpResponse response = client.execute(httpGet); 
		        StatusLine statusLine = response.getStatusLine();
		        int statusCode = statusLine.getStatusCode();
		        if(statusCode == 200){
		        	Toast.makeText(Refute.this, "HTTP 200", Toast.LENGTH_LONG).show();
		        	Log.i(TAG, "StatusCode == 200");
		        }else{
		        	Toast.makeText(Refute.this, "HTTP Connection Error!", Toast.LENGTH_LONG).show();
		        	Log.i(TAG, "StatusCode =?: " + statusCode);
		        }
		    } catch (ClientProtocolException e) {
		        e.printStackTrace();
		        Log.i(TAG, "ClientProtocolException");
		    } catch (IOException e) {
		        e.printStackTrace();
		        Log.i(TAG, "IOException");
		    }
		}
	
	public String getRefutes(long refId) {
		String refIdString = String.valueOf(refId);
		StringBuilder builder = new StringBuilder();
	    HttpClient client = new DefaultHttpClient();
	    Log.i(TAG, "Getting commets for question #: " + refIdString);
	    String url = ("http://54.83.12.144/refute_get.php?refute_id=" + refIdString);
	    HttpGet httpGet = new HttpGet(url);
	    try {
	      HttpResponse response = client.execute(httpGet);
	      StatusLine statusLine = response.getStatusLine();
	      int statusCode = statusLine.getStatusCode();
	      if (statusCode == 200) {
	    	  Log.i(TAG,"getRefutes HTTP == 200");  
	        HttpEntity entity = response.getEntity();
	        InputStream content = entity.getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	        String line;
	        while ((line = reader.readLine()) != null) {
	          builder.append(line);
	          Log.i(TAG,"getRefutes Line: " + line);
	        }
	      } else {
	        Log.e(java.text.ParseException.class.toString(), "Failed to download file");
	        Log.i(TAG,"getRefutes ParseException");
	      }
	    } catch (ClientProtocolException e) {
	    	Log.i(TAG,"getRefutes ClientProtocolException");
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	Log.i(TAG,"getRefutes IOException");
	        e.printStackTrace();
	    }
	    return builder.toString();
	  }
	
}
