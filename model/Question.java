package gui.uebung9.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Question
{
	 private SimpleStringProperty question;
	 private SimpleIntegerProperty totalAnswers;
	 private SimpleIntegerProperty correctAnswers;
	 private String[] possibleAnswers;
	 private int indexOfCorrectAnswer;
	 


	 public void editQuestion(String newtitle, String[] newAnswers, int correctIndex) {
		 question = new SimpleStringProperty(newtitle);
		 possibleAnswers = newAnswers;
		 indexOfCorrectAnswer = correctIndex;
		 reset();
	 }
	 public void reset() {
		 totalAnswers = new SimpleIntegerProperty(0);
		 correctAnswers = new SimpleIntegerProperty(0);
	 }
 // weitere Attribute nach Bedarf
 public Question(String question, String[] possibleAnswers, int indexOfCorrectAnswer) throws IllegalArgumentException
 {
	 this.question = new SimpleStringProperty(question);
	 this.totalAnswers = new SimpleIntegerProperty(0);
	 this.correctAnswers = new SimpleIntegerProperty(0);
	 this.possibleAnswers = possibleAnswers;
	 this.indexOfCorrectAnswer = indexOfCorrectAnswer;
 }
 public SimpleStringProperty questionProperty()
 {
	 return question;
 }
 public SimpleIntegerProperty totalAnswersProperty()
 {
	 return totalAnswers;
 }
 public SimpleIntegerProperty correctAnswersProperty()
 {
	 return correctAnswers;
 }
 
 public String getQuestion() {
	 return question.get();
 }
 public int getTotalAnswers() {
	 return totalAnswers.get();
 }
 public int getCorrectAnswers() {
	 return correctAnswers.get();
 }
 
 public void iterateCorrect() {
	 correctAnswers.set(correctAnswers.get()+1);

 }
 public void iterateTotal() {
	 totalAnswers.set(totalAnswers.get()+1);

 }
 public SimpleIntegerProperty getTotal() {
	 return totalAnswers;
 }
 public SimpleIntegerProperty getCorrect() {
	 return correctAnswers;
 }
 public String[] getPossibleAnswers()
 {
	 return possibleAnswers;
 }
 	public int getIndexOfCorrectAnswer()
 {
 	return indexOfCorrectAnswer;
 }
 // weitere Methoden nach Bedarf
} 
