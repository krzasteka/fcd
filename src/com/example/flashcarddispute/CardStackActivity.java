package com.example.flashcarddispute;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CardStackActivity extends ListActivity{
	
	/* Eventually this will data will be grabbed from a JSON object */
	static final boolean multipleChoice = true; 
	static final boolean hasMultipleAnswers = true;
	Question oneMath = new Question("Question 1, Answer C", (new String[]{"This is A", "This is B", "This is C", "This is D", "This is E"}), multipleChoice, hasMultipleAnswers, 3);
	Question twoMath = new Question("Question 2, Answer B", (new String[]{"This is A", "This is B", "This is C"}), multipleChoice, !hasMultipleAnswers, 2);
	Question threeMath = new Question("Question 3, Answer A", (new String[]{"This is A", "This is B", "This is C","This is D", "This is E","This is F"}), multipleChoice, hasMultipleAnswers, 1);
	Question fourMath = new Question("Question 4, Answer is False", (new String[]{"This is True", "This is False"}), !multipleChoice, !hasMultipleAnswers, 2);
	Question[] mathCards = new Question[]{oneMath, twoMath, threeMath, fourMath};
	CardStack mathStack = new CardStack(mathCards, "Math");
	
	Question oneBio = new Question("Question 1, Answer C", (new String[]{"This is A", "This is B", "This is C", "This is D"}), multipleChoice, !hasMultipleAnswers, 3);
	Question twoBio = new Question("Question 2, Answer B", (new String[]{"This is A", "This is B", "This is C", "This is D", "This is E"}), multipleChoice, hasMultipleAnswers, 2);
	Question threeBio = new Question("Question 3, Answer A", (new String[]{"This is A", "This is B", "This is C"}), multipleChoice, !hasMultipleAnswers, 1);
	Question fourBio = new Question("Question 4, Answer is False", (new String[]{"This is True", "This is False"}), !multipleChoice, !hasMultipleAnswers, 2);
	Question[] bioCards = new Question[]{oneBio, twoBio, threeBio, fourBio};
	CardStack bioStack = new CardStack(bioCards, "Biology");
	
	CardStack[] currentStack = new CardStack[]{mathStack, bioStack};
	static final String TAG = "FCD";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_stack);
		String foo = oneBio.getmQuestion();
		if(foo != null){
			//Log.i(TAG, foo);
		}
		/* Add the header for the cardStackActivity */
		View header = getLayoutInflater().inflate(R.layout.activity_card_stack_header, null);
		ListView listView = getListView();
		listView.addHeaderView(header);
		
		/* This will put all the titles in the cardStackTitles list, this way this is done dynamically and
		 * we don't have to worry about putting each title in the title ListView*/	
		List<String> cardStackTitles = new ArrayList<String>();
		for(int i = 0; i < currentStack.length; i++){
			cardStackTitles.add(currentStack[i].getmTitle());
		}
		/* add all the CardStack Titles to the adapter, from the cardStackTitles List, and set the adapter on the listView 
		 * This will display all the titles in the in the list view, which allows the user to select a card stack */
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(CardStackActivity.this, android.R.layout.simple_list_item_1, cardStackTitles);
		listView.setAdapter(adapter);
			
	}
	/* Override the onListItemClick. We are going to start the Question activity which will display all the questions within the Card Stack
	 * it will pass the stack the user selected as an intent extra, and then it will be serialized in the receving activity (Answer Activity) */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(CardStackActivity.this, ShowQuestionsActivity.class);
		//Toast.makeText(CardStackActivity.this, "position: " + position + " | id: "+ id, Toast.LENGTH_LONG).show(); //Debugging purposes
		Bundle cardBundle = new Bundle();
		cardBundle.putSerializable("cardStack", currentStack[(int) id]); // we need to serialize the cardStack object to be able to send it as an Extra with the intent 
		i.putExtras(cardBundle); 
		startActivity(i);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.card_stack, menu);
		return true;
	}

}
