package com.example.flashcarddispute;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

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
	private boolean mHasMultipleAnswers; //can the user choose more than one right answer?
	private int mCorrectAns;
	private int length = 2;
	/**** CREATE UNIQUE ID FOR EVERY QUESTION *******/
    static final AtomicLong NEXT_ID = new AtomicLong(0);
    final long id = NEXT_ID.getAndIncrement();
    
	public Question(String question, String[] answers, boolean multipleChoice, boolean hasMultipleAnswers, int correctAns){
		this.mQuestion = question;
		this.mMultipleChoice = multipleChoice;
		this.mCorrectAns = correctAns;
		this.mHasMultipleAnswers = hasMultipleAnswers;
		if(answers.length < 2){                  
			return;								 // need to throw an error
		}else if(multipleChoice == false){       // check if answer is true/false
			this.mAnswers = new String[length];  // initialize the length of mAnswers to 2
			mAnswers = answers;
			this.mHasMultipleAnswers = false;    // if it's a true false it can't have more than one right answer 
		}else{
			length = answers.length;             // set length to the length of the array being passed in
			this.mAnswers = new String[length];
			mAnswers = answers;
		}
	}

	public long getId() {
	    return id;
	}
	
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

	public boolean hasMultipleAnswers() {
		return mHasMultipleAnswers;
	}
		
}
