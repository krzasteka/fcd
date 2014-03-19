package com.example.flashcarddispute;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class ShowQuestionsActivity extends Activity {
	static final String TAG = "FCD";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_questions);

		CardStack currentCardStack = (CardStack) getIntent().getSerializableExtra("cardStack");
		TextView tv = (TextView) findViewById(R.id.question);

		tv.setText(currentCardStack.getmTitle());
		
		Question[] stackQuestions = currentCardStack.getmQuestions().clone();
		Log.i(TAG, stackQuestions[0].getmQuestion());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_questions, menu);
		return true;
	}

}
