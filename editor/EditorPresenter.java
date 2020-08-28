package gui.uebung9.editor;


import gui.uebung9.main.MainPresenter;
import gui.uebung9.model.Model;
import gui.uebung9.model.Question;
import javafx.scene.Parent;

public class EditorPresenter {
	
	private EditorView ev;
	private MainPresenter mp;
	private Model m;
	
	public void setView(EditorView ev) {
		this.ev = ev;
	}
	public void setModel(Model m) {
		this.m = m;
	}

	public void setMainPresenter(MainPresenter mp) {
		this.mp = mp;
		
	}
	public Question getQuestion(int index) {
		return m.getQuestions().get(index);
	}
	public Parent getUI() {
		ev.initView();
		return ev.getUI();
	}
	
	public void requestList() {
		for(int i = 0; i <= m.getQuestions().size()-1; i++) {
			ev.fillList(m.getQuestions().get(i).getQuestion());
		}
	}
	public void deleteQuestion(int index) {
		m.removeQuestion(index);
	}
	public void refreshView() {
		mp.setEditview();
	}
	
	public Question getNewQuestion() {
		String[] answers = new String[0];
		Question q = new Question("", answers, 0);
		m.addQuestion(q);
		return q;
	}
	

	
	
	

}
