/*
 * Code written/updated: 07-11-2026
 * ChronoQuest - Main JavaFX Application
 *
 * Initializes the primary stage, window title, and loads the Main Menu scene.
 */

package com.chronoquest;

import com.chronoquest.util.ViewNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle(
                "ChronoQuest: The Mystery of the Lost Timeline"
        );

        ViewNavigator.setPrimaryStage(stage);

        ViewNavigator.loadView(
                "/com/chronoquest/MainMenu.fxml"
        );

        // Show first, then maximize
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}