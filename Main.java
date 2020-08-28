package gui.uebung9;

import java.util.List;

import gui.uebung9.editor.EditorPresenter;
import gui.uebung9.editor.EditorView;
import gui.uebung9.game.QuizPresenter;
import gui.uebung9.game.QuizView;
import gui.uebung9.main.MainPresenter;
import gui.uebung9.main.MainView;
import gui.uebung9.model.Model;
import gui.uebung9.overview.OverviewPresenter;
import gui.uebung9.overview.OverviewView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private List<String> info;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		info = getParameters().getUnnamed();
		Model m = ModelInitializer.initModel(getParameters().getRaw().get(0));
		OverviewPresenter op = new OverviewPresenter();
		MainPresenter mp = new MainPresenter();
		QuizPresenter qp = new QuizPresenter();
		EditorPresenter ep = new EditorPresenter();
		
		OverviewView ov = new OverviewView();
		MainView mv = new MainView();
		QuizView qv = new QuizView();
		EditorView ev = new EditorView();
		
		op.setView(ov);
		mp.setView(mv);
		qp.setView(qv);
		ep.setView(ev);
		
		ov.setPresenter(op);
		mv.setPresenter(mp);
		qv.setPresenter(qp);
		ev.setPresenter(ep);
		mp.setQuizPresenter(qp);
		mp.setOverviewPresenter(op);
		mp.setEditorPresenter(ep);
		qp.setMainPresenter(mp);
		op.setMainPresenter(mp);
		ep.setMainPresenter(mp);
		
		op.setModel(m);
		mp.setModel(m);
		qp.setModel(m);
		ep.setModel(m);
		qv.startQuiz();
		Scene scene = new Scene(mv.getUI());
		mp.resumeQuiz();
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Quiz");
	}

}
