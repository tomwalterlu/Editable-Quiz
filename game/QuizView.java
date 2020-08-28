package gui.uebung9.game;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class QuizView extends VBox {

	private QuizPresenter qp;
	private ToggleGroup tg;
	private Label header;
	private Button next;
	
	
	
	public void setPresenter(QuizPresenter qp) {
		this.qp = qp;
	}
	
	public Parent getUI() {
		return this;
	}
	
	public void startQuiz() {
		qp.loadFirstQuestion();
	}
	
	public void showQuestion(String question, String[] possibleAnswers) {
		getChildren().clear();
		header = new Label(question);
		header.setFont(Font.font(28));
		tg = new ToggleGroup();
		getChildren().add(header);
		for(int i = 0; i <= possibleAnswers.length-1; i++) {
			RadioButton rb = new RadioButton(possibleAnswers[i]);
			rb.setUserData(i);
			tg.getToggles().add(rb);
			getChildren().add(rb);
		}
		next = new Button("=>");
		getChildren().add(next);
		
		next.setOnAction(e -> {
			qp.saveResult((int)tg.getSelectedToggle().getUserData());
			qp.loadNextQuestion();
			
		});
	}
	public void showEnd() {
		header = new Label("Ende vom Quiz");
		header.setFont(Font.font(32));
		getChildren().clear();
		getChildren().add(header);

	}


}
