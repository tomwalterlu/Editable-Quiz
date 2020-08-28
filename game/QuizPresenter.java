package gui.uebung9.game;

import gui.uebung9.main.MainPresenter;
import gui.uebung9.model.Model;
import gui.uebung9.model.Question;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class QuizPresenter {
	
	private QuizView qv;
	private Model m;
	private MainPresenter mp;

	public void setView(QuizView qv) {
		this.qv = qv;
	}

	public void setModel(Model m) {
		this.m = m;
	}
	public void setMainPresenter(MainPresenter mp) {
		this.mp = mp;
	}

	public Parent getUI() {
		return qv.getUI();
	}

	public void loadFirstQuestion() {
		qv.showQuestion(m.getQuestion(0).get(), m.getAnswers(0));
	}
	public void loadNextQuestion() {
		if(m.isLast()) {
			qv.showEnd();
		}
		else {
			qv.showQuestion(m.getQuestion(m.getCurrentIndex()+1).get(), m.getAnswers(m.getCurrentIndex()+1));
			m.setCurrentIndex(m.getCurrentIndex()+1);
		}
	}

	public void saveResult(int answerIndex) {
		m.evaluate(answerIndex);
	}
	

	

}
