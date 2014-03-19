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
		//need to get data back out from serilized extras.... is the question[] serialized as well??
//		Question[] stackQuestions = new Question[currentCardStack.length()];
//		for(int i = 0; i < currentCardStack.length(); i++ ){
//			stackQuestions[i] = (Question) currentCardStack.getQuestionAt(i);
//		}
//		
//		Log.i(TAG, stackQuestions[0].getmQuestion());
		Question[] stackQuestions = currentCardStack.getmQuestions();
		int i = stackQuestions[1].getmCorrectAns();
		
		Log.i(TAG, String.valueOf(i));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_questions, menu);
		return true;
	}

}
