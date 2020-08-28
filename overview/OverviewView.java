package gui.uebung9.overview;

import java.util.ArrayList;
import java.util.List;

import gui.uebung9.main.MainPresenter;
import gui.uebung9.model.Question;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class OverviewView extends VBox{

	private OverviewPresenter op;
	private MainPresenter mp;
	
	private TableView<Question> tv;
	private Button results;
	private Label overview;
	private Button deleteButton;
	public void setPresenter(OverviewPresenter op) {
		this.op = op;
	
	}
	public void setMainPresenter(MainPresenter mp) {
		this.mp = mp;
	}
	
	public Parent getUI() {
		initView();
		op.requestTableData();
		return this;
	}
	

	public void initView() {
		getChildren().clear();
		overview = new Label("Übersicht");
		tv = new TableView<>();
		TableColumn<Question, String> question =new TableColumn<Question, String>("question");
		question.setCellValueFactory(new PropertyValueFactory<Question,String>("question"));
		tv.getColumns().add(question);

		TableColumn<Question, Integer> totalAnswers =new TableColumn<Question, Integer>("totalAnswers");
		totalAnswers.setCellValueFactory(new PropertyValueFactory<Question,Integer>("totalAnswers"));
		tv.getColumns().add(totalAnswers);

		TableColumn<Question, Integer> correctAnswers =new TableColumn<Question, Integer>("correctAnswers");
		correctAnswers.setCellValueFactory(new PropertyValueFactory<Question,Integer>("correctAnswers"));
		tv.getColumns().add(correctAnswers);


		deleteButton = new Button("Ergebnisse löschen");
		getChildren().addAll(overview, tv, deleteButton);	
		
		deleteButton.setOnAction(e-> {
			op.resetTable();
		});
	}
	public void fillTable(ObservableList<Question> data) {
		tv.setItems(data);
		System.out.println(data.get(0).getTotalAnswers());
		tv.refresh();
	}

}
