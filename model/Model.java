package gui.uebung9.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import gui.uebung9.ModelInitializer;
import javafx.beans.property.SimpleStringProperty;

public class Model {
	private int currentQuestion = 0;
	private ArrayList<Question> questions;
	
	public void removeQuestion(int index) {
		questions.remove(index);
	}

	public void evaluate(int index) {
		if(questions.get(currentQuestion).getIndexOfCorrectAnswer() == index) {
				questions.get(currentQuestion).iterateCorrect();
				questions.get(currentQuestion).iterateTotal();
		}
		else {
			questions.get(currentQuestion).iterateTotal();
		}
	}

	public Model() {
		questions = new ArrayList<Question>();


	}
	public int getCurrentIndex() {
		return currentQuestion;
	}
	public void addQuestion(Question q) {
		questions.add(q);
	}
	public SimpleStringProperty getQuestion(int index) {
		if(index == 0) {
			currentQuestion = 0;
		}
		if(questions.get(index) != null) {
			return questions.get(index).questionProperty();
		}
		else {
			return null;
		}
	}
	public String[] getAnswers(int index) {
		if(questions.get(index) != null) {
			return questions.get(index).getPossibleAnswers();		
		}
		else {
			return null;
		}
	}
	public int getCorrectAnswer(int index) {

		if(questions.get(index) != null) {
			return questions.get(index).getIndexOfCorrectAnswer();		
		}
		else {
			return -1;
		}
	}
	public void setCurrentIndex(int i) {
		currentQuestion = i;
	}
	public boolean isLast() {
		if(questions.size()-1 == currentQuestion) {
			return true;
		}
		else {
			return false;		
		}
	}
	public ArrayList<Question> getQuestions() {
		// TODO Auto-generated method stub
		return questions;
	}
}
