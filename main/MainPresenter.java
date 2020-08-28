package gui.uebung9.main;

import gui.uebung9.editor.EditorPresenter;
import gui.uebung9.game.QuizPresenter;
import gui.uebung9.model.Model;
import gui.uebung9.overview.OverviewPresenter;
import javafx.scene.Node;
import javafx.scene.Parent;

public class MainPresenter {
	
	private QuizPresenter qp;
	private OverviewPresenter op;
	private EditorPresenter ep;
	private MainView mv;
	private Model m;
	
	public void setQuizPresenter(QuizPresenter qp) {
		this.qp = qp;
	}
	
	public void setOverviewPresenter(OverviewPresenter op) {
		this.op = op;
	}
	public void setEditorPresenter(EditorPresenter ep) {
		this.ep = ep;
	}
	public void setView(MainView mv) {
		this.mv = mv;
	}

	public void setModel(Model m) {
		this.m = m;
	}

	public void restartQuiz() {
		qp.loadFirstQuestion();
		resumeQuiz();
	}

	public void setOverview() {
		mv.setContent(op.getUI());
	}

	public void resumeQuiz() {
		mv.setContent(qp.getUI());
	}

	public void setEditview() {
		mv.setContent(ep.getUI());
	}
}
