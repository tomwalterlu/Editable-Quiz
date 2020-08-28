package gui.uebung9.overview;

import java.util.ArrayList;

import gui.uebung9.main.MainPresenter;
import gui.uebung9.model.Model;
import gui.uebung9.model.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class OverviewPresenter {

	private OverviewView ov;
	private Model m;
	private MainPresenter mp;
	
	public void setMainPresenter(MainPresenter mp) {
		this.mp = mp;
	}
	
	public void setView(OverviewView ov) {
		this.ov = ov;
	}

	public void setModel(Model m) {
		this.m = m;
	}
	public Parent getUI() {
		return ov.getUI();
	}
	
	public void requestTableData() {
		ArrayList<Question> questions = m.getQuestions();
		ObservableList<Question> data = FXCollections.observableArrayList();
		for(int i = 0; i<= questions.size()-1; i++) {
			data.add(questions.get(i));
		}
		ov.fillTable(data);
	}
	
	public void resetTable() {
		ArrayList<Question> questions = m.getQuestions();
		for(int i = 0; i<= questions.size()-1; i++) {
			questions.get(i).reset();
		}
	}



}
