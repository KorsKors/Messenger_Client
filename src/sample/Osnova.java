package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Priem.Priem;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Хускар on 15.02.2016.
 */
public class Osnova {
    ArrayList ar = new ArrayList ();//отправляем на сервер
    ArrayList<String> bb;//получаем от сервера при формировании списка контактов
    private volatile ObservableList<User> uu = FXCollections.observableArrayList ();//конвертируем полученно от сервера в User
    static ArrayList input = new ArrayList ();
    static String myname;
    Stage primaryStage;
    AnchorPane rootLayout;
    Client client;

    public Osnova() {
    }

    public Osnova(String login) {
        //bb=DB.output (login);
        //for (int i = 0;i<bb.size ();i++){
        //uu.add (new User(bb.get(i)));
        client = new Client (ar);
        client.runs (2021);
        for (int i = 0; i < input.size (); i++) {
            System.out.println ("Получили Основа:" + input.get (i));
            uu.add (new User ((String) input.get (i)));
        }
    }

    public ObservableList<User> getUser() {
        return uu;
    }

    public void Osnova() {
        //Opros opros= new Opros();
       // Thread thread = new Thread (opros);
       // thread.start ();
        // if (!DB.proverka (myname)){
        //    DB.createTableUser (myname);
        //   client = new Client(ar)  ;
        //   client.runs (2021);
        //   for(int i = 0;i<input.size();i++){
        //       System.out.println ("Получили Основа:"+input.get(i));
        //   }
        //   DB.addUser (input,myname);
        //  System.out.print ("Загружено!");

        //}
        //получили массив ar который смело можно отправлять на форму


        //System.out.println(ar.size());
        try {
            //primaryStage.close ();
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation (Main.class.getResource ("osnova.fxml"));
            AnchorPane page = (AnchorPane) loader.load ();

            // Create the dialog Stage.
            Stage dialogStage = new Stage ();
            dialogStage.setTitle ("TeT");
            dialogStage.initModality (Modality.WINDOW_MODAL);
            dialogStage.initOwner (primaryStage);
            Scene scene = new Scene (page);
            dialogStage.setScene (scene);

            // Set the person into the controller.
            OsnovaController controller1 = loader.getController ();
            controller1.setDialogStage (dialogStage);
            controller1.setMainApp (new Osnova (myname), uu);

            //controller1.setMainApp(this);


            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait ();
        } catch (IOException e) {
            e.printStackTrace ();

        }
    } //Основная форма мессенджера!

    public void addUser(TableView<User> tb) {
        try {
            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation (Main.class.getResource ("adduser.fxml"));
            AnchorPane page = (AnchorPane) loader.load ();

            // Create the dialog Stage.
            Stage dialogStage = new Stage ();
            dialogStage.setTitle ("Add User");
            dialogStage.initModality (Modality.WINDOW_MODAL);
            dialogStage.initOwner (primaryStage);
            Scene scene = new Scene (page);
            dialogStage.setScene (scene);

            // Set the person into the controller.
            ControllerAddUser controller1 = loader.getController ();
            controller1.setBro (uu, tb);
            //controller1.setMainApp (this);
            controller1.setDialogStage (dialogStage);

            //controller1.setMainApp(this);


            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait ();
        } catch (IOException e) {
            e.printStackTrace ();

        }
    }

    public void zareg(Boolean t) {
        // Nothing selected.
        try {
            if (t) {
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.initOwner (this.getPrimaryStage ());
                alert.setTitle ("Клиент lдобавлен");
                alert.setHeaderText ("Клиент успешно добавлен в ваш список кнтактов");
                alert.setContentText ("Всё хорошо!");
                alert.showAndWait ();


            } else {
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.initOwner (this.getPrimaryStage ());
                alert.setTitle ("Клиент с таким никнеймом не найден");
                alert.setHeaderText ("К сожалению такой клиент не найден, или уже находится в вашем списке контактов");
                alert.setContentText ("Не найден!");
                alert.showAndWait ();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.initOwner (this.getPrimaryStage ());
            alert.setTitle ("Сервер недоступен");
            alert.setHeaderText ("К сожалению сервер не доступен");
            alert.setContentText ("К сожалению сервер не доступен!");
            alert.showAndWait ();
        }
    }//Информационные формы о том найден пользователь или нет

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void close() {
        this.primaryStage.close ();
    }
}
