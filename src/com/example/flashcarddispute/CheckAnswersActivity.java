package com.example.flashcarddispute;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CheckAnswersActivity extends Activity {

	static final String TAG = "FCD";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_answers);
		Button refuteButton = (Button) findViewById(R.id.button_refute);
		
		/*Here we receive the question sent from the ShowQuestions Activity*/
		Intent answerIntent = getIntent();
		
		final Question question = (Question) answerIntent.getSerializableExtra("question");
		int answer = answerIntent.getIntExtra("answer", 0);
		Log.i(TAG, question.getmQuestion());
		Log.i(TAG, String.valueOf(answer));
		updateQuestion(question, answer);
		
		
		refuteButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent refuteIntent = new Intent(CheckAnswersActivity.this, Refute.class);
				refuteIntent.putExtra("question", question);
				startActivity(refuteIntent);
			}
 		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_questions, menu);
		return true;
	}
	
	private void updateQuestion(Question question, int userChoice){
		/*Set the question text to the current question*/
 		TextView questionText = (TextView) findViewById(R.id.question);
 		questionText.setText(question.getmQuestion());
 		
 		/* Get all the answer choices for the current question */
 		String[] answers = question.getmAnswers();
 		 
 		if(question.ismMultipleChoice() || !(question.ismMultipleChoice())){
 			/* This adds ture_false_group.xml to the activity_show_questions.xml's Layout
 			specifically this takes the xml from true_false_group.xml and adds it into
 			the element with id: answer_choices (a LinearLayout) */
 			LinearLayout answerChoices = (LinearLayout) findViewById(R.id.answer_check);  // find the answer_choices element and assign it to a variable
 			View trueFalse = getLayoutInflater().inflate(R.layout.radio_group, null);  // make ture_false_group.xml into a view that Android Java can use 
 			answerChoices.addView(trueFalse);  // add true_false_group.xml inside of the answer_choices element
 			
 			/* Here we add buttons to the element with id: group_answers. group_answers currently
 			 * resides in activity_show_questions.xml because the code above 'inflated' the group_answers
 			 * element into actvity_show_question.xml (in layman terms conetnets of group_answers.xml were 
 			 * copied and pasted into true_false_group.xml */
 			RadioGroup groupTrueFalse = (RadioGroup) findViewById(R.id.group_answers); // locate group_answers inside of activity_show_questions.xml and assign the reference to a variable
 			 			
 			for(int i = 0; i < answers.length; i++){
 				// for every answer in the Question mAnswers string array add a new button which coincides with the text, i.e. an answer choice
	 			RadioButton button;
	 			button = new RadioButton(this);
	 			button.setText(answers[i]);
	 			button.setId(i);   // to prevent the OS from incrementing buttons' ID from 0-n
	 			button.setEnabled(false);  // since the user is only looking whether or not he/she got right answer, we need the buttons to be functional
	 			if(i == (userChoice) && (userChoice) == question.getmCorrectAns()){
	 				button.setBackgroundResource(R.color.green);
	 			}else if(i == userChoice){
	 				Log.i(TAG, "Correct Answer = " + String.valueOf(question.getmCorrectAns()));
	 				Log.i(TAG, "Current Position = " + String.valueOf(i));
	 				button.setBackgroundResource(R.color.red);
	 			}else if(i == question.getmCorrectAns()){
	 				button.setBackgroundResource(R.color.green);
	 			}
	 			
	 			groupTrueFalse.addView(button);
 		     }
 			
 		}
	}

}
