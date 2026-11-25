/*
 * Code written/updated: 08-11-2026
 * ChronoQuest - View Navigator Utility
 *
 * Manages centralized FXML scene loading and stage transitions across screens.
 */

package com.chronoquest.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class ViewNavigator {

    private static Stage primaryStage;

    private ViewNavigator() {
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static Object loadView(String fxmlPath) {

        try {

            System.out.println(">>> Loading FXML: " + fxmlPath);

            URL resource =
                    ViewNavigator.class.getResource(fxmlPath);

            if (resource == null) {

                System.err.println(
                        ">>> FXML NOT FOUND: " + fxmlPath
                );

                return null;
            }

            System.out.println(
                    ">>> FXML FOUND: " + resource
            );

            FXMLLoader loader =
                    new FXMLLoader(resource);

            Parent root = loader.load();

            System.out.println(
                    ">>> FXML LOADED SUCCESSFULLY"
            );

            if (primaryStage.getScene() == null) {

                Scene scene = new Scene(root);

                primaryStage.setScene(scene);

            } else {

                primaryStage.getScene().setRoot(root);
            }

            primaryStage.show();

            System.out.println(
                    ">>> VIEW DISPLAYED"
            );

            return loader.getController();

        } catch (Exception e) {

            System.err.println(
                    ">>> ERROR LOADING FXML: " + fxmlPath
            );

            e.printStackTrace();

            return null;
        }
    }
}