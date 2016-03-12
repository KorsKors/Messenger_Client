package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.text.TableView;

public class Controller implements MyCallBack {
    @FXML
    private Label rez;
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private void initialize() {

    }
    private Main mainApp;

    @FXML
    private void toEnter() {
        User user1 = mainApp.user;

        user1.setID(null);
        user1.setIP(null);
        if(isInputValid ()){
            user1.setLogin(login.getText());
            user1.setPassword(password.getText());
            Client client=new Client(user1,mainApp,this);
            if(client.runs(202)){
                Client.mylogin=user1.getLogin ();
                Osnova.myname=user1.getLogin ();
            mainApp.close();

                new Osnova ().Osnova ();


            //mainApp.Osnova ();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialog);
                alert.setTitle("Неверные данные");
                alert.setHeaderText("Введите корректные данные");
                alert.setContentText("Имя пользователя и пароль неверные введите данные и повторите попытку!");

                alert.showAndWait();
            }
        }
    }
    @FXML
    private void handleNewPerson() throws Exception {
        User user = mainApp.user;
        user.setID(null);
        user.setIP(null);
        boolean okClicked = mainApp.showPersonEditDialog();
        if (okClicked) {
            Client client=new Client(user,mainApp,this);
           mainApp.zareg (client.runs (101));
        }

    }
    public void setMainApp(Main main) {
        this.mainApp = main;
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (login.getText() == null || login.getText().length() == 0) {
            errorMessage += "Неверный логин!\n";
        }
        if (password.getText() == null || password.getText().length() == 0) {
            errorMessage += "Неверно введен пароль!\n";
        }

        String s1=login.getText();
        String s2=password.getText();

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialog);
            alert.setTitle("Неверные данные");
            alert.setHeaderText("Введите корректные данные");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }

    }
    Stage dialog;
    public void setDialogStage(Stage dialogStage) {
        this.dialog = dialogStage;

    }

    @Override
    public void onGetResult(String str) {

    }

}


