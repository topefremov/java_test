package io.github.topefremov.javatest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class App extends Application {

	private MyString myString;
	private Lock lock;
	private MyStringChanger changer1;
	private MyStringChanger changer2;
	private ExecutorService es;

	private Label currentStringLabel = new Label();
	private Button getStringBtn = new Button("Текущая строка");
	private Button clearStringBtn = new Button("Очистить");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		myString = new MyString();
		lock = new Lock();
		changer1 = new MyStringChanger(lock, 1, 1, 2, 1000, myString);
		changer2 = new MyStringChanger(lock, 2, 2, 1, 1000, myString);
	}

	@Override
	public void start(Stage stage) throws Exception {
		es = Executors.newFixedThreadPool(2);
		es.execute(changer1);
		es.execute(changer2);
		stage.setTitle("Test task");
		FlowPane rootNode = new FlowPane(10, 10);
		rootNode.setAlignment(Pos.CENTER);
		Scene scene = new Scene(rootNode, 300, 200);
		stage.setScene(scene);

		getStringBtn.setOnAction(e -> currentStringLabel.setText(myString.getString()));
		clearStringBtn.setOnAction(e -> {
			myString.clear();
			currentStringLabel.setText(myString.getString());
		});

		rootNode.getChildren().addAll(getStringBtn, clearStringBtn, currentStringLabel);

		stage.show();
	}

	@Override
	public void stop() throws Exception {
		es.shutdownNow();
	}
}
