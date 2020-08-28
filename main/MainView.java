package gui.uebung9.main;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
	
	private Button start, next, overview, editor;
	private MainPresenter mp;
	private HBox box;

	public void setPresenter(MainPresenter mp) {
		this.mp = mp;
	}

	public Parent getUI() {
		setPrefSize(1000,200);
		box = new HBox();
		start = new Button("Quiz starten!");
		next = new Button("Quiz fortsetzen!");
		overview = new Button("Überblick!");
		editor = new Button("Quiz editieren!");
		box.getChildren().addAll(start,next,overview, editor);
		getChildren().add(box);
		assignButtons();
		return this;
	}
	
	public void assignButtons() {
		start.setOnAction(e -> {
			initView();
			mp.restartQuiz();
		});
		next.setOnAction(e -> {
			initView();
			mp.resumeQuiz();
		});
		overview.setOnAction(e ->{
			initView();
			mp.setOverview();
		});
		editor.setOnAction(e -> {
			initView();
			mp.setEditview();
		});
	}
	
	public void initView() {
		getChildren().clear();
		getChildren().add(box);
	}
	public void setContent(Parent view) {
		getChildren().remove(view);
		getChildren().add(view);
		
	}

}
