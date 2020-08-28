package gui.uebung9.editor;

import java.util.ArrayList;

import gui.uebung9.model.Question;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditorView extends VBox {
	
	private EditorPresenter ep;
	private Label label;
	private ListView<String> editorList;
	private Button addQB;
	private Button editQB;
	private Button delQB;
	private HBox buttonBox;
	
	
	public void setPresenter(EditorPresenter ep) {
		this.ep = ep;
	}
	
	public Parent getUI() {
		return this;
	}
	
	public void initView() {
		getChildren().clear();
		label = new Label("Editor");
		editorList = new ListView<String>();
		ep.requestList();
		addQB = new Button("Frage hinzufügen");
		editQB = new Button("Frage editieren");
		delQB = new Button("Frage löschen");
		buttonBox = new HBox();
		buttonBox.getChildren().addAll(addQB, editQB, delQB);
		getChildren().addAll(label, editorList, buttonBox);
		
		addQB.setOnAction(e -> {
			openDialog(100);
			
		});
		editQB.setOnAction(e -> {
			int index = editorList.getSelectionModel().getSelectedIndex();
			String selected = editorList.getSelectionModel().getSelectedItem();
			if(selected == null) {
				showInfo();
			}
			else {
				openDialog(index);
			}
		});
		delQB.setOnAction(e -> {
			int index = editorList.getSelectionModel().getSelectedIndex();
			String selected = editorList.getSelectionModel().getSelectedItem();
			if(selected == null) {
				showInfo();
			}
			else {
				confirmDelete(index);
				
			}
		});
	}
	private void showInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Es muss eine Frage ausgewählt werden!");
		alert.showAndWait();
	}
	private void confirmDelete(int index) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Frage");
		alert.setHeaderText("Soll diese Frage wirklich gelöscht werden?");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			ep.deleteQuestion(index);
			ep.refreshView();
		}
	}
	private void openDialog(int index) {
		if(index == 100) {
			EditDialog dialogNewQ = new EditDialog(ep.getNewQuestion());
			dialogNewQ.show();
		}
		else {
			EditDialog dialog = new EditDialog(ep.getQuestion(index));
			dialog.show();
		}
		
	}	
	public void fillList(String question) {
		editorList.getItems().add(question);
		editorList.refresh();
	}
}

class EditDialog extends Dialog<Question>
{
	private Label question;
	private TextArea area;
	private Button addAnswerB;
	private ToggleGroup tg;
	private int count;
	private VBox root;
	
	
	
	
	public void addAnswer(String answer) {
		RadioButton rb = new RadioButton("");
		tg.getToggles().add(rb);
		rb.setId(""+count);
		count++;
		Button deleteB = new Button("Löschen");
		deleteB.setUserData(count);
		TextField tf = new TextField(answer);
		rb.setUserData(count);
		HBox box = new HBox();
		box.setId("hb" + count);
		box.getChildren().addAll(rb, tf,deleteB);
		root.getChildren().add(box);
		
		deleteB.setOnAction(e -> root.getChildren().remove(((int)(((Button)e.getSource()).getUserData()))-1));			

	}
	
	
	public EditDialog(Question q) {
		setTitle("Dialog");
		question = new Label("Frage:");
		area = new TextArea(q.getQuestion());
		addAnswerB = new Button("Antwort hinzufügen");
		tg = new ToggleGroup();
		
		root = new VBox();
		root.getChildren().addAll(question,area,addAnswerB);
		for(String answer: q.getPossibleAnswers()) {
			addAnswer(answer);
		}
		addAnswerB.setOnAction(e -> addAnswer(""));
		
		getDialogPane().setContent(root);
		getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
		
		Button okB = ((Button)getDialogPane().lookupButton(ButtonType.APPLY));
		okB.setText("Ändern");
		okB.setOnAction(e -> {
			if(tg.getSelectedToggle() != null) {
			int correctIndex = 0;
			int index;
			String[] newAnswers = new String[tg.getToggles().size()-1];
			for(Toggle t: tg.getToggles()) {
				index = (int) (((RadioButton)t).getUserData());
				if(tg.getSelectedToggle() == t) {
					correctIndex = index;
				}
				HBox tempBox = (HBox) root.lookup("#hb" + index);
				TextField tftemp = (TextField)(tempBox.getChildren().get(1));
				System.out.println("index " + index);
				newAnswers[index] = tftemp.getText();
			}
			q.editQuestion(area.getText(), newAnswers, correctIndex);
		}
		});
	}
	
}


