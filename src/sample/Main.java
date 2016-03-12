package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Exchanger;


public class Main extends Application {
    Stage primaryStage;
    static Client client;
    static User user;
    AnchorPane rootLayout;

    public Main() {
        this.user = new User ();

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle ("Tet");
        loginrun ();
    }

    public void loginrun() {
        try {
            FXMLLoader loader = new FXMLLoader ();
            // Parent root = loader.load(getClass().getResource("sample.fxml"));
            loader.setLocation (Main.class.getResource ("sample.fxml"));
            rootLayout = (AnchorPane) loader.load ();
            primaryStage.setScene (new Scene (rootLayout, 200, 443));
            primaryStage.show ();
            Controller controller = loader.getController ();
            controller.setMainApp (this);
            // controller.setPrim(primaryStage);

        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public static void main(String[] args) {

        //new Client().run();
        //DB.proverka ("USERr");
        launch (args);


    }

    public boolean showPersonEditDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation (Main.class.getResource ("registration.fxml"));
            AnchorPane page = (AnchorPane) loader.load ();

            // Create the dialog Stage.
            Stage dialogStage = new Stage ();
            dialogStage.setTitle ("Create User");
            dialogStage.initModality (Modality.WINDOW_MODAL);
            dialogStage.initOwner (primaryStage);
            Scene scene = new Scene (page);
            dialogStage.setScene (scene);

            // Set the person into the controller.
            RegistrationController controller = loader.getController ();
            controller.setDialogStage (dialogStage);
            //controller.setMainApp(this);
            controller.setUser (user);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait ();
            return controller.isOkClicked ();
        } catch (IOException e) {
            e.printStackTrace ();
            return false;
        }
    }

    public void zareg(Boolean t) {
        // Nothing selected.
        try {
            if (t) {
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.initOwner (this.getPrimaryStage ());
                alert.setTitle ("Клиент создан");
                alert.setHeaderText ("Клиент успешно создан");
                alert.setContentText ("Всё хорошо!");
                alert.showAndWait ();
            } else {
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.initOwner (this.getPrimaryStage ());
                alert.setTitle ("Клиент не создан");
                alert.setHeaderText ("К сожалению такой клиент уже есть");
                alert.setContentText ("Придумайте другой ник!");
                alert.showAndWait ();
                this.showPersonEditDialog ();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.initOwner (this.getPrimaryStage ());
            alert.setTitle ("Сервер недоступен");
            alert.setHeaderText ("К сожалению сервер не доступен");
            alert.setContentText ("К сожалению сервер не доступен!");
            alert.showAndWait ();
        }
    }//Информационные формы о том прошла регистрация или нет


public void close(){
    primaryStage.close ();
}
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
