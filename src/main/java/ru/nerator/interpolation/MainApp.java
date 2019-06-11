package ru.nerator.interpolation;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


/**
 * Тут ничего интересного - сначала была задумка сделать графическое
 * приложение, в результате я решил остановиться на консольном
 * @author nerator
 *
 */
public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,400,400);
            scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
		/*
		if (args.length == 0) {
			launch(args);
		} else {
			MainAppConsole.main(args);
		}
		*/
        MainAppConsole.main(args);
    }
}
