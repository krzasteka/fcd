package com.example.flashcarddispute;

public class CardStack {
	private String mTitle;
	private Question[] mQuestions;
	private int length = 0;
	
	public CardStack(Question[] questions, String title) {
		length = questions.length;
		setmQuestions(new Question[length]);
	    setmQuestions(questions);
	}
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public Question[] getmQuestions() {
		return mQuestions;
	}
	public void setmQuestions(Question[] mQuestions) {
		this.mQuestions = mQuestions;
	}
}
