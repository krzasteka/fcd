package com.example.flashcarddispute;

import java.io.Serializable;



/*********************************************************************************************************
     Author: Andrzej Krzastek
     Date: March 16th, 2014

     Description: The Question class will only return values except for the Explanation, the explanation
	              will be able to be adjusted if the Teacher decides the explanation does not suffice.
 
Refute structure - add a unique ID to each Question?
**********************************************************************************************************/
@SuppressWarnings("serial")
public class Question implements Serializable{
	private String mQuestion;
	private String[] mAnswers;
	private boolean mMultipleChoice; // true/false question if mMultipleChoice = false
	//private String mExplanation;
	private int mCorrectAns;
	private int length = 2;
	
	public Question(String question, String[] answers, boolean multipleChoice, int correctAns){
		if(answers.length < 2){                  // 
			return;
		}else if(multipleChoice == false){       // check if answer is true/false
			this.mAnswers = new String[length];  // initialize the length of mAnswers to 2
			mAnswers = answers;
		}else{
			length = answers.length;             // set length to the length of the array being passed in
			this.mAnswers = new String[length];
			mAnswers = answers;
		}

	}

//	public String getmExplanation() {
//		return mExplanation;
//	}
//
//	public void setmExplanation(String mExplanation) {
//		this.mExplanation = mExplanation;
//	}

	public String getmQuestion() {
		return mQuestion;
	}

	public String[] getmAnswers() {
		return mAnswers;
	}

	public boolean ismMultipleChoice() {
		return mMultipleChoice;
	}

	public int getmCorrectAns() {
		return mCorrectAns;
	}
	
	
		
}
