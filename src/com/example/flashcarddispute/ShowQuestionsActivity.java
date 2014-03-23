package com.example.flashcarddispute;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ShowQuestionsActivity extends Activity {
	static final String TAG = "FCD";
	private int currentQuestion = 0; // used to keep track of the current question in the activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_questions);
		 
		final Button nextButton = (Button) findViewById(R.id.button_next);
		final Button previousButton = (Button) findViewById(R.id.button_previous);
		final Button submitAnswerButton = (Button) findViewById(R.id.button_submit_answer);
		/*Here we recieve the card stack sent from the CardStack Activity*/
		
		CardStack currentCardStack = (CardStack) getIntent().getSerializableExtra("cardStack");
 		
		/*We pull out the array from the CardStack object */
		
		final Question[] questionStack = currentCardStack.getmQuestions();
		updateQuestion(questionStack, (currentQuestion));
 	
		/*Next button*/
		nextButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				//Log.i(TAG, String.valueOf(currentQuestion));
				if(currentQuestion == (questionStack.length)-1){
					currentQuestion = 0;
					updateQuestion(questionStack, (currentQuestion));
				}
				else
				{
					updateQuestion(questionStack, (currentQuestion += 1));
				}
			}
 			
 		});
		/*Previous Button*/
		previousButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				//Log.i(TAG, String.valueOf(currentQuestion));
				if(currentQuestion == 0){
					//Log.i(TAG, "set stack to last position");
					currentQuestion = (questionStack.length)-1;
					updateQuestion(questionStack, (currentQuestion));
				}
				else
				{
					//Log.i(TAG, "Keep decrementing stack");
					updateQuestion(questionStack, (currentQuestion -= 1));
				}
			}
 			
 		});
		/*Submit Asnwer Button*/
		submitAnswerButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				RadioGroup groupTrueFalse = (RadioGroup) findViewById(R.id.group_answers); 
				int selectedAnswer = groupTrueFalse.getCheckedRadioButtonId();
				Log.i(TAG, String.valueOf(selectedAnswer));
			}
 			
 		});
			
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_questions, menu);
		return true;
	}
	
	private void updateQuestion(Question[] questionStack, int currentQuestion){
		/*Set the question text to the current question*/
 		TextView questionText = (TextView) findViewById(R.id.question);
 		questionText.setText(questionStack[currentQuestion].getmQuestion());
 		
 		/* Get all the answer choices for the current question */
 		String[] answers = questionStack[currentQuestion].getmAnswers();
 		 
 		if(questionStack[currentQuestion].ismMultipleChoice() || !(questionStack[currentQuestion].ismMultipleChoice())){
 			/* This adds ture_false_group.xml to the activity_show_questions.xml's Layout
 			specifically this takes the xml from true_false_group.xml and adds it into
 			the element with id: answer_choices (a LinearLayout) */
 			LinearLayout answerChoices = (LinearLayout) findViewById(R.id.answer_choices);  // find the answer_choices element and assign it to a variable
 			View trueFalse = getLayoutInflater().inflate(R.layout.radio_group, null);  // make ture_false_group.xml into a view that Android Java can use 
 			answerChoices.addView(trueFalse);  // add true_false_group.xml inside of the answer_choices element
 			
 			/* Here we add buttons to the element with id: group_answers. group_answers currently
 			 * resides in activity_show_questions.xml because the code above 'inflated' the group_answers
 			 * element into actvity_show_question.xml (in layman terms conetnets of group_answers.xml were 
 			 * copied and pasted into true_false_group.xml */
 			RadioGroup groupTrueFalse = (RadioGroup) findViewById(R.id.group_answers); // locate group_answers inside of activity_show_questions.xml and assign the reference to a variable
 			
 			/* Why do we have to removeAllViews? Above question.setText during every method call changes the text 
 			 * which means it overrides the existing text. The radio buttons are being added to a view which means
 			 * there is no overriding, a new radio button gets added to the bottom of the list. So, to remove 
 			 * answers from previous questions we call the removeAllViews() method on the RadioGroup to which we are adding
 			 * answers (Radio Buttons) to from the new question. */
 			groupTrueFalse.removeAllViews();
 			
 			for(int i = 0; i < answers.length; i++){
 				// for every answer in the Question mAnswers string array add a new button which coincides with the text, i.e. an answer choice
	 			RadioButton button;
	 			button = new RadioButton(this);
	 			button.setText(answers[i]);
	 			button.setId(i);
	 			groupTrueFalse.addView(button);
 			}
 			
 		}
	}
}
