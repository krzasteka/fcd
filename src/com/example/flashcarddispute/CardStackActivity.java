package com.example.flashcarddispute;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CardStackActivity extends ListActivity{
	static final boolean multipleChoice = true; 
	Question oneMath = new Question("Question 1, Answer C", (new String[]{"This is A", "This is B", "This is C"}), multipleChoice, 3);
	Question twoMath = new Question("Question 2, Answer B", (new String[]{"This is A", "This is B", "This is C"}), multipleChoice, 2);
	Question threeMath = new Question("Question 3, Answer A", (new String[]{"This is A", "This is B", "This is C"}), multipleChoice, 1);
	Question fourMath = new Question("Question 4, Answer is False", (new String[]{"This is True", "This is False"}), multipleChoice, 2);
	Question[] mathCards = new Question[]{oneMath, twoMath, threeMath, fourMath};
	CardStack mathStack = new CardStack(mathCards, "Math");
	
	Question oneBio = new Question("Question 1, Answer C", (new String[]{"This is A", "This is B", "This is C"}), multipleChoice, 3);
	Question twoBio = new Question("Question 2, Answer B", (new String[]{"This is A", "This is B", "This is C"}), multipleChoice, 2);
	Question threeBio = new Question("Question 3, Answer A", (new String[]{"This is A", "This is B", "This is C"}), multipleChoice, 1);
	Question fourBio = new Question("Question 4, Answer is False", (new String[]{"This is True", "This is False"}), multipleChoice, 2);
	Question[] bioCards = new Question[]{oneBio, twoBio, threeBio, fourBio};
	CardStack bioStack = new CardStack(bioCards, "Biology");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_stack);
		
		View header = getLayoutInflater().inflate(R.layout.activity_card_stack_header, null);
		View footer = getLayoutInflater().inflate(R.layout.activity_card_stack_footer, null);
		
		ListView listView = getListView();
		listView.addHeaderView(header);
		listView.addFooterView(footer);
		String[] myStringArray = new String[]{bioStack.getmTitle(), mathStack.getmTitle()};
		ArrayAdapter adapter = new ArrayAdapter<String>(CardStackActivity.this, R.layout.activity_card_stack_list_item, myStringArray);
		listView.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.card_stack, menu);
		return true;
	}

}
